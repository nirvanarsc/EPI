package epi.Chapter12;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class SearchEntryEqualToIndex {

    public static int searchEntryEqualToItsIndex(List<Integer> integers) {
        int low = 0, high = integers.size() - 1;
        while (low <= high) {
            final int mid = (low + high) >>> 1;
            if (integers.get(mid) > mid) {
                high = mid - 1;
            } else if (integers.get(mid) == mid) {
                return mid;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    @EpiTest(testDataFile = "search_entry_equal_to_index.tsv")
    public static void searchEntryEqualToItsIndexWrapper(TimedExecutor executor,
                                                         List<Integer> integers) throws Exception {
        final int result = executor.run(() -> searchEntryEqualToItsIndex(integers));

        if (result != -1) {
            if (integers.get(result) != result) {
                throw new TestFailure("Entry does not equal to its index");
            }
        } else {
            for (int i = 0; i < integers.size(); ++i) {
                if (integers.get(i) == i) {
                    throw new TestFailure("Entries equal to their index exist");
                }
            }
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchEntryEqualToIndex() {}
}
