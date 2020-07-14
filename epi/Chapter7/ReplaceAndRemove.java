package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.List;

public final class ReplaceAndRemove {

    public static int replaceAndRemove(int size, char[] s) {
        int j = s.length - 1;
        for (int i = size - 1; i >= 0; i--) {
            if (s[i] == 'a') {
                s[j--] = 'd';
                s[j--] = 'd';
            } else if (s[i] != 'b') {
                s[j--] = s[i];
            }
        }
        final int res = s.length - 1 - j++;
        for (int i = 0; i < res; i++, j++) {
            s[i] = s[j];
        }
        return res;
    }

    @EpiTest(testDataFile = "replace_and_remove.tsv")
    public static List<String> replaceAndRemoveWrapper(TimedExecutor executor,
                                                       Integer size,
                                                       List<String> s) throws Exception {
        final char[] sCopy = new char[s.size()];
        for (int i = 0; i < size; ++i) {
            if (!s.get(i).isEmpty()) {
                sCopy[i] = s.get(i).charAt(0);
            }
        }

        final Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

        final List<String> result = new ArrayList<>();
        for (int i = 0; i < resSize; ++i) {
            result.add(Character.toString(sCopy[i]));
        }
        return result;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ReplaceAndRemove() {}
}
