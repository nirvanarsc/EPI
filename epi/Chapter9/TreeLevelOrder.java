package epi.Chapter9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TreeLevelOrder {

    @EpiTest(testDataFile = "tree_level_order.tsv")
    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        final Deque<BinaryTreeNode<Integer>> q = new ArrayDeque<>();
        if (tree != null) {
            q.offerLast(tree);
        }
        final List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            final List<Integer> curr = new ArrayList<>();
            for (int size = q.size(); size > 0; size--) {
                final BinaryTreeNode<Integer> currNode = q.removeFirst();
                curr.add(currNode.data);
                if (currNode.left != null) {
                    q.offerLast(currNode.left);
                }
                if (currNode.right != null) {
                    q.offerLast(currNode.right);
                }
            }
            res.add(curr);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeLevelOrder() {}
}
