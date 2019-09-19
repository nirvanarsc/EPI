package epi.Chapter5;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

public final class CountBits {

    @EpiTest(testDataFile = "count_bits.tsv")
    public static short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += x & 1;
            x >>>= 1;
        }
        return numBits;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private CountBits() {
    }
}
