package epi.Chapter9;

import epi.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public final class PrintReverseLinkedList {

    public static void printReverseLinkedList(ListNode<Integer> head) {
        final Deque<Integer> nodes = new LinkedList<>();
        while (head != null) {
            nodes.addFirst(head.data);
            head = head.next;
        }
        while (!nodes.isEmpty()) {
            System.out.print(nodes.removeFirst() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final ListNode<Integer> head = new ListNode<>(0, null);
        ListNode<Integer> iter = head;
        for (int i = 1; i < 10; i++) {
            iter.next = new ListNode<>(i, null);
            iter = iter.next;
        }

        printReverseLinkedList(head);
    }

    private PrintReverseLinkedList() {
    }
}
