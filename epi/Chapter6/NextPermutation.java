package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.Collections;
import java.util.List;

public final class NextPermutation {

    @EpiTest(testDataFile = "next_permutation.tsv")
    public static List<Integer> nextPermutation(List<Integer> perm) {
        int swapIdx = -1;
        final int n = perm.size();
        for (int i = n - 1; i >= 1; i--) {
            if (perm.get(i - 1) < perm.get(i)) {
                swapIdx = i - 1;
                break;
            }
        }
        if (swapIdx == -1) {
            return Collections.emptyList();
        }
        for (int i = n - 1; i >= 1; i--) {
            if (perm.get(i) > perm.get(swapIdx)) {
                Collections.swap(perm, swapIdx, i);
                break;
            }
        }
        Collections.reverse(perm.subList(swapIdx + 1, perm.size()));
        return perm;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NextPermutation() {}
}
