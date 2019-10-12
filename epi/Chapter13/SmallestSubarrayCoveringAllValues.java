package epi.Chapter13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.Subarray;
import epi.utils.TestRunner;

public final class SmallestSubarrayCoveringAllValues {

    public static Subarray findSmallestSequentiallyCoveringSubset(List<String> list, List<String> keywords) {
        final Subarray res = new Subarray(0, Integer.MAX_VALUE);

        for (int i = list.size() - 1; i >= 0; i--) {
            if (keywords.get(keywords.size() - 1).equals(list.get(i))) {
                final int start = check(i, list, keywords);
                if (start != Integer.MAX_VALUE && i - start < res.end - res.start) {
                    res.start = start;
                    res.end = i;
                }
            }
        }
        return res;
    }

    private static int check(int end, List<String> list, List<String> keywords) {
        final ListIterator<String> keys = keywords.listIterator(keywords.size());
        while (keys.hasPrevious() && end >= 0) {
            final String previous = keys.previous();
            while (end >= 0 && !list.get(end).equals(previous)) {
                end--;
            }
        }
        return keys.hasPrevious() || end < 0 ? Integer.MAX_VALUE : end;
    }

    public static Subarray findSmallestSequentiallyCoveringSubset2(List<String> list, List<String> keywords) {
        final Map<String, Integer> keysToIndex = new HashMap<>();
        final List<Integer> latestOccurrence = new ArrayList<>(keywords.size());
        final List<Integer> results = new ArrayList<>(keywords.size());

        for (int i = 0; i < keywords.size(); i++) {
            latestOccurrence.add(-1);
            results.add(Integer.MAX_VALUE);
            keysToIndex.put(keywords.get(i), i);
        }

        final Subarray result = new Subarray(-1, -1);
        int shortestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            final Integer keywordIdx = keysToIndex.get(list.get(i));
            if (keywordIdx != null) {
                if (keywordIdx == 0) {
                    results.set(0, 1);
                } else if (results.get(keywordIdx - 1) != Integer.MAX_VALUE) {
                    final int distanceToPreviousKeywords = i - latestOccurrence.get(keywordIdx - 1);
                    results.set(keywordIdx, distanceToPreviousKeywords + results.get(keywordIdx - 1));
                }
                latestOccurrence.set(keywordIdx, i);

                if (keywordIdx == keywords.size() - 1 && results.get(results.size() - 1) < shortestDistance) {
                    shortestDistance = results.get(results.size() - 1);
                    result.start = i - results.get(results.size() - 1) + 1;
                    result.end = i;
                }
            }
        }
        return result;
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
    public static int findSmallestSequentiallyCoveringSubsetWrapper(TimedExecutor executor,
                                                                    List<String> list,
                                                                    List<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSequentiallyCoveringSubset(list, keywords));
        return verify(list, keywords, result);
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
    public static int findSmallestSequentiallyCoveringSubsetWrapper2(TimedExecutor executor,
                                                                     List<String> list,
                                                                     List<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSequentiallyCoveringSubset2(list, keywords));
        return verify(list, keywords, result);
    }

    private static int verify(List<String> list, List<String> keywords, Subarray result)
            throws TestFailure {
        int kwIdx = 0;
        if (result.start < 0) {
            throw new TestFailure("Subarray start index is negative");
        }
        int paraIdx = result.start;

        while (kwIdx < keywords.size()) {
            if (paraIdx >= list.size()) {
                throw new TestFailure("Not all keywords are in the generated subarray");
            }
            if (list.get(paraIdx).equals(keywords.get(kwIdx))) {
                kwIdx++;
            }
            paraIdx++;
        }
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SmallestSubarrayCoveringAllValues() {}
}
