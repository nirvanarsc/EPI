package epi.Chapter10;

import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class SuccessorInTree {

    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        BinaryTree<Integer> c = node;
        while (node.parent != null) {
            node = node.parent;
            if (node.left == c) {
                return node;
            }
            c = node;
        }
        return null;
    }

    @EpiTest(testDataFile = "successor_in_tree.tsv")
    public static int findSuccessorWrapper(TimedExecutor executor,
                                           BinaryTree<Integer> tree,
                                           int nodeIdx) throws Exception {
        final BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);
        final BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

        return result == null ? -1 : result.data;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SuccessorInTree() {
    }
}
