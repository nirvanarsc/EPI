package epi.Chapter6;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PascalTriangle {

    @EpiTest(testDataFile = "pascal_triangle.tsv")
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        final List<List<Integer>> pascal = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                pascal.add(Collections.nCopies(1, 1));
            } else {
                final List<Integer> prevRow = pascal.get(i - 1);
                final List<Integer> newRow = new ArrayList<>();
                newRow.add(1);
                for (int n = 1; n < prevRow.size(); n++) {
                    newRow.add(prevRow.get(n - 1) + prevRow.get(n));
                }
                newRow.add(1);
                pascal.add(newRow);
            }
        }
        return pascal;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PascalTriangle() {
    }
}
