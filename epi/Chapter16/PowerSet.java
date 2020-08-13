package epi.Chapter16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class PowerSet {

    private static final double LOG_2 = Math.log(2);

    @EpiTest(testDataFile = "power_set.tsv")
    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        final List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1 << inputSet.size(); i++) {
            final List<Integer> curr = new ArrayList<>();
            for (int k = i, j = 0; k != 0; j++, k >>= 1) {
                if ((k & 1) == 1) {
                    curr.add(inputSet.get(j));
                }
            }
            res.add(curr);
        }
        return res;
    }

    @EpiTest(testDataFile = "power_set.tsv")
    public static List<List<Integer>> generatePowerSetLSB(List<Integer> inputSet) {
        final List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1 << inputSet.size(); i++) {
            final List<Integer> curr = new ArrayList<>();
            int word = i;
            while (word > 0) {
                curr.add(inputSet.get((int) (Math.log(lsb(word)) / LOG_2)));
                word -= lsb(word);
            }
            res.add(curr);
        }
        return res;
    }

    @EpiTest(testDataFile = "power_set.tsv")
    public static List<List<Integer>> generatePowerSetDFS(List<Integer> inputSet) {
        Collections.sort(inputSet);
        final List<List<Integer>> powerSet = new ArrayList<>();
        dfs(0, inputSet, new ArrayList<>(), powerSet);
        return powerSet;
    }

    private static void dfs(int start, List<Integer> input, List<Integer> curr, List<List<Integer>> res) {
        if (start == input.size()) {
            res.add(new ArrayList<>(curr));
            return;
        }
        res.add(new ArrayList<>(curr));
        for (int i = start; i < input.size(); i++) {
            // duplicates
            if (i > start && input.get(i).equals(input.get(i - 1))) { continue; }
            curr.add(input.get(i));
            dfs(i + 1, input, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    private static int lsb(int i) {
        return i & -i;  // zeroes all the bits except the least significant one
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.getComp(true);

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PowerSet() {}
}
