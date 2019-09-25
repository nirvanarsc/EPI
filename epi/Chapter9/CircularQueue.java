package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.List;

public final class CircularQueue {

    public static class Queue {
        List<Integer> elements;
        int enq;
        int deq;

        public Queue(int capacity) {
            elements = new ArrayList<>(capacity);
        }

        public void enqueue(Integer x) {
            elements.add(x);
            enq++;
        }

        public Integer dequeue() {
            return !elements.isEmpty() ? elements.get(deq++) : null;
        }

        public int size() {
            return enq - deq;
        }

        @Override
        public String toString() {
            return elements.subList(deq, enq).toString();
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

        @Override
        public String toString() {
            return op;
        }
    }

    @EpiTest(testDataFile = "circular_queue.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailure {
        Queue q = new Queue(1);
        int opIdx = 0;
        for (QueueOp op : ops) {
            switch (op.op) {
                case "Queue":
                    q = new Queue(op.arg);
                    break;
                case "enqueue":
                    q.enqueue(op.arg);
                    break;
                case "dequeue":
                    final int result = q.dequeue();
                    if (result != op.arg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result);
                    }
                    break;
                case "size":
                    final int s = q.size();
                    if (s != op.arg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, s);
                    }
                    break;
            }
            opIdx++;
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private CircularQueue() {
    }
}
