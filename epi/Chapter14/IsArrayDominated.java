package epi.Chapter14;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.Team;
import epi.utils.TestRunner;

public final class IsArrayDominated {

    @EpiTest(testDataFile = "is_array_dominated.tsv")
    public static void validPlacementExistsWrapper(TimedExecutor executor,
                                                   List<Integer> team0,
                                                   List<Integer> team1,
                                                   boolean expected1,
                                                   boolean expected2) throws Exception {
        final Team t0 = new Team(team0);
        final Team t1 = new Team(team1);

        final boolean result1 = executor.run(() -> validPlacementExists(t0, t1));
        final boolean result2 = executor.run(() -> validPlacementExists(t1, t0));
        if (result1 != expected1 || result2 != expected2) {
            throw new TestFailure("");
        }
    }

    // Checks if team0 can be placed in front of team1.
    public static boolean validPlacementExists(Team team0, Team team1) {
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsArrayDominated() {}
}
