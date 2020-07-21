package epi.Chapter10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TreePreorder {

    @EpiTest(testDataFile = "tree_preorder.tsv")
    public static List<Integer> preorderTraversalRecursive(BinaryTreeNode<Integer> tree) {
        final List<Integer> res = new ArrayList<>();
        helper(tree, res);
        return res;
    }

    private static void helper(BinaryTreeNode<Integer> node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.data);
        helper(node.left, res);
        helper(node.right, res);
    }

    @EpiTest(testDataFile = "tree_preorder.tsv")
    public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
        final Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        final List<Integer> res = new ArrayList<>();
        if (tree != null) {
            stack.addFirst(tree);
        }
        while (!stack.isEmpty()) {
            final BinaryTreeNode<Integer> curr = stack.removeFirst();
            res.add(curr.data);
            if (curr.right != null) { stack.addFirst(curr.right); }
            if (curr.left != null) { stack.addFirst(curr.left); }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreePreorder() {}
}
