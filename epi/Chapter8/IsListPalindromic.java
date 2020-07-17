package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

@SuppressWarnings({ "MethodParameterNamingConvention", "ConstantConditions" })
public final class IsListPalindromic {

    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
        if (L == null || L.next == null) {
            return true;
        }
        ListNode<Integer> slow = new ListNode<>(-1, L);
        ListNode<Integer> fast = new ListNode<>(-1, L);
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        final ListNode<Integer> t = slow.next;
        slow.next = null;
        ListNode<Integer> rev = reverse(t, null);
        ListNode<Integer> iter = L;
        while (iter != null) {
            if (!iter.data.equals(rev.data)) {
                return false;
            }
            iter = iter.next;
            rev = rev.next;
        }
        return true;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> L, ListNode<Integer> newTail) {
        while (L != null) {
            final ListNode<Integer> temp = L.next;
            L.next = newTail;
            newTail = L;
            L = temp;
        }
        return newTail;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsListPalindromic() {}
}
