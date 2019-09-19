package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ConvertBase {

    @EpiTest(testDataFile = "convert_base.tsv")
    public static String convertBase(String numAsString, int b1, int b2) {
        final StringBuilder sb = intToNbaseString(stringTo10Int(numAsString, b1), b2);

        if (numAsString.charAt(0) == '-') {
            sb.append('-');
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private static int stringTo10Int(String s, int base) {
        int res = 0;

        for (char c : s.toCharArray()) {
            if (c == '-') {
                continue;
            }

            res = c >= 'A'
                    ? res * base + c - 'A' + 10
                    : res * base + c - '0';
        }
        return res;
    }

    private static StringBuilder intToNbaseString(int x, int base) {
        final StringBuilder sb = new StringBuilder();

        do {
            final int i = Math.abs(x % base);
            final char c = i < 10
                    ? (char) ('0' + i)
                    : (char) ('A' + i - 10);
            sb.append(c);
            x /= base;
        } while (x != 0);

        return sb;
    }

    private ConvertBase() {
    }
}
