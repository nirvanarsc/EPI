package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ConvertBase {

    @EpiTest(testDataFile = "convert_base.tsv")
    public static String convertBase(String numAsString, int b1, int b2) {
        final int base10 = baseNto10(numAsString, b1);
        final StringBuilder sb = base10toN(b2, base10);
        if (sb.length() == 0) { sb.append(0); }
        if (numAsString.charAt(0) == '-') { sb.append('-'); }
        return sb.reverse().toString();
    }

    private static int baseNto10(String numAsString, int b1) {
        int base10 = 0;
        for (char c : numAsString.toCharArray()) {
            if (c == '-') {
                continue;
            }
            final int curr;
            if (c >= 'A') {
                curr = 10 + c - 'A';
            } else {
                curr = c - '0';
            }
            base10 = base10 * b1 + curr;
        }
        return base10;
    }

    private static StringBuilder base10toN(int b2, int base10) {
        final StringBuilder sb = new StringBuilder();
        while (base10 > 0) {
            final int rem = base10 % b2;
            if (rem >= 10) {
                sb.append((char) ('A' + rem % 10));
            } else {
                sb.append(rem);
            }
            base10 /= b2;
        }
        return sb;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ConvertBase() {}
}
