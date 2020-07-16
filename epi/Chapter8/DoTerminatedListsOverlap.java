package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class DoTerminatedListsOverlap {

    @SuppressWarnings("ReturnOfNull")
    public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        if (l0 == null || l1 == null) {
            return null;
        }
        ListNode<Integer> iter0 = l0;
        ListNode<Integer> iter1 = l1;
        int count = 0;
        while (count != 2) {
            iter0 = iter0.next;
            iter1 = iter1.next;
            if (iter0 == null) {
                iter0 = l1;
                count++;
            }
            if (iter1 == null) {
                iter1 = l0;
                count++;
            }
        }
        while (iter0 != null && iter1 != null) {
            if (iter0 == iter1) {
                return iter0;
            }
            iter0 = iter0.next;
            iter1 = iter1.next;
        }
        return null;
    }

    public static ListNode<Integer> overlappingNoCycleListsConcise(ListNode<Integer> l0, ListNode<Integer> l1) {
        ListNode<Integer> iter0 = l0;
        ListNode<Integer> iter1 = l1;

        while (iter0 != iter1) {
            iter0 = iter0 == null ? l1 : iter0.next;
            iter1 = iter1 == null ? l0 : iter1.next;
        }

        return iter0;
    }

    @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
    public static void overlappingNoCycleListsWrapper(TimedExecutor executor,
                                                      ListNode<Integer> l0,
                                                      ListNode<Integer> l1,
                                                      ListNode<Integer> common) throws Exception {
        if (common != null) {
            if (l0 != null) {
                ListNode<Integer> i = l0;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l0 = common;
            }

            if (l1 != null) {
                ListNode<Integer> i = l1;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l1 = common;
            }
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;
        final ListNode<Integer> result = executor.run(() -> overlappingNoCycleListsConcise(finalL0, finalL1));

        if (result != common) {
            throw new TestFailure("Invalid result");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DoTerminatedListsOverlap() {}
}
