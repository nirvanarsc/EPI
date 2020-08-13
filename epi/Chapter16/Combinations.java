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
        dfs(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(int start, int n, int k, List<Integer> curr, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            curr.add(i);
            dfs(i + 1, n, k - 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.getComp(false);

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Combinations() {}
}
