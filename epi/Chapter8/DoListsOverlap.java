package epi.Chapter8;

import static epi.Chapter8.DoTerminatedListsOverlap.*;
import static epi.Chapter8.IsListCyclic.*;

import java.util.HashSet;
import java.util.Set;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;


public final class DoListsOverlap {

    @SuppressWarnings("ReturnOfNull")
    public static ListNode<Integer> overlappingLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        final ListNode<Integer> cycle0 = hasCycle(l0);
        final ListNode<Integer> cycle1 = hasCycle(l1);
        if (cycle0 == null && cycle1 == null) {
            return overlappingNoCycleLists(l0, l1);
        }
        if (cycle0 == null || cycle1 == null) {
            return null;
        }
        if (cycle0 == cycle1) {
            return cycle0;
        }
        ListNode<Integer> iter = cycle0.next;
        while (iter != cycle0) {
            if (iter == cycle1) {
                return cycle1;
            }
            iter = iter.next;
        }
        return null;
    }

    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    public static void overlappingListsWrapper(TimedExecutor executor,
                                               ListNode<Integer> l0,
                                               ListNode<Integer> l1,
                                               ListNode<Integer> common,
                                               int cycle0,
                                               int cycle1) throws Exception {
        if (common != null) {
            if (l0 == null) {
                l0 = common;
            } else {
                ListNode<Integer> it = l0;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }

            if (l1 == null) {
                l1 = common;
            } else {
                ListNode<Integer> it = l1;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }
        }

        processCycle(l0, cycle0);
        processCycle(l1, cycle1);

        final Set<Integer> commonNodes = new HashSet<>();
        ListNode<Integer> it = common;
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data);
            it = it.next;
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;
        final ListNode<Integer> result = executor.run(() -> overlappingLists(finalL0, finalL1));

        if (!((commonNodes.isEmpty() && result == null)
              || (result != null && commonNodes.contains(result.data)))) {
            throw new TestFailure("Invalid result");
        }
    }

    private static void processCycle(ListNode<Integer> l1, int cycle1) {
        if (cycle1 != -1 && l1 != null) {
            ListNode<Integer> last = l1;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l1;
            while (cycle1-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DoListsOverlap() {}
}
