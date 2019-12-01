package epi.Chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;
import epi.utils.Verifiers;

public final class IsStringDecomposableIntoWords {

    public static List<String> decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
        final List<String> decompositions = new ArrayList<>();
        final int[] lastLength = new int[domain.length()];
        Arrays.fill(lastLength, -1);

        for (int i = 0; i < domain.length(); i++) {
            if (dictionary.contains(domain.substring(0, i + 1))) {
                lastLength[i] = i + 1;
            }

            if (lastLength[i] == -1) {
                for (int j = 0; j < i; j++) {
                    if (lastLength[j] != -1 && dictionary.contains(domain.substring(j + 1, i + 1))) {
                        lastLength[i] = i - j;
                        break;
                    }
                }
            }
        }

        if (lastLength[lastLength.length - 1] != -1) {
            int idx = domain.length() - 1;
            while (idx >= 0) {
                decompositions.add(domain.substring(idx + 1 - lastLength[idx], idx + 1));
                idx -= lastLength[idx];
            }
            Collections.reverse(decompositions);
        }
        return decompositions;
    }

    public static List<String> wordBreak(String domain, Set<String> dictionary) {
        int maxLen = 0;
        for (String w : dictionary) {
            maxLen = Math.max(maxLen, w.length());
        }
        return recurse(0, maxLen, domain, dictionary, new HashMap<>());
    }

    private static List<String> recurse(int start,
                                        int maxLength,
                                        String s,
                                        Set<String> dict,
                                        Map<Integer, List<String>> dp) {
        if (dp.containsKey(start)) {
            return dp.get(start);
        }
        final List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int i = start; i < start + maxLength && i < s.length(); i++) {
            if (dict.contains(s.substring(start, i + 1))) {
                final List<String> recurse = recurse(i + 1, maxLength, s, dict, dp);
                for (String sub : recurse) {
                    res.add(s.substring(start, i + 1) + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        dp.put(start, res);
        return dp.get(start);
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                           String domain,
                                                           Set<String> dictionary,
                                                           Boolean decomposable) throws Exception {
        final List<String> result = executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));
        Verifiers.verifyDecomposableIntoWords(domain, dictionary, decomposable, result);
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    public static void wordBreakWrapper(TimedExecutor executor,
                                        String domain,
                                        Set<String> dictionary,
                                        Boolean decomposable) throws Exception {
        final List<String> result = executor.run(() -> wordBreak(domain, dictionary));
        Verifiers.verifyWordBreak(domain, dictionary, decomposable, result);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringDecomposableIntoWords() {}
}
