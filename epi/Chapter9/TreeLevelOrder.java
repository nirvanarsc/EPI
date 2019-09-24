package epi.Chapter9;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class TreeLevelOrder {

    @EpiTest(testDataFile = "tree_level_order.tsv")
    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        final List<List<Integer>> res = new ArrayList<>();
        final Deque<BinaryTreeNode<Integer>> q = new LinkedList<>();

        if (tree != null) {
            q.offerLast(tree);
        }
        while (!q.isEmpty()) {
            final int l = q.size();
            final List<Integer> level = new ArrayList<>();
            for (int i = 0; i < l; i++) {
                final BinaryTreeNode<Integer> curr = q.pollFirst();
                level.add(curr.data);
                if (curr.left != null) {
                    q.offerLast(curr.left);
                }
                if (curr.right != null) {
                    q.offerLast(curr.right);
                }
            }
            res.add(level);
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeLevelOrder() {
    }
}
