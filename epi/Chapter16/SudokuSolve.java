package epi.Chapter16;

import static epi.utils.SudokuValidator.SUDOKU_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.SudokuValidator;
import epi.utils.TestRunner;

public final class SudokuSolve {

    public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
        return compute(0, 0, partialAssignment);
    }

    private static boolean compute(int i, int j, List<List<Integer>> assignment) {
        if (i == SUDOKU_SIZE) {
            i %= SUDOKU_SIZE;
            if (++j == SUDOKU_SIZE) {
                return true;
            }
        }

        if (assignment.get(i).get(j) != 0) {
            return compute(i + 1, j, assignment);
        }

        for (int k = 1; k <= SUDOKU_SIZE; k++) {
            if (SudokuValidator.isAssignmentValid(k, i, j, assignment)) {
                assignment.get(i).set(j, k);
                if (compute(i + 1, j, assignment)) {
                    return true;
                }
                assignment.get(i).set(j, 0);
            }
        }
        return false;
    }

    @EpiTest(testDataFile = "sudoku_solve.tsv")
    public static void solveSudokuWrapper(TimedExecutor executor,
                                          List<List<Integer>> partialAssignment) throws Exception {
        final List<List<Integer>> solved = new ArrayList<>(Collections.nCopies(SUDOKU_SIZE, new ArrayList<>()));
        Collections.copy(solved, partialAssignment);

        executor.run(() -> solveSudoku(solved));

        SudokuValidator.validateTest(partialAssignment, solved);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SudokuSolve() {}
}
