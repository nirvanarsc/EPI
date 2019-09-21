package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ReverseSublist {

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist(ListNode<Integer> l, int from, int to) {
        if (from == to) {
            return l;
        }

        final ListNode<Integer> dummyHead = new ListNode<>(0, l);
        ListNode<Integer> sublistHead = dummyHead;

        int k = 1;
        while (k++ < from) {
            sublistHead = sublistHead.next;
        }

        final ListNode<Integer> iterator = sublistHead.next;
        while (from++ < to) {
            final ListNode<Integer> temp = iterator.next;
            iterator.next = temp.next;
            temp.next = sublistHead.next;
            sublistHead.next = temp;
        }
        return dummyHead.next;
    }

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist2(ListNode<Integer> l, int from, int to) {
        if (from == to) {
            return l;
        }

        ListNode<Integer> beforeFrom = l;
        ListNode<Integer> iterator = l;
        ListNode<Integer> fromNode = null;
        ListNode<Integer> toNode = null;

        for (int i = 1; iterator != null; i++) {
            if (i + 1 == from) {
                beforeFrom = iterator;
            }
            if (i == from) {
                fromNode = iterator;
            }
            if (i - 1 == to) {
                toNode = iterator;
            }
            iterator = iterator.next;
        }

        if (beforeFrom == fromNode) {
            return reverse(fromNode, toNode);
        }

        beforeFrom.next = reverse(fromNode, toNode);
        return l;
    }

    public static <T> ListNode<T> reverse(ListNode<T> from, ListNode<T> to) {
        ListNode<T> head = to;

        while (from != to) {
            final ListNode<T> next = from.next;
            from.next = head;
            head = from;
            from = next;
        }

        return head;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ReverseSublist() {
    }
}
