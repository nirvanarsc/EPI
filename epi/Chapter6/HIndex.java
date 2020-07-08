package epi.Chapter6;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class HIndex {

    @EpiTest(testDataFile = "h_index.tsv")
    // https://leetcode.com/problems/h-index/
    // https://leetcode.com/problems/h-index-ii/
    public static int hIndex(List<Integer> citations) {
        Collections.sort(citations);
        final int n = citations.size();
        int lo = 0, hi = n;
        while (lo < hi) {
            final int mid = lo + hi >>> 1;
            if (citations.get(mid) < n - mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return n - lo;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private HIndex() {}
}
