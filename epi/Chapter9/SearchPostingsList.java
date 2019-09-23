package epi.Chapter9;

import epi.PostingListNode;

import java.util.Deque;
import java.util.LinkedList;

public final class SearchPostingsList {

    public static void main(String[] args) {
        final PostingListNode head1 = getTestNode();
        final PostingListNode head2 = getTestNode();

        System.out.println(head1);
        computePostingOrderRecursive(head1);
        System.out.println(head1);

        System.out.println();

        System.out.println(head2);
        computePostingOrderIterative(head2);
        System.out.println(head2);
    }

    private static void computePostingOrderIterative(PostingListNode node) {
        final Deque<PostingListNode> processOrder = new LinkedList<>();
        processOrder.addFirst(node);
        int order = 0;
        while (!processOrder.isEmpty()) {
            final PostingListNode curr = processOrder.removeFirst();
            if (curr != null && curr.order == -1) {
                curr.order = order++;
                processOrder.addFirst(curr.next);
                processOrder.addFirst(curr.jump);
            }
        }
    }

    private static void computePostingOrderRecursive(PostingListNode head) {
        computePostingOrderHelper(head, 0);
    }

    private static int computePostingOrderHelper(PostingListNode node, int order) {
        if (node != null && node.order == -1) {
            node.order = order++;
            order = computePostingOrderHelper(node.jump, order);
            order = computePostingOrderHelper(node.next, order);
        }
        return order;
    }

    private static PostingListNode getTestNode() {
        final PostingListNode a = new PostingListNode(-1, null, null);
        final PostingListNode b = new PostingListNode(-1, null, null);
        final PostingListNode c = new PostingListNode(-1, null, null);
        final PostingListNode d = new PostingListNode(-1, null, null);
        a.next = b;
        b.next = c;
        c.next = d;
        a.jump = c;
        c.jump = b;
        b.jump = d;
        d.jump = d;
        return a;
    }
}
