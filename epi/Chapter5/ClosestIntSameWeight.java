package epi.Chapter5;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ClosestIntSameWeight {

    @EpiTest(testDataFile = "closest_int_same_weight.tsv")
    public static long closestIntSameBitCount(long x) {
        int i = 0;

        while (isBitSet(x, i) == isBitSet(x, i + 1)) {
            i++;
        }

        return SwapBits.swapBits(x, i + 1, i);
    }

    private static boolean isBitSet(long x, int idx) {
        return (x & (1L << idx)) > 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ClosestIntSameWeight() {
    }
}
