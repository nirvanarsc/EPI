package epi.Chapter15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchFirstGreaterValueInBst {

    public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree, Integer k) {
        final List<BstNode<Integer>> res = new ArrayList<>(Collections.singletonList(null));
        dfs(tree, k, res);
        return res.get(0);
    }

    private static void dfs(BstNode<Integer> tree, Integer k, List<BstNode<Integer>> res) {
        if (tree == null) {
            return;
        }
        if (tree.data > k && (res.get(0) == null || tree.data < res.get(0).data)) {
            res.set(0, tree);
        }
        if (tree.data > k) {
            dfs(tree.left, k, res);
        } else {
            dfs(tree.right, k, res);
        }
    }

    @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree, Integer k) {
        final BstNode<Integer> result = findFirstGreaterThanK(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchFirstGreaterValueInBst() {}
}
