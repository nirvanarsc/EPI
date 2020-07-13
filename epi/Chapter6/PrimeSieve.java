package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PrimeSieve {

    @EpiTest(testDataFile = "prime_sieve.tsv")
    public static List<Integer> generatePrimes(int n) {
        final List<Integer> res = new ArrayList<>();
        final boolean[] sieve = new boolean[n + 1];
        Arrays.fill(sieve, true);
        for (int p = 2; p * p <= n; p++) {
            if (sieve[p]) {
                for (int k = 2 * p; k <= n; k += p) {
                    sieve[k] = false;
                }
            }
        }
        for (int p = 2; p < sieve.length; p++) {
            if (sieve[p]) {
                res.add(p);
            }
        }
        return res;
    }

    @EpiTest(testDataFile = "prime_sieve.tsv")
    public static List<Integer> generatePrimes2(int n) {
        final List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PrimeSieve() {}
}
