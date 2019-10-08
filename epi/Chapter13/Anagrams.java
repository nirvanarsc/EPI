package epi.Chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.utils.TestRunner;

public final class Anagrams {

    @EpiTest(testDataFile = "anagrams.tsv")
    public static List<List<String>> findAnagrams(List<String> dictionary) {
        final Map<String, List<String>> sortedToAnagrams = new HashMap<>();
        for (String s : dictionary) {
            final char[] chars = s.toCharArray();
            Arrays.sort(chars);
            final String sortedStr = new String(chars);
            sortedToAnagrams.putIfAbsent(sortedStr, new ArrayList<>());
            sortedToAnagrams.get(sortedStr).add(s);
        }

        return sortedToAnagrams.values()
                               .stream()
                               .filter(i -> i.size() >= 2)
                               .collect(Collectors.toList());
    }

    @EpiTestComparator
    public static BiPredicate<List<List<String>>, List<List<String>>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        for (List<String> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<String> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Anagrams() {}
}
