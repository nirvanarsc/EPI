package epi.Chapter13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.Subarray;
import epi.utils.TestRunner;

public final class SmallestSubarrayCoveringSet {

    public static Subarray findSmallestSubarrayCoveringSetLHM(List<String> paragraph, Set<String> keywords) {
        final Map<String, Integer> dict = new LinkedHashMap<>();
        final Subarray res = new Subarray(-1, -1);
        for (int i = 0; i < paragraph.size(); i++) {
            final String s = paragraph.get(i);
            if (keywords.contains(s)) {
                // Updating the value of an existing key does not update the internal access queue.
                // Therefore we explicitly remove the existing entry with key s and then put (s,i).
                dict.remove(s);
                dict.put(s, i);
            }
            if (dict.size() == keywords.size()) {
                final Integer firstEntry = dict.entrySet().iterator().next().getValue();
                if ((res.start == -1 && res.end == -1) || i - firstEntry < res.end - res.start) {
                    res.start = firstEntry;
                    res.end = i;
                }
            }
        }
        return res;
    }

    public static Subarray findSmallestSubarrayCoveringSetSW(List<String> paragraph, Set<String> keywords) {
        final Map<String, Integer> count = new HashMap<>();
        for (String kw : keywords) {
            count.merge(kw, 1, Integer::sum);
        }
        int match = keywords.size();
        int j = 0;
        int minL = Integer.MAX_VALUE;
        int minStart = -1;
        for (int i = 0; i < paragraph.size(); i++) {
            if (count.merge(paragraph.get(i), -1, Integer::sum) >= 0) {
                match--;
            }
            while (match == 0) {
                if (i - j < minL) {
                    minL = i - j;
                    minStart = j;
                }
                if (count.merge(paragraph.get(j++), 1, Integer::sum) > 0) {
                    match++;
                }
            }
        }
        return new Subarray(minStart, minStart + minL);
    }

    public static Subarray findSmallestSubarrayCoveringSet(List<String> list, Set<String> keywords) {
        final Map<String, Integer> dict = new HashMap<>();
        final Subarray result = new Subarray(-1, -1);
        for (int left = 0, right = 0; right < list.size(); ++right) {
            if (keywords.contains(list.get(right))) {
                dict.merge(list.get(right), 1, Integer::sum);
            }
            while (dict.size() == keywords.size()) {
                if ((result.start == -1 && result.end == -1) || right - left < result.end - result.start) {
                    result.start = left;
                    result.end = right;
                }
                dict.computeIfPresent(list.get(left++), (k, v) -> v == 1 ? null : v - 1);
            }
        }
        return result;
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper(TimedExecutor executor,
                                                             List<String> list,
                                                             Set<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSubarrayCoveringSet(list, keywords));
        return verify(list, keywords, result);
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapperSW(TimedExecutor executor,
                                                               List<String> list,
                                                               Set<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSubarrayCoveringSetSW(list, keywords));
        return verify(list, keywords, result);
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapperLHM(TimedExecutor executor,
                                                                List<String> list,
                                                                Set<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSubarrayCoveringSetLHM(list, keywords));
        return verify(list, keywords, result);
    }

    private static int verify(List<String> list, Set<String> keywords, Subarray result) throws TestFailure {
        final Set<String> copy = new HashSet<>(keywords);

        if (result.start < 0 || result.start >= list.size() ||
            result.end < 0 || result.end >= list.size() ||
            result.start > result.end) { throw new TestFailure("Index out of range"); }

        for (int i = result.start; i <= result.end; i++) {
            copy.remove(list.get(i));
        }

        if (!copy.isEmpty()) { throw new TestFailure("Not all keywords are in the range"); }
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SmallestSubarrayCoveringSet() {}
}
