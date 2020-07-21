package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class LowestCommonAncestor {

    static class Pair {
        int matched;
        BinaryTreeNode<Integer> p;

        Pair(int matched, BinaryTreeNode<Integer> p) {
            this.matched = matched;
            this.p = p;
        }
    }

    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {
        return dfs(tree, node0, node1).p;
    }

    private static Pair dfs(BinaryTreeNode<Integer> t, BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
        if (t == null) {
            return new Pair(0, null);
        }
        final Pair left = dfs(t.left, a, b);
        if (left.matched == 2) {
            return left;
        }
        final Pair right = dfs(t.right, a, b);
        if (right.matched == 2) {
            return right;
        }
        final int nextMatches = (t == a ? 1 : 0) + (t == b ? 1 : 0) + left.matched + right.matched;
        return new Pair(nextMatches, t);
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTreeNode<Integer> tree,
                                 Integer key0,
                                 Integer key1) throws Exception {
        final BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        final BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);
        final BinaryTreeNode<Integer> result = executor.run(() -> LCA(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LowestCommonAncestor() {}
}
