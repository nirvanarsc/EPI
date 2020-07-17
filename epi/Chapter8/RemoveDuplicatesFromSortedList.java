package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class RemoveDuplicatesFromSortedList {

    @SuppressWarnings({ "ConstantConditions", "MethodParameterNamingConvention" })
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
        ListNode<Integer> iter1 = L;
        ListNode<Integer> iter2 = L;
        while (iter2 != null) {
            while (iter2 != null && iter1.data.equals(iter2.data)) {
                iter2 = iter2.next;
            }
            iter1.next = iter2;
            iter1 = iter1.next;
        }
        return L;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RemoveDuplicatesFromSortedList() {}
}
