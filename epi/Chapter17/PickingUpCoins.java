package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class PickingUpCoins {

    @EpiTest(testDataFile = "picking_up_coins.tsv")
    public static int pickUpCoins(List<Integer> coins) {
        return recurse(0, coins.size() - 1, coins, new int[coins.size()][coins.size()]);
    }

    public static int recurse(int start, int end, List<Integer> coins, int[][] dp) {
        if (start > end) {
            return 0;
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        dp[start][end] = Math.max(coins.get(start) + Math.min(recurse(start + 2, end, coins, dp),
                                                              recurse(start + 1, end - 1, coins, dp)),
                                  coins.get(end) + Math.min(recurse(start + 1, end - 1, coins, dp),
                                                            recurse(start, end - 2, coins, dp)));
        return dp[start][end];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PickingUpCoins() {}
}
