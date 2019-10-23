package epi.Chapter15;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import epi.BinaryTreeNode;
import epi.Chapter10.TreeInorder;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeABst {

    static class QueueEntry {
        public BinaryTreeNode<Integer> treeNode;
        public Integer left, right;

        QueueEntry(BinaryTreeNode<Integer> treeNode, Integer left, Integer right) {
            this.treeNode = treeNode;
            this.left = left;
            this.right = right;
        }
    }

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return validateBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validateBST(BinaryTreeNode<Integer> tree, Integer left, Integer right) {
        if (tree == null) {
            return true;
        }

        if (tree.data < left || tree.data > right) {
            return false;
        }

        return validateBST(tree.left, left, tree.data) && validateBST(tree.right, tree.data, right);
    }

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST_BFS(BinaryTreeNode<Integer> tree) {
        final Deque<QueueEntry> queue = new LinkedList<>();
        queue.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));

        QueueEntry curr;
        while (!queue.isEmpty()) {
            curr = queue.pollFirst();
            if (curr.treeNode != null) {
                if (curr.treeNode.data < curr.left || curr.treeNode.data > curr.right) {
                    return false;
                }

                queue.addLast(new QueueEntry(curr.treeNode.left, curr.left, curr.treeNode.data));
                queue.addLast(new QueueEntry(curr.treeNode.right, curr.treeNode.data, curr.right));
            }
        }

        return true;
    }

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST_inOrder(BinaryTreeNode<Integer> tree) {
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
