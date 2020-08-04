package epi.Chapter14;

import epi.Chapter8.SortedListsMerge;
import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings({ "MethodParameterNamingConvention", "ConstantConditions" })
public final class SortList {

    @EpiTest(testDataFile = "sort_list.tsv")
    public static ListNode<Integer> stableSortList(ListNode<Integer> L) {
        if (L == null || L.next == null) {
            return L;
        }
        ListNode<Integer> slow = new ListNode<>(-1, L);
        ListNode<Integer> fast = new ListNode<>(-1, L);

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        final ListNode<Integer> temp = slow.next;
        slow.next = null;

        final ListNode<Integer> left = stableSortList(L);
        final ListNode<Integer> right = stableSortList(temp);

        return SortedListsMerge.mergeTwoSortedLists(left, right);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SortList() {}
}
