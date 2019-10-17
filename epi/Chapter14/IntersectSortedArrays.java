package epi.Chapter14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> a, List<Integer> b) {
        final List<Integer> intersection = new ArrayList<>();
        final List<Integer> shorter = a.size() > b.size() ? b : a;
        final List<Integer> longer = shorter == a ? b : a;
        for (int i = 0; i < shorter.size(); i++) {
            final Integer curr = shorter.get(i);
            if ((i == 0 || !curr.equals(shorter.get(i - 1))) && Collections.binarySearch(longer, curr) > -1) {
                intersection.add(curr);
            }
        }
        return intersection;
    }

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays2(List<Integer> a, List<Integer> b) {
        final List<Integer> intersection = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if ((i == 0 || !a.get(i).equals(a.get(i - 1))) && a.get(i).equals(b.get(j))) {
                intersection.add(a.get(i));
                ++i;
                ++j;
            } else if (a.get(i) < b.get(j)) {
                ++i;
            } else {
                ++j;
            }
        }
        return intersection;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntersectSortedArrays() {}
}
