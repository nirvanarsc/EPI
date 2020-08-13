package epi.Chapter16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class EnumerateTrees {

    public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int numNodes) {
        if (numNodes == 0) {
            return Collections.singletonList(null);
        }
        final List<BinaryTreeNode<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            final List<BinaryTreeNode<Integer>> l = generateAllBinaryTrees(i);
            final List<BinaryTreeNode<Integer>> r = generateAllBinaryTrees(numNodes - i - 1);
            for (BinaryTreeNode<Integer> left : l) {
                for (BinaryTreeNode<Integer> right : r) {
                    res.add(new BinaryTreeNode<>(1, left, right));
                }
            }
        }
        return res;
    }

    @EpiTest(testDataFile = "enumerate_trees.tsv")
    public static List<List<Integer>> generateAllBinaryTreesWrapper(TimedExecutor executor,
                                                                    int numNodes) throws Exception {
        return executor.run(() -> generateAllBinaryTrees(numNodes))
                       .stream()
                       .map(BinaryTreeNode::serialize)
                       .sorted(new LexicographicalListComparator<>())
                       .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EnumerateTrees() {}
}
