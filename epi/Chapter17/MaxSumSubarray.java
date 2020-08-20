package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MaxSumSubarray {

    @EpiTest(testDataFile = "max_sum_subarray.tsv")
    public static int findMaximumSubarray(List<Integer> list) {
        int minSum = 0, sum = 0, maxSum = 0;
        for (int num : list) {
            sum += num;
            minSum = Math.min(minSum, sum);
            maxSum = Math.max(maxSum, sum - minSum);
        }
        return maxSum;
    }

    @EpiTest(testDataFile = "max_sum_subarray.tsv")
    public static int kadane(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        int curr = list.get(0);
        int res = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            curr = Math.max(list.get(i), curr + list.get(i));
            res = Math.max(res, curr);
        }
        return Math.max(res, 0);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MaxSumSubarray() {}
}
