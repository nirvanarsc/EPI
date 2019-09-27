package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class PathSum {

    @EpiTest(testDataFile = "path_sum.tsv")
    public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int remainingWeight) {
        return dfs(tree, remainingWeight);
    }

    public static boolean dfs(BinaryTreeNode<Integer> node, int remainingWeight) {
        if (node == null) {
            return false;
        }
        final int val = remainingWeight - node.data;

        return node.left == node.right && val == 0 ? true : dfs(node.right, val) || dfs(node.left, val);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PathSum() {
    }
}
