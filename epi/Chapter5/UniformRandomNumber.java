package epi.Chapter5;

import static epi.test_framework.RandomSequenceChecker.checkSequenceIsUniformlyRandom;
import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class UniformRandomNumber {

    private static int zeroOneRandom() {
        final Random gen = new Random();
        return gen.nextInt(2);
    }

    public static int uniformRandom(int lowerBound, int upperBound) {
        final int limit = upperBound - lowerBound;
        int res;
        do {
            res = 0;
            for (int i = 0; (1 << i) <= limit; i++) {
                res |= zeroOneRandom() << i;
            }
        } while (res > limit);
        return lowerBound + res;
    }

    private static boolean uniformRandomRunner(TimedExecutor executor,
                                               int lowerBound,
                                               int upperBound) throws Exception {
        final List<Integer> results = executor
                .run(() -> IntStream.range(0, 100000)
                                    .boxed()
                                    .map(i -> uniformRandom(lowerBound, upperBound) - lowerBound)
                                    .collect(Collectors.toList()));

        return checkSequenceIsUniformlyRandom(results, upperBound - lowerBound + 1, 0.01);
    }

    @EpiTest(testDataFile = "uniform_random_number.tsv")
    public static void uniformRandomWrapper(TimedExecutor executor,
                                            int lowerBound,
                                            int upperBound) throws Exception {
        runFuncWithRetries(() -> uniformRandomRunner(executor, lowerBound, upperBound));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private UniformRandomNumber() {
    }
}
