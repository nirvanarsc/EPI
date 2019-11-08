package epi.Chapter17;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinations(int finalScore, List<Integer> scores) {
        final int[][] dp = new int[scores.size()][finalScore + 1];
        for (int i = 0; i < scores.size(); i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= finalScore; j++) {
                final int withoutThisPlay = i - 1 >= 0 ? dp[i - 1][j] : 0;
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
    public static int numCombinationsRecursive(int finalScore, List<Integer> scores) {
        return dp(finalScore, 0, scores, new int[scores.size()][finalScore + 1]);
    }

    private static int dp(int amount, int index, List<Integer> scores, int[][] cache) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index == scores.size()) {
            return 0;
        }
        if (cache[index][amount] != 0) {
            return cache[index][amount];
        }
        final int l = dp(amount - scores.get(index), index, scores, cache);
        final int r = dp(amount, index + 1, scores, cache);
        cache[index][amount] = l + r;
        return cache[index][amount];
    }

    //    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int listAllCombinations(int top, List<Integer> scores) {
        final List<List<Integer>> lists = new ArrayList<>();
        dp(top, scores, new ArrayList<>(), lists);
        lists.forEach(System.out::println);
        return lists.size();
    }

    private static void dp(int top, List<Integer> scores, List<Integer> curr, List<List<Integer>> lists) {
        if (top == 0) {
            lists.add(new ArrayList<>(curr));
            return;
        }
        if (top < 0) {
            return;
        }
        for (int k : scores) {
            curr.add(k);
            dp(top - k, scores, curr, lists);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfScoreCombinations() {}
}
