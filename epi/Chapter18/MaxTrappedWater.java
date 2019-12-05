package epi.Chapter18;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MaxTrappedWater {

    @EpiTest(testDataFile = "max_trapped_water.tsv")
    public static int getMaxTrappedWater(List<Integer> heights) {
        int start = 0, end = heights.size() - 1, res = 0;
        while (start < end) {
            res = Math.max(res, (end - start) * Math.min(heights.get(start), heights.get(end)));
            if (heights.get(start).equals(heights.get(end))) {
                start++;
                end--;
            } else if (heights.get(start) < heights.get(end)) {
                start++;
            } else {
                end--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MaxTrappedWater() {}
}
