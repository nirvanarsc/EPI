package epi.Chapter18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class TaskPairing {

    @EpiUserType(ctorParams = { Integer.class, Integer.class })
    public static class PairedTasks {
        public Integer task1;
        public Integer task2;

        public PairedTasks(Integer task1, Integer task2) {
            this.task1 = task1;
            this.task2 = task2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final PairedTasks other = (PairedTasks) o;

            return task1.equals(other.task1) && task2.equals(other.task2);
        }

        @Override
        public String toString() {
            return "[" + task1 + ", " + task2 + ']';
        }

        @Override
        public int hashCode() {
            return Objects.hash(task1, task2);
        }
    }

    @EpiTest(testDataFile = "task_pairing.tsv")
    public static List<PairedTasks> optimumTaskAssignment(List<Integer> taskDurations) {
        Collections.sort(taskDurations);
        final List<PairedTasks> res = new ArrayList<>();
        for (int i = 0; i < taskDurations.size() / 2; i++) {
            res.add(new PairedTasks(taskDurations.get(i), taskDurations.get(taskDurations.size() - 1 - i)));
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private TaskPairing() {}
}
