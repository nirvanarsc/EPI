package epi.Chapter13;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestContainedInterval {

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange(List<Integer> list) {
        final Set<Integer> map = new HashSet<>();
        int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
        int res = 0, curr = 0;
        for (Integer i : list) {
            maxVal = Math.max(maxVal, i);
            minVal = Math.min(minVal, i);
            map.add(i);
        }
        for (int i = minVal; i <= maxVal; i++) {
            if (map.contains(i)) {
                curr++;
            }
            if (!map.contains(i) || i == maxVal) {
                res = Math.max(res, curr);
                curr = 0;
            }
        }
        return res;
    }

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange2(List<Integer> list) {
        final Set<Integer> map = new HashSet<>(list);
        int res = 0;
        while (!map.isEmpty()) {
            final Integer next = map.iterator().next();
            int currKeyUp = next + 1, currKeyDown = next - 1;
            while (map.contains(currKeyUp)) {
                map.remove(currKeyUp);
                currKeyUp++;

            }
            while (map.contains(currKeyDown)) {
                map.remove(currKeyDown);
                currKeyDown--;
            }
            map.remove(next);
            res = Math.max(res, currKeyUp - currKeyDown - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestContainedInterval() {}
}
