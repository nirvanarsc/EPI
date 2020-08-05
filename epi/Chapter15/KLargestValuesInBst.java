package epi.Chapter15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.BiPredicate;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class KLargestValuesInBst {

    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
        final List<Integer> vals = new ArrayList<>();
        reverseInOrder(tree, vals, k);
        return vals;
    }

    private static void reverseInOrder(BstNode<Integer> tree, List<Integer> res, int k) {
        if (tree == null || res.size() == k) {
            return;
        }
        reverseInOrder(tree.right, res, k);
        if (res.size() < k) {
            res.add(tree.data);
        }
        reverseInOrder(tree.left, res, k);
    }

    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    public static List<Integer> findKLargestInBstIterative(BstNode<Integer> tree, int k) {
        final Deque<Integer> res = new ArrayDeque<>();
        final Deque<BstNode<Integer>> stack = new ArrayDeque<>();
        BstNode<Integer> curr = tree;

        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }

            curr = stack.removeFirst();
            res.addFirst(curr.data);
            if (res.size() > k) {
                res.removeLast();
            }
            curr = curr.right;
        }

        return new ArrayList<>(res);
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = TestRunner.getSimpleComp();

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KLargestValuesInBst() {}
}
