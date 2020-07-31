package epi.Chapter13;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsAnonymousLetterConstructible {

    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
        final int[] count = new int[128];
        for (char c : magazineText.toCharArray()) { count[c]++; }
        for (char c : letterText.toCharArray()) { count[c]--; }
        for (int freq : count) {
            if (freq < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsAnonymousLetterConstructible() {}
}
