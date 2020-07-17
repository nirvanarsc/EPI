package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class EvenOddListMerge {

    @SuppressWarnings({ "ConstantConditions", "MethodParameterNamingConvention" })
    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
        final ListNode<Integer> evenHead = new ListNode<>(-1, L);
        final ListNode<Integer> oddHead = new ListNode<>(-1, L);
        ListNode<Integer> odd = oddHead;
        ListNode<Integer> even = evenHead;
        ListNode<Integer> iter = L;
        for (int i = 0; iter != null; i++) {
            if (i % 2 == 0) {
                even.next = iter;
                even = even.next;
            } else {
                odd.next = iter;
                odd = odd.next;
            }
            iter = iter.next;
        }
        odd.next = null;
        even.next = oddHead.next;
        return evenHead.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private EvenOddListMerge() {}
}
