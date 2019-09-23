package epi.Chapter9;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public final class StackWithMax {

    public static class Stack {
        int size;
        ListNode<Integer> stack = new ListNode<>(0, null);
        Deque<Integer> max = new LinkedList<>();

        public boolean empty() {
            return size == 0;
        }

        public Integer max() {
            return max.isEmpty() ? Integer.MIN_VALUE : max.element();
        }

        public Integer pop() {
            if (stack.next == null) return null;
            size--;
            final ListNode<Integer> node = stack;
            stack = stack.next;
            if (node.data.equals(max())) max.pollFirst();
            return node.data;
        }

        public void push(Integer x) {
            size++;
            stack = new ListNode<>(x, stack);
            if (x >= max()) max.addFirst(x);
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
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
                        if (result != op.arg) throw new TestFailure("Pop: expected " + op.arg + ", got " + result);
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) throw new TestFailure("Max: expected " + op.arg + ", got " + result);
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) throw new TestFailure("Empty: expected " + op.arg + ", got " + s);
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

    private StackWithMax() {
    }
}
