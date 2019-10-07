package epi.Chapter12;

import java.util.List;
import java.util.stream.IntStream;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class SearchForMissingElement {

    @EpiUserType(ctorParams = { Integer.class, Integer.class })
    public static class DuplicateAndMissing {
        public Integer duplicate;
        public Integer missing;

        public DuplicateAndMissing(Integer duplicate, Integer missing) {
            this.duplicate = duplicate;
            this.missing = missing;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final DuplicateAndMissing other = (DuplicateAndMissing) o;

            if (!duplicate.equals(other.duplicate)) {
                return false;
            }
            return missing.equals(other.missing);
        }

        @Override
        public int hashCode() {
            int result = duplicate.hashCode();
            result = 31 * result + missing.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "duplicate: " + duplicate + ", missing: " + missing;
        }
    }

    @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")
    public static DuplicateAndMissing findDuplicateMissing(List<Integer> integers) {
        final int missing = AbsentValueArray.findMissingElement(integers);
        final int sum1 = integers.stream().mapToInt(x -> x).sum();
        final int sum2 = IntStream.range(0, integers.size()).sum();
        return new DuplicateAndMissing(sum1 + missing - sum2, missing);
    }

    @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")
    public static DuplicateAndMissing findDuplicateMissing2(List<Integer> integers) {
        int missXORDup = 0, missOrDup = 0;
        for (int i = 0; i < integers.size(); i++) {
            missXORDup ^= i ^ integers.get(i);
        }

        final int differBit = missXORDup & -missXORDup;
        for (int i = 0; i < integers.size(); i++) {
            if ((i & differBit) != 0) {
                missOrDup ^= i;
            }
            if ((integers.get(i) & differBit) != 0) {
                missOrDup ^= integers.get(i);
            }
        }

        for (int a : integers) {
            if (a == missOrDup) {
                return new DuplicateAndMissing(missOrDup, missOrDup ^ missXORDup);
            }
        }

        return new DuplicateAndMissing(missOrDup ^ missXORDup, missOrDup);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchForMissingElement() {}
}
