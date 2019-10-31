package epi.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import epi.test_framework.TestFailure;

public final class SudokuValidator {

    public static final int SUDOKU_SIZE = 9;
    public static final int BLOCK_SIZE = 3;

    public static boolean isAssignmentValid(int n, int i, int j, List<List<Integer>> assignment) {
        return validRow(n, j, assignment) && validCol(n, i, assignment) && validBlock(n, i, j, assignment);
    }

    public static void validateTest(List<List<Integer>> partialAssignment,
                                    List<List<Integer>> solved) throws TestFailure {
        if (partialAssignment.size() != solved.size()) {
            throw new TestFailure("Initial cell assignment has been changed");
        }

        for (int i = 0; i < partialAssignment.size(); i++) {
            final List<Integer> br = partialAssignment.get(i);
            final List<Integer> sr = solved.get(i);
            if (br.size() != sr.size()) {
                throw new TestFailure("Initial cell assignment has been changed");
            }
            for (int j = 0; j < br.size(); j++) {
                if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j))) {
                    throw new TestFailure("Initial cell assignment has been changed");
                }
            }
        }

        final int blockSize = (int) Math.sqrt(solved.size());
        for (int i = 0; i < solved.size(); i++) {
            assertUniqueSeq(solved.get(i));
            assertUniqueSeq(gatherColumn(solved, i));
            assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
        }
    }

    private static boolean validRow(int n, int j, List<List<Integer>> partialAssignment) {
        return partialAssignment.stream().noneMatch(row -> row.get(j) == n);
    }

    private static boolean validCol(int n, int i, List<List<Integer>> partialAssignment) {
        return !partialAssignment.get(i).contains(n);
    }

    private static boolean validBlock(int n, int i, int j, List<List<Integer>> partialAssignment) {
        final int regionSize = BLOCK_SIZE;
        final int I = i / regionSize;
        final int J = j / regionSize;
        for (int a = 0; a < regionSize; ++a) {
            for (int b = 0; b < regionSize; ++b) {
                if (n == partialAssignment.get(regionSize * I + a).get(regionSize * J + b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void assertUniqueSeq(List<Integer> seq) throws TestFailure {
        final Set<Integer> seen = new HashSet<>();
        for (Integer x : seq) {
            if (x == 0) {
                throw new TestFailure("Cell left uninitialized");
            }
            if (x < 0 || x > seq.size()) {
                throw new TestFailure("Cell value out of range");
            }
            if (seen.contains(x)) {
                throw new TestFailure("Duplicate value in section");
            }
            seen.add(x);
        }
    }

    private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
        return data.stream().flatMap(t -> t.stream().skip(i).limit(1)).collect(Collectors.toList());
    }

    private static List<Integer> gatherSquareBlock(List<List<Integer>> data, int blockSize, int n) {
        final List<Integer> result = new ArrayList<>();
        final int blockX = n % blockSize;
        final int blockY = n / blockSize;
        for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
            for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
                result.add(data.get(i).get(j));
            }
        }

        return result;
    }

    private SudokuValidator() {}
}
