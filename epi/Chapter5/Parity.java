package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.HashMap;
import java.util.Map;

public final class Parity {
    private static final Map<Integer, Integer> table = new HashMap<>();

    static {
        table.put(15, 0);
        table.put(14, 1);
        table.put(13, 1);
        table.put(11, 1);
        table.put(7, 1);
        table.put(12, 0);
        table.put(9, 0);
        table.put(3, 0);
        table.put(6, 0);
        table.put(10, 0);
        table.put(5, 0);
        table.put(8, 1);
        table.put(4, 1);
        table.put(2, 1);
        table.put(1, 1);
        table.put(0, 0);
    }

    private Parity() {
    }

    @EpiTest(testDataFile = "parity.tsv")
    public static short parity(long x) {
        short res = 0;
        while (x != 0) {
            final long mask = (1 << 4) - 1;
            final long curr = x & mask;
            res ^= table.get((int) curr);
            x >>= 4;
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
        TestRunner.run(args, "epi.Chapter5.Parity");
    }
}
