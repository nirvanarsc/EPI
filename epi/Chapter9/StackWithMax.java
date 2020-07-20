package epi.Chapter9;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class StackWithMax {

    public static class Stack {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();

        public boolean empty() {
            return stack.isEmpty();
        }

        public Integer max() {
            return max.isEmpty() ? 0 : max.peekFirst();
        }

        public Integer pop() {
            if (max.getFirst().equals(stack.getFirst())) {
                max.removeFirst();
            }
            return stack.removeFirst();
        }

        public void push(Integer x) {
            if (max.isEmpty() || max.getFirst() <= x) {
                max.addFirst(x);
            }
            stack.addFirst(x);
        }
    }

    @EpiUserType(ctorParams = { String.class, int.class })
    public static class StackOp {
        public String op;
        public int arg;

        public StackOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testDataFile = "stack_with_max.tsv")
    public static void stackTest(List<StackOp> ops) throws TestFailure {
        try {
            Stack s = new Stack();
            int result;
            for (StackOp op : ops) {
                switch (op.op) {
                    case "Stack":
                        s = new Stack();
                        break;
                    case "push":
                        s.push(op.arg);
                        break;
                    case "pop":
                        result = s.pop();
                        if (result != op.arg) {
                            throw new TestFailure("Pop: expected " + op.arg + ", got " + result);
                        }
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) {
                            throw new TestFailure("Max: expected " + op.arg + ", got " + result);
                        }
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) {
                            throw new TestFailure("Empty: expected " + op.arg + ", got " + s);
                        }
                        break;
                    default:
                        throw new RuntimeException("Unsupported stack operation: " + op.op);
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private StackWithMax() {}
}
