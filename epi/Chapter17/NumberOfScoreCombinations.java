package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class NumberOfScoreCombinations {

    @EpiTest(testDataFile = "number_of_score_combinations.tsv")
    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {
        final int[][] cache = new int[finalScore + 1][individualPlayScores.size()];
        return dp(finalScore, 0, individualPlayScores, cache);
    }

    static int dp(int amount, int index, List<Integer> scores, int[][] cache) {
        if (amount == 0) {
            return 1;
        }
        if (index > scores.size() - 1) {
            return 0;
        }
        if (cache[amount][index] != 0) {
            return cache[amount][index];
        }
        final int curr = scores.get(index);
        int ways = 0;
        for (int i = 0; i * curr <= amount; i++) {
            ways += dp(amount - i * curr, index + 1, scores, cache);
        }
        cache[amount][index] = ways;
        return ways;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private NumberOfScoreCombinations() {}
}
