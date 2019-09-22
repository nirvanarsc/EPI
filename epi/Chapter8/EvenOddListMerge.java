package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Arrays;
import java.util.List;

public final class EvenOddListMerge {

    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    public static ListNode<Integer> evenOddMerge(ListNode<Integer> l) {
        if (l == null) return null;
        final ListNode<Integer> evenDummyHead = new ListNode<>(0, null);
        final ListNode<Integer> oddDummyHead = new ListNode<>(0, null);
        final List<ListNode<Integer>> tails = Arrays.asList(evenDummyHead, oddDummyHead);
        int turn = 0;
        for (ListNode<Integer> iter = l; iter != null; iter = iter.next) {
            tails.get(turn).next = iter;
            tails.set(turn, tails.get(turn).next);
            turn ^= 1;
        }
        tails.get(1).next = null;
        tails.get(0).next = oddDummyHead.next;
        return evenDummyHead.next;
    }

    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    public static ListNode<Integer> evenOddMerge2(ListNode<Integer> l) {
        final ListNode<Integer> dummyHead = new ListNode<>(0, l);
        final ListNode<Integer> odds = new ListNode<>(0, null);
        ListNode<Integer> newOdd = odds;
        while (l != null && l.next != null) {
            final ListNode<Integer> next = l.next;
            newOdd.next = next;
            newOdd = newOdd.next;
            if (l.next.next == null) {
                l.next = odds.next;
                break;
            }
            l.next = l.next.next;
            l = l.next;
            next.next = null;
        }
        if (l != null) {
            l.next = odds.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EvenOddListMerge() {
    }
}
