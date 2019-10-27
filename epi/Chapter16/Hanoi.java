package epi.Chapter16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestFailure.PropertyName;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class Hanoi {

    public static List<List<Integer>> computeTowerHanoi(int numRings) {
        final List<Deque<Integer>> pegs = generatePegs(0, numRings, 3);
        final List<List<Integer>> result = new ArrayList<>();
        hanoi(pegs, result, 0, 1, 2, numRings);
        return result;
    }

    private static void hanoi(List<Deque<Integer>> pegs, List<List<Integer>> res,
                              int from, int buffer, int to, int n) {
        if (n == 0) {
            return;
        }
        hanoi(pegs, res, from, to, buffer, n - 1);
        pegs.get(to).addFirst(pegs.get(from).removeFirst());
        res.add(Arrays.asList(from, to));
        hanoi(pegs, res, buffer, from, to, n - 1);
    }

    private static List<Deque<Integer>> generatePegs(int idx, int numRings, int numPegs) {
        final List<Deque<Integer>> pegs = IntStream.range(0, numPegs)
                                                   .mapToObj(i -> new LinkedList<Integer>())
                                                   .collect(Collectors.toList());
        for (int i = numRings; i >= 1; i--) {
            pegs.get(idx).addFirst(i);
        }
        return pegs;
    }

    @EpiTest(testDataFile = "hanoi.tsv")
    public static void computeTowerHanoiWrapper(TimedExecutor executor, int numRings) throws Exception {
        final List<Deque<Integer>> pegs = generatePegs(0, numRings, 3);
        final List<List<Integer>> result = executor.run(() -> computeTowerHanoi(numRings));

        for (List<Integer> operation : result) {
            final int fromPeg = operation.get(0);
            final int toPeg = operation.get(1);
            if (!pegs.get(toPeg).isEmpty() && pegs.get(fromPeg).getFirst() >= pegs.get(toPeg).getFirst()) {
                throw new TestFailure("Illegal move")
                        .withProperty(PropertyName.EXPLANATION, pegs.get(fromPeg).getFirst())
                        .withProperty(PropertyName.EXPLANATION, pegs.get(toPeg).getFirst());
            }
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
        }

        if (!pegs.equals(generatePegs(2, numRings, 3))) {
            throw new TestFailure("Pegs are not placed in the right configuration");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Hanoi() {}
}
