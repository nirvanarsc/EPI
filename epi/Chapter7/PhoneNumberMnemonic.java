package epi.Chapter7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class PhoneNumberMnemonic {

    private static final String[] NUMBERS =
            { "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        final List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), res, phoneNumber, 0);
        return res;
    }

    private static void dfs(StringBuilder sb, List<String> res, String number, int idx) {
        if (idx == number.length()) {
            res.add(new String(sb));
            return;
        }
        for (char c : NUMBERS[number.charAt(idx) - '0'].toCharArray()) {
            sb.append(c);
            dfs(sb, res, number, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonicBFS(String phoneNumber) {
        final List<String> res = new ArrayList<>();
        final Deque<StringBuilder> q = new ArrayDeque<>(Collections.singleton(new StringBuilder()));
        for (int i = 0; !q.isEmpty(); i++) {
            for (int level = q.size(); level > 0; level--) {
                final StringBuilder curr = q.removeFirst();
                if (i == phoneNumber.length()) {
                    res.add(curr.toString());
                    continue;
                }
                for (char c : NUMBERS[phoneNumber.charAt(i) - '0'].toCharArray()) {
                    curr.append(c);
                    q.offerLast(new StringBuilder(curr));
                    curr.deleteCharAt(curr.length() - 1);
                }
            }
        }
        return res;
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = TestRunner.getSimpleComp();

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PhoneNumberMnemonic() {}
}
