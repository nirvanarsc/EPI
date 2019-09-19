package epi.Chapter5;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

public final class PrimitiveDivide {

    @EpiTest(testDataFile = "primitive_divide.tsv")
    public static int divide(long x, long y) {
        int result = 0, power = Long.SIZE / 2;
        long yPower = y << power;
        while (x >= y) {
            while (yPower > x) {
                yPower >>= 1;
                --power;
            }
            result += 1L << power;
            x -= yPower;
        }
        return result;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PrimitiveDivide() {
    }
}
