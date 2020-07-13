package epi.Chapter6;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PascalTriangle {

    @EpiTest(testDataFile = "pascal_triangle.tsv")
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        final List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        res.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            final List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                final int prevL = j > 0 ? res.get(i - 1).get(j - 1) : 0;
                final int prevU = j < res.get(i - 1).size() ? res.get(i - 1).get(j) : 0;
                row.add(prevL + prevU);
            }
            res.add(row);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PascalTriangle() {}
}
