package epi.Chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class StringDecompositionsIntoDictionaryWords {

    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
    public static List<Integer> findAllSubstrings(String s, List<String> words) {
        final Map<String, Integer> wordToFreq = new HashMap<>();
        for (String word : words) {
            wordToFreq.merge(word, 1, Integer::sum);
        }
        final int unitSize = words.get(0).length();
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i + unitSize * words.size() <= s.length(); i++) {
            if (matchAllWordsInDict(s, new HashMap<>(wordToFreq), i, words.size(), unitSize)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean matchAllWordsInDict(String s,
                                               Map<String, Integer> wordToFreq,
                                               int start,
                                               int numWords,
                                               int unitSize) {
        for (int i = 0; i < numWords; i++) {
            final String currWord = s.substring(start + i * unitSize, start + (i + 1) * unitSize);
            if (!wordToFreq.containsKey(currWord)) {
                return false;
            }
            wordToFreq.compute(currWord, (a, b) -> b == 1 ? null : b - 1);
        }
        return wordToFreq.isEmpty();
    }

    public static List<Integer> findAllSubstrings2(String s, List<String> words) {
        final int N = s.length();
        final List<Integer> indexes = new ArrayList<>();
        if (words.isEmpty()) {
            return indexes;
        }
        final int M = words.get(0).length();
        if (N < M * words.size()) {
            return indexes;
        }
        final int last = N - M + 1;

        final Map<String, Integer> mapping = new HashMap<>();
        final int[][] table = new int[2][words.size()];
        int failures = 0, index = 0;
        for (int i = 0; i < words.size(); i++) {
            Integer mapped = mapping.get(words.get(i));
            if (mapped == null) {
                ++failures;
                mapping.put(words.get(i), index);
                mapped = index++;
            }
            ++table[0][mapped];
        }

        final int[] sMapping = new int[last];
        for (int i = 0; i < last; i++) {
            final String section = s.substring(i, i + M);
            final Integer mapped = mapping.get(section);
            if (mapped == null) {
                sMapping[i] = -1;
            } else {
                sMapping[i] = mapped;
            }
        }

        //fix the number of linear scans
        for (int i = 0; i < M; i++) {
            //reset scan variables
            int currentFailures = failures; //number of current mismatches
            int left = i, right = i;
            Arrays.fill(table[1], 0);
            //here, simple solve the minimum-window-substring problem
            while (right < last) {
                while (currentFailures > 0 && right < last) {
                    final int target = sMapping[right];
                    if (target != -1 && ++table[1][target] == table[0][target]) {
                        --currentFailures;
                    }
                    right += M;
                }
                while (currentFailures == 0 && left < right) {
                    final int target = sMapping[left];
                    if (target != -1 && --table[1][target] == table[0][target] - 1) {
                        final int length = right - left;
                        //instead of checking every window, we know exactly the length we want
                        if ((length / M) == words.size()) {
                            indexes.add(left);
                        }
                        ++currentFailures;
                    }
                    left += M;
                }
            }

        }
        return indexes;
    }

    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
    public static List<Integer> findAllSubstringsWrapper(TimedExecutor executor,
                                                         String s,
                                                         List<String> words) throws Exception {
        final List<Integer> integers = executor.run(() -> findAllSubstrings2(s, words));
        Collections.sort(integers);
        return integers;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private StringDecompositionsIntoDictionaryWords() {}
}
