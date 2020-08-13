package epi.Chapter16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class EnumerateBalancedParentheses {

    @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
    public static List<String> generateBalancedParentheses(int numPairs) {
        final List<String> res = new ArrayList<>();
        dfs(numPairs * 2, numPairs, numPairs, new StringBuilder(), res);
        return res;
    }

    private static void dfs(int n, int open, int close, StringBuilder sb, List<String> res) {
        if (sb.length() == n) {
            res.add(new String(sb));
            return;
        }
        if (open > 0) {
            sb.append('(');
            dfs(n, open - 1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (open < close) {
            sb.append(')');
            dfs(n, open, close - 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = TestRunner.getSimpleComp();

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EnumerateBalancedParentheses() {}
}
