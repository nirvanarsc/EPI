package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeBalanced {

    static class HeightBalance {
        boolean balance;
        int height;

        HeightBalance(boolean balance, int height) {
            this.balance = balance;
            this.height = height;
        }
    }

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return checkBalanced(tree).balance;
    }

    private static HeightBalance checkBalanced(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new HeightBalance(true, -1);
        }

        final HeightBalance left = checkBalanced(tree.left);
        if (!left.balance) {
            return left;
        }

        final HeightBalance right = checkBalanced(tree.right);
        if (!right.balance) {
            return right;
        }

        final boolean balanced = Math.abs(left.height - right.height) <= 1;
        final int height = Math.max(left.height, right.height) + 1;

        return new HeightBalance(balanced, height);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsTreeBalanced() {
    }
}
