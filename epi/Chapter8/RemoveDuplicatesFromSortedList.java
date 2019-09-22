package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class RemoveDuplicatesFromSortedList {

    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    public static ListNode<Integer> removeDuplicates(ListNode<Integer> l) {
        if (l == null) return null;

        ListNode<Integer> prev = l;
        ListNode<Integer> curr = l;

        while (curr != null) {
            if (prev.data.equals(curr.data)) {
                curr = curr.next;
            } else {
                prev.next = curr;
                prev = curr;
            }
        }
        prev.next = null;

        return l;
    }

    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    public static ListNode<Integer> removeDuplicates2(ListNode<Integer> l) {
        ListNode<Integer> iter = l;

        while (iter != null) {
            ListNode<Integer> nextDistinct = iter.next;
            while (nextDistinct != null && nextDistinct.data.equals(iter.data)) {
                nextDistinct = nextDistinct.next;
            }
            iter.next = nextDistinct;
            iter = nextDistinct;
        }
        return l;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RemoveDuplicatesFromSortedList() {
    }
}
