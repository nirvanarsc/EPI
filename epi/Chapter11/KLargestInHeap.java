package epi.Chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class KLargestInHeap {

    static class Pair {
        int idx;
        int val;

        Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    public static List<Integer> kLargestInBinaryHeap(List<Integer> integers, int k) {
        final PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Integer.compare(y.val, x.val));
        final List<Integer> res = new ArrayList<>();
        pq.add(new Pair(0, integers.get(0)));
        for (int i = 0; i < k; i++) {
            final Pair curr = pq.remove();
            res.add(curr.val);
            final int left = 2 * curr.idx + 1;
            final int right = 2 * curr.idx + 2;
            if (left < integers.size()) { pq.add(new Pair(left, integers.get(left))); }
            if (right < integers.size()) { pq.add(new Pair(right, integers.get(right))); }
        }
        return res;
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    };

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KLargestInHeap() {}
}
