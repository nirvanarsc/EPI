package epi.Chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class Anagrams {

    @EpiTest(testDataFile = "anagrams.tsv")
    public static List<List<String>> findAnagrams(List<String> dictionary) {
        final Map<String, List<String>> sortedToAnagrams = new HashMap<>();
        for (String s : dictionary) {
            final char[] chars = s.toCharArray();
            Arrays.sort(chars);
            final String sortedStr = new String(chars);
            sortedToAnagrams.computeIfAbsent(sortedStr, v -> new ArrayList<>()).add(s);
        }
        return sortedToAnagrams.values()
                               .stream()
                               .filter(i -> i.size() > 1)
                               .collect(Collectors.toList());
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp = TestRunner.getComp(false);

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Anagrams() {}
}
