package epi.Chapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.utils.TestRunner;

public final class ValidIpAddresses {

    @EpiTest(testDataFile = "valid_ip_addresses.tsv")
    public static List<String> getValidIpAddress(String s) {
        final int len = s.length();
        final List<String> res = new ArrayList<>();
        for (int i = 1; i <= Math.min(len, 3); i++) {
            final String m = s.substring(0, i);
            if (!check(m)) { continue; }
            for (int j = i + 1; j <= Math.min(len, i + 4); j++) {
                final String n = s.substring(i, j);
                if (!check(n)) { continue; }
                for (int k = j + 1; k < Math.min(len, j + 4); k++) {
                    final String o = s.substring(j, k);
                    final String p = s.substring(k);
                    if (check(o) && check(p)) {
                        res.add(m + '.' + n + '.' + o + '.' + p);
                    }
                }
            }
        }
        return res;
    }

    private static boolean check(String num) {
        if (num.length() > 3 || num.charAt(0) == '0' && num.length() > 1) {
            return false;
        }
        final int address = Integer.parseInt(num);
        return 0 <= address && address <= 255;
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = TestRunner.getSimpleComp();

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ValidIpAddresses() {}
}
