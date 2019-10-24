package epi.Chapter15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class KLargestValuesInBst {

    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
        final List<Integer> kLargestElements = new ArrayList<>();
        findKLargestInBSTHelper(tree, k, kLargestElements);
        return kLargestElements;
    }

    private static void findKLargestInBSTHelper(BstNode<Integer> tree, int k, List<Integer> kLargestElements) {
        // Perform reverse inorder traversal
        if (tree != null && kLargestElements.size() < k) {
            findKLargestInBSTHelper(tree.right, k, kLargestElements);
            if (kLargestElements.size() < k) {
                kLargestElements.add(tree.data);
                findKLargestInBSTHelper(tree.left, k, kLargestElements);
            }
        }
    }

    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")
    public static List<Integer> findKLargestInBst2(BstNode<Integer> tree, int k) {
        return inorderTraversalIterative(tree, k);
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    };

    public static List<Integer> inorderTraversalIterative(BstNode<Integer> tree, int k) {
        final LinkedList<Integer> res = new LinkedList<>();
        final Deque<BstNode<Integer>> s = new LinkedList<>();
        BstNode<Integer> curr = tree;

        while (!s.isEmpty() || curr != null) {
            while (curr != null) {
                s.addFirst(curr);
                curr = curr.left;
            }

            curr = s.removeFirst();
            res.addFirst(curr.data);
            if (res.size() > k) {
                res.removeLast();
            }
            curr = curr.right;
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KLargestValuesInBst() {}
}
