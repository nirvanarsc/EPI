package epi.Chapter15;

import java.util.ArrayList;
import java.util.List;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class RangeLookupInBst {

    @EpiUserType(ctorParams = { int.class, int.class })
    public static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    @EpiTest(testDataFile = "range_lookup_in_bst.tsv")
    public static List<Integer> rangeLookupInBst(BstNode<Integer> tree, Interval interval) {
        final List<Integer> res = new ArrayList<>();
        dfs(tree, interval.left, interval.right, res);
        return res;
    }

    private static void dfs(BstNode<Integer> tree, int lo, int hi, List<Integer> res) {
        if (tree == null) {
            return;
        }
        if (lo <= tree.data && tree.data <= hi) {
            dfs(tree.left, lo, hi, res);
            res.add(tree.data);
            dfs(tree.right, lo, hi, res);
        } else if (tree.data < hi) {
            dfs(tree.right, lo, hi, res);
        } else if (tree.data > lo) {
            dfs(tree.left, lo, hi, res);
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RangeLookupInBst() {}
}
