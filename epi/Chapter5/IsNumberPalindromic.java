package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public class IsNumberPalindromic {

    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        // TODO - you fill in here.
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.IsNumberPalindromic");
    }
}
