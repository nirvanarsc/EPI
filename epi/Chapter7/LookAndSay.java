package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LookAndSay {

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay(int n) {
        if (n <= 0) {
            return "";
        }

        String s = "1";
        for (int i = 1; i < n; i++) {
            s = lookAndSay(s);
        }
        return s;
    }

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay2(int n) {
        if (n <= 0) {
            return "";
        }

        String s = "1";
        for (int i = 1; i < n; i++) {
            s = lookAndSay2(s);
        }
        return s;
    }

    public static String lookAndSay(String str) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int count = 1;
            while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                i++;
                count++;
            }
            sb.append(count);
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    private static String lookAndSay2(String str) {
        final StringBuilder sb = new StringBuilder();
        char prev = str.charAt(0);
        int times = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 1) {
                if (prev == str.charAt(i)) {
                    sb.append(times + 1);
                } else {
                    sb.append(times);
                    sb.append(prev);
                    sb.append(1);
                }
                sb.append(str.charAt(i));
            } else if (str.charAt(i) != prev) {
                sb.append(times);
                sb.append(prev);
                prev = str.charAt(i);
                times = 1;
            } else {
                times++;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LookAndSay() {
    }
}
