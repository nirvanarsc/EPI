package epi.Chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class EnumeratePalindromicDecompositions {

    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    public static List<List<String>> palindromeDecompositions(String text) {
        final List<List<String>> res = new ArrayList<>();
        dfs(text, 0, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(String t, int idx, List<String> curr, List<List<String>> res) {
        if (idx == t.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = idx; i < t.length(); i++) {
            if (isPalindrome(t, idx, i)) {
                curr.add(t.substring(idx, i + 1));
                dfs(t, i + 1, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String t, int i, int j) {
        while (i < j) {
            if (t.charAt(i++) != t.charAt(j--)) {
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
