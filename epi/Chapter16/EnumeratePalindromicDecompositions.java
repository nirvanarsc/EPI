package epi.Chapter16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class EnumeratePalindromicDecompositions {

    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    public static List<List<String>> palindromeDecompositions(String input) {
        if (input.isEmpty()) {
            return Collections.singletonList(new ArrayList<>());
        }
        final List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (isPalindrome(input.substring(0, i + 1))) {
                final List<List<String>> lists = palindromeDecompositions(input.substring(i + 1));
                for (List<String> l : lists) {
                    l.add(input.substring(0, i + 1));
                }
                res.addAll(lists);
            }
        }
        return res;
    }

    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    public static List<List<String>> palindromePartitioning(String input) {
        final List<List<String>> result = new ArrayList<>();
        directedPalindromePartitioning(0, input, new ArrayList<>(), result);
        return result;
    }

    private static void directedPalindromePartitioning(int offset,
                                                       String input,
                                                       List<String> partialPartition,
                                                       List<List<String>> result) {
        if (offset == input.length()) {
            result.add(new ArrayList<>(partialPartition));
            return;
        }
        for (int i = offset + 1; i <= input.length(); ++i) {
            final String prefix = input.substring(offset, i);
            if (isPalindrome(prefix)) {
                partialPartition.add(prefix);
                directedPalindromePartitioning(i, input, partialPartition, result);
                partialPartition.remove(partialPartition.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String prefix) {
        for (int i = 0, j = prefix.length() - 1; i < j; ++i, --j) {
            if (prefix.charAt(i) != prefix.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<String>>, List<List<String>>> comp = TestRunner.getComp(true);

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EnumeratePalindromicDecompositions() {}
}
