package epi.Chapter6;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings("MethodParameterNamingConvention")
public final class ApplyPermutation {

    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        for (int i = 0; i < perm.size(); i++) {
            int next = i;
            while (perm.get(next) >= 0) {
                final int temp = perm.get(next);
                Collections.swap(A, i, temp);
                perm.set(next, -1);
                next = temp;
            }
        }
    }

    @EpiTest(testDataFile = "apply_permutation.tsv")
    public static List<Integer> applyPermutationWrapper(List<Integer> perm, List<Integer> a) {
        applyPermutation(perm, a);
        return a;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ApplyPermutation() {}
}
