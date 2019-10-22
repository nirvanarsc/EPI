package epi.Chapter14;

import epi.Chapter8.SortedListsMerge;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SortList {

    @EpiTest(testDataFile = "sort_list.tsv")
    public static ListNode<Integer> stableSortList(ListNode<Integer> list) {
        if (list == null || list.next == null) {
            return list;
        }

        ListNode<Integer> preSlow = new ListNode<>(0, list), fast = list, slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            preSlow = preSlow.next;
        }
        preSlow.next = null; // Splits the list into two equal-sized lists.

        list = stableSortList(list);
        slow = stableSortList(slow);

        return SortedListsMerge.mergeTwoSortedLists(list, slow);
    }

    @EpiTest(testDataFile = "sort_list.tsv")
    public static ListNode<Integer> stableSortList2(ListNode<Integer> list) {
        final ListNode<Integer> dummyHead = new ListNode<>(0, list);
        ListNode<Integer> iter = list;

        while (iter != null && iter.next != null) {
            if (iter.data > iter.next.data) {
                final ListNode<Integer> target = iter.next;
                ListNode<Integer> pre = dummyHead;
                while (pre.next.data < target.data) {
                    pre = pre.next;
                }
                final ListNode<Integer> temp = pre.next;
                pre.next = target;
                iter.next = target.next;
                target.next = temp;
            } else {
                iter = iter.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortList() {}
}
