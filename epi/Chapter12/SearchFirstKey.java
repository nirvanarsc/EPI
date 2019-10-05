package epi.Chapter12;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchFirstKey {

    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> integers, int k) {
        int low = 0, high = integers.size() - 1, ans = -1;
        while (low <= high) {
            final int mid = (low + high) >>> 1;
            if (integers.get(mid) < k) {
                low = mid + 1;
            } else if (integers.get(mid) == k) {
                high = mid - 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchFirstKey() {}
}
