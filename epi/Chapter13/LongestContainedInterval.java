package epi.Chapter13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LongestContainedInterval {

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange(List<Integer> list) {
        final Set<Integer> unprocessed = new HashSet<>(list);
        int res = 0;
        while (!unprocessed.isEmpty()) {
            final Integer next = unprocessed.iterator().next();
            int upperBound = next + 1;
            int lowerBound = next - 1;
            unprocessed.remove(next);
            while (unprocessed.contains(lowerBound)) {
                unprocessed.remove(lowerBound);
                lowerBound--;
            }
            while (unprocessed.contains(upperBound)) {
                unprocessed.remove(upperBound);
                upperBound++;
            }
            res = Math.max(res, upperBound - lowerBound - 1);
        }
        return res;
    }

    public static class UnionFind {
        private final int[] parent;
        private final int[] size;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            // path compression
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            final int rootP = find(p);
            final int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            // union by size
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int count() { return count; }

        public int[] size() { return size; }
    }

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRangeUF(List<Integer> list) {
        final Map<Integer, Integer> map = new HashMap<>();
        final tt.UnionFind uf = new tt.UnionFind(list.size());
        for (int i = 0; i < list.size(); i++) {
            final int curr = list.get(i);
            if (map.containsKey(curr)) { continue; }

            uf.union(i, map.getOrDefault(curr - 1, i));
            uf.union(i, map.getOrDefault(curr + 1, i));
            map.put(curr, i);
        }
        int res = 0;
        for (int size : uf.size()) {
            res = Math.max(res, size);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LongestContainedInterval() {}
}
