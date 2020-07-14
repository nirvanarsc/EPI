package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class RunLengthCompression {

    public static String decoding(String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            int j = i;
            while (j < s.length() && Character.isDigit(s.charAt(j))) {
                count = count * 10 + s.charAt(j) - '0';
                j++;
            }
            for (int k = 0; k < count; k++) {
                sb.append(s.charAt(j));
            }
            i = j;
        }
        return sb.toString();
    }

    public static String encoding(String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            sb.append(j - i);
            sb.append(s.charAt(i));
            i = j - 1;
        }
        return sb.toString();
    }

    @EpiTest(testDataFile = "run_length_compression.tsv")
    public static void rleTester(String encoded, String decoded) throws TestFailure {
        if (!decoding(encoded).equals(decoded)) {
            throw new TestFailure("Decoding failed");
        }
        if (!encoding(decoded).equals(encoded)) {
            throw new TestFailure("Encoding failed");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RunLengthCompression() {}
}
