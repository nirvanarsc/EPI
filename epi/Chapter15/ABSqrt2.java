package epi.Chapter15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ABSqrt2 {

    static final double SQRT_2 = 1.4142135623730951;

    static class ABRoot2 implements Comparable<ABRoot2> {
        public int a, b;
        public double val;

        ABRoot2(int a, int b) {
            this.a = a;
            this.b = b;
            val = a + b * SQRT_2;
        }

        @Override
        public int compareTo(ABRoot2 o) {
            return Double.compare(val, o.val);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ABRoot2)) { return false; }
            return compareTo((ABRoot2) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, val);
        }
    }

    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    public static List<Double> generateFirstKABRoot2(int k) {
        final List<Double> res = new ArrayList<>();
        final NavigableSet<ABRoot2> set = new TreeSet<>(Collections.singleton(new ABRoot2(0, 0)));
        while (res.size() < k) {
            final ABRoot2 smallest = set.pollFirst();
            assert smallest != null;
            res.add(smallest.val);
            set.add(new ABRoot2(smallest.a + 1, smallest.b));
            set.add(new ABRoot2(smallest.a, smallest.b + 1));
        }
        return res;
    }

    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    public static List<Double> generateFirstKABRoot2Linear(int k) {
        final List<ABRoot2> result = new ArrayList<>(Collections.singletonList(new ABRoot2(0, 0)));
        int i = 0, j = 0;
        for (int n = 1; n < k; n++) {
            final ABRoot2 iPlus1 = new ABRoot2(result.get(i).a + 1, result.get(i).b);
            final ABRoot2 jPlusSqrt2 = new ABRoot2(result.get(j).a, result.get(j).b + 1);
            result.add(iPlus1.val < jPlusSqrt2.val ? iPlus1 : jPlusSqrt2);
            if (iPlus1.compareTo(result.get(result.size() - 1)) == 0) { ++i; }
            if (jPlusSqrt2.compareTo(result.get(result.size() - 1)) == 0) { ++j; }
        }
        return result.stream().map(root2 -> root2.val).collect(Collectors.toList());
    }

    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    public static List<Double> generateFirstKABSqrt2(int k) {
        final NavigableSet<Double> set = new TreeSet<>();
        for (int a = 0; a < k; a++) {
            for (int b = 0; b < k; b++) {
                final double curr = a + b * SQRT_2;
                if (set.size() < k) {
                    set.add(curr);
                } else if (curr < set.last()) {
                    set.pollLast();
                    set.add(curr);
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ABSqrt2() {}
}
