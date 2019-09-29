package epi.Chapter10;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class TreeRightSibling {

    public static class BinaryTreeNode<T> {
        public T data;
        public BinaryTreeNode<T> left, right;
        public BinaryTreeNode<T> next; // Populate this field.

        public BinaryTreeNode(T data) {
            this.data = data;
        }
    }

    // Variant: Solve the same problem for a general binary tree. See Figure 10.7 for an example.
    public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
        if (tree != null) {
            final Deque<BinaryTreeNode<Integer>> q = new LinkedList<>();
            q.offerLast(tree);

            while (!q.isEmpty()) {
                final int l = q.size();
                BinaryTreeNode<Integer> prev = null;
                for (int i = 0; i < l; i++) {
                    final BinaryTreeNode<Integer> curr = q.pollFirst();
                    if (prev != null) {
                        prev.next = curr;
                    }
                    if (curr.left != null) {
                        q.offerLast(curr.left);
                    }
                    if (curr.right != null) {
                        q.offerLast(curr.right);
                    }
                    prev = curr;
                }
            }
        }
    }

    private static BinaryTreeNode<Integer> cloneTree(BinaryTree<Integer> original) {
        if (original == null) {
            return null;
        }
        final BinaryTreeNode<Integer> cloned = new BinaryTreeNode<>(original.data);
        cloned.left = cloneTree(original.left);
        cloned.right = cloneTree(original.right);
        return cloned;
    }

    @EpiTest(testDataFile = "tree_right_sibling.tsv")
    public static List<List<Integer>> constructRightSiblingWrapper(TimedExecutor executor,
                                                                   BinaryTree<Integer> tree) throws Exception {
        final BinaryTreeNode<Integer> cloned = cloneTree(tree);

        executor.run(() -> constructRightSibling(cloned));

        final List<List<Integer>> result = new ArrayList<>();
        BinaryTreeNode<Integer> levelStart = cloned;
        while (levelStart != null) {
            final List<Integer> level = new ArrayList<>();
            BinaryTreeNode<Integer> levelIt = levelStart;
            while (levelIt != null) {
                level.add(levelIt.data);
                levelIt = levelIt.next;
            }
            result.add(level);
            levelStart = levelStart.left;
        }
        return result;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeRightSibling() {
    }
}
