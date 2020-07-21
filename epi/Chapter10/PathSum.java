package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class PathSum {

    @EpiTest(testDataFile = "path_sum.tsv")
    public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int remainingWeight) {
        final int[] path = { remainingWeight, 0 };
        return dfs(tree, path);
    }

    private static boolean dfs(BinaryTreeNode<Integer> tree, int[] path) {
        if (tree == null) {
            return false;
        }
        path[1] += tree.data;
        if (dfs(tree.left, path)) {
            return true;
        }
        if (tree.left == null && tree.right == null) {
            if (path[0] == path[1]) {
                return true;
            }
        }
        if (dfs(tree.right, path)) {
            return true;
        }
        path[1] -= tree.data;
        return false;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PathSum() {}
}
