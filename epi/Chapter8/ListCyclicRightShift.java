package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class ListCyclicRightShift {

    @SuppressWarnings({ "ReturnOfNull", "ConstantConditions", "MethodParameterNamingConvention" })
    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
        if (L == null) {
            return null;
        }
        ListNode<Integer> prev = new ListNode<>(-1, L);
        ListNode<Integer> iter = L;
        int size = 0;
        while (iter != null) {
            iter = iter.next;
            prev = prev.next;
            size++;
        }
        prev.next = L;
        iter = L;
        for (int i = 0; i < size - (k % size); i++) {
            iter = iter.next;
            prev = prev.next;
        }
        prev.next = null;
        return iter;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private ListCyclicRightShift() {}
}
