package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeSymmetric {

    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return true;
        }
        return dfs(tree.left, tree.right);
    }

    private static boolean dfs(BinaryTreeNode<Integer> t1, BinaryTreeNode<Integer> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.data.equals(t2.data) && dfs(t1.left, t2.right) && dfs(t1.right, t2.left);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsTreeSymmetric() {}
}
