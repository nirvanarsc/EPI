package epi.Chapter14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class IntervalsUnion {

    public static class Interval {
        public Endpoint left = new Endpoint();
        public Endpoint right = new Endpoint();

        private static class Endpoint {
            public boolean isClosed;
            public int val;
        }

        @Override
        public String toString() {
            return left.val + " " + right.val;
        }
    }

    public static List<Interval> unionOfIntervals(List<Interval> intervals) {
        final Comparator<Interval> byValue = Comparator.comparingInt(a -> a.left.val);
        final Comparator<Interval> byRange = (a, b) -> Boolean.compare(b.left.isClosed, a.left.isClosed);
        intervals.sort(byValue.thenComparing(byRange));
        final List<Interval> res = new ArrayList<>();
        Interval curr = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (curr.right.val == intervals.get(i).left.val) {
                if (curr.right.isClosed || intervals.get(i).left.isClosed) {
                    curr.right = intervals.get(i).right;
                } else {
                    res.add(curr);
                    curr = intervals.get(i);
                }
            } else if (curr.right.val > intervals.get(i).left.val) {
                if (curr.right.val < intervals.get(i).right.val
                    || curr.right.val == intervals.get(i).right.val && intervals.get(i).right.isClosed) {
                    curr.right = intervals.get(i).right;
                }
            } else {
                res.add(curr);
                curr = intervals.get(i);
            }
        }
        res.add(curr);
        return res;
    }

    @EpiUserType(ctorParams = { int.class, boolean.class, int.class, boolean.class })
    public static class FlatInterval {
        int leftVal;
        boolean leftIsClosed;
        int rightVal;
        boolean rightIsClosed;

        public FlatInterval(int leftVal, boolean leftIsClosed, int rightVal, boolean rightIsClosed) {
            this.leftVal = leftVal;
            this.leftIsClosed = leftIsClosed;
            this.rightVal = rightVal;
            this.rightIsClosed = rightIsClosed;
        }

        public FlatInterval(Interval i) {
            if (i != null) {
                leftVal = i.left.val;
                leftIsClosed = i.left.isClosed;
                rightVal = i.right.val;
                rightIsClosed = i.right.isClosed;
            }
        }

        public Interval toInterval() {
            final Interval i = new Interval();
            i.left.val = leftVal;
            i.left.isClosed = leftIsClosed;
            i.right.val = rightVal;
            i.right.isClosed = rightIsClosed;
            return i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final FlatInterval that = (FlatInterval) o;

            if (leftVal != that.leftVal) {
                return false;
            }
            if (leftIsClosed != that.leftIsClosed) {
                return false;
            }
            if (rightVal != that.rightVal) {
                return false;
            }
            return rightIsClosed == that.rightIsClosed;
        }

        @Override
        public int hashCode() {
            int result = leftVal;
            result = 31 * result + (leftIsClosed ? 1 : 0);
            result = 31 * result + rightVal;
            result = 31 * result + (rightIsClosed ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return (leftIsClosed ? "<" : "(") + leftVal + ", " + rightVal + (rightIsClosed ? ">" : ")");
        }
    }

    @EpiTest(testDataFile = "intervals_union.tsv")
    public static List<FlatInterval> unionIntervalWrapper(TimedExecutor executor,
                                                          List<FlatInterval> intervals) throws Exception {
        final List<Interval> casted = new ArrayList<>(intervals.size());
        for (FlatInterval in : intervals) {
            casted.add(in.toInterval());
        }

        final List<Interval> result = executor.run(() -> unionOfIntervals(casted));

        intervals = new ArrayList<>(result.size());
        for (Interval i : result) {
            intervals.add(new FlatInterval(i));
        }
        return intervals;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntervalsUnion() {}
}
