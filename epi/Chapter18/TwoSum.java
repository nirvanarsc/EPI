package epi.Chapter18;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class TwoSum {

    @EpiTest(testDataFile = "two_sum.tsv")
    public static boolean hasTwoSumWalk(List<Integer> list, int t) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            if (list.get(start) + list.get(end) == t) {
                return true;
            } else if (list.get(start) + list.get(end) < t) {
                start++;
            } else {
                end--;
            }
        }

        return false;
    }

    @EpiTest(testDataFile = "two_sum.tsv")
    public static boolean hasTwoSumBinarySearch(List<Integer> list, int t) {
        for (int i = 0; i < list.size() && list.get(i) <= t / 2; i++) {
            if (Collections.binarySearch(list, t - list.get(i)) >= 0) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TwoSum() {}
}
