package epi.Chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class OnlineMedian {

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        final PriorityQueue<Integer> min = new PriorityQueue<>();
        final PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        final List<Double> res = new ArrayList<>();
        while (sequence.hasNext()) {
            final int curr = sequence.next();
            min.add(curr);
            max.add(min.remove());
            if (min.size() < max.size()) {
                min.add(max.remove());
            }
            res.add((res.size() % 2 == 0) ? 1.0 * min.element()
                                          : (0.5 * (max.element() + min.element())));
        }
        return res;
    }

    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private OnlineMedian() {}
}
