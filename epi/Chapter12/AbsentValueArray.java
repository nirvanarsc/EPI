package epi.Chapter12;

import java.util.BitSet;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class AbsentValueArray {

    private static final int NUM_BUCKET = 1 << 16;

    @EpiTest(testDataFile = "absent_value_array.tsv")
    public static int findMissingElement(Iterable<Integer> stream) {
        final int[] cache = new int[100];
        for (Integer integer : stream) {
            cache[integer / Integer.SIZE] |= 1 << integer % Integer.SIZE;
        }

        for (int i = 0; i < 100; i++) {
            int curr = cache[i];
            if (curr != 0xffffffff) {
                int j = 0;
                while ((curr & 1) == 1) {
                    curr >>= 1;
                    j++;
                }
                return Integer.SIZE * i + j;
            }
        }

        return -1;
    }

    @EpiTest(testDataFile = "absent_value_array.tsv")
    public static int findMissingElement2(Iterable<Integer> stream) {
        final int[] cache = new int[NUM_BUCKET];
        for (Integer integer : stream) {
            final int idx = integer >>> 16;
            ++cache[idx];
        }

        for (int i = 0; i < cache.length; i++) {
            if (cache[i] < NUM_BUCKET) {
                final BitSet bitVec = new BitSet(NUM_BUCKET);
                for (Integer integer : stream) {
                    if (i == (integer >>> 16)) {
                        bitVec.set((NUM_BUCKET - 1) & integer);
                    }
                }
                for (int j = 0; j < NUM_BUCKET; j++) {
                    if (!bitVec.get(j)) {
                        return (i << 16) | j;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private AbsentValueArray() {}
}
