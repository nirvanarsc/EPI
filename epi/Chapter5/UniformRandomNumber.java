package epi.Chapter5;

import epi.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static epi.test_framework.RandomSequenceChecker.checkSequenceIsUniformlyRandom;
import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;

public final class UniformRandomNumber {

    private static int zeroOneRandom() {
        final Random gen = new Random();
        return gen.nextInt(2);
    }

    public static int uniformRandom(int lowerBound, int upperBound) {
        final int limit = upperBound - lowerBound;
        int idx = Integer.SIZE - 1;

        while ((limit & (1 << --idx)) == 0) {
        }

        while (true) {
            int res = 0;
            for (int i = 0; i <= idx; i++) {
                if (zeroOneRandom() == 1) {
                    res |= 1 << i;
                }
            }
            if (res <= limit) {
                return lowerBound + res;
            }
        }
    }

    private static boolean uniformRandomRunner(TimedExecutor executor, int lowerBound, int upperBound) throws Exception {
        final List<Integer> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 100000; ++i) {
                results.add(uniformRandom(lowerBound, upperBound));
            }
        });

        final List<Integer> sequence = new ArrayList<>();

        for (Integer result : results) {
            sequence.add(result - lowerBound);
        }
        return checkSequenceIsUniformlyRandom(sequence, upperBound - lowerBound + 1, 0.01);
    }

    @EpiTest(testDataFile = "uniform_random_number.tsv")
    public static void uniformRandomWrapper(TimedExecutor executor, int lowerBound, int upperBound) throws Exception {
        runFuncWithRetries(() -> uniformRandomRunner(executor, lowerBound, upperBound));
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter5.UniformRandomNumber");
    }

    private UniformRandomNumber() {
    }
}
