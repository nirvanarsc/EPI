
package epi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class ListNode<T> {
    public T data;
    public ListNode<T> next;

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public List<T> toArray() {
        final List<T> result = new ArrayList<>();
        ListNode<T> iter = this;
        while (iter != null) {
            result.add(iter.data);
            iter = iter.next;
        }
        return result;
    }

    public static boolean equalsIterativeImpl(ListNode<?> a, ListNode<?> b) {
        final Set<ListNode<?>> visitedA = new HashSet<>();
        final Set<ListNode<?>> visitedB = new HashSet<>();

        while (a != null && b != null) {
            if (visitedA.contains(a)) {
                return a.data.equals(b.data) && visitedB.contains(b);
            }
            if (!a.data.equals(b.data)) {
                return false;
            }
            visitedA.add(a);
            visitedB.add(b);
            a = a.next;
            b = b.next;
        }
        return a == null && b == null;
    }

    public int size() {
        int result = 0;
        final Set<ListNode<T>> visited = new HashSet<>();
        ListNode<T> node = this;

        while (node != null && !visited.contains(node)) {
            result++;
            visited.add(node);
            node = node.next;
        }
        return result;
    }

    public static <T> ListNode<T> reverse(ListNode<T> l) {
        ListNode<T> head = null;

        while (l != null) {
            final ListNode<T> next = l.next;
            l.next = head;
            head = l;
            l = next;
        }

        return head;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        final Set<ListNode<T>> visited = new HashSet<>();
        ListNode<T> node = this;
        boolean first = true;

        while (node != null) {
            if (first) {
                first = false;
            } else {
                result.append(" -> ");
            }
            if (visited.contains(node)) {
                if (node.next != node) {
                    result.append(node.data == null ? "null" : node.data.toString())
                            .append(" -> ... -> ");
                }
                result.append(node.data == null ? "null" : node.data.toString())
                        .append(" -> ...");
                break;
            } else {
                result.append(node.data == null ? "null" : node.data.toString());
                visited.add(node);
                node = node.next;
            }
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ListNode<?> other = (ListNode<?>) o;

        return equalsIterativeImpl(this, other);
    }
}
