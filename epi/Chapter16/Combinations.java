package epi.Chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class Combinations {

    @EpiTest(testDataFile = "combinations.tsv")
    public static List<List<Integer>> combinations(int n, int k) {
        final List<List<Integer>> res = new ArrayList<>();
        solve(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private static void solve(int start, int n, int k, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = start; i <= n; i++) {
                curr.add(i);
                solve(i + 1, n, k, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.COMP;

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Combinations() {}
}
