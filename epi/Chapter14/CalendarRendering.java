package epi.Chapter14;

import java.util.ArrayList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

@SuppressWarnings("MethodParameterNamingConvention")
public final class CalendarRendering {

    @EpiUserType(ctorParams = { int.class, int.class })
    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    private static class Endpoint {
        public int time;
        public boolean isStart;

        Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    @EpiTest(testDataFile = "calendar_rendering.tsv")
    public static int findMaxSimultaneousEvents(List<Event> A) {
        final List<Endpoint> points = new ArrayList<>();
        for (Event e : A) {
            points.add(new Endpoint(e.start, true));
            points.add(new Endpoint(e.finish, false));
        }
        points.sort((a, b) -> a.time == b.time ? Boolean.compare(b.isStart, a.isStart)
                                               : Integer.compare(a.time, b.time));
        int max = 0, curr = 0;
        for (Endpoint p : points) {
            curr += p.isStart ? 1 : -1;
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private CalendarRendering() {}
}
