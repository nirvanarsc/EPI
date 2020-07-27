package epi.Chapter12;

import java.util.List;
import java.util.Objects;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class SearchForMinMaxInArray {

    @EpiUserType(ctorParams = { Integer.class, Integer.class })
    public static class MinMax {
        public Integer smallest;
        public Integer largest;

        public MinMax(Integer smallest, Integer largest) {
            this.smallest = smallest;
            this.largest = largest;
        }

        private static MinMax minMax(Integer a, Integer b) {
            return b < a ? new MinMax(b, a) : new MinMax(a, b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final MinMax minMax = (MinMax) o;

            if (!smallest.equals(minMax.smallest)) {
                return false;
            }
            return largest.equals(minMax.largest);
        }

        @Override
        public String toString() {
            return "min: " + smallest + ", max: " + largest;
        }

        @Override
        public int hashCode() {
            return Objects.hash(smallest, largest);
        }
    }

    @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")
    public static MinMax findMinMax(List<Integer> integers) {
        if (integers.size() <= 1) {
            return new MinMax(integers.get(0), integers.get(0));
        }

        MinMax globalMinMax = MinMax.minMax(integers.get(0), integers.get(1));
        for (int i = 2; i + 1 < integers.size(); i += 2) {
            final MinMax localMinMax = MinMax.minMax(integers.get(i), integers.get(i + 1));
            globalMinMax = new MinMax(Math.min(globalMinMax.smallest, localMinMax.smallest),
                                      Math.max(globalMinMax.largest, localMinMax.largest));
        }

        if ((integers.size() % 2) != 0) {
            globalMinMax = new MinMax(Math.min(globalMinMax.smallest, integers.get(integers.size() - 1)),
                                      Math.max(globalMinMax.largest, integers.get(integers.size() - 1)));
        }
        return globalMinMax;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchForMinMaxInArray() {}
}
