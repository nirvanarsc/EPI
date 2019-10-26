package epi.utils;

import java.util.Objects;

import epi.test_framework.EpiUserType;

@EpiUserType(ctorParams = { int.class, int.class })
public class Interval {
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
