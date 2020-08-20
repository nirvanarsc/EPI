package epi.Chapter17;

import java.util.Arrays;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestNondecreasingSubsequence {

    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    public static int longestNondecreasingSubsequenceLength(List<Integer> list) {
        final int[] dp = new int[list.size()];
        Arrays.fill(dp, 1);
        int length = 1;
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) >= list.get(j)) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            length = Math.max(length, dp[i]);
        }

        return length;
    }

    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    public static int longestNondecreasingSubsequenceLengthBS(List<Integer> list) {
        final int[] dp = new int[list.size()];
        int len = 0;
        for (int num : list) {
            final int index = binarySearch(dp, 0, len, num);
            dp[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    // find first element greater than target
    public static int binarySearch(int[] nums, int lo, int hi, int target) {
        while (lo < hi) {
            final int mid = (lo + hi) >>> 1;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestNondecreasingSubsequence() {}
}
