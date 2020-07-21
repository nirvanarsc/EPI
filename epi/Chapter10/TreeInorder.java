package epi.Chapter10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TreeInorder {

    @EpiTest(testDataFile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversalIterative(BinaryTreeNode<Integer> tree) {
        final Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        final List<Integer> res = new ArrayList<>();
        BinaryTreeNode<Integer> curr = tree;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            } else {
                curr = stack.removeFirst();
                res.add(curr.data);
                curr = curr.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeInorder() {}
}
