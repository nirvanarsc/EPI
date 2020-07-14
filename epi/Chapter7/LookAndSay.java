package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LookAndSay {

    @EpiTest(testDataFile = "look_and_say.tsv")
    public static String lookAndSay(int n) {
        String curr = "1";
        for (int i = 1; i < n; i++) {
            curr = lookAndSay(curr);
        }
        return curr;
    }

    public static String lookAndSay(String curr) {
        final StringBuilder next = new StringBuilder();
        for (int j = 0; j < curr.length(); j++) {
            int t = j;
            while (t < curr.length() && curr.charAt(t) == curr.charAt(j)) {
                t++;
            }
            next.append(t - j);
            next.append(curr.charAt(j));
            j = t - 1;
        }
        curr = next.toString();
        return curr;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LookAndSay() {}
}
