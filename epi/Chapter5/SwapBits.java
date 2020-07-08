package epi.Chapter5;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SwapBits {

    @EpiTest(testDataFile = "swap_bits.tsv")
    public static long swapBits(long x, int i, int j) {
        if (isBitSet(x, i) != isBitSet(x, j)) {
            x ^= (1L << i) | (1L << j);
        }
        return x;
    }

    private static boolean isBitSet(long x, int idx) {
        return (x & (1L << idx)) != 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SwapBits() {
    }
}
