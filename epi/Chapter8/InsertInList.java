package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class InsertInList {

    // Insert newNode after node.
    public static void insertAfter(ListNode<Integer> node, ListNode<Integer> newNode) {
        newNode.next = node.next;
        node.next = newNode;
    }

    @EpiTest(testDataFile = "insert_in_list.tsv")
    public static ListNode<Integer> insertListWrapper(TimedExecutor executor,
                                                      ListNode<Integer> l,
                                                      int nodeIdx,
                                                      int newNodeData) throws Exception {
        ListNode<Integer> node = l;
        while (nodeIdx > 1) {
            node = node.next;
            --nodeIdx;
        }

        final ListNode<Integer> newNode = new ListNode<>(newNodeData, null);
        final ListNode<Integer> finalNode = node;

        executor.run(() -> insertAfter(finalNode, newNode));

        return l;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private InsertInList() {
    }
}
