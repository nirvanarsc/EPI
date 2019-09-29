package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class TreeExterior {

    public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return Collections.emptyList();
        }
        final List<BinaryTreeNode<Integer>> res = new ArrayList<>(Collections.singletonList(tree));
        res.addAll(getLeft(tree.left));
        res.addAll(TreeConnectLeaves.createListOfLeaves(tree.left));
        res.addAll(TreeConnectLeaves.createListOfLeaves(tree.right));
        res.addAll(getRight(tree.right));
        return res;
    }

    public static List<BinaryTreeNode<Integer>> getLeft(BinaryTreeNode<Integer> node) {
        final List<BinaryTreeNode<Integer>> res = new LinkedList<>();
        while (node != null && node.left != node.right) {
            res.add(node);
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return res;
    }

    public static List<BinaryTreeNode<Integer>> getRight(BinaryTreeNode<Integer> node) {
        final LinkedList<BinaryTreeNode<Integer>> res = new LinkedList<>();
        while (node != null && node.left != node.right) {
            res.addFirst(node);
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }

        }
        return res;
    }

    public static List<BinaryTreeNode<Integer>> exteriorBinaryTree2(BinaryTreeNode<Integer> tree) {
        final List<BinaryTreeNode<Integer>> exterior = new LinkedList<>();
        if (tree != null) {
            exterior.add(tree);
            exterior.addAll(leftBoundaryAndLeaves(tree.left, true));
            exterior.addAll(rightBoundaryAndLeaves(tree.right, true));
        }
        return exterior;
    }

    private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeaves(BinaryTreeNode<Integer> subtreeRoot,
                                                                       boolean isBoundary) {
        final List<BinaryTreeNode<Integer>> result = new LinkedList<>();
        if (subtreeRoot != null) {
            if (isBoundary || subtreeRoot.left == subtreeRoot.right) {
                result.add(subtreeRoot);
            }
            result.addAll(leftBoundaryAndLeaves(subtreeRoot.left, isBoundary));
            result.addAll(leftBoundaryAndLeaves(subtreeRoot.right, isBoundary && subtreeRoot.left == null));
        }
        return result;
    }

    private static List<BinaryTreeNode<Integer>> rightBoundaryAndLeaves(BinaryTreeNode<Integer> subtreeRoot,
                                                                        boolean isBoundary) {
        final List<BinaryTreeNode<Integer>> result = new LinkedList<>();
        if (subtreeRoot != null) {
            result.addAll(rightBoundaryAndLeaves(subtreeRoot.left, isBoundary && subtreeRoot.right == null));
            result.addAll(rightBoundaryAndLeaves(subtreeRoot.right, isBoundary));
            if (isBoundary || subtreeRoot.left == subtreeRoot.right) {
                result.add(subtreeRoot);
            }
        }
        return result;
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    public static List<Integer> exteriorBinaryTreeWrapper(TimedExecutor executor,
                                                          BinaryTreeNode<Integer> tree) throws Exception {
        final List<BinaryTreeNode<Integer>> result = executor.run(() -> exteriorBinaryTree(tree));

        if (result.contains(null)) {
            throw new TestFailure("Resulting list contains null");
        }

        return result.stream()
                .map(x -> x.data)
                .collect(Collectors.toList());
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    public static List<Integer> exteriorBinaryTreeWrapper2(TimedExecutor executor,
                                                           BinaryTreeNode<Integer> tree) throws Exception {
        final List<BinaryTreeNode<Integer>> result = executor.run(() -> exteriorBinaryTree2(tree));

        if (result.contains(null)) {
            throw new TestFailure("Resulting list contains null");
        }

        return result.stream()
                .map(x -> x.data)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeExterior() {
    }
}
