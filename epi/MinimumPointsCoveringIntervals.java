package epi;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.Interval;
import epi.utils.TestRunner;

public final class MinimumPointsCoveringIntervals {

    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
    public static Integer findMinimumVisits(List<Interval> intervals) {
        // TODO - you fill in here.
        return 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumPointsCoveringIntervals() {}
}
