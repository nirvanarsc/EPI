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
import epi.utils.TestRunner;

public final class SmallestSubarrayCoveringSet {

    private static final class Subarray {
        public Integer start;
        public Integer end;

        private Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Subarray findSmallestSubarrayCoveringSet3(Iterator<String> iter, Set<String> queryStrings) {
        final LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();
        final Subarray res = new Subarray(-1, -1);
        for (String s : queryStrings) { dict.put(s, -1); }
        int numStringsFromQueryStringsSeenSoFar = 0;
        int idx = 0;
        while (iter.hasNext()) {
            final String s = iter.next();
            if (dict.containsKey(s)) {
                final Integer it = dict.get(s);
                if (it == -1) {
                    numStringsFromQueryStringsSeenSoFar++;
                }
                // dict.put(s,idx) won't work because it does not move the entry to the front of the queue
                // if an entry with key s is already present.
                // So we explicitly remove the existing entry with key s, then put (s,idx).
                dict.remove(s);
                dict.put(s, idx);
            }
            if (numStringsFromQueryStringsSeenSoFar == queryStrings.size()) {
                // We have seen all strings in queryStrings, let’s get to work.
                if ((res.start == -1 && res.end == -1)
                    || idx - getValueForFirstEntry(dict) < res.end - res.start) {
                    res.start = getValueForFirstEntry(dict);
                    res.end = idx;
                }
            }
            ++idx;
        }
        return res;
    }

    private static Integer getValueForFirstEntry(LinkedHashMap<String, Integer> m) {
        return m.entrySet().iterator().next().getValue();
    }

    public static Subarray findSmallestSubarrayCoveringSet2(List<String> list, Set<String> keywords) {
        final Map<String, Integer> keywordsToCover = new HashMap<>();
        final Subarray result = new Subarray(-1, -1);
        for (String keyword : keywords) {
            keywordsToCover.merge(keyword, 1, Integer::sum);
        }

        int remainingToCover = keywords.size();
        for (int left = 0, right = 0; right < list.size(); ++right) {
            Integer keywordCount = keywordsToCover.get(list.get(right));
            if (keywordCount != null) {
                keywordsToCover.put(list.get(right), --keywordCount);
                if (keywordCount >= 0) {
                    --remainingToCover;
                }
            }
            while (remainingToCover == 0) {
                if ((result.start == -1 && result.end == -1) || right - left < result.end - result.start) {
                    result.start = left;
                    result.end = right;
                }
                keywordCount = keywordsToCover.get(list.get(left));
                if (keywordCount != null) {
                    keywordsToCover.put(list.get(left), ++keywordCount);
                    if (keywordCount > 0) {
                        ++remainingToCover;
                    }
                }
                ++left;
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
