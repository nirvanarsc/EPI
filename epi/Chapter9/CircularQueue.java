package epi.Chapter9;

import static epi.test_framework.TestFailure.PropertyName.COMMAND;
import static epi.test_framework.TestFailure.PropertyName.STATE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

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

        @SuppressWarnings("ReturnOfNull")
        public Integer dequeue() {
            return !elements.isEmpty() ? elements.get(deq++) : null;
        }

        public int size() {
            return enq - deq;
        }
    }

    public static class QueueArray {
        Integer[] q;
        int head;
        int tail;
        int size;

        public QueueArray(int capacity) {
            head = 0;
            tail = 0;
            q = new Integer[capacity];
        }

        public void enqueue(Integer x) {
            if (size == q.length) {
                Collections.rotate(Arrays.asList(q), -head);
                head = 0;
                tail = size;
                q = Arrays.copyOf(q, size << 1);
            }
            q[tail] = x;
            tail = (tail + 1) % q.length;
            size++;
        }

        public Integer dequeue() {
            final int res = q[head];
            head = (head + 1) % q.length;
            size--;
            return res;
        }

        public int size() {
            return size;
        }
    }

    @EpiUserType(ctorParams = { String.class, int.class })
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
    public static void queueTester(List<QueueOp> ops) throws TestFailure {
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
                                .withProperty(STATE, q)
                                .withProperty(COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result);
                    }
                    break;
                case "size":
                    final int s = q.size();
                    if (s != op.arg) {
                        throw new TestFailure()
                                .withProperty(STATE, q)
                                .withProperty(COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, s);
                    }
                    break;
            }
            opIdx++;
        }
    }

    @EpiTest(testDataFile = "circular_queue.tsv")
    public static void queueArrayTester(List<QueueOp> ops) throws TestFailure {
        QueueArray q = new QueueArray(1);
        int opIdx = 0;
        for (QueueOp op : ops) {
            switch (op.op) {
                case "Queue":
                    q = new QueueArray(op.arg);
                    break;
                case "enqueue":
                    q.enqueue(op.arg);
                    break;
                case "dequeue":
                    final int result = q.dequeue();
                    if (result != op.arg) {
                        throw new TestFailure()
                                .withProperty(STATE, q)
                                .withProperty(COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result);
                    }
                    break;
                case "size":
                    final int s = q.size();
                    if (s != op.arg) {
                        throw new TestFailure()
                                .withProperty(STATE, q)
                                .withProperty(COMMAND, op)
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

    private CircularQueue() {}
}
