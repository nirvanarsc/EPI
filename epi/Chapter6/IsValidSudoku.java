package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class IsValidSudoku {

    private static final int SUDOKU_SIZE = 9;
    private static final int SUDOKU_CUBE = (int) Math.sqrt(SUDOKU_SIZE);

    @EpiTest(testDataFile = "is_valid_sudoku.tsv")
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        final Set<Integer> set = new HashSet<>();

        if (!checkRowOrCol(partialAssignment, set, true)) {
            return false;
        }

        if (!checkRowOrCol(partialAssignment, set, false)) {
            return false;
        }

        for (int i = 0; i < SUDOKU_SIZE; i += SUDOKU_CUBE) {
            for (int j = 0; j < SUDOKU_SIZE; j += SUDOKU_CUBE) {
                if (!checkCube(partialAssignment, set, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkRowOrCol(List<List<Integer>> partialAssignment, Set<Integer> set, boolean horizontal) {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                final Integer curr = horizontal ? partialAssignment.get(i).get(j) : partialAssignment.get(j).get(i);
                if (curr != 0) {
                    if (set.contains(curr)) {
                        return false;
                    }
                    set.add(curr);
                }
            }
            set.clear();
        }
        return true;
    }

    private static boolean checkCube(List<List<Integer>> partialAssignment, Set<Integer> set, int i, int j) {
        for (int k = i; k < i + SUDOKU_CUBE; k++) {
            for (int n = j; n < j + SUDOKU_CUBE; n++) {
                final Integer curr = partialAssignment.get(k).get(n);
                if (curr != 0) {
                    if (set.contains(curr)) {
                        return false;
                    }
                    set.add(curr);
                }
            }
        }
        set.clear();
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.IsValidSudoku");
    }

    private IsValidSudoku() {
    }
}
