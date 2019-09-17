package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static epi.test_framework.RandomSequenceChecker.binomialCoefficient;
import static epi.test_framework.RandomSequenceChecker.checkSequenceIsUniformlyRandom;
import static epi.test_framework.RandomSequenceChecker.computeCombinationIdx;
import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;

public final class OfflineSampling {

    public static void randomSampling(int k, List<Integer> a) {
        final Random r = new Random();
        int l = a.size() - k;
        while (l-- > 0) {
            a.remove(r.nextInt(a.size()));
        }
    }

//    public static void randomSampling(int k, List<Integer> a) {
//        final Random r = new Random();
//        for (int i = 0; i < k; i++) {
//            Collections.swap(a, i, i + r.nextInt(a.size() - i));
//        }
//    }

    private static boolean randomSamplingRunner(TimedExecutor executor, int k, List<Integer> a) throws Exception {
        final List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                randomSampling(k, a);
                results.add(new ArrayList<>(a.subList(0, k)));
            }
        });

        final int totalPossibleOutcomes = binomialCoefficient(a.size(), k);
        Collections.sort(a);
        final List<List<Integer>> combinations = new ArrayList<>();
        for (int i = 0; i < binomialCoefficient(a.size(), k); ++i) {
            combinations.add(computeCombinationIdx(a, a.size(), k, i));
        }
        final List<Integer> sequence = new ArrayList<>();
        for (List<Integer> result : results) {
            Collections.sort(result);
            sequence.add(combinations.indexOf(result));
        }
        return checkSequenceIsUniformlyRandom(sequence, totalPossibleOutcomes, 0.01);
    }

    @EpiTest(testDataFile = "offline_sampling.tsv")
    public static void randomSamplingWrapper(TimedExecutor executor, int k, List<Integer> a) throws Exception {
        runFuncWithRetries(() -> randomSamplingRunner(executor, k, a));
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.OfflineSampling");
    }

    private OfflineSampling() {
    }
}
