package epi.Chapter13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NearestRepeatedEntries {

    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    public static int findNearestRepetition(List<String> paragraph) {
        int ans = Integer.MAX_VALUE;
        final Map<String, Integer> distance = new HashMap<>();
        for (int i = 0; i < paragraph.size(); i++) {
            final String curr = paragraph.get(i);
            if (distance.containsKey(curr)) {
                ans = Math.min(ans, i - distance.get(curr));
            }
            distance.put(curr, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NearestRepeatedEntries() {}
}
