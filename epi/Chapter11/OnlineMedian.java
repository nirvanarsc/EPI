package epi.Chapter11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class OnlineMedian {

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        final PriorityQueue<Double> min = new PriorityQueue<>(Comparator.naturalOrder());
        final PriorityQueue<Double> max = new PriorityQueue<>(Comparator.reverseOrder());
        final List<Double> res = new ArrayList<>();
        boolean odd = true;

        final double first = sequence.next();
        min.add(first);
        res.add(min.peek());

        if (!sequence.hasNext()) {
            return res;
        }

        final double second = sequence.next();
        if (first > second) {
            max.add(second);
        } else {
            max.add(min.poll());
            min.add(second);
        }
        res.add((min.peek() + max.peek()) / 2);

        while (sequence.hasNext()) {
            final double curr = sequence.next();
            if (min.size() < max.size() && curr > max.peek()) {
                min.add(curr);
            } else if (min.size() < max.size() && curr <= max.peek()) {
                min.add(max.poll());
                max.add(curr);
            } else if (min.size() >= max.size() && curr > min.peek()) {
                max.add(min.poll());
                min.add(curr);
            } else {
                max.add(curr);
            }

            res.add(odd ? max.peek() : (min.peek() + max.peek()) / 2);
            odd = !odd;
        }

        return res;
    }

    public static List<Double> onlineMedian2(Iterator<Integer> sequence) {
        final PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.naturalOrder());
        final PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        final List<Double> res = new ArrayList<>();

        while (sequence.hasNext()) {
            final int curr = sequence.next();
            if (min.isEmpty()) {
                min.add(curr);
            } else if (curr >= min.peek()) {
                min.add(curr);
            } else {
                max.add(curr);
            }

            if (min.size() > max.size() + 1) {
                max.add(min.poll());
            } else if (max.size() > min.size()) {
                min.add(max.poll());
            }

            res.add(min.size() == max.size() ? 0.5 * (min.peek() + max.peek()) : 1.0 * min.peek());
        }

        return res;
    }

    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper2(List<Integer> sequence) {
        return onlineMedian2(sequence.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private OnlineMedian() {
    }
}
