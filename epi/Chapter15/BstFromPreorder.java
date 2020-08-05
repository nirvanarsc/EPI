package epi.Chapter15;

import java.util.List;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings({ "ConstantConditions", "ReturnOfNull" })
public final class BstFromPreorder {

    static int idx;

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        idx = 0;
        return dfs(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static BstNode<Integer> dfs(List<Integer> preorder, Integer lo, Integer hi) {
        if (idx == preorder.size()) {
            return null;
        }
        if (!(lo <= preorder.get(idx) && preorder.get(idx) <= hi)) {
            return null;
        }
        final BstNode<Integer> root = new BstNode<>(preorder.get(idx++));
        root.left = dfs(preorder, lo, root.data);
        root.right = dfs(preorder, root.data, hi);
        return root;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BstFromPreorder() {}
}
