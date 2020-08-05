package epi.Chapter15;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import epi.BinaryTreeNode;
import epi.Chapter10.TreeInorder;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsTreeABst {

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return dfs(tree, null, null);
    }

    private static boolean dfs(BinaryTreeNode<Integer> tree, Integer lo, Integer hi) {
        if (tree == null) {
            return true;
        }
        return (lo == null || lo <= tree.data)
               && (hi == null || tree.data <= hi)
               && dfs(tree.left, lo, tree.data)
               && dfs(tree.right, tree.data, hi);
    }

    private static class Pair {
        BinaryTreeNode<Integer> node;
        Integer lo, hi;

        Pair(BinaryTreeNode<Integer> node, Integer lo, Integer hi) {
            this.node = node;
            this.lo = lo;
            this.hi = hi;
        }
    }

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBSTBFS(BinaryTreeNode<Integer> tree) {
        final Deque<Pair> q = new ArrayDeque<>();
        if (tree != null) {
            q.offerLast(new Pair(tree, null, null));
        }
        while (!q.isEmpty()) {
            final Pair curr = q.removeFirst();
            if ((curr.lo != null && curr.node.data < curr.lo)
                || (curr.hi != null && curr.node.data > curr.hi)) {
                return false;
            }
            if (curr.node.left != null) { q.offerLast(new Pair(curr.node.left, curr.lo, curr.node.data)); }
            if (curr.node.right != null) { q.offerLast(new Pair(curr.node.right, curr.node.data, curr.hi)); }
        }
        return true;
    }

    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST_inOrder(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return true;
        }
        final List<Integer> integers = TreeInorder.inorderTraversalIterative(tree);
        Integer prev = integers.get(0);
        for (int i = 1; i < integers.size(); i++) {
            if (integers.get(i) < prev) {
                return false;
            }
            prev = integers.get(i);
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsTreeABst() {}
}
