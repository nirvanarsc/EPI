package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class SunsetView {

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        final Deque<Integer> buildings = new LinkedList<>();
        final Deque<Integer> res = new LinkedList<>();
        int i = 0;
        while (sequence.hasNext()) {
            final Integer curr = sequence.next();
            while (!buildings.isEmpty() && buildings.peekFirst() <= curr) {
                buildings.pollFirst();
                res.pollFirst();
            }
            buildings.addFirst(curr);
            res.addFirst(i);
            i++;
        }

        return new ArrayList<>(res);
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SunsetView() {
    }
}
