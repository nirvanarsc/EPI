package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeSymmetric {

    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        return tree == null || isSymmetric(tree.left, tree.right);
    }

    public static boolean isSymmetric(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null || !left.data.equals(right.data)) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsTreeSymmetric() {
    }
}
