package epi.Chapter11;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class StackFromHeap<T> {

    PriorityQueue<Node<T>> pq = new PriorityQueue<>((x, y) -> Integer.compare(y.idx, x.idx));
    int idx;

    static class Node<T> {
        T element;
        int idx;

        Node(T element, int idx) {
            this.element = element;
            this.idx = idx;
        }
    }

    T pop() {
        if (pq.isEmpty()) { throw new NoSuchElementException("Stack is empty."); }
        return pq.poll().element;
    }

    void push(T e) {
        pq.add(new Node<>(e, idx++));
    }

    T peek() {
        if (pq.isEmpty()) { return null; }
        return pq.peek().element;
    }

    boolean isEmpty() {
        return pq.isEmpty();
    }

    // Add "-ea" (-enableassertions) to VM options before execution
    public static void main(String[] args) {
        final StackFromHeap<Integer> stackFromHeap = new StackFromHeap<>();
        final Deque<Integer> stack = new LinkedList<>();
        IntStream.range(0, 100).forEach(i -> {
            stackFromHeap.push(i);
            stack.addFirst(i);
        });

        for (int i = 0; i < 50; i++) {
            final Integer stackPop = stack.removeFirst();
            final Integer heapPop = stackFromHeap.pop();
            assert stackPop.equals(heapPop);
        }

        assert stack.peekFirst().equals(stackFromHeap.peek());

        IntStream.range(100, 200).forEach(i -> {
            stackFromHeap.push(i);
            stack.addFirst(i);
        });

        final List<Integer> heapList = new ArrayList<>();
        final List<Integer> stackList = new ArrayList<>(stack);
        while (!stackFromHeap.isEmpty()) {
            heapList.add(stackFromHeap.pop());
        }

        assert stackList.equals(heapList);

        System.out.println("Finished");
    }
}
