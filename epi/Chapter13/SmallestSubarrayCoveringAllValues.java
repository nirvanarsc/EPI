package epi.Chapter13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.Subarray;
import epi.utils.TestRunner;

public final class SmallestSubarrayCoveringAllValues {

    public static Subarray findSmallestSequentiallyCoveringSubset(List<String> list, List<String> keywords) {
        final Map<String, Integer> indexMap = new HashMap<>();
        final int n = keywords.size();
        final int[] latest = new int[n];
        final int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            latest[i] = -1;
            res[i] = Integer.MAX_VALUE;
            indexMap.put(keywords.get(i), i);
        }
        int end = -1;
        int minL = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            final Integer idx = indexMap.get(list.get(i));
            if (idx != null) {
                if (idx == 0) {
                    res[0] = 1;
                } else if (res[idx - 1] != Integer.MAX_VALUE) {
                    final int distanceToPreviousKeywords = i - latest[idx - 1];
                    res[idx] = distanceToPreviousKeywords + res[idx - 1];
                }
                latest[idx] = i;
                if (idx == n - 1 && res[n - 1] < minL) {
                    minL = res[n - 1];
                    end = i;
                }
            }
        }
        return new Subarray(end - minL + 1, end);
    }

    @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
    public static int findSmallestSequentiallyCoveringSubsetWrapper(TimedExecutor executor,
                                                                    List<String> list,
                                                                    List<String> keywords) throws Exception {
        final Subarray result = executor.run(() -> findSmallestSequentiallyCoveringSubset(list, keywords));
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
