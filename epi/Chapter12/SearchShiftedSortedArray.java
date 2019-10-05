package epi.Chapter12;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchShiftedSortedArray {

    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
    public static int searchSmallest(List<Integer> integers) {
        int low = 0, high = integers.size() - 1;
        while (low < high) {
            final int mid = (low + high) >>> 1;
            if (integers.get(mid) > integers.get(high)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchShiftedSortedArray() {}
}
