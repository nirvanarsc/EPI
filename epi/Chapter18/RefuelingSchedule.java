package epi.Chapter18;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;
import epi.utils.Verifiers;

public final class RefuelingSchedule {
    public static final int MPG = 20;

    private static final class Pair {
        int city;
        int remainingGallons;

        private Pair(int city, int remainingGallons) {
            this.city = city;
            this.remainingGallons = remainingGallons;
        }
    }

    public static int findAmpleCity(List<Integer> gallons, List<Integer> distances) {
        int remainingGallons = 0;
        Pair min = new Pair(0, 0);
        final int numCities = gallons.size();
        for (int i = 1; i < numCities; i++) {
            remainingGallons += gallons.get(i - 1) - distances.get(i - 1) / MPG;
            if (remainingGallons < min.remainingGallons) {
                min = new Pair(i, remainingGallons);
            }
        }
        return min.city;
    }

    public static int findAmpleCity2(List<Integer> gallons, List<Integer> distances) {
        final int circle = distances.size();
        for (int i = 0; i < distances.size(); i++) {
            int currGas = 0;
            boolean ranOutOfGas = false;
            for (int j = i + 1; j < distances.size() + i + 1; j++) {
                currGas += gallons.get((j - 1) % circle) - distances.get((j - 1) % circle) / MPG;
                if (currGas < 0) {
                    ranOutOfGas = true;
                    break;
                }
            }
            if (!ranOutOfGas) {
                return i;
            }
        }

        return -1;
    }

    @EpiTest(testDataFile = "refueling_schedule.tsv")
    public static void findAmpleCityWrapper(TimedExecutor executor,
                                            List<Integer> gallons,
                                            List<Integer> distances) throws Exception {
        final int result = executor.run(() -> findAmpleCity(gallons, distances));
        Verifiers.verifyRefuelingSchedule(gallons, distances, result);
    }

    @EpiTest(testDataFile = "refueling_schedule.tsv")
    public static void findAmpleCityWrapper2(TimedExecutor executor,
                                             List<Integer> gallons,
                                             List<Integer> distances) throws Exception {
        final int result = executor.run(() -> findAmpleCity2(gallons, distances));
        Verifiers.verifyRefuelingSchedule(gallons, distances, result);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RefuelingSchedule() {}
}
