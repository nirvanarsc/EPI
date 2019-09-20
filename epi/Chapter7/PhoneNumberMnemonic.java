package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public final class PhoneNumberMnemonic {

    static final Map<Character, List<Character>> phoneMap = new HashMap<>();

    static {
        phoneMap.put('2', Arrays.asList('A', 'B', 'C'));
        phoneMap.put('3', Arrays.asList('D', 'E', 'F'));
        phoneMap.put('4', Arrays.asList('G', 'H', 'I'));
        phoneMap.put('5', Arrays.asList('J', 'K', 'L'));
        phoneMap.put('6', Arrays.asList('M', 'N', 'O'));
        phoneMap.put('7', Arrays.asList('P', 'Q', 'R', 'S'));
        phoneMap.put('8', Arrays.asList('T', 'U', 'V'));
        phoneMap.put('9', Arrays.asList('W', 'X', 'Y', 'Z'));
    }

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        List<StringBuilder> total = new ArrayList<>();
        for (int i = phoneNumber.length() - 1; i >= 0; i--) {
            final char curr = phoneNumber.charAt(i);
            if (total.isEmpty()) {
                if (phoneMap.containsKey(curr)) {
                    for (Character c : phoneMap.get(curr)) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        total.add(sb);
                    }
                } else {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(curr);
                    total.add(sb);
                }
            } else {
                final List<StringBuilder> temp = new ArrayList<>();
                if (phoneMap.containsKey(curr)) {
                    for (Character c : phoneMap.get(curr)) {
                        appendToSb(total, c, temp);
                    }
                } else {
                    appendToSb(total, curr, temp);
                }
                total = temp;
            }
        }

        final List<String> res = new ArrayList<>();
        for (StringBuilder sb : total) {
            res.add(sb.reverse().toString());
        }

        return res;
    }

    private static void appendToSb(List<StringBuilder> total, char curr, List<StringBuilder> temp) {
        for (StringBuilder sb : total) {
            final StringBuilder newSb = new StringBuilder();
            newSb.append(sb);
            newSb.append(curr);
            temp.add(newSb);
        }
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    };

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PhoneNumberMnemonic() {
    }
}
