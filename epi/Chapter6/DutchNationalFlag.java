package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DutchNationalFlag {

    public enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> a) {
        final Color pivot = a.get(pivotIndex);
        int begin = 0, mid = a.size() - 1, end = a.size() - 1;
        while (begin <= mid) {
            if (a.get(begin).compareTo(pivot) < 0) {
                begin++;
            } else if (a.get(begin).compareTo(pivot) == 0) {
                Collections.swap(a, begin, mid--);
            } else {
                Collections.swap(a, begin, end--);
            }
            mid = Math.min(mid, end);
        }
    }

//    public static void dutchFlagPartition(int pivotIndex, List<Color> a) {
//        final Color pivot = a.get(pivotIndex);
//        int begin = 0, mid = 0, end = a.size() - 1;
//        while (mid <= end) {
//            if (a.get(mid).compareTo(pivot) < 0) {
//                Collections.swap(a, begin++, mid++);
//            } else if (a.get(mid).compareTo(pivot) == 0) {
//                mid++;
//            } else {
//                Collections.swap(a, mid, end--);
//            }
//        }
//    }

    @EpiTest(testDataFile = "dutch_national_flag.tsv")
    public static void dutchFlagPartitionWrapper(TimedExecutor executor, List<Integer> a, int pivotIdx) throws Exception {
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

    private DutchNationalFlag() {
    }
}
