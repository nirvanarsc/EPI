package epi.Chapter17;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class Knapsack {

    @EpiUserType(ctorParams = { Integer.class, Integer.class })
    public static class Item {
        public Integer weight;
        public Integer value;

        public Item(Integer weight, Integer value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "weight: " + weight + " value: " + value;
        }
    }

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        return knapsack(items, capacity, 0, new int[items.size()][capacity + 1]);
    }

    public static int knapsack(List<Item> items, int w, int i, int[][] dp) {
        if (i == items.size()) {
            return 0;
        }

        if (dp[i][w] != 0) {
            return dp[i][w];
        }

        final int withoutItem = knapsack(items, w, i + 1, dp);
        final int withItem = w < items.get(i).weight
                             ? 0 : knapsack(items, w - items.get(i).weight, i + 1, dp) + items.get(i).value;
        dp[i][w] = Math.max(withoutItem, withItem);
        return dp[i][w];
    }

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int knapsackBottomUp(List<Item> items, int capacity) {
        final int[][] dp = new int[items.size()][capacity + 1];

        for (int c = 0; c <= capacity; c++) {
            if (items.get(0).weight <= c) {
                dp[0][c] = items.get(0).value;
            }
        }

        for (int i = 1; i < items.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                final int withoutItem = dp[i - 1][j];
                final int withItem = j < items.get(i).weight
                                     ? 0 : dp[i - 1][j - items.get(i).weight] + items.get(i).value;
                dp[i][j] = Math.max(withoutItem, withItem);
            }
        }

        return dp[items.size() - 1][capacity];
    }

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int knapsackSpace(List<Item> items, int capacity) {
        final int[][] dp = new int[2][capacity + 1];

        for (int c = 0; c <= capacity; c++) {
            if (items.get(0).weight <= c) {
                dp[0][c] = items.get(0).value;
            }
        }

        for (int i = 1; i < items.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                final int withoutItem = dp[(i - 1) % 2][j];
                final int withItem = j < items.get(i).weight
                                     ? 0 : dp[(i - 1) % 2][j - items.get(i).weight] + items.get(i).value;
                dp[i % 2][j] = Math.max(withoutItem, withItem);
            }
        }

        return dp[(items.size() - 1) % 2][capacity];
    }

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int knapsackSpaceC(List<Item> items, int capacity) {
        final int[] dp = new int[capacity + 1];
        for (int i = 0; i < items.size(); i++) {
            for (int j = capacity; j >= items.get(i).weight; j--) {
                dp[j] = Math.max(dp[j], items.get(i).value + dp[j - items.get(i).weight]);
            }
        }

        return dp[capacity];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Knapsack() {}
}
