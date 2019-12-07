package epi.Chapter19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class SearchMaze {

    @EpiUserType(ctorParams = { int.class, int.class })
    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Coordinate other = (Coordinate) o;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public enum Color {WHITE, BLACK}

    public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate s, Coordinate e) {
        final List<Coordinate> path = new ArrayList<>(Collections.singletonList(s));
        if (search(maze, s, e, path)) {
            return path;
        }
        return Collections.emptyList();
    }

    public static boolean search(List<List<Color>> maze, Coordinate s, Coordinate e, List<Coordinate> path) {
        if (s.x == e.x && s.y == e.y) {
            return true;
        }

        if (s.x < 0 || s.y < 0 || s.x >= maze.size() || s.y >= maze.get(0).size()
            || maze.get(s.x).get(s.y) == Color.BLACK) {
            return false;
        }

        maze.get(s.x).set(s.y, Color.BLACK);
        for (int[] dir : new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }) {
            final Coordinate next = new Coordinate(s.x + dir[0], s.y + dir[1]);
            path.add(next);
            if (search(maze, next, e, path)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        return false;
    }

    public static boolean pathElementIsFeasible(List<List<Integer>> maze, Coordinate prev, Coordinate cur) {
        if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
              cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
            return false;
        }
        return cur.x == prev.x + 1 && cur.y == prev.y ||
               cur.x == prev.x - 1 && cur.y == prev.y ||
               cur.x == prev.x && cur.y == prev.y + 1 ||
               cur.x == prev.x && cur.y == prev.y - 1;
    }

    @EpiTest(testDataFile = "search_maze.tsv")
    public static boolean searchMazeWrapper(List<List<Integer>> maze, Coordinate s, Coordinate e)
            throws TestFailure {
        final List<List<Color>> colored = new ArrayList<>();
        for (List<Integer> col : maze) {
            final List<Color> tmp = new ArrayList<>();
            for (Integer i : col) {
                tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
            }
            colored.add(tmp);
        }
        final List<Coordinate> path = searchMaze(colored, s, e);
        if (path.isEmpty()) {
            return s.equals(e);
        }

        if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
            throw new TestFailure("Path doesn't lay between start and end points");
        }

        for (int i = 1; i < path.size(); i++) {
            if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
                throw new TestFailure("Path contains invalid segments");
            }
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchMaze() {}
}
