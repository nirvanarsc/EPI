package epi.Chapter10;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.List;
import java.util.stream.Collectors;

public final class TreeFromPreorderWithNull {

    private static Integer subTreeIdx;

    public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
        if (preorder.get(0) == null) {
            preorder.remove(0);
            return null;
        }
        return new BinaryTreeNode<>(preorder.remove(0), reconstructPreorder(preorder), reconstructPreorder(preorder));
    }

    public static BinaryTreeNode<Integer> reconstructPreorder2(List<Integer> preorder) {
        subTreeIdx = 0;
        return helper(preorder);
    }

    public static BinaryTreeNode<Integer> helper(List<Integer> preorder) {
        final Integer root = preorder.get(subTreeIdx++);
        if (root == null) {
            return null;
        }

        return new BinaryTreeNode<>(root, helper(preorder), helper(preorder));
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    public static BinaryTreeNode<Integer> reconstructPreorderWrapper(TimedExecutor executor,
                                                                     List<String> strings) throws Exception {
        return executor.run(() -> reconstructPreorder(getIntegerBinaryTreeNode(strings)));
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    public static BinaryTreeNode<Integer> reconstructPreorderWrapper2(TimedExecutor executor,
                                                                      List<String> strings) throws Exception {
        return executor.run(() -> reconstructPreorder2(getIntegerBinaryTreeNode(strings)));
    }

    private static List<Integer> getIntegerBinaryTreeNode(List<String> strings) throws Exception {
        return strings
                .stream()
                .map(s -> "null".equals(s) ? null : Integer.parseInt(s))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TreeFromPreorderWithNull() {
    }
}
