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
        if (partialAssignment.stream().noneMatch(row -> row.stream().anyMatch(cell -> cell == 0))) {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (partialAssignment.get(i).get(j) == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (SudokuValidator.isAssignmentValid(k, i, j, partialAssignment)) {
                            partialAssignment.get(i).set(j, k);
                            if (solveSudoku(partialAssignment)) {
                                return true;
                            }
                        }
                    }
                    partialAssignment.get(i).set(j, 0);
                    return false;
                }
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
