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

    private static final double EPSILON = 1e-7;

    @EpiTest(testDataFile = "find_salary_threshold.tsv")
    public static double findSalaryCapBS(int targetPayroll, List<Integer> currentSalaries) {
        double lo = 0;
        double hi = 1e9;
        while (hi - lo > EPSILON) {
            final double mid = 0.5 * (lo + hi);
            double payroll = 0;
            for (double salary : currentSalaries) {
                payroll += Math.min(salary, mid);
            }
            if (Double.compare(payroll, targetPayroll) == -1) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return Double.compare(lo, 1e9) == 0 ? -1 : lo;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private FindSalaryThreshold() {}
}
