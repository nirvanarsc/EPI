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
        dfs(n, 0, res, new ArrayList<>());
        return res;
    }

    private static void dfs(int n, int row, List<List<Integer>> res, List<Integer> curr) {
        if (row == n) {
            res.add(new ArrayList<>(curr));
        }
        for (int col = 0; col < n; col++) {
            if (isValid(curr, row, col)) {
                curr.add(col);
                dfs(n, row + 1, res, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private static boolean isValid(List<Integer> curr, int row, int col) {
        for (int i = 1; i <= row; i++) {
            final int prev = curr.get(row - i);
            if (prev == col || prev == col - i || prev == col + i) {
                return false;
            }
        }
        return true;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.getComp(false);

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NQueens() {}
}
