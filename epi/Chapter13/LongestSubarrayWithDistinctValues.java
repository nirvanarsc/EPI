package epi.Chapter13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestSubarrayWithDistinctValues {

    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    public static int longestSubarrayWithDistinctEntries(List<Integer> list) {
        if (list.size() <= 1) { return list.size(); }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            res = Math.max(res, check(i, list, new HashSet<>()));
        }
        return res;
    }

    private static int check(int start, List<Integer> list, Set<Integer> set) {
        for (int i = start; i < list.size(); i++) {
            if (set.contains(list.get(i))) {
                return i - start;
            }
            set.add(list.get(i));
        }
        return list.size() - start;
    }

    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    public static int longestSubarrayWithDistinctEntries2(List<Integer> list) {
        final Map<Integer, Integer> map = new HashMap<>();
        int currLongestStart = 0, res = 0;
        for (int i = 0; i < list.size(); i++) {
            final Integer dupIdx = map.put(list.get(i), i);
            if (dupIdx != null && dupIdx >= currLongestStart) {
                res = Math.max(res, i - currLongestStart);
                currLongestStart = dupIdx + 1;
            }
        }
        return Math.max(res, list.size() - currLongestStart);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestSubarrayWithDistinctValues() {}
}
