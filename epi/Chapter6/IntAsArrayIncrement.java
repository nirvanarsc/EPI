package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.List;

public final class IntAsArrayIncrement {

    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> a) {
        int end = a.size() - 1;
        while (end >= 0 && a.get(end) == 9) {
            a.set(end, 0);
            end--;
        }
        if (end == -1) {
            a.add(0, 1);
        } else {
            a.set(end, a.get(end) + 1);
        }

        return a;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntAsArrayIncrement() {
    }
}
