package epi.Chapter17;

import java.util.Arrays;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfTraversalsMatrix {

    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    public static int numberOfWays(int n, int m) {
        final int[][] dp = new int[n][m];

        Arrays.fill(dp[0], 1);

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[n - 1][m - 1];
    }

    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    public static int numberOfWaysSpace(int n, int m) {
        if (n > m) {
            return numberOfWaysSpace(m, n);
        }

        final int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < m; j++) {
            for (int i = 1; i < n; i++) {
                dp[i][j % 2] = dp[i - 1][j % 2] + dp[i][(j - 1) % 2];
            }
        }

        return dp[n - 1][(m - 1) % 2];
    }

    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    public static int numWays(int n, int m) {
        return calc(n - 1, m - 1, new int[n][m]);
    }

    public static int calc(int n, int m, int[][] cache) {
        if (n == 0 || m == 0) {
            return 1;
        }

        if (cache[n][m] != 0) {
            return cache[n][m];
        }

        cache[n][m] = calc(n - 1, m, cache) + calc(n, m - 1, cache);

        return cache[n][m];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfTraversalsMatrix() {}
}
