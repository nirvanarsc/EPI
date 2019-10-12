package epi.Chapter13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

    public static Subarray findSmallestSubarrayCoveringSet3(Iterator<String> iter, Set<String> keywords) {
        final Map<String, Integer> dict = new LinkedHashMap<>();
        final Subarray res = new Subarray(-1, -1);
        int idx = 0;
        while (iter.hasNext()) {
            final String s = iter.next();
            if (keywords.contains(s)) {
                // Updating the value of an existing key does not update the internal access queue.
                // Therefore we explicitly remove the existing entry with key s and then put (s,idx).
                dict.computeIfPresent(s, (k, v) -> null);
                dict.put(s, idx);
            }
            if (dict.size() == keywords.size()) {
                final Integer firstEntry = dict.entrySet().iterator().next().getValue();
                if ((res.start == -1 && res.end == -1) || idx - firstEntry < res.end - res.start) {
                    res.start = firstEntry;
                    res.end = idx;
                }
            }
            ++idx;
        }
        return res;
    }

    public static Subarray findSmallestSubarrayCoveringSet2(List<String> list, Set<String> keywords) {
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

    public static Subarray findSmallestSubarrayCoveringSet(List<String> list, Set<String> keywords) {
        final Subarray res = new Subarray(0, Integer.MAX_VALUE);
        for (int i = 0; i < list.size(); i++) {
            if (keywords.contains(list.get(i))) {
                final int newEnd = check(i, list, new HashSet<>(keywords));
                if (newEnd != Integer.MAX_VALUE && res.end - res.start > newEnd - i) {
                    res.start = i;
                    res.end = newEnd;
                }
            }
        }
        return res;
    }

    private static int check(int start, List<String> list, Set<String> keywords) {
        for (int i = start; i < list.size(); i++) {
            if (keywords.remove(list.get(i)) && keywords.isEmpty()) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper(TimedExecutor executor,
                                                             List<String> list,
                                                             Set<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSubarrayCoveringSet(list, keywords));
        return verify(list, keywords, result);
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper2(TimedExecutor executor,
                                                              List<String> list,
                                                              Set<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSubarrayCoveringSet2(list, keywords));
        return verify(list, keywords, result);
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper3(TimedExecutor executor,
                                                              List<String> list,
                                                              Set<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSubarrayCoveringSet3(list.iterator(), keywords));
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
