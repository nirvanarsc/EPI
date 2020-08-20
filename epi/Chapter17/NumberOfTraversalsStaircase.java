package epi.Chapter17;

import java.util.Arrays;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfTraversalsStaircase {

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTop(int top, int maximumStep) {
        return dfs(top, maximumStep, new Integer[top + 1]);
    }

    private static int dfs(int top, int maximumStep, Integer[] dp) {
        if (top <= 1) {
            return 1;
        }
        if (dp[top] != null) {
            return dp[top];
        }
        int res = 0;
        for (int step = 1; step <= Math.min(top, maximumStep); step++) {
            res += dfs(top - step, maximumStep, dp);
        }
        return dp[top] = res;
    }

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTopBottomUp(int top, int maximumStep) {
        final int[] dp = new int[maximumStep];
        dp[maximumStep - 1] = 1;

        for (int i = 1; i <= top; i++) {
            final int temp = Arrays.stream(dp).sum();
            System.arraycopy(dp, 1, dp, 0, maximumStep - 1);
            dp[maximumStep - 1] = temp;
        }
        return dp[maximumStep - 1];
    }

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTopKnapsack(int top, int maximumStep) {
        final int[] dp = new int[top + 1];
        dp[0] = 1;
        for (int i = 0; i <= top; i++) {
            for (int j = 1; j <= maximumStep; j++) {
                if (i >= j) {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[top];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfTraversalsStaircase() {}
}
