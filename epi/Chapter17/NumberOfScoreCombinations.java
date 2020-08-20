package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsBottomUp(int finalScore, List<Integer> scores) {
        final int[][] dp = new int[scores.size()][finalScore + 1];
        for (int i = 0; i < scores.size(); i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= finalScore; j++) {
                final int withoutThisPlay = i > 0 ? dp[i - 1][j] : 0;
                final int withThisPlay = j >= scores.get(i) ? dp[i][j - scores.get(i)] : 0;
                dp[i][j] = withoutThisPlay + withThisPlay;
            }
        }
        return dp[scores.size() - 1][finalScore];
    }

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsSpace(int finalScore, List<Integer> scores) {
        final int[] dp = new int[finalScore + 1];
        dp[0] = 1;
        for (int score : scores) {
            for (int i = score; i <= finalScore; i++) {
                dp[i] += dp[i - score];
            }
        }
        return dp[finalScore];
    }

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> scores) {
        return dfs(scores, finalScore, 0, new Integer[scores.size()][finalScore + 1]);
    }

    private static int dfs(List<Integer> scores, int target, int idx, Integer[][] dp) {
        if (idx == scores.size()) {
            return target == 0 ? 1 : 0;
        }
        if (dp[idx][target] != null) {
            return dp[idx][target];
        }

        final int skip = dfs(scores, target, idx + 1, dp);
        int take = 0;
        if (target >= scores.get(idx)) {
            take = dfs(scores, target - scores.get(idx), idx, dp);
        }
        return dp[idx][target] = skip + take;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfScoreCombinations() {}
}
