package epi.Chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SpiralOrderingSegments {

    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    @EpiTest(testDataFile = "spiral_ordering_segments.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        if (squareMatrix.isEmpty()) {
            return Collections.emptyList();
        }
        int dir = 0, x = 0, y = 0;
        int currL = squareMatrix.get(0).size() - 1;
        final List<Integer> res = new ArrayList<>();
        while (currL > 0) {
            for (int i = 0; i < currL; i++) {
                res.add(squareMatrix.get(x).get(y));
                x += DIRS[dir][0];
                y += DIRS[dir][1];
            }
            dir = (dir + 1) % 4;
            if (dir == 0) {
                currL -= 2;
                x++;
                y++;
            }
        }
        if (squareMatrix.size() % 2 != 0) {
            res.add(squareMatrix.get(x).get(y));
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SpiralOrderingSegments() {}
}
