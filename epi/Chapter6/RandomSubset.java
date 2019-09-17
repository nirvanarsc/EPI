package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static epi.test_framework.RandomSequenceChecker.binomialCoefficient;
import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;
import static epi.utils.RandomSampleUtils.computeHelper;

public final class RandomSubset {

    // Returns a random k-sized subset of {0, 1, ..., n - 1}.
    public static List<Integer> randomSubset(int n, int k) {
        final Map<Integer, Integer> changedElements = new HashMap<>();
        final Random random = new Random();

        for (int i = 0; i < k; i++) {
            final int randomIdx = i + random.nextInt(n - i);
            final Integer ptr1 = changedElements.get(randomIdx);
            final Integer ptr2 = changedElements.get(i);
            if (ptr1 == null && ptr2 == null) {
                changedElements.put(randomIdx, i);
                changedElements.put(i, randomIdx);
            } else if (ptr1 == null) {
                changedElements.put(randomIdx, ptr2);
                changedElements.put(i, randomIdx);
            } else if (ptr2 == null) {
                changedElements.put(i, ptr1);
                changedElements.put(randomIdx, i);
            } else {
                changedElements.put(i, ptr1);
                changedElements.put(randomIdx, ptr2);
            }
        }

        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(changedElements.get(i));
        }
        return result;
    }

//    public static List<Integer> randomSubset(int n, int k) {
//        final List<Integer> res = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            res.add(i);
//        }
//        OfflineSampling.randomSampling(k, res);
//        return res.subList(0, k);
//    }

    private static boolean randomSubsetRunner(TimedExecutor executor, int n, int k) throws Exception {
        final List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                results.add(randomSubset(n, k));
            }
        });

        final int totalPossibleOutcomes = binomialCoefficient(n, k);
        final List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            a.add(i);
        }
        return computeHelper(n, k, results, totalPossibleOutcomes, a);
    }

    @EpiTest(testDataFile = "random_subset.tsv")
    public static void randomSubsetWrapper(TimedExecutor executor, int n, int k) throws Exception {
        runFuncWithRetries(() -> randomSubsetRunner(executor, n, k));
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.RandomPermutation");
    }

    private RandomSubset() {
    }
}
