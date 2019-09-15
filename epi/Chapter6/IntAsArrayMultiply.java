package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class IntAsArrayMultiply {

    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        if (num1.get(0) == 0 || num2.get(0) == 0) {
            return new ArrayList<>(Collections.singleton(0));
        }
        boolean negative = false;
        if (num1.get(0) < 0 ^ num2.get(0) < 0) {
            negative = true;
        }
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));
        final List<Integer> larger = num1.size() > num2.size() ? num1 : num2;
        final List<Integer> smaller = larger == num1 ? num2 : num1;
        List<Integer> res = new ArrayList<>();
        for (int i = smaller.size() - 1, j = 0; i >= 0; i--, j++) {
            final List<Integer> copy = new ArrayList<>(larger);
            res = sum(res, multiply(j, smaller.get(i), copy));

        }
        if (negative) {
            res.set(0, -res.get(0));
        }
        return res;
    }

    public static List<Integer> multiply(int carry, int mul, List<Integer> num) {
        int c = 0;
        for (int i = num.size() - 1; i >= 0; i--) {
            final int curr = (num.get(i) * mul) + c;
            c = curr / 10;
            num.set(i, curr % 10);
        }
        if (c > 0) {
            num.add(0, c);
        }
        while (carry-- > 0) {
            num.add(0);
        }
        return num;
    }

    public static List<Integer> sum(List<Integer> smaller, List<Integer> larger) {
        int idx = larger.size() - smaller.size() - 1;
        int carry = 0;
        for (int i = smaller.size() - 1, j = larger.size() - 1; i >= 0; i--, j--) {
            final int curr = smaller.get(i) + larger.get(j) + carry;
            carry = curr / 10;
            larger.set(j, curr % 10);
        }
        if (carry > 0) {
            while (idx >= 0 && carry > 0) {
                final int element = larger.get(idx) + carry;
                larger.set(idx, element % 10);
                carry = element / 10;
                idx--;
            }
            if (carry > 0) {
                larger.add(0, carry);
            }
        }

        return larger;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.IntAsArrayMultiply");
    }

    private IntAsArrayMultiply() {
    }
}
