package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public final class EvaluateRpn {

    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    public static int eval(String expression) {
        final Set<String> set = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        final Deque<Integer> digits = new LinkedList<>();

        for (String s : expression.split(",")) {
            if (set.contains(s)) {
                final Integer b = digits.pollFirst();
                final Integer a = digits.pollFirst();
                switch(s) {
                    case "+": digits.addFirst(a + b); break;
                    case "-": digits.addFirst(a - b); break;
                    case "*": digits.addFirst(a * b); break;
                    case "/": digits.addFirst(a / b); break;
                    default: throw new IllegalArgumentException("Malformed RPN at :" + s);
                }
            }
            else {
                digits.addFirst(Integer.parseInt(s));
            }
        }

        return digits.removeFirst();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EvaluateRpn() {
    }
}
