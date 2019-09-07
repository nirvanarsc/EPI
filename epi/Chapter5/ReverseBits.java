package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.HashMap;
import java.util.Map;

public final class ReverseBits {

    private static final Map<Long, Long> table = new HashMap<>();

    static {
        table.put(15L, 15L);
        table.put(14L, 7L);
        table.put(13L, 11L);
        table.put(11L, 13L);
        table.put(7L, 14L);
        table.put(12L, 3L);
        table.put(9L, 9L);
        table.put(3L, 12L);
        table.put(6L, 6L);
        table.put(10L, 5L);
        table.put(5L, 10L);
        table.put(8L, 1L);
        table.put(4L, 2L);
        table.put(2L, 4L);
        table.put(1L, 8L);
        table.put(0L, 0L);
    }

    @EpiTest(testDataFile = "reverse_bits.tsv")
    public static long reverseBits(long x) {
        int start = 0;
        int end = Long.SIZE - 1;
        while (start < end) {
            x = SwapBits.swapBits(x, start++, end--);
        }
        return x;
    }

    @EpiTest(testDataFile = "reverse_bits.tsv")
    public static long reverseBits2(long x) {
        final int WORD_SIZE = 4;
        final int BIT_MASK = 15;
        long res = 0;
        final int loops = Long.SIZE / WORD_SIZE;
        for (int i = 0; i <= loops; i++) {
            res |= table.get((x >> (i * WORD_SIZE)) & BIT_MASK) << ((loops - i - 1) * WORD_SIZE);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.ReverseBits");
    }

    private ReverseBits() {
    }
}
