package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.List;

public final class AdvanceByOffsets {

    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int currMax = 0;
        for (int j = 0; j < maxAdvanceSteps.size() && j <= currMax; j++) {
            currMax = Math.max(currMax, j + maxAdvanceSteps.get(j));
        }
        return currMax >= maxAdvanceSteps.size() - 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private AdvanceByOffsets() {}
}
