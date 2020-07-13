package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;

import java.util.List;

@SuppressWarnings("MethodParameterNamingConvention")
public final class SortedArrayRemoveDups {

    // Returns the number of valid entries after deletion.
    public static int deleteDuplicates(List<Integer> A) {
        if (A.isEmpty()) {
            return 0;
        }
        int k = 0;
        for (int i = 1; i < A.size(); i++) {
            if (!A.get(i).equals(A.get(k))) {
                A.set(++k, A.get(i));
            }
        }
        return k + 1;
    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor, List<Integer> a) throws Exception {
        final int end = executor.run(() -> deleteDuplicates(a));
        return a.subList(0, end);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortedArrayRemoveDups() {}
}
