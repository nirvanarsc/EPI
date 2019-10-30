package epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import epi.test_framework.BinaryTreeUtils;

public class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(T data) {
        this.data = data;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BinaryTreeNode other = (BinaryTreeNode) o;
        if (!Objects.equals(data, other.data)) {
            return false;
        }
        if (!Objects.equals(left, other.left)) {
            return false;
        }
        return Objects.equals(right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, left, right);
    }

    @Override
    public String toString() {
        return BinaryTreeUtils.binaryTreeToString(this);
    }

    public static BinaryTreeNode<Integer> testTree() {
        final BinaryTreeNode<Integer> t = new BinaryTreeNode<>(8);
        t.left = new BinaryTreeNode<>(3);
        t.left.left = new BinaryTreeNode<>(1);
        t.left.right = new BinaryTreeNode<>(6);
        t.right = new BinaryTreeNode<>(10);
        t.right.left = new BinaryTreeNode<>(9);
        t.right.right = new BinaryTreeNode<>(14);

        return t;
    }

    public static List<Integer> serialize(BinaryTreeNode<Integer> tree) {
        final List<Integer> result = new ArrayList<>();
        final Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(tree);
        while (!stack.empty()) {
            final BinaryTreeNode<Integer> p = stack.pop();
            result.add(p == null ? 0 : 1);
            if (p != null) {
                stack.push(p.left);
                stack.push(p.right);
            }
        }
        return result;
    }
}
