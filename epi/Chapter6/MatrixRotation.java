package epi.Chapter6;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MatrixRotation {

    static class RotatedMatrix {
        private final List<List<Integer>> squareMatrix;

        RotatedMatrix(List<List<Integer>> squareMatrix) {
            this.squareMatrix = squareMatrix;
        }

        public int readEntry(int i, int j) {
            return squareMatrix.get(squareMatrix.size() - 1 - j).get(i);
        }

        public void writeEntry(int i, int j, int v) {
            squareMatrix.get(squareMatrix.size() - 1 - j).set(i, v);
        }
    }

    public static void useRotatedMatrix() {
        final List<List<Integer>> m = new ArrayList<>();
        m.add(Arrays.asList(1, 2, 3, 4));
        m.add(Arrays.asList(5, 6, 7, 8));
        m.add(Arrays.asList(9, 10, 11, 12));
        m.add(Arrays.asList(13, 14, 15, 16));

        final RotatedMatrix r = new RotatedMatrix(m);
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.size(); j++) {
                System.out.print(r.readEntry(i, j) + "\t");
            }
            System.out.println();
        }
    }

    public static void rotateMatrix(List<List<Integer>> squareMatrix) {
        final int n = squareMatrix.size();

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                final int p = squareMatrix.get(i).get(j);
                final int q = squareMatrix.get(j).get(n - 1 - i);
                final int r = squareMatrix.get(n - 1 - i).get(n - 1 - j);
                final int s = squareMatrix.get(n - 1 - j).get(i);
                squareMatrix.get(i).set(j, s);
                squareMatrix.get(j).set(n - 1 - i, p);
                squareMatrix.get(n - 1 - i).set(n - 1 - j, q);
                squareMatrix.get(n - 1 - j).set(i, r);
            }
        }
    }

    @EpiTest(testDataFile = "matrix_rotation.tsv")
    public static List<List<Integer>> rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
        rotateMatrix(squareMatrix);
        return squareMatrix;
    }

    public static void main(String[] args) {
        useRotatedMatrix();
        TestRunner.run(args);
    }

    private MatrixRotation() {}
}
