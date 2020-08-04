package epi.Chapter14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings({ "TailRecursion", "MethodParameterNamingConvention" })
public final class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArraysBS(List<Integer> A, List<Integer> B) {
        if (A.size() > B.size()) {
            return intersectTwoSortedArraysBS(B, A);
        }
        final List<Integer> res = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            if (i > 0 && A.get(i - 1).equals(A.get(i))) { continue; }
            if (Collections.binarySearch(B, A.get(i)) >= 0) {
                res.add(A.get(i));
            }
        }
        return res;
    }

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> A, List<Integer> B) {
        final List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (i > 0 && A.get(i - 1).equals(A.get(i))) {
                i++;
            } else if (A.get(i).equals(B.get(j))) {
                res.add(A.get(i));
                i++;
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntersectSortedArrays() {}
}
