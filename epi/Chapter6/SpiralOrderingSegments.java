package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.ArrayList;
import java.util.List;

public final class SpiralOrderingSegments {

    @EpiTest(testDataFile = "spiral_ordering_segments.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        final List<Integer> spiralOrdering = new ArrayList<>();
        matrixInSpiralOrder(squareMatrix, spiralOrdering, 0);
        return spiralOrdering;
    }

    public static void matrixInSpiralOrder(List<List<Integer>> squareMatrix, List<Integer> res, int n) {
        if (n == squareMatrix.size() / 2) {
            if ((squareMatrix.size() & 1) == 1) {
                res.add(squareMatrix.get(n).get(n));
            }
            return;
        }

        for (int i = n; i < squareMatrix.size() - 1 - n; i++) {
            res.add(squareMatrix.get(n).get(i));
        }
        for (int i = n; i < squareMatrix.size() - 1 - n; i++) {
            res.add(squareMatrix.get(i).get(squareMatrix.size() - 1 - n));
        }
        for (int i = squareMatrix.size() - 1 - n; i > n; i--) {
            res.add(squareMatrix.get(squareMatrix.size() - 1 - n).get(i));
        }
        for (int i = squareMatrix.size() - 1 - n; i > n; i--) {
            res.add(squareMatrix.get(i).get(n));
        }

        matrixInSpiralOrder(squareMatrix, res, n + 1);
    }

    @EpiTest(testDataFile = "spiral_ordering_segments.tsv")
    public static List<Integer> matrixInSpiralOrder2(List<List<Integer>> squareMatrix) {
        final List<Integer> spiralOrdering = new ArrayList<>();
        final int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0, x = 0, y = 0;

        for (int i = 0; i < squareMatrix.size() * squareMatrix.size(); i++) {
            spiralOrdering.add(squareMatrix.get(x).get(y));
            squareMatrix.get(x).set(y, 0);
            int nextX = x + shift[dir][0], nextY = y + shift[dir][1];
            if (nextX < 0
                    || nextX >= squareMatrix.size()
                    || nextY < 0
                    || nextY >= squareMatrix.size()
                    || squareMatrix.get(nextX).get(nextY) == 0) {
                dir = (dir + 1) % 4;
                nextX = x + shift[dir][0];
                nextY = y + shift[dir][1];
            }
            x = nextX;
            y = nextY;
        }

        return spiralOrdering;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SpiralOrderingSegments() {
    }
}
