package epi.Chapter11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SortedArraysMerge {

    static class Pair {
        Iterator<Integer> it;
        int num;

        Pair(Iterator<Integer> it, int num) {
            this.it = it;
            this.num = num;
        }
    }

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        final PriorityQueue<Pair> pq = new PriorityQueue<>(sortedArrays.size(),
                                                              Comparator.comparingInt(a -> a.num));
        for (int i = 0; i < sortedArrays.size(); i++) {
            final Iterator<Integer> iterator = sortedArrays.get(i).iterator();
            if (iterator.hasNext()) {
                pq.offer(new Pair(iterator, iterator.next()));
            }
        }
        final List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            final Pair curr = pq.remove();
            res.add(curr.num);
            if (curr.it.hasNext()) {
                pq.offer(new Pair(curr.it, curr.it.next()));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortedArraysMerge() {}
}
