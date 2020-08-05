package epi.Chapter15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ABSqrt2 {

    private static final double SQRT_2 = 1.4142135623730951;

    static class Pair {
        int a, b;
        double val;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
            val = a + b * SQRT_2;
        }
    }

    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    public static List<Double> generateFirstKABSqrt2(int k) {
        final Pair[] dp = new Pair[k];
        dp[0] = new Pair(0, 0);
        int i = 0;
        int j = 0;
        for (int t = 1; t < k; t++) {
            final Pair left = new Pair(dp[i].a + 1, dp[i].b);
            final Pair right = new Pair(dp[j].a, dp[j].b + 1);
            dp[t] = Double.compare(left.val, right.val) < 0 ? left : right;
            if (Double.compare(dp[t].val, left.val) == 0) { i++; }
            if (Double.compare(dp[t].val, right.val) == 0) { j++; }
        }
        return Arrays.stream(dp).map(x -> x.a + x.b * SQRT_2).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ABSqrt2() {}
}
