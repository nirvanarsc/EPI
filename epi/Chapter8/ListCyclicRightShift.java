package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ListCyclicRightShift {

    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
        if (L == null || k == 0) return L;

        int i = 1;
        ListNode<Integer> tail = L;
        while (tail.next != null) {
            i++;
            tail = tail.next;
        }
        tail.next = L;

        int diff = i - (k % i);
        while (diff-- > 0) {
            tail = tail.next;
        }

        final ListNode<Integer> newHead = tail.next;
        tail.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ListCyclicRightShift() {
    }
}
