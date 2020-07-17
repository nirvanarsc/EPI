package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

import java.util.Objects;

public final class DeleteFromList {

    // Delete the node immediately following aNode. Assumes aNode is not a tail.
    public static void deleteNextNode(ListNode<Integer> aNode) {
        aNode.next = aNode.next.next;
    }

    @EpiTest(testDataFile = "delete_from_list.tsv")
    public static ListNode<Integer> deleteListWrapper(TimedExecutor executor,
                                                      ListNode<Integer> head,
                                                      int nodeIdx) throws Exception {
        ListNode<Integer> nodeToDelete = head;
        ListNode<Integer> prev = null;

        if (nodeToDelete == null) {
            throw new RuntimeException("List is empty");
        }
        while (nodeIdx-- > 0) {
            if (nodeToDelete.next == null) {
                throw new RuntimeException("Can't delete last node");
            }
            prev = nodeToDelete;
            nodeToDelete = nodeToDelete.next;
        }

        final ListNode<Integer> finalPrev = prev;

        executor.run(() -> deleteNextNode(Objects.requireNonNull(finalPrev)));

        return head;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DeleteFromList() {}
}
