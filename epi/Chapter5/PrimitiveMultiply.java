package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public final class PrimitiveMultiply {

    @EpiTest(testDataFile = "primitive_multiply.tsv")
    public static long multiply(long x, long y) {
        return x * y;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.PrimitiveMultiply");
    }

    private PrimitiveMultiply() {
    }
}
