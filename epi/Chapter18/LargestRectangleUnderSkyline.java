package epi.Chapter18;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class LargestRectangleUnderSkyline {

    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
    public static int calculateLargestRectangle(List<Integer> heights) {
        final Deque<Integer> stack = new LinkedList<>();
        int res = 0, i = 0;
        for (; i < heights.size(); i++) {
            while (!stack.isEmpty() && heights.get(i) < heights.get(stack.peekFirst())) {
                final int top = stack.removeFirst();
                final int width = stack.isEmpty() ? i : i - stack.peekFirst() - 1;
                res = Math.max(res, heights.get(top) * width);
            }
            stack.addFirst(i);
        }

        while (!stack.isEmpty()) {
            final int top = stack.removeFirst();
            final int width = stack.isEmpty() ? i : i - stack.peekFirst() - 1;
            res = Math.max(res, heights.get(top) * width);
        }

        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private LargestRectangleUnderSkyline() {}
}
