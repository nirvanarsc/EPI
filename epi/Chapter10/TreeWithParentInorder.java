package epi.Chapter10;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.List;

public final class TreeWithParentInorder {

    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        final List<Integer> res = new ArrayList<>();
        while (tree != null && tree.left != null) {
            tree = tree.left;
        }
        while (tree != null) {
            res.add(tree.data);
            tree = SuccessorInTree.findSuccessor(tree);
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeWithParentInorder() {
    }
}
