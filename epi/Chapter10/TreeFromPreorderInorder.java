package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TreeFromPreorderInorder {

    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.isEmpty() || inorder.isEmpty()) {
            return null;
        }

        final int rootIndex = inorder.indexOf(preorder.get(0));
        final List<Integer> leftInorder = inorder.subList(0, rootIndex);
        final List<Integer> rightInOrder = inorder.subList(rootIndex + 1, inorder.size());
        final List<Integer> leftPreorder = preorder.subList(1, rootIndex + 1);
        final List<Integer> rightPreorder = preorder.subList(rootIndex + 1, preorder.size());

        return new BinaryTreeNode<>(inorder.get(rootIndex),
                binaryTreeFromPreorderInorder(leftPreorder, leftInorder),
                binaryTreeFromPreorderInorder(rightPreorder, rightInOrder));
    }

    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    public static BinaryTreeNode<Integer> withHashMap(List<Integer> preorder, List<Integer> inorder) {
        final Map<Integer, Integer> rootIdxMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            rootIdxMap.put(inorder.get(i), i);
        }

        return helper(preorder, 0, preorder.size(), 0, inorder.size(), rootIdxMap);
    }

    public static BinaryTreeNode<Integer> helper(List<Integer> preorder,
                                                 int preorderStart,
                                                 int preorderEnd,
                                                 int inorderStart,
                                                 int inorderEnd, Map<Integer, Integer> rootIdxMap) {
        if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) {
            return null;
        }
        final int rootIndex = rootIdxMap.get(preorder.get(preorderStart));
        final int leftSubtreeSize = rootIndex - inorderStart;
        return new BinaryTreeNode<>(
                preorder.get(preorderStart),
                helper(preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize, inorderStart, rootIndex, rootIdxMap),
                helper(preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd, rootIndex + 1, inorderEnd, rootIdxMap));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeFromPreorderInorder() {
    }
}
