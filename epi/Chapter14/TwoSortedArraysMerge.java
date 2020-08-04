package epi.Chapter14;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings("MethodParameterNamingConvention")
public final class TwoSortedArraysMerge {

    public static void mergeTwoSortedArrays(List<Integer> A, int m, List<Integer> B, int n) {
        int i = m - 1;
        int j = n - 1;
        for (int idx = A.size() - 1; idx >= 0; idx--) {
            if (i < 0) {
                A.set(idx, B.get(j--));
            } else if (j < 0) {
                A.set(idx, A.get(i--));
            } else if (A.get(i) < B.get(j)) {
                A.set(idx, B.get(j--));
            } else {
                A.set(idx, A.get(i--));
            }
        }
    }

    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    public static List<Integer> mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
        mergeTwoSortedArrays(A, m, B, n);
        return A;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TwoSortedArraysMerge() {}
}
