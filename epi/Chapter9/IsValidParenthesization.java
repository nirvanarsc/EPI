package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class IsValidParenthesization {

    private static final Map<Character, Character> PAREN_MAP = new HashMap<>();

    static {
        PAREN_MAP.put('(', ')');
        PAREN_MAP.put('[', ']');
        PAREN_MAP.put('{', '}');
    }

    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    public static boolean isWellFormed(String s) {
        final Deque<Character> openParens = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            final char currParen = s.charAt(i);
            if (PAREN_MAP.keySet().contains(currParen)) {
                openParens.addFirst(currParen);
            } else {
                if (openParens.isEmpty() || !PAREN_MAP.get(openParens.peekFirst()).equals(currParen)) {
                    return false;
                }

                openParens.pollFirst();
            }
        }

        return openParens.isEmpty();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsValidParenthesization() {
    }
}
