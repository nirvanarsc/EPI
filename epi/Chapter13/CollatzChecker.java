package epi.Chapter13;

import java.util.HashSet;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class CollatzChecker {

    @EpiTest(testDataFile = "collatz_checker.tsv")
    public static boolean testCollatzConjecture(int n) {
        final Set<Long> verifiedNumbers = new HashSet<>();
        for (int i = 3; i <= n; i += 2) {
            final Set<Long> sequence = new HashSet<>();
            long currTest = i;
            while (currTest >= i) {
                if (!sequence.add(currTest)) { return false; }
                if ((currTest % 2) != 0) {
                    if (!verifiedNumbers.add(currTest)) { break; }
                    currTest = 3 * currTest + 1;
                } else {
                    currTest /= 2;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private CollatzChecker() {}
}
