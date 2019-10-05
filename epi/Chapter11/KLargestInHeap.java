package epi.Chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class KLargestInHeap {

    static class Node {
        int idx;
        int val;

        Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    public static List<Integer> kLargestInBinaryHeap(List<Integer> integers, int k) {
        final PriorityQueue<Node> max = new PriorityQueue<>((x, y) -> Integer.compare(y.val, x.val));
        final List<Integer> res = new ArrayList<>();
        max.add(new Node(0, integers.get(0)));
        while (k-- > 0) {
            final Node curr = max.poll();
            res.add(curr.val);
            final int leftChild = 2 * curr.idx + 1;
            final int rightChild = 2 * curr.idx + 2;
            if (leftChild < integers.size()) { max.add(new Node(leftChild, integers.get(leftChild))); }
            if (rightChild < integers.size()) { max.add(new Node(rightChild, integers.get(rightChild))); }
        }

        return res;
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    };

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KLargestInHeap() {}
}
