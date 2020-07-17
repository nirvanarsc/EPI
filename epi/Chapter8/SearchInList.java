package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SearchInList {

    public static ListNode<Integer> searchList(ListNode<Integer> l, int key) {
        while (l != null && l.data != key) {
            l = l.next;
        }
        return l;
    }

    @EpiTest(testDataFile = "search_in_list.tsv")
    public static int searchListWrapper(ListNode<Integer> l, int key) {
        final ListNode<Integer> result = searchList(l, key);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SearchInList() {}
}
