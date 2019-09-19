package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.List;

public final class AdvanceByOffsets {

    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReach = 0;
        for (int i = 0; i < maxAdvanceSteps.size() && i <= furthestReach; i++) {
            furthestReach = Math.max(maxAdvanceSteps.get(i) + i, furthestReach);
        }

        return furthestReach >= maxAdvanceSteps.size() - 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private AdvanceByOffsets() {
    }
}
