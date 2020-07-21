package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeBalanced {

    static class Pair {
        int d;
        boolean isBalanced;

        Pair(int d, boolean isBalanced) {
            this.d = d;
            this.isBalanced = isBalanced;
        }
    }

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return dfs(tree).isBalanced;
    }

    private static Pair dfs(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new Pair(0, true);
        }
        final Pair left = dfs(tree.left);
        if (!left.isBalanced) {
            return left;
        }
        final Pair right = dfs(tree.right);
        if (!right.isBalanced) {
            return right;
        }
        return new Pair(Math.max(left.d, right.d) + 1, Math.abs(left.d - right.d) <= 1);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsTreeBalanced() {}
}
