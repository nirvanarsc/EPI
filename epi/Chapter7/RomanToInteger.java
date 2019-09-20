package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.HashMap;
import java.util.Map;

public final class RomanToInteger {

    private static final Map<Character, Integer> ROMAN_NUMERALS = new HashMap<>();

    static {
        ROMAN_NUMERALS.put('I', 1);
        ROMAN_NUMERALS.put('V', 5);
        ROMAN_NUMERALS.put('X', 10);
        ROMAN_NUMERALS.put('L', 50);
        ROMAN_NUMERALS.put('C', 100);
        ROMAN_NUMERALS.put('D', 500);
        ROMAN_NUMERALS.put('M', 1000);
    }

    @EpiTest(testDataFile = "roman_to_integer.tsv")
    public static int romanToInteger(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                res += ROMAN_NUMERALS.get(s.charAt(i));
            } else if (ROMAN_NUMERALS.get(s.charAt(i + 1)) > ROMAN_NUMERALS.get(s.charAt(i))) {
                res -= ROMAN_NUMERALS.get(s.charAt(i));
            } else {
                res += ROMAN_NUMERALS.get(s.charAt(i));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RomanToInteger() {
    }
}
