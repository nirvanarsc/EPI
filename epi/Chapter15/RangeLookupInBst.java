package epi.Chapter15;

import java.util.ArrayList;
import java.util.List;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.utils.Interval;
import epi.utils.TestRunner;

public final class RangeLookupInBst {

    @EpiTest(testDataFile = "range_lookup_in_bst.tsv")
    public static List<Integer> rangeLookupInBst(BstNode<Integer> tree, Interval interval) {
        final ArrayList<Integer> res = new ArrayList<>();
        rangeLookupInBstHelper(tree, interval, res);
        return res;
    }

    public static void rangeLookupInBstHelper(BstNode<Integer> tree, Interval interval, List<Integer> result) {
        if (tree == null) {
            return;
        }
        if (interval.left <= tree.data && tree.data <= interval.right) {
            rangeLookupInBstHelper(tree.left, interval, result);
            result.add(tree.data);
            rangeLookupInBstHelper(tree.right, interval, result);
        } else if (interval.left > tree.data) {
            rangeLookupInBstHelper(tree.right, interval, result);
        } else {
            rangeLookupInBstHelper(tree.left, interval, result);
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RangeLookupInBst() {}
}
