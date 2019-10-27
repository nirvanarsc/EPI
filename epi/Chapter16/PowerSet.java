package epi.Chapter16;

import java.util.ArrayList;
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
    public static List<List<Integer>> generatePowerSet2(List<Integer> inputSet) {
        final List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1 << inputSet.size(); i++) {
            final List<Integer> curr = new ArrayList<>();
            for (int bitArray = i; bitArray != 0; bitArray &= bitArray - 1) {
                curr.add(inputSet.get((int) (Math.log(bitArray & -bitArray) / LOG_2)));
            }
            res.add(curr);
        }
        return res;
    }

    @EpiTest(testDataFile = "power_set.tsv")
    public static List<List<Integer>> generatePowerSet3(List<Integer> inputSet) {
        final List<List<Integer>> powerSet = new ArrayList<>();
        directedPowerSet(0, inputSet, new ArrayList<>(), powerSet);
        return powerSet;
    }

    private static void directedPowerSet(int toBeSelected,
                                         List<Integer> inputSet,
                                         List<Integer> selectedSoFar,
                                         List<List<Integer>> powerSet) {
        if (toBeSelected == inputSet.size()) {
            powerSet.add(new ArrayList<>(selectedSoFar));
            return;
        }
        // Generate all subsets that contain inputSet[toBeSelected].
        selectedSoFar.add(inputSet.get(toBeSelected));
        directedPowerSet(toBeSelected + 1, inputSet, selectedSoFar, powerSet);
        // Generate all subsets that do not contain inputSet[toBeSelected].
        selectedSoFar.remove(selectedSoFar.size() - 1);
        directedPowerSet(toBeSelected + 1, inputSet, selectedSoFar, powerSet);
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.NESTED_COMP;

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PowerSet() {}
}
