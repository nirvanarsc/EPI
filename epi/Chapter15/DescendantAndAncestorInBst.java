package epi.Chapter15;

import epi.BstNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class DescendantAndAncestorInBst {

    public static boolean pairIncludesAncestorAndDescendantOfM(BstNode<Integer> node1,
                                                               BstNode<Integer> node2,
                                                               BstNode<Integer> mid) {
        BstNode<Integer> search0 = node1, search1 = node2;

        while (search0 != node2 && search0 != mid
               && search1 != node1 && search1 != mid
               && (search0 != null || search1 != null)) {
            if (search0 != null) {
                search0 = search0.data > mid.data ? search0.left : search0.right;
            }
            if (search1 != null) {
                search1 = search1.data > mid.data ? search1.left : search1.right;
            }
        }

        if (search0 == node2 || search1 == node1 || (search0 != mid && search1 != mid)) {
            return false;
        }

        return search0 == mid ? searchTarget(mid, node2) : searchTarget(mid, node1);
    }

    private static boolean searchTarget(BstNode<Integer> from, BstNode<Integer> target) {
        while (from != null && from != target) {
            from = from.data > target.data ? from.left : from.right;
        }
        return from == target;
    }

    @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
    public static boolean pairIncludesAncestorAndDescendantOfMWrapper(TimedExecutor executor,
                                                                      BstNode<Integer> tree,
                                                                      int possibleAncOrDesc0,
                                                                      int possibleAncOrDesc1,
                                                                      int middle) throws Exception {
        final BstNode<Integer> candidate0 = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
        final BstNode<Integer> candidate1 = BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
        final BstNode<Integer> middleNode = BinaryTreeUtils.mustFindNode(tree, middle);
        return executor.run(() -> pairIncludesAncestorAndDescendantOfM(candidate0, candidate1, middleNode));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DescendantAndAncestorInBst() {}
}
