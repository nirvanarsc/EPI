package epi.Chapter17;

import java.util.Arrays;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfTraversalsMatrix {

    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    public static int numberOfWays(int n, int m) {
        int[] row = new int[m];
        Arrays.fill(row, 1);
        for (int i = 1; i < n; i++) {
            final int[] next = new int[m];
            for (int j = 0; j < m; j++) {
                next[j] = row[j] + ((j > 0) ? next[j - 1] : 0);
            }
            row = next;
        }
        return row[m - 1];
    }

    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
    public static int numWays(int n, int m) {
        return dfs(n - 1, m - 1, new Integer[n][m]);
    }

    public static int dfs(int n, int m, Integer[][] dp) {
        if (n == 0 || m == 0) {
            return 1;
        }
        if (dp[n][m] != null) {
            return dp[n][m];
        }
        return dp[n][m] = dfs(n - 1, m, dp) + dfs(n, m - 1, dp);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfTraversalsMatrix() {}
}
