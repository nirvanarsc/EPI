package epi.Chapter14;

import java.util.Comparator;
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

    public static boolean validPlacementExists(Team team0, Team team1) {
        team0.players.sort(Comparator.naturalOrder());
        team1.players.sort(Comparator.naturalOrder());
        for (int i = 0; i < team0.players.size(); i++) {
            if (team1.players.get(i).height <= team0.players.get(i).height) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsArrayDominated() {}
}
