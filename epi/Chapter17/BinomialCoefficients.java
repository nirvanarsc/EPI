package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class BinomialCoefficients {

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficient(int n, int k) {
        if (k == 0) { return 1; }
        int[] row = new int[k + 1];
        row[0] = 1;
        row[1] = 1;
        for (int i = 0; i < n - 1; i++) {
            final int[] next = new int[k + 1];
            for (int j = 0; j <= k; j++) {
                next[j] = ((j > 0) ? row[j - 1] : 0) + row[j];
            }
            row = next;
        }
        return row[k];
    }

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficientR(int n, int k) {
        return dfs(n, k, new int[n + 1][k + 1]);
    }

    public static int dfs(int n, int k, int[][] dp) {
        if (n == k || k == 0) {
            return 1;
        }
        if (dp[n][k] != 0) {
            return dp[n][k];
        }
        return dp[n][k] = dfs(n - 1, k - 1, dp) + dfs(n - 1, k, dp);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BinomialCoefficients() {}
}
