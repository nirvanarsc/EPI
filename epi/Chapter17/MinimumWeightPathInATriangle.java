package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MinimumWeightPathInATriangle {

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0, new Integer[triangle.size()][triangle.size()]);
    }

    private static int dfs(List<List<Integer>> triangle, int i, int j, Integer[][] dp) {
        if (i == triangle.size()) {
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        final int left = dfs(triangle, i + 1, j, dp);
        int right = (int) 1e9;
        if (j < triangle.get(i).size()) {
            right = dfs(triangle, i + 1, j + 1, dp);
        }
        return dp[i][j] = triangle.get(i).get(j) + Math.min(left, right);
    }

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathBottomUp(List<List<Integer>> triangle) {
        final int[] dp = new int[triangle.size() + 1];
        for (int level = triangle.size() - 1; level >= 0; level--) {
            for (int i = 0; i < triangle.get(level).size(); i++) {
                final int val = triangle.get(level).get(i);
                dp[i] = Math.min(dp[i] + val, dp[i + 1] + val);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumWeightPathInATriangle() {}
}
