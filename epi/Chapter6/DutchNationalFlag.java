package epi.Chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

@SuppressWarnings("MethodParameterNamingConvention")
public final class DutchNationalFlag {

    public enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
        int lo = 0;
        int mid = 0;
        int hi = A.size() - 1;
        final Color p = A.get(pivotIndex);
        while (mid <= hi) {
            if (A.get(mid).compareTo(p) < 0) {
                Collections.swap(A, lo, mid);
                lo++;
                mid++;
            } else if (A.get(mid).compareTo(p) == 0) {
                mid++;
            } else {
                Collections.swap(A, mid, hi);
                hi--;
            }
        }
    }

    @EpiTest(testDataFile = "dutch_national_flag.tsv")
    public static void dutchFlagPartitionWrapper(TimedExecutor executor, List<Integer> a, int pivotIdx)
            throws Exception {
        final List<Color> colors = new ArrayList<>();
        final int[] count = new int[3];

        final Color[] C = Color.values();
        for (int i = 0; i < a.size(); i++) {
            count[a.get(i)]++;
            colors.add(C[a.get(i)]);
        }

        final Color pivot = colors.get(pivotIdx);
        executor.run(() -> dutchFlagPartition(pivotIdx, colors));

        int i = 0;
        while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        if (i != colors.size()) {
            System.out.println(colors);
            throw new TestFailure("Not partitioned after " + i + "th element");
        } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
            throw new TestFailure("Some elements are missing from original array");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DutchNationalFlag() {}
}
