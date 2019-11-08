package epi.Chapter17;

import java.util.Arrays;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LevenshteinDistance {

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinRecursive(String a, String b) {
        final int[][] cache = new int[a.length()][b.length()];
        for (int[] row : cache) { Arrays.fill(row, -1); }
        return dp(a, a.length() - 1, b, b.length() - 1, cache);
    }

    private static int dp(String a, int aL, String b, int bL, int[][] cache) {
        if (aL < 0) {
            return bL + 1;
        }
        if (bL < 0) {
            return aL + 1;
        }
        if (cache[aL][bL] == -1) {
            final int cost = a.charAt(aL) == b.charAt(bL) ? 0 : 1;
            final int substituteLast = dp(a, aL - 1, b, bL - 1, cache);
            final int addLast = dp(a, aL, b, bL - 1, cache);
            final int deleteLast = dp(a, aL - 1, b, bL, cache);
            cache[aL][bL] = min(substituteLast + cost, addLast + 1, deleteLast + 1);
        }
        return cache[aL][bL];
    }

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinSpace(String a, String b) {
        if (a.length() < b.length()) {
            return levenshteinSpace(b, a);
        }
        final int[][] dp = new int[2][b.length() + 1];
        for (int i = 0; i <= b.length(); i++) { dp[0][i] = i; }
        for (int r = 1; r <= a.length(); r++) {
            dp[r % 2][0] = r;
            for (int c = 1; c <= b.length(); c++) {
                final int cost = a.charAt(r - 1) == b.charAt(c - 1) ? 0 : 1;
                dp[r % 2][c] = min(dp[(r - 1) % 2][c - 1] + cost, dp[(r - 1) % 2][c] + 1, dp[r % 2][c - 1] + 1);
            }
        }

        return dp[a.length() % 2][b.length()];
    }

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinDistance(String a, String b) {
        final int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) { dp[i][0] = i; }
        for (int i = 0; i <= b.length(); i++) { dp[0][i] = i; }

        for (int r = 1; r <= a.length(); r++) {
            for (int c = 1; c <= b.length(); c++) {
                final int cost = a.charAt(r - 1) == b.charAt(c - 1) ? 0 : 1;
                dp[r][c] = min(dp[r - 1][c - 1] + cost, dp[r - 1][c] + 1, dp[r][c - 1] + 1);
            }
        }

        return dp[a.length()][b.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LevenshteinDistance() {}
}
