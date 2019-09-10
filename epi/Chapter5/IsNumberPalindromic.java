package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public final class IsNumberPalindromic {

    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        return x < 0 ? false : ReverseDigits.reverse(x) == x;
    }

    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber2(int x) {
        if (x < 0) {
            return false;
        }

        final short length = (short) Math.log10(x);
        int msdMask = (int) Math.pow(10, length);

        for (int i = 0; 2 * i < length; x %= msdMask, x /= 10, msdMask /= 100, i++) {
            if (x / msdMask != x % 10) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.IsNumberPalindromic");
    }

    private IsNumberPalindromic() {
    }
}
