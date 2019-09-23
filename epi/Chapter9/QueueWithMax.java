package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

import java.util.List;
import java.util.NoSuchElementException;

public class QueueWithMax {

    public void enqueue(Integer x) {
        // TODO - you fill in here.
        return;
    }

    public Integer dequeue() {
        // TODO - you fill in here.
        return 0;
    }

    public Integer max() {
        // TODO - you fill in here.
        return 0;
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class QueueOp {
        public String op;
        public int arg;

        public QueueOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testDataFile = "queue_with_max.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        try {
            QueueWithMax q = new QueueWithMax();
            for (QueueOp op : ops) {
                switch (op.op) {
                    case "QueueWithMax":
                        q = new QueueWithMax();
                        break;
                    case "enqueue":
                        q.enqueue(op.arg);
                        break;
                    case "dequeue":
                        final int result = q.dequeue();
                        if (result != op.arg) throw new TestFailure("Dequeue: expected " + op.arg + ", got " + result);
                        break;
                    case "max":
                        final int s = q.max();
                        if (s != op.arg) throw new TestFailure("Max: expected " + op.arg + ", got " + s);
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }
}
