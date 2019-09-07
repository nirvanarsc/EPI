package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public final class SwapBits {

    @EpiTest(testDataFile = "swap_bits.tsv")
    public static long swapBits(long x, int i, int j) {
        if (isBitSet(x, i) == isBitSet(x, j)) {
            return x;
        }

        final long bitMask = (1L << i) | (1L << j);
        return x ^ bitMask;
    }

    private static boolean isBitSet(long x, int idx) {
        return (x & (1L << idx)) > 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.SwapBits");
    }

    private SwapBits() {
    }
}
