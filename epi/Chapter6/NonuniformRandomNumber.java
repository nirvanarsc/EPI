package epi.Chapter6;

import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class NonuniformRandomNumber {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res, new ArrayList<>());
        return res;
    }

    private static void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> curr) {
        res.add(new ArrayList<>(curr));
        for (int j = i; j < nums.length; j++) {
            curr.add(nums[j]);
            dfs(nums, j + 1, res, curr);
            curr.remove(curr.size() - 1);
        }
    }

    public static int nonuniformRandomNumberGeneration(List<Integer> values,
                                                       List<Double> probabilities) {
        final double[] prefixSum = new double[probabilities.size()];
        for (int i = 0; i < probabilities.size(); i++) {
            prefixSum[i] = probabilities.get(i) + (i > 0 ? prefixSum[i - 1] : 0);
        }
        final double random = Math.random();
        return values.get(lowerBound(prefixSum, random));
    }

    private static int lowerBound(double[] arr, double target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            final int mid = lo + hi >>> 1;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

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
        TestRunner.run(args);
    }

    private NonuniformRandomNumber() {}
}
