package epi.Chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class TreeExterior {

    public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
        final List<BinaryTreeNode<Integer>> res = new ArrayList<>();
        if (tree != null) {
            res.add(tree);
            getLeft(tree.left, res, true);
            getRight(tree.right, res, true);
        }
        return res;
    }

    private static void getLeft(BinaryTreeNode<Integer> node,
                                List<BinaryTreeNode<Integer>> res,
                                boolean isBoundary) {
        if (node == null) {
            return;
        }
        if (isBoundary || node.left == node.right) {
            res.add(node);
        }
        getLeft(node.left, res, isBoundary);
        getLeft(node.right, res, isBoundary && node.left == null);
    }

    private static void getRight(BinaryTreeNode<Integer> node,
                                 List<BinaryTreeNode<Integer>> res,
                                 boolean isBoundary) {
        if (node == null) {
            return;
        }
        getRight(node.left, res, isBoundary && node.right == null);
        getRight(node.right, res, isBoundary);
        if (isBoundary || node.left == node.right) {
            res.add(node);
        }
    }

    @EpiTest(testDataFile = "tree_exterior.tsv")
    public static List<Integer> exteriorBinaryTreeWrapper(TimedExecutor executor,
                                                          BinaryTreeNode<Integer> tree) throws Exception {
        final List<BinaryTreeNode<Integer>> result = executor.run(() -> exteriorBinaryTree(tree));

        if (result.contains(null)) {
            throw new TestFailure("Resulting list contains null");
        }

        return result.stream()
                     .map(node -> node.data)
                     .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeExterior() {}
}
