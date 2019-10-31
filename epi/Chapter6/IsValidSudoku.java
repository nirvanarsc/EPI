package epi.Chapter6;

import static epi.utils.SudokuValidator.BLOCK_SIZE;
import static epi.utils.SudokuValidator.SUDOKU_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsValidSudoku {

    @EpiTest(testDataFile = "is_valid_sudoku.tsv")
    public static boolean isValidSudoku(List<List<Integer>> assignment) {
        final Set<Integer> set = new HashSet<>();

        if (!validRowCol(assignment, set, true) || !validRowCol(assignment, set, false)) {
            return false;
        }

        for (int i = 0; i < SUDOKU_SIZE; i += BLOCK_SIZE) {
            for (int j = 0; j < SUDOKU_SIZE; j += BLOCK_SIZE) {
                if (!checkCube(assignment, set, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean validRowCol(List<List<Integer>> assignment, Set<Integer> set, boolean row) {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                final Integer curr = row ? assignment.get(i).get(j) : assignment.get(j).get(i);
                if (curr != 0 && set.contains(curr)) {
                    return false;
                }
                set.add(curr);
            }
            set.clear();
        }
        return true;
    }

    private static boolean checkCube(List<List<Integer>> partialAssignment, Set<Integer> set, int i, int j) {
        for (int k = i; k < i + BLOCK_SIZE; k++) {
            for (int n = j; n < j + BLOCK_SIZE; n++) {
                final Integer curr = partialAssignment.get(k).get(n);
                if (curr != 0 && set.contains(curr)) {
                    return false;
                }
                set.add(curr);
            }
        }
        set.clear();
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsValidSudoku() {
    }
}
