package epi.Chapter18;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ThreeSum {

    @EpiTest(testDataFile = "three_sum.tsv")
    public static boolean hasThreeSum(List<Integer> list, int t) {
        Collections.sort(list);
        for (int i : list) {
            if (TwoSum.hasTwoSumWalk(list, t - i)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ThreeSum() {}
}
