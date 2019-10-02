package epi.Chapter11;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public final class SortAlmostSortedArray {

    public static List<Integer> sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.naturalOrder());
        final List<Integer> integers = new ArrayList<>();
        while (sequence.hasNext()) {
            final Integer next = sequence.next();
            if (pq.size() == k) {
                while (!pq.isEmpty()) {
                    integers.add(pq.poll());
                }
            }
            pq.add(next);
            if (!sequence.hasNext()) {
                while (!pq.isEmpty()) {
                    integers.add(pq.poll());
                }
            }
        }

        return integers;
    }

    public static List<Integer> sortApproximatelySortedData2(Iterator<Integer> sequence, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.naturalOrder());
        final List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < k && sequence.hasNext(); i++) {
            pq.add(sequence.next());
        }

        while (sequence.hasNext()) {
            pq.add(sequence.next());
            integers.add(pq.poll());
        }

        while (!pq.isEmpty()) {
            integers.add(pq.poll());
        }

        return integers;
    }

    @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
    public static List<Integer> sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
        return sortApproximatelySortedData(sequence.iterator(), k);
    }

    @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
    public static List<Integer> sortApproximatelySortedDataWrapper2(List<Integer> sequence, int k) {
        return sortApproximatelySortedData2(sequence.iterator(), k);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortAlmostSortedArray() {
    }
}
