package epi.Chapter10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TreeFromPreorderInorder {

    static int idx;

    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    public static BinaryTreeNode<Integer> buildTree(List<Integer> preorder, List<Integer> inorder) {
        idx = 0;
        final Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            idx.put(inorder.get(i), i);
        }
        return dfs(preorder, idx, 0, preorder.size() - 1);
    }

    @SuppressWarnings({ "ConstantConditions", "ReturnOfNull" })
    private static BinaryTreeNode<Integer> dfs(List<Integer> preorder, Map<Integer, Integer> in, int l, int r) {
        if (l > r) {
            return null;
        }
        final int root = preorder.get(idx++);
        final BinaryTreeNode<Integer> res = new BinaryTreeNode<>(root);
        res.left = dfs(preorder, in, l, in.get(root) - 1);
        res.right = dfs(preorder, in, in.get(root) + 1, r);
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeFromPreorderInorder() {}
}
