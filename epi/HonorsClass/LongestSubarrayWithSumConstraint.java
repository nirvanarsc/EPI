package epi.HonorsClass;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestSubarrayWithSumConstraint {

    @EpiTest(testDataFile = "longest_subarray_with_sum_constraint.tsv")
    public static int findLongestSubarrayLessEqualK(List<Integer> list, int k) {
        final List<Integer> prefixSum = new ArrayList<>();
        int sum = 0;
        for (int num : list) {
            sum += num;
            prefixSum.add(sum);
        }

        if (prefixSum.get(prefixSum.size() - 1) <= k) {
            return list.size();
        }

        final List<Integer> minPrefixSum = new ArrayList<>(prefixSum);
        for (int i = minPrefixSum.size() - 2; i >= 0; --i) {
            minPrefixSum.set(i,
                             Math.min(minPrefixSum.get(i), minPrefixSum.get(i + 1)));
        }

        int a = 0, b = 0, maxLength = 0;
        while (a < list.size() && b < list.size()) {
            final int minCurrSum = a > 0 ? minPrefixSum.get(b) - prefixSum.get(a - 1)
                                         : minPrefixSum.get(b);
            if (minCurrSum <= k) {
                maxLength = Math.max(maxLength, b - a + 1);
                ++b;
            } else {
                ++a;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestSubarrayWithSumConstraint() {}
}
