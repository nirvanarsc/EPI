package epi.Chapter14;

import java.util.Comparator;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class FindSalaryThreshold {

    @EpiTest(testDataFile = "find_salary_threshold.tsv")
    public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {
        currentSalaries.sort(Comparator.naturalOrder());
        double currSum = 0;
        for (int i = 0, n = currentSalaries.size(); i < currentSalaries.size(); i++) {
            if (currSum + (n - i) * currentSalaries.get(i) >= targetPayroll) {
                return (targetPayroll - currSum) / (n - i);
            }
            currSum += currentSalaries.get(i);
        }

        return -1.0;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private FindSalaryThreshold() {}
}
