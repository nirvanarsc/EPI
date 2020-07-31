package epi.Chapter13;

import java.util.HashSet;
import java.util.Set;

import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class LowestCommonAncestorCloseAncestor {

    public static BinaryTree<Integer> lca(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        final Set<BinaryTree<Integer>> seen0 = new HashSet<>();
        final Set<BinaryTree<Integer>> seen1 = new HashSet<>();
        while (true) {
            if (node0 == node1) { return node0; }
            if (seen0.contains(node1)) { return node1; }
            if (seen1.contains(node0)) { return node0; }
            if (node0 != null) {
                seen0.add(node0);
                node0 = node0.parent;
            }
            if (node1 != null) {
                seen1.add(node1);
                node1 = node1.parent;
            }
        }
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTree<Integer> tree,
                                 Integer key0,
                                 Integer key1) throws Exception {
        final BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        final BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);
        final BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LowestCommonAncestorCloseAncestor() {}
}
