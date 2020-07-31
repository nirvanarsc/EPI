package epi.Chapter13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NearestRepeatedEntries {

    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    public static int findNearestRepetition(List<String> paragraph) {
        final Map<String, Integer> map = new HashMap<>();
        final int n = paragraph.size();
        int res = n;
        for (int i = 0; i < n; i++) {
            final String word = paragraph.get(i);
            res = Math.min(res, i - map.getOrDefault(word, -n));
            map.put(word, i);
        }
        return res == n ? -1 : res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NearestRepeatedEntries() {}
}
