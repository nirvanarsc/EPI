package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SnakeString {

    @EpiTest(testDataFile = "snake_string.tsv")
    public static String snakeString(String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i += 4) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i += 2) {
            sb.append(s.charAt(i));
        }
        for (int i = 3; i < s.length(); i += 4) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SnakeString() {}
}
