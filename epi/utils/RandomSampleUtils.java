package epi.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static epi.test_framework.RandomSequenceChecker.binomialCoefficient;
import static epi.test_framework.RandomSequenceChecker.checkSequenceIsUniformlyRandom;
import static epi.test_framework.RandomSequenceChecker.computeCombinationIdx;

public final class RandomSampleUtils {

    public static boolean computeRandomSamples(List<Integer> a, int k, List<List<Integer>> results) {
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

    private RandomSampleUtils() {
    }
}
