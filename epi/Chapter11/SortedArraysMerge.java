package epi.Chapter11;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public final class SortedArraysMerge {

    static class ArrayEntry {
        public Integer value;
        public Integer id;

        ArrayEntry(Integer value, Integer id) {
            this.value = value;
            this.id = id;
        }
    }

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        final PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(), Comparator.comparingInt(x -> x.value));
        final List<Iterator<Integer>> iterators = new ArrayList<>(sortedArrays.size());
        final List<Integer> result = new ArrayList<>();

        sortedArrays.forEach(arr -> iterators.add(arr.iterator()));

        for (int i = 0; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iterators.get(i).next(), i));
            }
        }

        while (!minHeap.isEmpty()) {
            final ArrayEntry headEntry = minHeap.poll();
            result.add(headEntry.value);
            if (iterators.get(headEntry.id).hasNext()) {
                minHeap.add(new ArrayEntry(iterators.get(headEntry.id).next(), headEntry.id));
            }
        }

        return result;
    }

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays2(List<List<Integer>> sortedArrays) {
        final PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
        final List<Integer> result = new ArrayList<>();

        sortedArrays.forEach(minHeap::addAll);

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortedArraysMerge() {
    }
}
