package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SubstringMatch {

    @EpiTest(testDataFile = "substring_match.tsv")
    public static int substringMatch(String t, String s) {
        return t.indexOf(s);
    }

    @EpiTest(testDataFile = "substring_match.tsv")
    public static int substringMatch2(String t, String s) {
        if (s.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(0)) {
                final int end = i + s.length();
                if (end <= t.length() && t.substring(i, end).equals(s)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @EpiTest(testDataFile = "substring_match.tsv")
    public static int rabinKarp3(String t, String s) {
        if (s.length() > t.length()) {
            return -1;
        }

        final int BASE = 26;
        int tHash = 0, sHash = 0;
        int powerS = 1;
        for (int i = 0; i < s.length(); i++) {
            powerS = i > 0 ? powerS * BASE : 1;
            tHash = tHash * BASE + t.charAt(i);
            sHash = sHash * BASE + s.charAt(i);
        }

        for (int i = s.length(); i < t.length(); i++) {
            if (tHash == sHash && t.substring(i - s.length(), i).equals(s)) {
                return i - s.length();
            }

            tHash -= t.charAt(i - s.length()) * powerS;
            tHash = tHash * BASE + t.charAt(i);
        }

        if (tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
            return t.length() - s.length();
        }

        return -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SubstringMatch() {
    }
}
