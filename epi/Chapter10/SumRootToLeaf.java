package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SumRootToLeaf {

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        final int[] res = { 0, 0 };
        dfs(tree, res);
        return res[0];
    }

    private static void dfs(BinaryTreeNode<Integer> tree, int[] path) {
        if (tree == null) {
            return;
        }
        path[1] = path[1] << 1 | tree.data;
        dfs(tree.left, path);
        if (tree.left == null && tree.right == null) {
            path[0] += path[1];
        }
        dfs(tree.right, path);
        path[1] >>= 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SumRootToLeaf() {}
}
