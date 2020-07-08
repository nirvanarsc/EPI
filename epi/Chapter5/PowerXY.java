package epi.Chapter5;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class PowerXY {

    @SuppressWarnings("TailRecursion")
    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        if (y < 0) {
            return 1.0 / power(x, -y);
        }
        if (y == 0) {
            return 1;
        }
        if ((y & 1) == 1) {
            return x * power(x, y - 1);
        }
        return power(x * x, y >> 1);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PowerXY() {
    }
}
