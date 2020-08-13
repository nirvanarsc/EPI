package epi.Chapter16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.Chapter6.NextPermutation;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class Permutations {

    @EpiTest(testDataFile = "permutations.tsv")
    public static List<List<Integer>> permutations(List<Integer> list) {
        final List<List<Integer>> res = new ArrayList<>();
        dfs(res, list, 0);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> curr, int idx) {
        if (idx == curr.size()) {
            res.add(new ArrayList<>(curr));
        }
        for (int i = idx; i < curr.size(); i++) {
            Collections.swap(curr, idx, i);
            dfs(res, curr, idx + 1);
            Collections.swap(curr, idx, i);
        }
    }

    @EpiTest(testDataFile = "permutations.tsv")
    public static List<List<Integer>> permutationsNP(List<Integer> list) {
        final List<List<Integer>> res = new ArrayList<>();
        Collections.sort(list);
        while (!list.isEmpty()) {
            res.add(new ArrayList<>(list));
            list = NextPermutation.nextPermutation(list);
        }
        return res;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.getComp(false);

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Permutations() {}
}
