package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;

public class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        // TODO - you fill in here.
        return 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.ReverseDigits");
    }
}
