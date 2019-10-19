package epi.Chapter14;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TwoSortedArraysMerge {

    public static void mergeTwoSortedArrays(List<Integer> a, int m, List<Integer> b, int n) {
        int left = m - 1;
        int right = n - 1;
        int end = m + n - 1;
        while (left >= 0 && right >= 0) {
            a.set(end--, a.get(left) > b.get(right) ? a.get(left--) : b.get(right--));
        }
        while (right >= 0) {
            a.set(end--, b.get(right--));
        }
    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    public static List<Integer> mergeTwoSortedArraysWrapper(List<Integer> a, int m, List<Integer> b, int n) {
        mergeTwoSortedArrays(a, m, b, n);
        return a;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TwoSortedArraysMerge() {}
}
