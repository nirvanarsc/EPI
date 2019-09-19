package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class ReverseWords {

    // "Alice likes Bob" transforms to "Bob likes Alice".
    public static void reverseWords(char[] input) {
        final int end = input.length - 1;
        int prev = 0;

        reverse(input, 0, end);

        for (int i = 0; i <= end; i++) {
            if (i == end && input[i] != ' ') {
                reverse(input, prev, end);
            } else if (input[i] == ' ') {
                reverse(input, prev, i - 1);
                prev = i + 1;
            }
        }
    }

    private static void reverse(char[] input, int from, int to) {
        while (from < to) {
            final char temp = input[from];
            input[from] = input[to];
            input[to] = temp;
            from++;
            to--;
        }
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s) throws Exception {
        final char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ReverseWords() {
    }
}
