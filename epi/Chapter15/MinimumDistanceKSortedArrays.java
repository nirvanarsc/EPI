package epi.Chapter15;

import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MinimumDistanceKSortedArrays {

    public static class ArrayData implements Comparable<ArrayData> {
        public int val;
        public int idx;

        public ArrayData(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);
            if (result == 0) {
                result = Integer.compare(idx, o.idx);
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ArrayData)) { return false; }
            return compareTo((ArrayData) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, idx);
        }

        @Override
        public String toString() {
            return val + " " + idx;
        }
    }

    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        final NavigableSet<ArrayData> integers = new TreeSet<>();
        final int[] indexes = new int[sortedArrays.size()];
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < sortedArrays.size(); i++) {
            integers.add(new ArrayData(sortedArrays.get(i).get(0), i));
        }

        while (true) {
            res = Math.min(res, integers.last().val - integers.first().val);
            final ArrayData curr = integers.pollFirst();
            assert curr != null;
            if (indexes[curr.idx] == sortedArrays.get(curr.idx).size() - 1) {
                return res;
            }
            integers.add(new ArrayData(sortedArrays.get(curr.idx).get(++indexes[curr.idx]), curr.idx));
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumDistanceKSortedArrays() {}
}
