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

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        final Set<BinaryTree<Integer>> set = new HashSet<>();
        while (node0 != null || node1 != null) {
            if (node0 != null) {
                if (!set.add(node0)) {
                    return node0;
                }
                node0 = node0.parent;
            }
            if (node1 != null) {
                if (!set.add(node1)) {
                    return node1;
                }
                node1 = node1.parent;
            }
        }

        throw new IllegalArgumentException("nodes are not in the same tree");
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

    private LowestCommonAncestorCloseAncestor() {}
}
