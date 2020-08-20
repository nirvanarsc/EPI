package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings({ "MethodParameterNamingConvention", "TailRecursion" })
public final class LevenshteinDistance {

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinDistance(String A, String B) {
        return dfs(A, B, 0, 0, new Integer[A.length()][B.length()]);
    }

    private static int dfs(String A, String B, int i, int j, Integer[][] dp) {
        if (i == A.length()) {
            return B.length() - j;
        }
        if (j == B.length()) {
            return A.length() - i;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        final int same = (A.charAt(i) == B.charAt(j) ? 0 : 1) + dfs(A, B, i + 1, j + 1, dp);
        final int takeA = 1 + dfs(A, B, i + 1, j, dp);
        final int takeB = 1 + dfs(A, B, i, j + 1, dp);
        return dp[i][j] = min(same, takeA, takeB);
    }

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinSpace(String A, String B) {
        if (A.length() < B.length()) {
            return levenshteinSpace(B, A);
        }
        final int[][] dp = new int[2][B.length() + 1];
        for (int i = 0; i <= B.length(); i++) { dp[0][i] = i; }
        for (int r = 1; r <= A.length(); r++) {
            dp[r % 2][0] = r;
            for (int c = 1; c <= B.length(); c++) {
                final int cost = A.charAt(r - 1) == B.charAt(c - 1) ? 0 : 1;
                dp[r % 2][c] = min(dp[(r - 1) % 2][c - 1] + cost,
                                   dp[(r - 1) % 2][c] + 1,
                                   dp[r % 2][c - 1] + 1);
            }
        }
        return dp[A.length() % 2][B.length()];
    }

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinDistanceTopDown(String A, String B) {
        final int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i <= A.length(); i++) { dp[i][0] = i; }
        for (int i = 0; i <= B.length(); i++) { dp[0][i] = i; }
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                final int cost = A.charAt(i - 1) == B.charAt(j - 1) ? 0 : 1;
                dp[i][j] = min(dp[i - 1][j - 1] + cost, dp[i - 1][j] + 1, dp[i][j - 1] + 1);
            }
        }
        return dp[A.length()][B.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LevenshteinDistance() {}
}
