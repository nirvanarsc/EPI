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
        final List<String> result = new ArrayList<>();
        for (int i = 1; i < 4 && i < s.length(); i++) {
            final String first = s.substring(0, i);
            if (isValidPart(first)) {
                for (int j = 1; i + j < s.length() && j < 4; j++) {
                    final String second = s.substring(i, i + j);
                    if (isValidPart(second)) {
                        for (int k = 1; i + j + k < s.length() && k < 4; k++) {
                            final String third = s.substring(i + j, i + j + k);
                            final String fourth = s.substring(i + j + k);
                            if (isValidPart(third) && isValidPart(fourth)) {
                                result.add(first + '.' + second + '.' + third + '.' + fourth);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isValidPart(String s) {
        if (s.length() > 3) {
            return false;
        }

        if (s.charAt(0) == '0' && s.length() > 1) {
            return false;
        }

        final int val = Integer.parseInt(s);
        return 0 <= val && val <= 255;
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp = TestRunner.getSimpleComp();

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ValidIpAddresses() {
    }
}
