package epi.Chapter14;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class IntervalsUnion {

    private static class Interval {
        public Endpoint left = new Endpoint();
        public Endpoint right = new Endpoint();

        Interval() { }

        Interval(Endpoint left, Endpoint right) {
            this.left = left;
            this.right = right;
        }
    }

    private static class Endpoint {
        public boolean isClosed;
        public int val;

        Endpoint() { }

        Endpoint(boolean isClosed, int val) {
            this.isClosed = isClosed;
            this.val = val;
        }
    }

    private static class Point {
        boolean isClosed;
        boolean isStart;
        int val;

        Point(boolean isClosed, boolean isStart, int val) {
            this.isClosed = isClosed;
            this.isStart = isStart;
            this.val = val;
        }
    }

    public static List<Interval> unionOfIntervals(List<Interval> intervals) {
        final List<Point> lineSweep = new ArrayList<>();
        for (Interval interval : intervals) {
            lineSweep.add(new Point(interval.left.isClosed, true, interval.left.val));
            lineSweep.add(new Point(interval.right.isClosed, false, interval.right.val));
        }
        lineSweep.sort((a, b) -> {
            if (a.val == b.val) {
                if (a.isStart && b.isStart) {
                    return Boolean.compare(b.isClosed, a.isClosed);
                } else if (!a.isStart && !b.isStart) {
                    return Boolean.compare(a.isClosed, b.isClosed);
                } else if (!a.isClosed && !b.isClosed) {
                    return Boolean.compare(a.isStart, b.isStart);
                } else {
                    return Boolean.compare(b.isStart, a.isStart);
                }
            }
            return Integer.compare(a.val, b.val);
        });
        final List<Interval> res = new ArrayList<>();
        int currOpen = 0;
        Endpoint currL = null;
        for (Point p : lineSweep) {
            currOpen += p.isStart ? 1 : -1;
            if (currL == null) {
                currL = new Endpoint(p.isClosed, p.val);
            }
            if (currOpen == 0) {
                res.add(new Interval(currL, new Endpoint(p.isClosed, p.val)));
                currL = null;
            }
        }
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
