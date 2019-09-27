package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SumRootToLeaf {

    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return helper(tree, 0);
    }

    public static int helper(BinaryTreeNode<Integer> node, int parentSum) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return 2 * parentSum + node.data;
        } else {
            return helper(node.right, 2 * parentSum + node.data) + helper(node.left, 2 * parentSum + node.data);
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SumRootToLeaf() {
    }
}
