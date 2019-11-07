package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        return dp(finalScore, 0, individualPlayScores, new int[finalScore + 1][individualPlayScores.size()]);
    }

    private static int dp(int amount, int index, List<Integer> scores, int[][] cache) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index == scores.size()) {
            return 0;
        }
        if (cache[amount][index] == 0) {
            final int l = dp(amount - scores.get(index), index, scores, cache);
            final int r = dp(amount, index + 1, scores, cache);
            cache[amount][index] = l + r;
        }
        return cache[amount][index];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfScoreCombinations() {}
}
