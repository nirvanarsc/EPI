package epi.Chapter14;

import java.util.Collections;
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

        executor.run(() -> {
            if (validPlacementExists(t0, t1) != expected1 || validPlacementExists(t1, t0) != expected2) {
                throw new TestFailure();
            }
            return 0;
        });
    }

    public static boolean validPlacementExists(Team team0, Team team1) {
        Collections.sort(team0.players);
        Collections.sort(team1.players);
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
