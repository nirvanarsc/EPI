package epi.Chapter5;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

public final class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        long reversed = 0;
        int absX = Math.abs(x);
        while (absX != 0) {
            reversed = reversed * 10 + absX % 10;
            absX /= 10;
        }
        return x < 0 ? -reversed : reversed;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ReverseDigits() {
    }
}
