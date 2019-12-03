package epi.Chapter17;

import java.util.Arrays;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class PrettyPrinting {

    @EpiTest(testDataFile = "pretty_printing.tsv")
    public static int minimumMessiness(List<String> words, int lineLength) {
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

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PrettyPrinting() {}
}
