package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SumRootToLeaf {

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return dfs(tree, 0);
    }

    public static int dfs(BinaryTreeNode<Integer> node, int parentSum) {
        if (node == null) {
            return 0;
        }
        final int val = parentSum << 1 | node.data;

        return node.left == node.right ? val : dfs(node.right, val) + dfs(node.left, val);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SumRootToLeaf() {
    }
}
