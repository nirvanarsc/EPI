package epi.Chapter5;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class Parity {

    private static final Map<Integer, Integer> CACHE = new HashMap<>();
    private static final int MASK = 0xf;
    private static final int WORD_SIZE = 4;

    static {
        CACHE.put(15, 0);
        CACHE.put(14, 1);
        CACHE.put(13, 1);
        CACHE.put(11, 1);
        CACHE.put(7, 1);
        CACHE.put(12, 0);
        CACHE.put(9, 0);
        CACHE.put(3, 0);
        CACHE.put(6, 0);
        CACHE.put(10, 0);
        CACHE.put(5, 0);
        CACHE.put(8, 1);
        CACHE.put(4, 1);
        CACHE.put(2, 1);
        CACHE.put(1, 1);
        CACHE.put(0, 0);
    }

    @EpiTest(testDataFile = "parity.tsv")
    public static short parity(long x) {
        short res = 0;
        for (int i = 0; i < Long.SIZE / WORD_SIZE; i++, x >>= WORD_SIZE) {
            res ^= CACHE.get((int) (x & MASK));
        }
        return res;
    }

    @EpiTest(testDataFile = "parity.tsv")
    public static short parity2(long x) {
        x ^= x >> 32;
        x ^= x >> 16;
        x ^= x >> 8;
        x ^= x >> 4;
        x ^= x >> 2;
        x ^= x >> 1;
        return (short) (x & 0x1);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Parity() {
    }
}
