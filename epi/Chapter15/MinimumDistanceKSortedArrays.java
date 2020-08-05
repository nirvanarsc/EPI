package epi.Chapter15;

import java.util.List;
import java.util.TreeSet;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MinimumDistanceKSortedArrays {

    private static class Pair {
        int val;
        int idx;
        int listIdx;

        Pair(int val, int idx, int listIdx) {
            this.val = val;
            this.idx = idx;
            this.listIdx = listIdx;
        }
    }

    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        final TreeSet<Pair> ts = new TreeSet<>((a, b) -> a.val == b.val ? Integer.compare(a.listIdx, b.listIdx)
                                                                        : Integer.compare(a.val, b.val));
        final int k = sortedArrays.size();
        for (int i = 0; i < k; i++) {
            ts.add(new Pair(sortedArrays.get(i).get(0), 0, i));
        }
        int res = Integer.MAX_VALUE;
        while (ts.size() == k) {
            res = Math.min(res, Math.abs(ts.last().val - ts.first().val));
            final Pair pair = ts.pollFirst();
            if (pair != null && pair.idx + 1 < sortedArrays.get(pair.listIdx).size()) {
                ts.add(new Pair(sortedArrays.get(pair.listIdx).get(pair.idx + 1), pair.idx + 1, pair.listIdx));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumDistanceKSortedArrays() {}
}
