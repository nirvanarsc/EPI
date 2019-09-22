package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class DeleteKthLastFromList {

    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
    public static ListNode<Integer> removeKthLast(ListNode<Integer> l, int k) {
        final ListNode<Integer> dummyHead = new ListNode<>(0, l);
        ListNode<Integer> first = dummyHead;
        ListNode<Integer> second = dummyHead.next;

        while (k-- > 0) {
            second = second.next;
        }

        while (second != null) {
            first = first.next;
            second = second.next;
        }

        DeleteFromList.deleteNextNode(first);

        return dummyHead.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DeleteKthLastFromList() {
    }
}
