package epi.Chapter15;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchFirstGreaterValueInBst {

    public static BstNode<Integer> helper(BstNode<Integer> tree, Integer k) {
        return helper(tree, k, null);
    }

    public static BstNode<Integer> helper(BstNode<Integer> tree, Integer k, BstNode<Integer> ans) {
        while (tree != null) {
            if (tree.data <= k) {
                tree = tree.right;
            } else {
                ans = tree;
                tree = tree.left;
            }
        }
        return ans;
    }

    @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree, Integer k) {
        final BstNode<Integer> result = helper(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchFirstGreaterValueInBst() {}
}
