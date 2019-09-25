package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public final class QueueFromStacks {

    public static class Queue {
        Deque<Integer> enq = new LinkedList<>();
        Deque<Integer> deq = new LinkedList<>();

        public void enqueue(Integer x) {
            enq.addFirst(x);
        }

        public Integer dequeue() {
            if (deq.isEmpty()) {
                while (!enq.isEmpty()) {
                    deq.addFirst(enq.removeFirst());
                }
            }
            return deq.removeFirst();
        }
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

    @EpiTest(testDataFile = "queue_from_stacks.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        try {
            Queue q = new Queue();
            for (QueueOp op : ops) {
                switch (op.op) {
                    case "QueueWithMax":
                        q = new Queue();
                        break;
                    case "enqueue":
                        q.enqueue(op.arg);
                        break;
                    case "dequeue":
                        final int result = q.dequeue();
                        if (result != op.arg) throw new TestFailure("Dequeue: expected " + op.arg + ", got " + result);
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

    private QueueFromStacks() {
    }
}
