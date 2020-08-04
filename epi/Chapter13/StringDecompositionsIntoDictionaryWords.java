package epi.Chapter13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class StringDecompositionsIntoDictionaryWords {

    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
    public static List<Integer> findAllSubstrings(String s, List<String> words) {
        final int k = words.get(0).length();
        final Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.merge(w, 1, Integer::sum);
        }
        final int target = k * (words.size() - 1);
        final List<Integer> res = new ArrayList<>();
        for (int window = 0; window < k; window++) {
            final Map<String, Integer> offset = new HashMap<>(freq);
            int match = words.size();
            for (int start = window, end = window; end <= s.length() - k; end += k) {
                final String curr = s.substring(end, end + k);
                if (offset.merge(curr, -1, Integer::sum) >= 0) {
                    match--;
                }
                while (match == 0) {
                    if (end - start == target) {
                        res.add(start);
                    }
                    final String begin = s.substring(start, start + k);
                    if (offset.merge(begin, 1, Integer::sum) > 0) {
                        match++;
                    }
                    start += k;
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private StringDecompositionsIntoDictionaryWords() {}
}
