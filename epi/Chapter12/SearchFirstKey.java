package epi.Chapter12;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchFirstKey {

    @SuppressWarnings("MethodParameterNamingConvention")
    @EpiTest(testDataFile = "search_first_key.tsv")
    public static int searchFirstOfK(List<Integer> A, int k) {
        if (A.isEmpty()) {
            return -1;
        }
        int lo = 0;
        int hi = A.size() - 1;
        while (lo < hi) {
            final int mid = lo + hi >>> 1;
            if (A.get(mid) < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return A.get(lo) == k ? lo : -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchFirstKey() {}
}
