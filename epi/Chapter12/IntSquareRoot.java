package epi.Chapter12;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IntSquareRoot {

    @EpiTest(testDataFile = "int_square_root.tsv")
    public static int squareRoot(int k) {
        long lo = 0, hi = k;
        while (lo <= hi) {
            final long mid = lo + hi >>> 1;
            if (mid * mid <= k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (int) lo - 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntSquareRoot() {}
}
