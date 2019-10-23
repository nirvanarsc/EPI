package epi.Chapter15;

import java.util.List;

import epi.BinaryTreeNode;
import epi.Chapter10.TreeInorder;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeABst {

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return true;
        }
        final List<Integer> integers = TreeInorder.inorderTraversalRecursive(tree);
        Integer prev = integers.get(0);
        for (int i = 1; i < integers.size(); i++) {
            if (integers.get(i) < prev) {
                return false;
            }
            prev = integers.get(i);
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsTreeABst() {}
}
