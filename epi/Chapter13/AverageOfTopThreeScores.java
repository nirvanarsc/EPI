package epi.Chapter13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public final class AverageOfTopThreeScores {

    public static String findStudentBestScore(Iterator<Object> nameScoreData) {
        final Map<String, PriorityQueue<Integer>> grades = new HashMap<>();
        while (nameScoreData.hasNext()) {
            final String name = (String) nameScoreData.next();
            final Integer score = (Integer) nameScoreData.next();
            final PriorityQueue<Integer> scores = grades.computeIfAbsent(name, k -> new PriorityQueue<>());
            scores.add(score);
            if (scores.size() > 3) {
                scores.poll();
            }
        }

        String topStudent = "";
        int currentTopThreeScoresSum = 0;
        for (Map.Entry<String, PriorityQueue<Integer>> scores : grades.entrySet()) {
            if (scores.getValue().size() == 3) {
                final int currentScoresSum = getTopThreeScoresSum(scores.getValue());
                if (currentScoresSum > currentTopThreeScoresSum) {
                    currentTopThreeScoresSum = currentScoresSum;
                    topStudent = scores.getKey();
                }
            }
        }

        return topStudent;
    }

    private static int getTopThreeScoresSum(PriorityQueue<Integer> scores) {
        final Iterator<Integer> it = scores.iterator();
        int result = 0;
        while (it.hasNext()) {
            result += it.next();
        }
        return result;
    }

    private AverageOfTopThreeScores() {}
}
