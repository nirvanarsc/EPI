package epi;

import epi.test_framework.BinaryTreeUtils;

import java.util.Objects;

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
        t.right.right = new BinaryTreeNode<>(14);

        return t;
    }
}
