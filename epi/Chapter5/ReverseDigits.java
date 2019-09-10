package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public final class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        long reversed = 0;
        int absX = Math.abs(x);
        int length = (int) Math.log10(absX);
        while (length >= 0) {
            final int digit = absX % 10;
            reversed += digit * Math.pow(10, length--);
            absX /= 10;
        }
        return x < 0 ? -reversed : reversed;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.ReverseDigits");
    }

    private ReverseDigits() {
    }
}
