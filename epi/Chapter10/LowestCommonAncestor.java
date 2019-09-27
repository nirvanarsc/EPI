package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class LowestCommonAncestor {

    static class Status {
        int targetNodes;
        BinaryTreeNode<Integer> ancestor;

        Status(int targetNodes, BinaryTreeNode<Integer> ancestor) {
            this.targetNodes = targetNodes;
            this.ancestor = ancestor;
        }
    }

    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {
        return helper(tree, node0, node1).ancestor;
    }

    public static Status helper(BinaryTreeNode<Integer> tree,
                                BinaryTreeNode<Integer> node0,
                                BinaryTreeNode<Integer> node1) {
        if (tree == null) {
            return new Status(0, null);
        }

        final Status left = helper(tree.left, node0, node1);
        if (left.targetNodes == 2) {
            return left;
        }

        final Status right = helper(tree.right, node0, node1);
        if (right.targetNodes == 2) {
            return right;
        }

        final int targetNodes = left.targetNodes + right.targetNodes + (tree == node0 ? 1 : 0) + (tree == node1 ? 1 : 0);
        return new Status(targetNodes, targetNodes == 2 ? tree : null);
    }

//    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
//                                              BinaryTreeNode<Integer> node0,
//                                              BinaryTreeNode<Integer> node1) {
//        if (isParent(node0, node1)) return node0;
//        if (isParent(node1, node0)) return node1;
//
//        final Deque<BinaryTreeNode<Integer>> q = new LinkedList<>();
//        q.offerLast(tree);
//        BinaryTreeNode<Integer> res = tree;
//        while (!q.isEmpty()) {
//            final BinaryTreeNode<Integer> curr = q.pollFirst();
//            if (isParent(curr, node0) && isParent(curr, node1)) res = curr;
//            if (curr.left != null) q.offerLast(curr.left);
//            if (curr.right != null) q.offerLast(curr.right);
//        }
//        return res;
//    }
//
//    public static boolean isParent(BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> c) {
//        if (p == null) {
//            return false;
//        }
//        if (p == c) {
//            return true;
//        }
//
//        return isParent(p.left, c) || isParent(p.right, c);
//    }

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

    private LowestCommonAncestor() {
    }
}
