package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class TreeInorder {

    @EpiTest(testDataFile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversalRecursive(BinaryTreeNode<Integer> tree) {
        final List<Integer> res = new LinkedList<>();
        helper(tree, res);
        return res;
    }

    private static void helper(BinaryTreeNode<Integer> node, List<Integer> res) {
        if (node == null) {
            return;
        }
        helper(node.left, res);
        res.add(node.data);
        helper(node.right, res);
    }

    @EpiTest(testDataFile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversalIterative(BinaryTreeNode<Integer> tree) {
        final List<Integer> res = new ArrayList<>();
        final Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
        BinaryTreeNode<Integer> curr = tree;

        while (!s.isEmpty() || curr != null) {
            while (curr != null) {
                s.addFirst(curr);
                curr = curr.left;
            }

            curr = s.removeFirst();
            res.add(curr.data);
            curr = curr.right;
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeInorder() {
    }
}
