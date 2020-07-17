package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IntAsListAdd {

    @SuppressWarnings("MethodParameterNamingConvention")
    @EpiTest(testDataFile = "int_as_list_add.tsv")
    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1, ListNode<Integer> L2) {
        int carry = 0;
        final ListNode<Integer> res = new ListNode<>(-1, null);
        ListNode<Integer> iter = res;
        while (L1 != null || L2 != null) {
            int curr = carry;
            if (L1 != null) {
                curr += L1.data;
                L1 = L1.next;
            }
            if (L2 != null) {
                curr += L2.data;
                L2 = L2.next;
            }
            carry = curr / 10;
            curr %= 10;
            iter.next = new ListNode<>(curr, null);
            iter = iter.next;
        }
        if (carry > 0) {
            iter.next = new ListNode<>(carry, null);
        }
        return res.next;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntAsListAdd() {}
}
