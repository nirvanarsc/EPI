package epi.Chapter17;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsStringInMatrix {

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid1(List<List<Integer>> grid, List<Integer> pattern) {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (calc(0, i, j, grid, pattern)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean calc(int start, int i, int j, List<List<Integer>> grid, List<Integer> pattern) {
        if (start == pattern.size()) {
            return true;
        }
        if (i < 0 || i == grid.size() || j < 0 || j == grid.get(0).size()
            || !grid.get(i).get(j).equals(pattern.get(start))) {
            return false;
        }

        return calc(start + 1, i - 1, j, grid, pattern) ||
               calc(start + 1, i + 1, j, grid, pattern) ||
               calc(start + 1, i, j - 1, grid, pattern) ||
               calc(start + 1, i, j + 1, grid, pattern);
    }

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid2(List<List<Integer>> grid, List<Integer> pattern) {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (calc(0, i, j, grid, pattern, new HashSet<>())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean calc(int start, int i, int j, List<List<Integer>> grid, List<Integer> pattern,
                               Set<Attempt> visited) {
        if (start == pattern.size()) {
            return true;
        }
        if (i < 0 || i == grid.size() || j < 0 || j == grid.get(0).size()
            || !grid.get(i).get(j).equals(pattern.get(start))
            || visited.contains(new Attempt(start, i, j))) {
            return false;
        }

        if (calc(start + 1, i - 1, j, grid, pattern, visited) ||
            calc(start + 1, i + 1, j, grid, pattern, visited) ||
            calc(start + 1, i, j - 1, grid, pattern, visited) ||
            calc(start + 1, i, j + 1, grid, pattern, visited)) {
            return true;
        }
        visited.add(new Attempt(start, i, j));
        return false;
    }

    static class Attempt {
        int start;
        int i;
        int j;

        Attempt(int start, int i, int j) {
            this.start = start;
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, i, j);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            final Attempt attempt = (Attempt) o;
            return start == attempt.start &&
                   i == attempt.i &&
                   j == attempt.j;
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringInMatrix() {}
}
