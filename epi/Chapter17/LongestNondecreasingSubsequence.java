package epi.Chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestNondecreasingSubsequence {

    static class Pair {
        int length;
        int index;

        Pair(int length, int index) {
            this.length = length;
            this.index = index;
        }

        @Override
        public String toString() {
            return length + " " + index;
        }
    }

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
    public static int longestNondecreasingSubsequence(List<Integer> list) {
        final Pair[] dp = new Pair[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dp[i] = new Pair(1, 0);
        }

        Pair res = new Pair(1, 0);
        int maxIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) >= list.get(j) && dp[i].length < 1 + dp[j].length) {
                    dp[i].length = 1 + dp[j].length;
                    dp[i].index = j;
                }
            }
            if (dp[i].length > res.length) {
                res = dp[i];
                maxIndex = i;
            }
        }

        final List<Integer> sequence = new ArrayList<>(Collections.singletonList(list.get(maxIndex)));
        Pair copy = new Pair(res.length, res.index);
        for (int i = 0; i < res.length - 1; i++) {
            sequence.add(list.get(copy.index));
            copy = dp[copy.index];
        }
        return res.length;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestNondecreasingSubsequence() {}
}
