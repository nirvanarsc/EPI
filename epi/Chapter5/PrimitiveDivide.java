package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public class PrimitiveDivide {

    @EpiTest(testDataFile = "primitive_divide.tsv")
    public static int divide(int x, int y) {
        // TODO - you fill in here.
        return 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.PrimitiveDivide");
    }
}
