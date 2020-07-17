package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class DeleteKthLastFromList {

    @SuppressWarnings("MethodParameterNamingConvention")
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
    public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
        final ListNode<Integer> res = new ListNode<>(-1, L);
        ListNode<Integer> prev = res;
        ListNode<Integer> iter = L, deleteIter = L;
        for (int i = 0; i < k; i++) {
            iter = iter.next;
        }
        while (iter != null) {
            iter = iter.next;
            deleteIter = deleteIter.next;
            prev = prev.next;
        }
        prev.next = prev.next.next;
        return res.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DeleteKthLastFromList() {}
}
