package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

@SuppressWarnings("ReturnOfNull")
public final class IsListCyclic {

    public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
        if (head == null) {
            return null;
        }
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast && fast != null && fast.next != null);

        if (slow == fast) {
            slow = head;
            do {
                slow = slow.next;
                fast = fast.next;
            } while (slow != fast);
            return slow;
        }
        return null;
    }

    @EpiTest(testDataFile = "is_list_cyclic.tsv")
    public static void HasCycleWrapper(TimedExecutor executor, ListNode<Integer> head, int cycleIdx)
            throws Exception {
        int cycleLength = 0;
        if (cycleIdx != -1) {
            if (head == null) {
                throw new RuntimeException("Can't cycle empty list");
            }
            ListNode<Integer> cycleStart = null, cursor = head;
            while (cursor.next != null) {
                if (cursor.data == cycleIdx) {
                    cycleStart = cursor;
                }
                cursor = cursor.next;
                if (cycleStart != null) {
                    cycleLength++;
                }
            }
            if (cursor.data == cycleIdx) {
                cycleStart = cursor;
            }
            if (cycleStart == null) {
                throw new RuntimeException("Can't find a cycle start");
            }
            cursor.next = cycleStart;
            cycleLength++;
        }

        final ListNode<Integer> result = executor.run(() -> hasCycle(head));

        if (cycleIdx == -1) {
            if (result != null) {
                throw new TestFailure("Found a non-existing cycle");
            }
        } else {
            if (result == null) {
                throw new TestFailure("Existing cycle was not found");
            }

            ListNode<Integer> cursor = result;
            do {
                cursor = cursor.next;
                cycleLength--;
                if (cursor == null || cycleLength < 0) {
                    throw new TestFailure(
                            "Returned node does not belong to the cycle or is not the closest node to the head");
                }
            } while (cursor != result);

            if (cycleLength != 0) {
                throw new TestFailure(
                        "Returned node does not belong to the cycle or is not the closest node to the head");
            }
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsListCyclic() {}
}
