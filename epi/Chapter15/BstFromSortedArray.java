package epi.Chapter15;

import java.util.List;

import epi.BstNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

@SuppressWarnings({ "ReturnOfNull", "ConstantConditions" })
public final class BstFromSortedArray {

    public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
        return dfs(A, 0, A.size() - 1);
    }

    private static BstNode<Integer> dfs(List<Integer> list, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        final int mid = lo + hi >>> 1;
        final BstNode<Integer> root = new BstNode<>(list.get(mid));
        root.left = dfs(list, lo, mid - 1);
        root.right = dfs(list, mid + 1, hi);
        return root;
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
