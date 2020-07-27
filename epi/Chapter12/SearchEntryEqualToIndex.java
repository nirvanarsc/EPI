package epi.Chapter12;

import java.util.Arrays;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

@SuppressWarnings("MethodParameterNamingConvention")
public final class SearchEntryEqualToIndex {

    public static int searchEntryEqualToItsIndex(List<Integer> A) {
        if (A.isEmpty()) {
            return -1;
        }
        int lo = 0;
        int hi = A.size() - 1;
        while (lo < hi) {
            final int mid = lo + hi >>> 1;
            if (A.get(mid) < mid) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return A.get(lo) == lo ? lo : -1;
    }

    @SuppressWarnings("TailRecursion")
    // O(n) -- can't do better
    public static int searchEntryEqualToItsIndexDups(List<Integer> arr, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        final int mid = lo + hi >>> 1;
        final int midValue = arr.get(mid);
        if (mid == midValue) {
            return mid;
        }
        final int left = searchEntryEqualToItsIndexDups(arr, lo, Math.min(midValue, mid - 1));
        if (left >= 0) {
            return left;
        }
        return searchEntryEqualToItsIndexDups(arr, Math.max(midValue, mid + 1), hi);
    }

    @EpiTest(testDataFile = "search_entry_equal_to_index.tsv")
    public static void searchEntryEqualToItsIndexWrapper(TimedExecutor executor, List<Integer> A)
            throws Exception {
        final int result = executor.run(() -> searchEntryEqualToItsIndex(A));

        if (result != -1) {
            if (A.get(result) != result) {
                throw new TestFailure("Entry does not equal to its index");
            }
        } else {
            for (int i = 0; i < A.size(); ++i) {
                if (A.get(i) == i) {
                    throw new TestFailure("Entries equal to their index exist");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                searchEntryEqualToItsIndexDups(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2), 0, 12));
        TestRunner.run(args);
    }

    private SearchEntryEqualToIndex() {}
}
