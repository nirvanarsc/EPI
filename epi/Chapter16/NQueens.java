package epi.Chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class NQueens {

    @EpiTest(testDataFile = "n_queens.tsv")
    public static List<List<Integer>> nQueens(int n) {
        final List<List<Integer>> res = new ArrayList<>();
        solve(n, 0, new ArrayList<>(), res);
        return res;
    }

    private static void solve(int n, int row, List<Integer> placement, List<List<Integer>> res) {
        if (row == n) {
            res.add(new ArrayList<>(placement));
        } else {
            for (int col = 0; col < n; col++) {
                placement.add(col);
                if (isValid(row, placement)) {
                    solve(n, row + 1, placement, res);
                }
                placement.remove(placement.size() - 1);
            }
        }
    }

    private static boolean isValid(int row, List<Integer> placement) {
        for (int i = 0; i < row; i++) {
            final int diff = Math.abs(placement.get(i) - placement.get(row));
            if (diff == 0 || diff == row - i) {
                return false;
            }
        }
        return true;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.COMP;

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NQueens() {}
}
