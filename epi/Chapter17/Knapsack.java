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
    public static int optimumSubjectToCapacityBottomUp(List<Item> items, int capacity) {
        final int[] dp = new int[capacity + 1];
        for (int i = 0; i < items.size(); i++) {
            for (int j = capacity; j >= items.get(i).weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - items.get(i).weight] + items.get(i).value);
            }
        }
        return dp[capacity];
    }

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        return dfs(items, capacity, 0, new Integer[items.size()][capacity + 1]);
    }

    public static int dfs(List<Item> items, int cap, int i, Integer[][] dp) {
        if (i == items.size()) {
            return 0;
        }
        if (dp[i][cap] != null) {
            return dp[i][cap];
        }
        final int skip = dfs(items, cap, i + 1, dp);
        int take = 0;
        if (cap >= items.get(i).weight) {
            take = dfs(items, cap - items.get(i).weight, i + 1, dp) + items.get(i).value;
        }
        return dp[i][cap] = Math.max(skip, take);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private Knapsack() {}
}
