package epi.Chapter14;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SmallestNonconstructibleValue {

    @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
    public static int smallestNonconstructibleValue(List<Integer> integers) {
        Collections.sort(integers);
        int maxConstructibleValue = 0;
        for (int a : integers) {
            if (a > maxConstructibleValue + 1) {
                break;
            }
            maxConstructibleValue += a;
        }
        return maxConstructibleValue + 1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SmallestNonconstructibleValue() {}
}
