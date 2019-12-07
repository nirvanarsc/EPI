package epi.Chapter19;

import java.util.List;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class MatrixConnectedRegions {

    public static void flipColor(int x, int y, List<List<Boolean>> image) {
        dfs(x, y, image, image.get(x).get(y));
    }

    public static void dfs(int x, int y, List<List<Boolean>> image, boolean flag) {
        if (x < 0 || y < 0 || x >= image.size() || y >= image.get(0).size() || image.get(x).get(y) != flag) {
            return;
        }

        image.get(x).set(y, !flag);
        for (int[] dir : new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }) {
            dfs(x + dir[0], y + dir[1], image, flag);
        }
    }

    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                       int x,
                                                       int y,
                                                       List<List<Integer>> image) throws Exception {
        final List<List<Boolean>> matrix = image
                .stream()
                .map(i -> i.stream().map(j -> j == 1).collect(Collectors.toList()))
                .collect(Collectors.toList());

        executor.run(() -> flipColor(x, y, matrix));

        return matrix.stream()
                     .map(i -> i.stream().map(j -> j ? 1 : 0).collect(Collectors.toList()))
                     .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MatrixConnectedRegions() {}
}
