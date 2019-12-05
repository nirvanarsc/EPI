package epi.Chapter18;

import java.util.Comparator;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.Interval;
import epi.utils.TestRunner;

public final class MinimumPointsCoveringIntervals {

    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
    public static Integer findMinimumVisits(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return 0;
        }
        intervals.sort(Comparator.comparingInt(a -> a.right));

        int res = 1;
        int prev = intervals.get(0).right;

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).left > prev) {
                res++;
                prev = intervals.get(i).right;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumPointsCoveringIntervals() {}
}
