package epi.Chapter15;

import java.util.List;

import epi.BstNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class BstFromSortedArray {

    public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> integers) {
        if (integers.isEmpty()) {
            return null;
        }
        final int mid = integers.size() / 2;
        return new BstNode<>(integers.get(mid),
                             buildMinHeightBSTFromSortedArray(integers.subList(0, mid)),
                             buildMinHeightBSTFromSortedArray(integers.subList(mid + 1, integers.size())));
    }

    @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
    public static int buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                                              List<Integer> integers) throws Exception {
        final BstNode<Integer> result = executor.run(() -> buildMinHeightBSTFromSortedArray(integers));
        final List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

        TestUtils.assertAllValuesPresent(integers, inorder);
        BinaryTreeUtils.assertTreeIsBst(result);
        return BinaryTreeUtils.binaryTreeHeight(result);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BstFromSortedArray() {}
}
