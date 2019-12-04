package epi.Chapter18;

import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MinimumWaitingTime {

    @EpiTest(testDataFile = "minimum_waiting_time.tsv")
    public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
        Collections.sort(serviceTimes);
        int res = 0;
        for (int i = 0; i < serviceTimes.size() - 1; i++) {
            for (int j = 0; j <= i; j++) {
                res += serviceTimes.get(j);
            }
        }
        return res;
    }

    @EpiTest(testDataFile = "minimum_waiting_time.tsv")
    public static int minimumTotalWaiting(List<Integer> serviceTimes) {
        Collections.sort(serviceTimes);
        int res = 0;
        for (int i = 0; i < serviceTimes.size(); i++) {
            res += serviceTimes.get(i) * (serviceTimes.size() - 1 - i);
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MinimumWaitingTime() {}
}
