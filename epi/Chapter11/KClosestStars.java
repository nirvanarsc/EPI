package epi.Chapter11;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.EpiTestExpectedType;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

public final class KClosestStars {

    @EpiUserType(ctorParams = {double.class, double.class, double.class})
    public static class Star implements Comparable<Star> {
        private final double x;
        private final double y;
        private final double z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distance() {
            return Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Star other) {
            return Double.compare(distance(), other.distance());
        }

        @Override
        public String toString() {
            return String.valueOf(distance());
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Star)) {
                return false;
            }
            return compareTo((Star) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {
        final PriorityQueue<Star> pq = new PriorityQueue<>(k, Comparator.reverseOrder());

        while (stars.hasNext()) {
            pq.add(stars.next());
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return new ArrayList<>(pq);
    }

    @EpiTest(testDataFile = "k_closest_stars.tsv")
    public static List<Star> findClosestKStarsWrapper(List<Star> stars, int k) {
        return findClosestKStars(stars.iterator(), k);
    }

    @EpiTestExpectedType
    public static List<Double> expectedType;

    @EpiTestComparator
    public static BiPredicate<List<Double>, List<Star>> comp =
            (expected, result) -> {
                if (expected.size() != result.size()) {
                    return false;
                }
                Collections.sort(result);
                for (int i = 0; i < result.size(); i++) {
                    if (!expected.get(i).equals(result.get(i).distance())) {
                        return false;
                    }
                }
                return true;
            };

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private KClosestStars() {
    }
}
