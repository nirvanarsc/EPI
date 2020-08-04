package epi.Chapter14;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.Interval;
import epi.utils.TestRunner;

public final class IntervalAdd {

    @EpiTest(testDataFile = "interval_add.tsv")
    public static List<Interval> addInterval(List<Interval> disjointIntervals, Interval newInterval) {
        final List<Interval> res = new ArrayList<>();
        int i;
        for (i = 0; i < disjointIntervals.size(); i++) {
            final Interval curr = disjointIntervals.get(i);
            if (overlaps(newInterval, curr)) {
                newInterval.left = Math.min(newInterval.left, curr.left);
                newInterval.right = Math.max(newInterval.right, curr.right);
            } else if (curr.right < newInterval.left) {
                res.add(curr);
            } else {
                break;
            }
        }
        res.add(newInterval);
        res.addAll(disjointIntervals.subList(i, disjointIntervals.size()));
        return res;
    }

    private static boolean overlaps(Interval left, Interval right) {
        return !(left.right < right.left || right.right < left.left);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntervalAdd() {}
}
