package epi.Chapter5;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

public final class PowerXY {

    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        if (y < 0) {
            y = -y;
            x = 1.0 / x;
        }
        short idx = 0;
        double res = 1;
        while (idx < Integer.SIZE) {
            if (((y >> idx) & 1) == 1) {
                res *= x;
            }
            x *= x;
            idx++;
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PowerXY() {
    }
}
