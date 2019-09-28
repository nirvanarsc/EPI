package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class TreePreorder {

    @EpiTest(testDataFile = "tree_preorder.tsv")
    public static List<Integer> preorderTraversalRecursive(BinaryTreeNode<Integer> tree) {
        final List<Integer> res = new LinkedList<>();
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
    public static List<Integer> preorderTraversalIterative(BinaryTreeNode<Integer> tree) {
        final LinkedList<Integer> res = new LinkedList<>();
        final Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
        s.addFirst(tree);

        while (!s.isEmpty()) {
            final BinaryTreeNode<Integer> curr = s.removeFirst();
            if (curr != null) {
                res.addLast(curr.data);
                s.addFirst(curr.right);
                s.addFirst(curr.left);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreePreorder() {
    }
}
