package epi.Chapter17;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class Fibonacci {

    @EpiTest(testDataFile = "fibonacci.tsv")
    public static int fibonacci(int n) {
        int a = 0;
        int b = 1;
        for (int i = 1; i <= n; i++) {
            final int t = a;
            a = b;
            b = a + t;
        }
        return a;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Fibonacci() {}
}
