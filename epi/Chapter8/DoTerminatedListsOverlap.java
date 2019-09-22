package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.HashSet;
import java.util.Set;

public final class DoTerminatedListsOverlap {

    public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        if (l0 == null || l1 == null) return null;

        final int size0 = l0.size();
        final int size1 = l1.size();
        int diff = Math.abs(size0 - size1);
        ListNode<Integer> longer = size0 > size1 ? l0 : l1;
        ListNode<Integer> shorter = longer == l1 ? l0 : l1;

        while (diff-- > 0) {
            longer = longer.next;
        }
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }

        return longer;
    }

    public static ListNode<Integer> overlappingNoCycleLists2(ListNode<Integer> l0, ListNode<Integer> l1) {
        final Set<ListNode<Integer>> set = new HashSet<>();
        while (l0 != null) {
            set.add(l0);
            l0 = l0.next;
        }
        while (l1 != null && !set.contains(l1)) {
            l1 = l1.next;
        }

        return l1;
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
        final ListNode<Integer> result = executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

        if (result != common) {
            throw new TestFailure("Invalid result");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DoTerminatedListsOverlap() {
    }
}
