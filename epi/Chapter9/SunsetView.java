package epi.Chapter9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SunsetView {

    static class Pair {
        int idx;
        int val;

        Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        final Deque<Pair> stack = new ArrayDeque<>();
        for (int i = 0; sequence.hasNext(); i++) {
            final int curr = sequence.next();
            while (!stack.isEmpty() && stack.getFirst().val <= curr) {
                stack.removeFirst();
            }
            stack.addFirst(new Pair(i, curr));
        }
        final List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.removeFirst().idx);
        }
        return res;
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SunsetView() {}
}
