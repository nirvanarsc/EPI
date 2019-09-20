package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class RunLengthCompression {

    public static String decoding(String s) {
        final StringBuilder sb = new StringBuilder();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                end++;
            } else {
                int n = Integer.parseInt(s.substring(start, end));
                while (n-- > 0) {
                    sb.append(s.charAt(i));
                }
                start = i + 1;
                end = i + 1;
            }
        }

        return sb.toString();
    }

    public static String encoding(String s) {
        return LookAndSay.lookAndSay(s);
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

    private RunLengthCompression() {
    }
}
