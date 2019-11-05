package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MaxSumSubarray {

    @EpiTest(testDataFile = "max_sum_subarray.tsv")
    public static int findMaximumSubarray(List<Integer> list) {
        int minSum = 0, sum = 0, maxSum = 0;
        for (int i : list) {
            sum += i;
            minSum = Math.min(sum, minSum);
            maxSum = Math.max(sum - minSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MaxSumSubarray() {}
}
