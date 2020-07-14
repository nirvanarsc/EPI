package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class ReverseWords {

    public static void reverseWords(char[] input) {
        reverse(input, 0, input.length - 1);
        int prev = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == ' ') {
                reverse(input, prev, i - 1);
                prev = i + 1;
            }
        }
        reverse(input, prev, input.length - 1);
    }

    private static void reverse(char[] arr, int from, int to) {
        while (from < to) {
            final char t = arr[from];
            arr[from] = arr[to];
            arr[to] = t;
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

    private ReverseWords() {}
}
