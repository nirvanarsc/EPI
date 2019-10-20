package epi.Chapter14;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class IntervalAdd {

    @EpiUserType(ctorParams = { int.class, int.class })
    public static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Interval interval = (Interval) o;

            if (left != interval.left) {
                return false;
            }
            return right == interval.right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + ']';
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }

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
