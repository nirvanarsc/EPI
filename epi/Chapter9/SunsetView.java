package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class SunsetView {

    private static final class Building {
        public Integer id;
        public Integer height;

        private Building(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }
    }

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        final Deque<Integer> buildings = new LinkedList<>();
        final Deque<Integer> res = new LinkedList<>();
        int i = 0;
        while (sequence.hasNext()) {
            final Integer curr = sequence.next();
            while (!buildings.isEmpty() && buildings.peekFirst() <= curr) {
                buildings.removeFirst();
                res.removeFirst();
            }
            buildings.addFirst(curr);
            res.addFirst(i++);
        }

        return new ArrayList<>(res);
    }

    public static List<Integer> examineBuildingsWithSunset2(Iterator<Integer> sequence) {

        final Deque<Building> buildings = new LinkedList<>();
        int i = 0;
        while (sequence.hasNext()) {
            final Integer curr = sequence.next();
            while (!buildings.isEmpty() && buildings.peekFirst().height <= curr) {
                buildings.removeFirst();
            }
            buildings.addFirst(new Building(i++, curr));
        }

        return buildings
                .stream()
                .map(building -> building.id)
                .collect(Collectors.toList());
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper2(List<Integer> sequence) {
        return examineBuildingsWithSunset2(sequence.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SunsetView() {
    }
}
