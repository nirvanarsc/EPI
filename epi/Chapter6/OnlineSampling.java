package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static epi.test_framework.RandomSequenceChecker.runFuncWithRetries;
import static epi.utils.RandomSampleUtils.computeRandomSamples;

public final class OnlineSampling {

    // Assumption: there are at least k elements in the stream.
    public static List<Integer> onlineRandomSample(Iterator<Integer> stream, int k) {
        final Random r = new Random();
        final List<Integer> res = new ArrayList<>();
        for (int i = 1; stream.hasNext(); i++) {
            final int curr = stream.next();
            if (i <= k) {
                res.add(curr);
            } else {
                final int swapIdx = r.nextInt(i);
                if (swapIdx < k) {
                    res.set(swapIdx, curr);
                }
            }
        }
        return res;
    }

    private static boolean onlineRandomSampleRunner(TimedExecutor executor, List<Integer> a, int k) throws Exception {
        final List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                results.add(onlineRandomSample(a.iterator(), k));
            }
        });

        return computeRandomSamples(a, a.size(), k, results);
    }

    @EpiTest(testDataFile = "online_sampling.tsv")
    public static void onlineRandomSampleWrapper(TimedExecutor executor, List<Integer> stream, int k) throws Exception {
        runFuncWithRetries(() -> onlineRandomSampleRunner(executor, stream, k));
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private OnlineSampling() {}
}
