package epi.Chapter9;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.List;

public final class TreeLevelOrder {

    @EpiTest(testDataFile = "tree_level_order.tsv")
    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        // TODO - you fill in here.
        return null;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeLevelOrder() {
    }
}
