package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings("MethodParameterNamingConvention")
public final class ReverseSublist {

    @EpiTest(testDataFile = "reverse_sublist.tsv")
    public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start, int finish) {
        if (start == finish) {
            return L;
        }
        final ListNode<Integer> head = new ListNode<>(-1, L);
        ListNode<Integer> iter = head;
        for (int i = 1; i < start; i++) {
            iter = iter.next;
        }
        final ListNode<Integer> preStart = iter;
        final ListNode<Integer> startNode = iter.next;
        iter = iter.next.next;
        ListNode<Integer> prev = startNode;
        for (int i = start; i < finish; i++) {
            final ListNode<Integer> next = iter.next;
            iter.next = prev;
            prev = iter;
            iter = next;
        }
        preStart.next = prev;
        startNode.next = iter;
        return head.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ReverseSublist() {}
}
