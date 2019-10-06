package epi.Chapter12;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchRowColSortedMatrix {

    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
    public static boolean matrixSearch(List<List<Integer>> integers, int x) {
        int row = 0, col = integers.get(0).size() - 1;
        while (row < integers.size() && col >= 0) {
            if (integers.get(row).get(col) < x) {
                row++;
            } else if (integers.get(row).get(col) == x) {
                return true;
            } else {
                col--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchRowColSortedMatrix() {}
}
