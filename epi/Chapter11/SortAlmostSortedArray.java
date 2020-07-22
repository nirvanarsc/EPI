package epi.Chapter11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SortAlmostSortedArray {

    public static List<Integer> sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        final List<Integer> res = new ArrayList<>();
        while (sequence.hasNext()) {
            pq.offer(sequence.next());
            if (pq.size() > k) {
                res.add(pq.remove());
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }

    @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
    public static List<Integer> sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
        return sortApproximatelySortedData(sequence.iterator(), k);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortAlmostSortedArray() {}
}
