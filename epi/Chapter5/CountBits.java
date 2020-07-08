package epi.Chapter5;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class CountBits {

    @EpiTest(testDataFile = "count_bits.tsv")
    public static short countBits(int x) {
        short res = 0;
        while (x > 0) {
            x &= x - 1;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private CountBits() {
    }
}
