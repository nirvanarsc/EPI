package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsStringInMatrix {

    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
        final Boolean[][][] dp = new Boolean[grid.size()][grid.get(0).size()][pattern.size()];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (dfs(grid, pattern, i, j, 0, dp)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(List<List<Integer>> grid, List<Integer> pattern, int i, int j, int idx,
                               Boolean[][][] dp) {
        if (idx == pattern.size()) {
            return true;
        }
        if (i < 0 || i == grid.size() || j < 0 || j == grid.get(0).size() ||
            !grid.get(i).get(j).equals(pattern.get(idx))) {
            return false;
        }
        if (dp[i][j][idx] != null) {
            return dp[i][j][idx];
        }
        boolean res = false;
        for (int[] dir : DIRS) {
            if (dfs(grid, pattern, i + dir[0], j + dir[1], idx + 1, dp)) {
                res = true;
                break;
            }
        }
        return dp[i][j][idx] = res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringInMatrix() {}
}
