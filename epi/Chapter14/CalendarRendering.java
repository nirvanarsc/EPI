package epi.Chapter14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class CalendarRendering {

    @EpiUserType(ctorParams = { int.class, int.class })
    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    private static class Endpoint implements Comparable<Endpoint> {
        public int time;
        public boolean isStart;

        Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Endpoint e) {
            return time != e.time ? Integer.compare(time, e.time) : Boolean.compare(e.isStart, isStart);
        }

        @Override
        public String toString() {
            return time + " " + isStart;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Endpoint)) { return false; }
            return compareTo((Endpoint) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, isStart);
        }
    }

    @EpiTest(testDataFile = "calendar_rendering.tsv")
    public static int findMaxSimultaneousEvents(List<Event> events) {
        final List<Endpoint> endpoints = new ArrayList<>();
        for (Event e : events) {
            endpoints.add(new Endpoint(e.start, true));
            endpoints.add(new Endpoint(e.finish, false));
        }
        Collections.sort(endpoints);
        int curr = 0;
        int res = Integer.MIN_VALUE;
        for (Endpoint e : endpoints) {
            if (e.isStart) {
                curr++;
                res = Math.max(curr, res);
            } else {
                curr--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private CalendarRendering() {}
}
