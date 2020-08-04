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
            grades.computeIfAbsent(name, k -> new PriorityQueue<>()).add(score);
            if (grades.get(name).size() > 3) {
                grades.get(name).poll();
            }
        }
        String topStudent = "";
        int bestSum = 0;
        for (Map.Entry<String, PriorityQueue<Integer>> scores : grades.entrySet()) {
            if (scores.getValue().size() == 3) {
                final int sum = scores.getValue().stream().mapToInt(Integer::intValue).sum();
                if (sum > bestSum) {
                    bestSum = sum;
                    topStudent = scores.getKey();
                }
            }
        }
        return topStudent;
    }

    private AverageOfTopThreeScores() {}
}
