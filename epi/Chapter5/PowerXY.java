package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public class PowerXY {

    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        return Math.pow(x, y);
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.PowerXY");
    }
}
