package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public final class PivotList {

    public static ListNode<Integer> listPivoting(ListNode<Integer> l, int x) {
        final ListNode<Integer> smaller = new ListNode<>(0, null);
        final ListNode<Integer> equal = new ListNode<>(0, null);
        final ListNode<Integer> greater = new ListNode<>(0, null);
        final List<ListNode<Integer>> tails = Arrays.asList(smaller, equal, greater);

        int curr;
        for (ListNode<Integer> iter = l; iter != null; iter = iter.next) {
            if (iter.data < x) {
                curr = 0;
            } else if (iter.data == x) {
                curr = 1;
            } else {
                curr = 2;
            }
            tails.get(curr).next = iter;
            tails.set(curr, iter);
        }

        tails.get(2).next = null;
        tails.get(1).next = greater.next;
        tails.get(0).next = equal.next;
        return smaller.next;
    }

    @EpiTest(testDataFile = "pivot_list.tsv")
    public static void listPivotingWrapper(TimedExecutor executor, ListNode<Integer> l, int x)
            throws Exception {
        final List<Integer> original = l == null ? new ArrayList<>() : l.toArray();
        final ListNode<Integer> finalL = l;
        l = executor.run(() -> listPivoting(finalL, x));

        final List<Integer> pivoted = l == null ? new ArrayList<>() : l.toArray();

        int mode = -1;
        for (Integer i : pivoted) {
            switch (mode) {
                case -1:
                    if (i == x) { mode = 0; } else if (i > x) { mode = 1; }
                    break;
                case 0:
                    if (i < x) { throw new TestFailure("List is not pivoted"); }
                    if (i > x) { mode = 1; }
                    break;
                case 1:
                    if (i <= x) { throw new TestFailure("List is not pivoted"); }
            }
        }

        Collections.sort(original);
        Collections.sort(pivoted);
        if (!original.equals(pivoted)) {
            throw new TestFailure("Result list contains different values");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private PivotList() {}
}
