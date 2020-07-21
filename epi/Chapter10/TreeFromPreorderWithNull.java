package epi.Chapter10;

import java.util.List;
import java.util.stream.Collectors;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

@SuppressWarnings("ReturnOfNull")
public final class TreeFromPreorderWithNull {

    static int idx;

    public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
        idx = 0;
        return dfs(preorder);
    }

    public static BinaryTreeNode<Integer> dfs(List<Integer> preorder) {
        final Integer root = preorder.get(idx++);
        if (root == null) {
            return null;
        }
        final BinaryTreeNode<Integer> left = dfs(preorder);
        final BinaryTreeNode<Integer> right = dfs(preorder);
        return new BinaryTreeNode<>(root, left, right);
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    public static BinaryTreeNode<Integer> reconstructPreorderWrapper(TimedExecutor executor,
                                                                     List<String> strings) throws Exception {
        return executor.run(() -> reconstructPreorder(getIntegerBinaryTreeNode(strings)));
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

    private TreeFromPreorderWithNull() {}
}
