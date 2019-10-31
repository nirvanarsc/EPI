package epi.HonorsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class RotateArray {

    public static void rotateArray(int rotateAmount, List<Integer> list) {
        rotateAmount %= list.size();
        Collections.reverse(list);
        Collections.reverse(list.subList(0, rotateAmount));
        Collections.reverse(list.subList(rotateAmount, list.size()));
    }

    @EpiTest(testDataFile = "rotate_array.tsv")
    public static List<Integer> rotateArrayWrapper(TimedExecutor executor,
                                                   List<Integer> list,
                                                   int rotateAmount) throws Exception {
        final List<Integer> aCopy = new ArrayList<>(list);
        executor.run(() -> rotateArray(rotateAmount, aCopy));
        return aCopy;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RotateArray() {}
}
