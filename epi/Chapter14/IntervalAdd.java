package epi.Chapter14;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.Interval;
import epi.utils.TestRunner;

public final class IntervalAdd {

    @EpiTest(testDataFile = "interval_add.tsv")
    public static List<Interval> addInterval(List<Interval> disjointIntervals, Interval newInterval) {
        int i = 0;
        while (i < disjointIntervals.size() && disjointIntervals.get(i).right < newInterval.left) {
            i++;
        }
        final List<Interval> res = new ArrayList<>(disjointIntervals.subList(0, i));
        while (i < disjointIntervals.size() && disjointIntervals.get(i).left <= newInterval.right) {
            newInterval.left = Math.min(newInterval.left, disjointIntervals.get(i).left);
            newInterval.right = Math.max(newInterval.right, disjointIntervals.get(i).right);
            ++i;
        }
        res.add(newInterval);
        res.addAll(disjointIntervals.subList(i, disjointIntervals.size()));

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntervalAdd() {}
}
