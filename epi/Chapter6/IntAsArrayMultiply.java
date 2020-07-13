package epi.Chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IntAsArrayMultiply {

    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        final boolean sign = num1.get(0) > 0 == num2.get(0) > 0;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));
        final int n = num1.size();
        final int m = num2.size();
        List<Integer> res = new ArrayList<>(Collections.nCopies(n + m, 0));
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                final int mul = num1.get(i) * num2.get(j);
                final int p1 = i + j;
                final int p2 = i + j + 1;
                final int sum = mul + res.get(p2);
                res.set(p1, res.get(p1) + (sum / 10));
                res.set(p2, sum % 10);
            }
        }
        int j = 0;
        while (j < res.size() - 1 && res.get(j) == 0) {
            j++;
        }
        res = res.subList(j, res.size());
        if (!sign) {
            res.set(0, res.get(0) * -1);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntAsArrayMultiply() {}
}
