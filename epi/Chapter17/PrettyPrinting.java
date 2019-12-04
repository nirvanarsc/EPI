package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Arrays;
import java.util.List;

public final class PrettyPrinting {

    @EpiTest(testDataFile = "pretty_printing.tsv")
    public static int minimumMessinessBottomUp(List<String> words, int lineLength) {
        final int[] dp = new int[words.size()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int blanks = lineLength - words.get(0).length();
        dp[0] = blanks * blanks;
        for (int i = 1; i < words.size(); i++) {
            blanks = lineLength - words.get(i).length();
            dp[i] = dp[i - 1] + blanks * blanks;
            for (int j = i - 1; j >= 0 && blanks > words.get(j).length(); j--) {
                blanks -= words.get(j).length() + 1;
                final int firstJMessiness = j - 1 < 0 ? 0 : dp[j - 1];
                final int currentLineMessiness = blanks * blanks;
                dp[i] = Math.min(dp[i], firstJMessiness + currentLineMessiness);
            }
        }
        return dp[words.size() - 1];
    }

    @EpiTest(testDataFile = "pretty_printing.tsv")
    public static int minimumMessinessTopDown(List<String> words, int lineLength) {
        final int[] dp = new int[words.size()];
        Arrays.fill(dp, -1);
        return recurse(0, words, lineLength, dp);
    }

    private static int recurse(int start, List<String> words, int lineLength, int[] dp) {
        if (start >= words.size()) {
            return 0;
        }

        if (dp[start] != -1) {
            return dp[start];
        }

        int res = Integer.MAX_VALUE;
        for (int j = start + 1; j <= words.size(); j++) {
            res = Math.min(res, recurse(j, words, lineLength, dp) + getBadness(start, j, words, lineLength));
        }
        dp[start] = res;
        return dp[start];
    }

    private static int getBadness(int from, int to, List<String> words, int lineLength) {
        int blanks = -1;
        for (int i = from; i < to; i++) {
            blanks += words.get(i).length() + 1;
        }
        if (blanks > lineLength) {
            return (int) 1e9;
        }
        return (lineLength - blanks) * (lineLength - blanks);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PrettyPrinting() {}
}
