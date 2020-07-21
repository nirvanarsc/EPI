package epi.Chapter10;

import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        final int d1 = getD(node0);
        final int d2 = getD(node1);
        for (int i = 0; i < Math.abs(d1 - d2); i++) {
            if (d1 > d2) {
                node0 = node0.parent;
            } else {
                node1 = node1.parent;
            }
        }
        while (node0 != node1) {
            node0 = node0.parent;
            node1 = node1.parent;
        }
        return node0;
    }

    private static int getD(BinaryTree<Integer> node) {
        if (node == null) {
            return 0;
        }
        return 1 + getD(node.parent);
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTree<Integer> tree,
                                 Integer key0,
                                 Integer key1) throws Exception {
        final BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        final BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);
        final BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LowestCommonAncestorWithParent() {}
}
