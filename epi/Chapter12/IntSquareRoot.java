package epi.Chapter12;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IntSquareRoot {

    @EpiTest(testDataFile = "int_square_root.tsv")
    public static int squareRoot(int k) {
        int low = 0, high = k;
        while (low <= high) {
            final long mid = (low + high) >>> 1;
            if (mid * mid <= k) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }

        return low - 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntSquareRoot() {}
}
