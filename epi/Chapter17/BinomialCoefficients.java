package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class BinomialCoefficients {

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficient(int n, int k) {
        final int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= k; i++) {
            dp[i][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        return dp[n][k];
    }

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficientSpace(int n, int k) {
        final int[][] dp = new int[2][k + 2];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + dp[(i - 1) % 2][j];
            }
        }

        return dp[n % 2][k];
    }

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficientR(int n, int k) {
        return calc(n, k, new int[n + 1][n + 1]);
    }

    public static int calc(int n, int k, int[][] cache) {
        if (n == k || k == 0) {
            return 1;
        }

        if (cache[n][k] != 0) {
            return cache[n][k];
        }

        cache[n][k] = calc(n - 1, k - 1, cache) + calc(n - 1, k, cache);
        return cache[n][k];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BinomialCoefficients() {}
}
