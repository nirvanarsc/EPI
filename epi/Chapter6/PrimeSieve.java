package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PrimeSieve {

    @EpiTest(testDataFile = "prime_sieve.tsv")
    public static List<Integer> generatePrimes(int n) {
        final List<Integer> res = new ArrayList<>();
        final List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
        isPrime.set(0, false);
        isPrime.set(1, false);
        for (int p = 2; p <= n; p++) {
            if (isPrime.get(p)) {
                res.add(p);
            }
            for (int i = p; i <= n; i += p) {
                isPrime.set(i, false);
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
        TestRunner.run(args, "epi.Chapter6.PrimeSieve");
    }

    private PrimeSieve() {
    }
}
