package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfTraversalsStaircase {

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTop(int top, int maximumStep) {
        return dp(top, maximumStep, new int[top + 1]);
    }

    private static int dp(int top, int maximumStep, int[] cache) {
        if (top == 0) {
            return 1;
        }
        if (top < 0) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i <= maximumStep; i++) {
            if (top - i >= 0 && cache[top - i] == 0) {
                cache[top - i] = dp(top - i, maximumStep, cache);
            }
            res += (top - i) < 0 ? 0 : cache[top - i];
        }
        return res;
    }

    private NumberOfTraversalsStaircase() {}

    public static void main(String[] args) {
        TestRunner.run(args);
    }
}
