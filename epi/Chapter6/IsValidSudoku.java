package epi.Chapter6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsValidSudoku {

    @EpiTest(testDataFile = "is_valid_sudoku.tsv")
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        final Set<String> set = new HashSet<>();
        for (int i = 0; i < partialAssignment.size(); i++) {
            for (int j = 0; j < partialAssignment.get(0).size(); j++) {
                final int curr = partialAssignment.get(i).get(j);
                if (curr != 0) {
                    if (!set.add(curr + "in row" + i)) { return false; }
                    if (!set.add(curr + "in col" + j)) { return false; }
                    if (!set.add(curr + "in cube" + i / 3 + ',' + j / 3)) { return false; }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsValidSudoku() {}
}
