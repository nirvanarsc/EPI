package epi.Chapter16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class GrayCode {

    public static List<Integer> grayCode(int numBits) {
        if (numBits == 0) {
            return new ArrayList<>(Collections.singletonList(0));
        }

        final List<Integer> integers = grayCode(numBits - 1);
        for (int i = integers.size() - 1; i >= 0; i--) {
            integers.add(integers.get(i) | (1 << numBits - 1));
        }
        return integers;
    }

    public static List<Integer> grayCode2(int numBits) {
        final List<Integer> result = new ArrayList<>(Collections.singletonList(0));
        directedGrayCode(numBits, new HashSet<>(Collections.singletonList(0)), result);
        return result;
    }

    private static boolean directedGrayCode(int numBits, Set<Integer> history, List<Integer> result) {
        if (result.size() == (1 << numBits)) {
            return differsByOneBit(result.get(0), result.get(result.size() - 1));

        }
        for (int i = 0; i < numBits; i++) {
            final int previousCode = result.get(result.size() - 1);
            final int candidateNextCode = previousCode ^ (1 << i);
            if (!history.contains(candidateNextCode)) {
                history.add(candidateNextCode);
                result.add(candidateNextCode);
                if (directedGrayCode(numBits, history, result)) {
                    return true;
                }
                history.remove(candidateNextCode);
                result.remove(result.size() - 1);
            }
        }
        return false;
    }

    @EpiTest(testDataFile = "gray_code.tsv")
    public static void grayCodeWrapper(TimedExecutor executor, int numBits) throws Exception {
        final List<Integer> result = executor.run(() -> grayCode(numBits));
        verifyGrayCode(numBits, result);
    }

    @EpiTest(testDataFile = "gray_code.tsv")
    public static void grayCodeWrapper2(TimedExecutor executor, int numBits) throws Exception {
        final List<Integer> result = executor.run(() -> grayCode2(numBits));
        verifyGrayCode(numBits, result);
    }

    private static void verifyGrayCode(int numBits, List<Integer> result) throws TestFailure {
        final int expectedSize = 1 << numBits;
        if (result.size() != expectedSize) {
            throw new TestFailure("Length mismatch: expected " + expectedSize + ", got " + result.size());
        }
        for (int i = 1; i < result.size(); i++) {
            if (!differsByOneBit(result.get(i - 1), result.get(i))) {
                if (result.get(i - 1).equals(result.get(i))) {
                    throw new TestFailure("Two adjacent entries are equal");
                } else {
                    throw new TestFailure("Two adjacent entries differ by more than 1 bit");
                }
            }
        }

        final Set<Integer> uniq = new HashSet<>(result);
        if (uniq.size() != result.size()) {
            throw new TestFailure(
                    "Not all entries are distinct: found " + (result.size() - uniq.size()) + " duplicates");
        }
    }

    private static boolean differsByOneBit(int x, int y) {
        final int bitDifference = x ^ y;
        return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private GrayCode() {}
}
