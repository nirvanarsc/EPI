package epi.Chapter16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class Hanoi {

    private static final int NUM_PEGS = 3;

    public static List<List<Integer>> computeTowerHanoi(int numRings) {
        final List<List<Integer>> res = new ArrayList<>();
        dfs(numRings, 0, 1, 2, res);
        return res;
    }

    private static void dfs(int n, int from, int buffer, int to, List<List<Integer>> res) {
        if (n == 0) {
            return;
        }
        dfs(n - 1, from, to, buffer, res);
        res.add(Arrays.asList(from, to));
        dfs(n - 1, buffer, from, to, res);
    }

    @EpiTest(testDataFile = "hanoi.tsv")
    public static void computeTowerHanoiWrapper(TimedExecutor executor, int numRings) throws Exception {
        final List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            pegs.add(new LinkedList<>());
        }
        for (int i = numRings; i >= 1; --i) {
            pegs.get(0).addFirst(i);
        }

        final List<List<Integer>> result =
                executor.run(() -> computeTowerHanoi(numRings));

        for (List<Integer> operation : result) {
            final int fromPeg = operation.get(0);
            final int toPeg = operation.get(1);
            if (!pegs.get(toPeg).isEmpty() &&
                pegs.get(fromPeg).getFirst() >= pegs.get(toPeg).getFirst()) {
                throw new TestFailure("Illegal move from " + pegs.get(fromPeg).getFirst() +
                                      " to " + pegs.get(toPeg).getFirst());
            }
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
        }

        final List<Deque<Integer>> expectedPegs1 = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            expectedPegs1.add(new LinkedList<>());
        }
        for (int i = numRings; i >= 1; --i) {
            expectedPegs1.get(1).addFirst(i);
        }

        final List<Deque<Integer>> expectedPegs2 = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            expectedPegs2.add(new LinkedList<>());
        }
        for (int i = numRings; i >= 1; --i) {
            expectedPegs2.get(2).addFirst(i);
        }
        if (!pegs.equals(expectedPegs1) && !pegs.equals(expectedPegs2)) {
            throw new TestFailure("Pegs doesn't place in the right configuration");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Hanoi() {}
}
