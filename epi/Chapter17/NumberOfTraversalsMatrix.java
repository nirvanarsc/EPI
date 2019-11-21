package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfTraversalsMatrix {

    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    public static int numberOfWays(int n, int m) {
        final int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfTraversalsMatrix() {}
}
