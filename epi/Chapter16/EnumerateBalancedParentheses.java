package epi.Chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class EnumerateBalancedParentheses {

    @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
    public static List<String> generateBalancedParentheses(int numPairs) {
        final List<String> res = new ArrayList<>();
        compute("", numPairs, numPairs, res);
        return res;
    }

    private static void compute(String s, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            compute(s + '(', left - 1, right, res);
        }
        if (left < right) {
            compute(s + ')', left, right - 1, res);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = TestRunner.STR_COMP;

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EnumerateBalancedParentheses() {}
}
