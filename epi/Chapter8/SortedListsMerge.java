package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SortedListsMerge {

    @EpiTest(testDataFile = "sorted_lists_merge.tsv")
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        final ListNode<Integer> dummy = new ListNode<>(-1, null);
        ListNode<Integer> curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortedListsMerge() {}
}
