package epi.Chapter17;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MinimumWeightPathInATriangle {

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathTotal(List<List<Integer>> triangle) {
        return recurse(0, 0, triangle, new int[triangle.size()][triangle.size()]);
    }

    public static int recurse(int level, int idx, List<List<Integer>> triangle, int[][] dp) {
        if (level == triangle.size()) {
            return 0;
        }

        if (dp[level][idx] != 0) {
            return dp[level][idx];
        }

        dp[level][idx] = triangle.get(level).get(idx) + Math.min(recurse(level + 1, idx, triangle, dp),
                                                                 recurse(level + 1, idx + 1, triangle, dp));

        return dp[level][idx];
    }

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathBottomUp(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        final int n = triangle.size() - 1;
        final List<Integer> list = new ArrayList<>(triangle.get(n));

        for (int level = n - 1; level >= 0; level--) {
            for (int i = 0; i < triangle.get(level).size(); i++) {
                list.set(i, Math.min(list.get(i) + triangle.get(level).get(i),
                                     list.get(i + 1) + triangle.get(level).get(i)));
            }
        }

        return list.get(0);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumWeightPathInATriangle() {}
}
