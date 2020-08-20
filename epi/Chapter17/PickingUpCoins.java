package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class PickingUpCoins {

    @EpiTest(testDataFile = "picking_up_coins.tsv")
    public static int pickUpCoins(List<Integer> coins) {
        return dfs(coins, 0, coins.size() - 1, new Integer[coins.size()][coins.size()]);
    }

    private static int dfs(List<Integer> coins, int i, int j, Integer[][] dp) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        final int left = coins.get(i) + Math.min(dfs(coins, i + 2, j, dp), dfs(coins, i + 1, j - 1, dp));
        final int right = coins.get(j) + Math.min(dfs(coins, i, j - 2, dp), dfs(coins, i + 1, j - 1, dp));
        return dp[i][j] = Math.max(left, right);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PickingUpCoins() {}
}
