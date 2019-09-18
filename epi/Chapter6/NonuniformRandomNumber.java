package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;

public final class NonuniformRandomNumber {

    public static int nonuniformRandomNumberGeneration(List<Integer> values, List<Double> probabilities) {
        final Random r = new Random();
        final double v = r.nextDouble();
        double curr = 0;

        for (int i = 0; i < values.size(); i++) {
            curr += probabilities.get(i);
            if (v <= curr) {
                return values.get(i);
            }
        }

        return -1;
    }

//    public static int nonuniformRandomNumberGeneration(List<Integer> values, List<Double> probabilities) {
//        final List<Double> sum = new ArrayList<>();
//        sum.add(0.0);
//        for (double d : probabilities) {
//            sum.add(sum.get(sum.size() - 1) + d);
//        }
//        final Random r = new Random();
//        final double v = r.nextDouble();
//
//        final int it = Collections.binarySearch(sum, v);
//        if (it < 0) {
//            final int intervalIdx = Math.abs(it) - 2;
//            return values.get(intervalIdx);
//        } else {
//            return values.get(it);
//        }
//    }

    private static boolean nonuniformRandomNumberGenerationRunner(TimedExecutor executor,
                                                                  List<Integer> values,
                                                                  List<Double> probabilities) throws Exception {
        final int N = 1000000;
        final List<Integer> results = new ArrayList<>(N);

        executor.run(() -> {
            for (int i = 0; i < N; ++i) {
                results.add(nonuniformRandomNumberGeneration(values, probabilities));
            }
        });

        final Map<Integer, Integer> counts = new HashMap<>();
        for (Integer result : results) {
            counts.put(result, counts.getOrDefault(result, 0) + 1);
        }
        for (int i = 0; i < values.size(); ++i) {
            final int v = values.get(i);
            final double p = probabilities.get(i);
            if (N * p < 50 || N * (1.0 - p) < 50) {
                continue;
            }
            final double sigma = Math.sqrt(N * p * (1.0 - p));
            if (Math.abs(counts.get(v) - (p * N)) > 5 * sigma) {
                return false;
            }
        }
        return true;
    }

    @EpiTest(testDataFile = "nonuniform_random_number.tsv")
    public static void nonuniformRandomNumberGenerationWrapper(TimedExecutor executor,
                                                               List<Integer> values,
                                                               List<Double> probabilities) throws Exception {
        runFuncWithRetries(() -> nonuniformRandomNumberGenerationRunner(executor, values, probabilities));
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.NonuniformRandomNumber");
    }

    private NonuniformRandomNumber() {
    }
}
