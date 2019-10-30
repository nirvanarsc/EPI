package epi.Chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class EnumerateTrees {

    public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int numNodes) {
        final List<BinaryTreeNode<Integer>> res = new ArrayList<>();
        if (numNodes == 0) {
            res.add(null);
        }
        for (int i = 0; i < numNodes; i++) {
            final List<BinaryTreeNode<Integer>> left = generateAllBinaryTrees(i);
            final List<BinaryTreeNode<Integer>> right = generateAllBinaryTrees(numNodes - i - 1);
            for (BinaryTreeNode<Integer> l : left) {
                for (BinaryTreeNode<Integer> r : right) {
                    res.add(new BinaryTreeNode<>(0, l, r));
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
