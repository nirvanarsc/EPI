package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class IsValidParenthesization {

    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    public static boolean isWellFormed(String s) {
        final Map<Character, Character> paren = new HashMap<>();
        paren.put(')', '(');
        paren.put(']', '[');
        paren.put('}', '{');
        final Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (paren.containsKey(c)) {
                if (stack.isEmpty() || stack.getFirst() != paren.get(c)) {
                    return false;
                }
                stack.removeFirst();
            } else {
                stack.addFirst(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsValidParenthesization() {}
}
