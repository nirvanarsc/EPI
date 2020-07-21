package epi.Chapter10;

import java.util.ArrayList;
import java.util.List;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class TreeRightSibling {

    private static final class BinaryTreeNode<T> {
        public T data;
        public BinaryTreeNode<T> left, right;
        public BinaryTreeNode<T> next; // Populate this field.

        private BinaryTreeNode(T data) {
            this.data = data;
        }
    }

    // Variant: Solve the same problem for a general binary tree. See Figure 10.7 for an example.
    public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
        if (tree != null) {
            BinaryTreeNode<Integer> iter = tree;
            while (iter != null) {
                final BinaryTreeNode<Integer> level = new BinaryTreeNode<>(-1);
                BinaryTreeNode<Integer> curr = level;
                while (iter != null) {
                    if (iter.left != null) {
                        curr.next = iter.left;
                        curr = curr.next;
                    }
                    if (iter.right != null) {
                        curr.next = iter.right;
                        curr = curr.next;
                    }
                    iter = iter.next;
                }
                iter = level.next;
            }
        }
    }

    @SuppressWarnings("ReturnOfNull")
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

    private TreeRightSibling() {}
}
