package epi.Chapter13;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsAnonymousLetterConstructible {

    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
        final Map<Character, Integer> letterMap = new HashMap<>();
        for (char c : letterText.toCharArray()) { letterMap.merge(c, 1, Integer::sum); }
        for (char c : magazineText.toCharArray()) {
            letterMap.computeIfPresent(c, (k, v) -> (v == 1) ? null : v - 1);
        }

        return letterMap.isEmpty();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsAnonymousLetterConstructible() {}
}
