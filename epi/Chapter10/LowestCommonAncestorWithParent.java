package epi.Chapter10;

import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        int d0 = getDepth(node0);
        int d1 = getDepth(node1);

        while (d0 < d1) {
            d1--;
            node1 = node1.parent;
        }

        while (d0 > d1) {
            d0--;
            node0 = node0.parent;
        }

        while (node0 != node1) {
            node0 = node0.parent;
            node1 = node1.parent;
        }
        return node0;
    }

    private static int getDepth(BinaryTree<Integer> node) {
        int i = 0;
        while (node.parent != null) {
            node = node.parent;
            i++;
        }
        return i;
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

    private LowestCommonAncestorWithParent() {
    }
}
