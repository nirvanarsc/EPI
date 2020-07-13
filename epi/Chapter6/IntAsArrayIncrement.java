package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.List;

@SuppressWarnings("MethodParameterNamingConvention")
public final class IntAsArrayIncrement {

    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> A) {
        int carry = 1;
        int idx = A.size() - 1;
        while (idx >= 0) {
            final int inc = A.get(idx) + carry;
            carry = inc / 10;
            A.set(idx, inc % 10);
            idx--;
        }
        if (carry > 0) {
            A.add(0, 1);
        }
        return A;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntAsArrayIncrement() {}
}
