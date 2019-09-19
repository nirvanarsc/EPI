package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsStringPalindromicPunctuation {

    // "A man, a plan, a canal, Panama."  -> true   "Ray a Ray" -> false
    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
    public static boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            } else {
                start++;
                end--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringPalindromicPunctuation() {
    }
}
