package epi.Chapter8;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class IntAsListAdd {

    @EpiTest(testDataFile = "int_as_list_add.tsv")
    public static ListNode<Integer> addTwoNumbers1(ListNode<Integer> l1, ListNode<Integer> l2) {
        final ListNode<Integer> dummyHead = new ListNode<>(0, null);
        ListNode<Integer> iter = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            iter.next = new ListNode<>(sum % 10, null);
            carry = sum / 10;
            iter = iter.next;
        }

        if (carry > 0) {
            iter.next = new ListNode<>(carry, null);
        }

        return dummyHead.next;
    }

    @EpiTest(testDataFile = "int_as_list_add.tsv")
    public static ListNode<Integer> addTwoNumbers2(ListNode<Integer> l1, ListNode<Integer> l2) {
        final int size1 = l1.size();
        final int size2 = l2.size();
        final ListNode<Integer> res = size1 > size2 ? l1 : l2;
        ListNode<Integer> larger = res;
        ListNode<Integer> smaller = larger == l1 ? l2 : l1;
        int carry = 0;

        while (smaller.next != null) {
            larger.data += smaller.data + carry;
            carry = larger.data / 10;
            larger.data %= 10;
            larger = larger.next;
            smaller = smaller.next;
        }

        larger.data += smaller.data + carry;
        carry = larger.data / 10;
        larger.data %= 10;

        if (carry > 0 && larger.next == null) {
            larger.next = new ListNode<>(carry, null);
        } else if (carry > 0) {
            larger = larger.next;
            while (carry > 0 && larger.next != null) {
                larger.data += carry;
                carry = larger.data / 10;
                larger.data %= 10;
                larger = larger.next;
            }
            larger.data += carry;
            carry = larger.data / 10;
            larger.data %= 10;
            if (carry > 0) {
                larger.next = new ListNode<>(carry, null);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IntAsListAdd() {
    }
}
