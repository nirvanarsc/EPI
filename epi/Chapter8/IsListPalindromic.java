package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IsListPalindromic {

    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    public static boolean isLinkedListAPalindrome(ListNode<Integer> l) {
        ListNode<Integer> slow = new ListNode<>(0, l), fast = l;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        final ListNode<Integer> toBeReversed = slow.next;
        slow.next = null;
        ListNode<Integer> reverse = ListNode.reverse(toBeReversed);

        while (l != null) {
            if (!l.data.equals(reverse.data)) {
                return false;
            }
            reverse = reverse.next;
            l = l.next;
        }

        return true;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsListPalindromic() {
    }
}
