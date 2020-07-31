package epi.Chapter13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestSubarrayWithDistinctValues {

    @SuppressWarnings("MethodParameterNamingConvention")
    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
        final Map<Integer, Integer> map = new HashMap<>();
        int j = 0, res = 0;
        for (int i = 0; i < A.size(); i++) {
            map.merge(A.get(i), 1, Integer::sum);
            while (map.get(A.get(i)) > 1) {
                map.merge(A.get(j++), -1, Integer::sum);
            }
            res = Math.max(res, i - j + 1); 
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestSubarrayWithDistinctValues() {}
}
