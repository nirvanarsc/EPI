package epi.Chapter12;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class KthLargestInArray {

    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> integers) {
        final PriorityQueue<Integer> min = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++) {
            min.add(integers.get(i));
        }

        for (int i = k; i < integers.size(); i++) {
            if (!min.isEmpty() && min.peek() < integers.get(i)) {
                min.poll();
                min.add(integers.get(i));
            }
        }

        return min.peek();
    }

    @EpiTest(testDataFile = "kth_largest_in_array.tsv")
    public static int findKthLargestInPlace(int k, List<Integer> integers) {
        final int largestIndex = findKthLargestIndex(k, integers, Comparator.comparingInt(i -> i));
        return integers.get(largestIndex);
    }

    public static <T> int findKthLargestIndex(int k, List<T> integers, Comparator<T> comparator) {
        final Random random = new Random(0);
        int low = 0, high = integers.size() - 1;
        while (low <= high) {
            final int pivotIdx = random.nextInt(high - low + 1) + low;
            final int newIdx = dutchFlagPartitionIndex(pivotIdx, integers, low, high, comparator);
            if (newIdx < k - 1) {
                low = newIdx + 1;
            } else if (newIdx == k - 1) {
                return newIdx;
            } else {
                high = newIdx - 1;
            }
        }

        return low;
    }

    public static <T> int dutchFlagPartitionIndex(int pivotIdx, List<T> a, int begin, int end,
                                                  Comparator<T> comparator) {
        final T pivot = a.get(pivotIdx);
        int mid = begin;
        while (mid <= end) {
            if (comparator.compare(a.get(mid), pivot) > 0) {
                Collections.swap(a, begin++, mid++);
            } else if (a.get(mid) == pivot) {
                mid++;
            } else {
                Collections.swap(a, mid, end--);
            }
        }

        return mid - 1;
    }

//    private static int partitionAroundPivot(int pivotIdx, List<Integer> integers, int start, int end) {
//        final int pivot = integers.get(pivotIdx);
//        int newPivotIdx = start;
//        Collections.swap(integers, pivotIdx, end);
//        for (int i = start; i < end; ++i) {
//            if (integers.get(i) > pivot) {
//                Collections.swap(integers, i, newPivotIdx++);
//            }
//        }
//        Collections.swap(integers, end, newPivotIdx);
//        return newPivotIdx;
//    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KthLargestInArray() {}
}