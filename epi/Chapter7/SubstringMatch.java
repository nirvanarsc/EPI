package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SubstringMatch {

    @EpiTest(testDataFile = "substring_match.tsv")
    public static int substringMatch(String t, String s) {
        if (s.isEmpty()) {
            return 0;
        }
        final int[] prefixSuffix = kmp(s);
        for (int i = 0, j = 0; i < t.length() && j < s.length(); i++) {
            while (j > 0 && t.charAt(i) != s.charAt(j)) {
                j = prefixSuffix[j - 1];
            }
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j == s.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    private static int[] kmp(String s) {
        final int n = s.length();
        final int[] prefixSuffix = new int[n];
        for (int i = 1, j = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = prefixSuffix[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            prefixSuffix[i] = j;
        }
        return prefixSuffix;
    }

    @EpiTest(testDataFile = "substring_match.tsv")
    public static int substringMatchRabinKarp(String t, String s) {
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
            if (tHash == sHash && t.startsWith(s, i - s.length())) {
                return i - s.length();
            }

            tHash -= t.charAt(i - s.length()) * powerS;
            tHash = tHash * BASE + t.charAt(i);
        }

        if (tHash == sHash && t.endsWith(s)) {
            return t.length() - s.length();
        }

        return -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SubstringMatch() {}
}
