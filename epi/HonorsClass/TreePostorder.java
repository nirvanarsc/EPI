package epi.HonorsClass;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TreePostorder {

    @EpiTest(testDataFile = "tree_postorder.tsv")
    public static List<Integer> postorderTraversalRecursive(BinaryTreeNode<Integer> tree) {
        final List<Integer> res = new ArrayList<>();
        helper(tree, res);
        return res;
    }

    private static void helper(BinaryTreeNode<Integer> node, List<Integer> res) {
        if (node == null) {
            return;
        }
        helper(node.left, res);
        helper(node.right, res);
        res.add(node.data);
    }

    @EpiTest(testDataFile = "tree_postorder.tsv")
    public static List<Integer> postorderTraversalIterative(BinaryTreeNode<Integer> tree) {
        final List<Integer> res = new ArrayList<>();
        final Deque<BinaryTreeNode<Integer>> s = new ArrayDeque<>();
        if (tree != null) {
            s.addFirst(tree);
        }
        while (!s.isEmpty()) {
            final BinaryTreeNode<Integer> curr = s.removeFirst();
            res.add(curr.data);
            if (curr.left != null) { s.addFirst(curr.left); }
            if (curr.right != null) { s.addFirst(curr.right); }
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreePostorder() {}
}
