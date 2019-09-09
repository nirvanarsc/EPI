package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public final class PrimitiveMultiply {

    @EpiTest(testDataFile = "primitive_multiply.tsv")
    public static long multiply(long x, long y) {
        long res = 0;
        short idx = 0;
        while (idx++ < Long.SIZE) {
            if (((x >> idx) & 1) == 1) {
                res = add(res, y << idx);
            }
        }

        return res;
    }

    private static long add(long x, long y) {
        long flag, prevFlag = 0, res = 0;
        short idx = 0;

        while (idx++ < Long.SIZE) {
            final short left = (short) ((x >> idx) & 1);
            final short right = (short) ((y >> idx) & 1);
            flag = (left == 1 && right == 1) || ((left != right) && prevFlag == 1) ? 1 : 0;
            if ((left ^ right ^ prevFlag) == 1) {
                res |= 1L << idx;
            }
            prevFlag = flag;
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.PrimitiveMultiply");
    }

    private PrimitiveMultiply() {
    }
}
