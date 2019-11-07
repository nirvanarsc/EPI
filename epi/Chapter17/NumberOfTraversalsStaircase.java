package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfTraversalsStaircase {

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTop(int top, int maximumStep) {
        return dp(top, maximumStep, new int[top + 1]);
    }

    private static int dp(int top, int maximumStep, int[] cache) {
        if (top <= 1) {
            return 1;
        }
        if (cache[top] == 0) {
            for (int i = 1; i <= maximumStep && top - i >= 0; i++) {
                cache[top] += dp(top - i, maximumStep, cache);
            }
        }
        return cache[top];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfTraversalsStaircase() {}
}
