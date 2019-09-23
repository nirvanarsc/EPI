package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Iterator;
import java.util.List;

public final class SunsetView {

    public static List<Integer> examineBuildingsWithSunset(Iterator<Integer> sequence) {
        // TODO - you fill in here.
        return null;
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    public static List<Integer> examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
        return examineBuildingsWithSunset(sequence.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SunsetView() {
    }
}
