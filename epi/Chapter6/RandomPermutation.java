package epi.Chapter6;

import static epi.test_framework.RandomSequenceChecker.checkSequenceIsUniformlyRandom;
import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class RandomPermutation {

    public static List<Integer> computeRandomPermutation(int n) {
        final List<Integer> list = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
        final Random r = new Random();
        for (int i = 0; i < list.size(); i++) {
            Collections.swap(list, i, i + r.nextInt(list.size() - i));
        }
        return list;
    }

    private static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    private static int permutationIndex(List<Integer> perm) {
        int idx = 0;
        int n = perm.size();
        for (int i = 0; i < perm.size(); ++i) {
            final int a = perm.get(i);
            idx += a * factorial(n - 1);
            for (int j = i + 1; j < perm.size(); ++j) {
                if (perm.get(j) > a) {
                    perm.set(j, perm.get(j) - 1);
                }
            }
            --n;
        }
        return idx;
    }

    private static boolean computeRandomPermutationRunner(TimedExecutor executor, int n) throws Exception {
        final List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                results.add(computeRandomPermutation(n));
            }
        });

        final List<Integer> sequence = new ArrayList<>();
        for (List<Integer> result : results) {
            sequence.add(permutationIndex(result));
        }
        return checkSequenceIsUniformlyRandom(sequence, factorial(n), 0.01);
    }

    @EpiTest(testDataFile = "random_permutation.tsv")
    public static void computeRandomPermutationWrapper(TimedExecutor executor, int n) throws Exception {
        runFuncWithRetries(() -> computeRandomPermutationRunner(executor, n));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RandomPermutation() {}
}
