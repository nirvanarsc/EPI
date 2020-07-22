package epi.Chapter11;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SortIncreasingDecreasingArray {

    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> list) {
        final List<List<Integer>> sortedArrays = new ArrayList<>();
        int prev = 0;
        boolean increasing = true;
        for (int i = 1; i <= list.size(); i++) {
            if (i == list.size()
                || (list.get(i - 1) < list.get(i) && !increasing)
                || (list.get(i - 1) >= list.get(i) && increasing)) {
                final List<Integer> subList = list.subList(prev, i);
                if (!increasing) {
                    Collections.reverse(subList);
                }
                sortedArrays.add(subList);
                increasing = !increasing;
                prev = i;
            }
        }
        return SortedArraysMerge.mergeSortedArrays(sortedArrays);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortIncreasingDecreasingArray() {}
}
