package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.List;

public final class SortedArrayRemoveDups {

//    public static int deleteDuplicates(List<Integer> a) {
//        if (a.isEmpty()) {
//            return 0;
//        }
//        int idx = 1;
//        for (int i = 0, j = 1; j < a.size(); i++, idx++) {
//            if (a.get(i).equals(a.get(j))) {
//                while (j < a.size() && a.get(i).equals(a.get(j))) {
//                    j++;
//                }
//                if (j == a.size()) {
//                    break;
//                }
//                a.set(i + 1, a.get(j));
//            } else {
//                j++;
//            }
//        }
//
//        return idx;
//    }

    // Returns the number of valid entries after deletion.
    public static int deleteDuplicates(List<Integer> a) {
        if (a.isEmpty()) {
            return 0;
        }
        int idx = 1;
        for (int i = 1; i < a.size(); i++) {
            if (!a.get(idx - 1).equals(a.get(i))) {
                a.set(idx++, a.get(i));
            }
        }

        return idx;
    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor, List<Integer> a) throws Exception {
        final int end = executor.run(() -> deleteDuplicates(a));
        return a.subList(0, end);
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.SortedArrayRemoveDups");
    }

    private SortedArrayRemoveDups() {
    }
}
