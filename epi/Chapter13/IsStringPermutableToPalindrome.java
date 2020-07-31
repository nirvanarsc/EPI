package epi.Chapter13;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsStringPermutableToPalindrome {

    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    public static boolean canFormPalindrome(String s) {
        final int[] map = new int[128];
        for (char c : s.toCharArray()) {
            map[c]++;
        }
        int odds = 0;
        for (int freq : map) {
            odds += freq % 2;
        }
        return odds <= 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringPermutableToPalindrome() {}
}
