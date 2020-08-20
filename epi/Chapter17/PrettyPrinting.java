package epi.Chapter17;

import java.util.Arrays;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

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
    public static int minimumMessiness(List<String> words, int lineLength) {
        return dfs(words, 0, lineLength, lineLength, new Integer[words.size()][lineLength + 1]);
    }

    private static int dfs(List<String> words, int idx, int currLine, int fullLine, Integer[][] dp) {
        if (idx == words.size()) {
            return currLine * currLine;
        }
        if (dp[idx][currLine] != null) {
            return dp[idx][currLine];
        }
        final int nextLength = currLine - (words.get(idx).length() + (currLine == fullLine ? 0 : 1));
        int sameLine = (int) 1e9;
        if (words.get(idx).length() < currLine) {
            sameLine = dfs(words, idx + 1, nextLength, fullLine, dp);
        }
        final int newLine = currLine * currLine
                            + dfs(words, idx + 1, fullLine - words.get(idx).length(), fullLine, dp);
        return dp[idx][currLine] = Math.min(sameLine, newLine);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PrettyPrinting() {}
}
