package epi.Chapter19;

import java.util.List;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MatrixEnclosedRegions {

    public static void fillSurroundedRegions(List<List<Character>> board) {
        final int lastRow = board.size() - 1;
        final int lastCol = board.get(0).size() - 1;
        final List<List<Boolean>> visited = board
                .stream()
                .map(i -> i.stream().map(j -> false).collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (int row = 0; row < board.size(); row++) {
            if (board.get(row).get(0).equals('W') && !visited.get(row).get(0)) {
                markBoundaryRegion(row, 0, visited, board);
            }

            if (board.get(row).get(lastCol).equals('W') && !visited.get(row).get(lastCol)) {
                markBoundaryRegion(row, lastCol, visited, board);
            }
        }

        for (int col = 0; col < board.get(0).size(); col++) {
            if (board.get(0).get(col).equals('W') && !visited.get(0).get(col)) {
                markBoundaryRegion(0, col, visited, board);
            }
            if (board.get(lastRow).get(col).equals('W') && !visited.get(lastRow).get(col)) {
                markBoundaryRegion(lastRow, col, visited, board);
            }
        }

        for (int i = 0; i < lastRow; i++) {
            for (int j = 0; j < lastCol; j++) {
                if (board.get(i).get(j) == 'W' && !visited.get(i).get(j)) {
                    board.get(i).set(j, 'B');
                }
            }
        }
    }

    private static void markBoundaryRegion(int i, int j, List<List<Boolean>> visited,
                                           List<List<Character>> board) {
        if (i < 0 || j < 0 || i >= visited.size() || j >= visited.get(0).size()
            || visited.get(i).get(j)
            || board.get(i).get(j) == 'B') {
            return;
        }

        visited.get(i).set(j, true);
        for (int[] dir : new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }) {
            markBoundaryRegion(i + dir[0], j + dir[1], visited, board);
        }
    }

    @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
    public static List<List<Character>> fillSurroundedRegionsWrapper(List<List<Character>> board) {
        fillSurroundedRegions(board);
        return board;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MatrixEnclosedRegions() {}
}
