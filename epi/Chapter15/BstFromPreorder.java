package epi.Chapter15;

import java.util.List;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class BstFromPreorder {

    private static Integer rootIdx;

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        if (preorderSequence.isEmpty()) {
            return null;
        }
        final Integer rootData = preorderSequence.get(0);
        final BstNode<Integer> root = new BstNode<>(rootData);
        int i = 1;
        while (i < preorderSequence.size() && preorderSequence.get(i) < rootData) {
            i++;
        }

        root.left = rebuildBSTFromPreorder(preorderSequence.subList(1, i));
        root.right = rebuildBSTFromPreorder(preorderSequence.subList(i, preorderSequence.size()));

        return root;
    }

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder2(List<Integer> preorderSequence) {
        rootIdx = 0;
        return helper(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static BstNode<Integer> helper(List<Integer> preorderSequence, Integer lower, Integer upper) {
        if (rootIdx == preorderSequence.size()) {
            return null;
        }
        final Integer root = preorderSequence.get(rootIdx);
        if (root < lower || root > upper) {
            return null;
        }
        rootIdx++;

        final BstNode<Integer> leftSubtree = helper(preorderSequence, lower, root);
        final BstNode<Integer> rightSubtree = helper(preorderSequence, root, upper);
        return new BstNode<>(root, leftSubtree, rightSubtree);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BstFromPreorder() {}
}
