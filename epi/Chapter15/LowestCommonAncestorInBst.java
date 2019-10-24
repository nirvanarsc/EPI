package epi.Chapter15;

import epi.BstNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class LowestCommonAncestorInBst {

    // Input nodes are nonempty and the key at s is less than the one at b.
    public static BstNode<Integer> findLCA(BstNode<Integer> tree,
                                           BstNode<Integer> s,
                                           BstNode<Integer> b) {
        while (true) {
            if (s.data <= tree.data && tree.data <= b.data) {
                return tree;
            } else if (s.data > tree.data && b.data > tree.data) {
                tree = tree.right;
            } else {
                tree = tree.left;
            }
        }
    }

    @EpiTest(testDataFile = "lowest_common_ancestor_in_bst.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BstNode<Integer> tree,
                                 Integer key0,
                                 Integer key1) throws Exception {
        final BstNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        final BstNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);
        final BstNode<Integer> result = executor.run(() -> findLCA(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can't be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LowestCommonAncestorInBst() {}
}
