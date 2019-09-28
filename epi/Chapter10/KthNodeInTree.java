package epi.Chapter10;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class KthNodeInTree {

    public static class BinaryTreeNode<T> {
        public T data;
        public BinaryTreeNode<T> left, right;
        public int size;

        public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right, int size) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.size = size;
        }
    }

    public static BinaryTreeNode<Integer> findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
        while (tree != null) {
            final int leftSize = (tree.left == null ? 0 : tree.left.size) + 1;
            if (k == leftSize) {
                return tree;
            } else if (k < leftSize) {
                tree = tree.left;
            } else {
                k -= leftSize;
                tree = tree.right;
            }
        }

        return null;
    }

//    Tail recursion
//    public static BinaryTreeNode<Integer> findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
//        final int leftSize = tree.left == null ? 0 : tree.left.size;
//        if (k - 1 == leftSize) {
//            return tree;
//        } else if (k - 1 < leftSize) {
//            return findKthNodeBinaryTree(tree.left, k);
//        } else {
//            return findKthNodeBinaryTree(tree.right, k - leftSize - 1);
//        }
//    }

    public static BinaryTreeNode<Integer> convertToTreeWithSize(BinaryTree<Integer> original) {
        if (original == null) {
            return null;
        }
        final BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
        final BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
        final int lSize = left == null ? 0 : left.size;
        final int rSize = right == null ? 0 : right.size;
        return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
    }

    @EpiTest(testDataFile = "kth_node_in_tree.tsv")
    public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
                                                   BinaryTree<Integer> tree,
                                                   int k) throws Exception {
        final BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);
        final BinaryTreeNode<Integer> result = executor.run(() -> findKthNodeBinaryTree(converted, k));

        if (result == null) {
            throw new TestFailure("Result can't be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KthNodeInTree() {
    }
}
