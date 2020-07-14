package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class StringIntegerInterconversion {

    public static String intToString(int x) {
        final StringBuilder sb = new StringBuilder();
        boolean negative = false;
        if (x < 0) { negative = true; }

        do {
            sb.append(Math.abs(x % 10));
            x /= 10;
        } while (x != 0);

        if (negative) { sb.append('-'); }

        return sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        int res = 0, sign = 1;
        for (char c : s.toCharArray()) {
            if (c == '-') {
                sign = -1;
                continue;
            }
            res = res * 10 + c - '0';
        }
        return sign * res;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (!intToString(x).equals(s)) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private StringIntegerInterconversion() {}
}
