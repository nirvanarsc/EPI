package epi.Chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class TreeConnectLeaves {

    public static List<BinaryTreeNode<Integer>> createListOfLeaves(BinaryTreeNode<Integer> tree) {
        final List<BinaryTreeNode<Integer>> res = new ArrayList<>();
        dfs(tree, res);
        return res;
    }

    private static void dfs(BinaryTreeNode<Integer> tree, List<BinaryTreeNode<Integer>> list) {
        if (tree == null) {
            return;
        }
        dfs(tree.left, list);
        if (tree.left == null && tree.right == null) {
            list.add(tree);
        }
        dfs(tree.right, list);
    }

    @EpiTest(testDataFile = "tree_connect_leaves.tsv")
    public static List<Integer> createListOfLeavesWrapper(TimedExecutor executor,
                                                          BinaryTreeNode<Integer> tree) throws Exception {
        final List<BinaryTreeNode<Integer>> result = executor.run(() -> createListOfLeaves(tree));

        if (result.stream().anyMatch(x -> x == null || x.data == null)) {
            throw new TestFailure("Result can't contain null");
        }

        return result.stream()
                     .map(x -> x.data)
                     .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeConnectLeaves() {}
}
