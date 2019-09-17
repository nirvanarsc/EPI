package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.Collections;
import java.util.List;

public final class NextPermutation {

    @EpiTest(testDataFile = "next_permutation.tsv")
    public static List<Integer> nextPermutation(List<Integer> perm) {
        int idx = perm.size() - 2;
        while (idx >= 0 && perm.get(idx) >= perm.get(idx + 1)) {
            idx--;
        }

        if (idx == -1) {
            return Collections.emptyList();
        }

        int newIdx = perm.size() - 1;
        while (perm.get(newIdx) <= perm.get(idx)) {
            newIdx--;
        }

        Collections.swap(perm, idx, newIdx);
        Collections.reverse(perm.subList(idx + 1, perm.size()));
        return perm;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.NextPermutation");
    }

    private NextPermutation() {
    }
}
