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
        if (list.size() == 1) {
            return new ArrayList<>(Collections.singletonList(list));
        }

        final List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            final List<Integer> integers = new ArrayList<>(list);
            final Integer remove = integers.remove(i);
            for (List<Integer> p : permutations(integers)) {
                final List<Integer> permutation = new ArrayList<>(Collections.singletonList(remove));
                permutation.addAll(p);
                res.add(permutation);
            }
        }

        return res;
    }

    @EpiTest(testDataFile = "permutations.tsv")
    public static List<List<Integer>> permutations2(List<Integer> list) {
        final List<List<Integer>> result = new ArrayList<>();
        directedPermutations(0, list, result);
        return result;
    }

    private static void directedPermutations(int i, List<Integer> list, List<List<Integer>> result) {
        if (i == list.size() - 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int j = i; j < list.size(); ++j) {
            Collections.swap(list, i, j);
            directedPermutations(i + 1, list, result);
            Collections.swap(list, i, j);
        }
    }

    @EpiTest(testDataFile = "permutations.tsv")
    public static List<List<Integer>> permutations3(List<Integer> list) {
        final List<List<Integer>> result = new ArrayList<>();
        Collections.sort(list);
        while (!list.isEmpty()) {
            result.add(new ArrayList<>(list));
            list = NextPermutation.nextPermutation(list);
        }
        return result;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.COMP;

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Permutations() {}
}
