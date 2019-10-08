package epi.Chapter13;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsStringPermutableToPalindrome {

    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    public static boolean canFormPalindrome(String s) {
        final Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        boolean odd = false;
        for (int i : map.values()) {
            if (((i & 1) == 1) && odd) {
                return false;
            } else if ((i & 1) == 1) {
                odd = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringPermutableToPalindrome() {}
}
