package epi.Chapter12;

import static epi.Chapter12.RealSquareRoot.Ordering.EQUAL;
import static epi.Chapter12.RealSquareRoot.Ordering.LARGER;
import static epi.Chapter12.RealSquareRoot.Ordering.SMALLER;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class RealSquareRoot {

    static final double TOLERANCE = 0.000001;
    static final double NEWTON_TOLERANCE = 0.00000001;

    @EpiTest(testDataFile = "real_square_root.tsv")
    public static double squareRoot(double x) {
        double low;
        double high;
        if (x < 1.0) {
            low = x;
            high = 1.0;
        } else {
            low = 1.0;
            high = x;
        }

        while (compare(low, high) == SMALLER) {
            final double mid = 0.5 * (low + high);
            final double midSquare = mid * mid;
            if (compare(midSquare, x) == SMALLER) {
                low = mid;
            } else if (compare(midSquare, x) == EQUAL) {
                return mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    @EpiTest(testDataFile = "real_square_root.tsv")
    public static double newtonsMethod(double x) {
        if (x == 0) { return 0; }
        double guess = Math.min(x, 1.0);
        while (true) {
            final double next = (guess + x / guess) * 0.5;
            if (Math.abs(guess - next) < NEWTON_TOLERANCE) { break; }
            guess = next;
        }
        return guess;
    }

    private static Ordering compare(double a, double b) {
        final double diff = (a - b) / b;

        if (Math.abs(diff) < TOLERANCE) {
            return EQUAL;
        }

        return diff < -TOLERANCE ? SMALLER : LARGER;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    enum Ordering {SMALLER, EQUAL, LARGER}

    private RealSquareRoot() {}
}
