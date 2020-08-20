package epi.Chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import epi.Chapter17.Knapsack.Item;

public final class ElectoralCollege {

    public static void main(String[] args) {
        final List<Item> votes = new ArrayList<>();
        votes.add(new Item(9, 9));
        votes.add(new Item(3, 3));
        votes.add(new Item(11, 11));
        votes.add(new Item(6, 6));
        votes.add(new Item(55, 55));
        votes.add(new Item(9, 9));
        votes.add(new Item(7, 7));
        votes.add(new Item(3, 3));
        votes.add(new Item(29, 29));
        votes.add(new Item(16, 16));
        votes.add(new Item(4, 4));
        votes.add(new Item(4, 4));
        votes.add(new Item(20, 20));
        votes.add(new Item(11, 11));
        votes.add(new Item(6, 6));
        votes.add(new Item(6, 6));
        votes.add(new Item(8, 8));
        votes.add(new Item(8, 8));
        votes.add(new Item(4, 4));
        votes.add(new Item(10, 10));
        votes.add(new Item(11, 11));
        votes.add(new Item(16, 16));
        votes.add(new Item(10, 10));
        votes.add(new Item(6, 6));
        votes.add(new Item(10, 10));
        votes.add(new Item(3, 3));
        votes.add(new Item(5, 5));
        votes.add(new Item(6, 6));
        votes.add(new Item(4, 4));
        votes.add(new Item(14, 14));
        votes.add(new Item(5, 5));
        votes.add(new Item(29, 29));
        votes.add(new Item(15, 15));
        votes.add(new Item(3, 3));
        votes.add(new Item(18, 18));
        votes.add(new Item(7, 7));
        votes.add(new Item(7, 7));
        votes.add(new Item(20, 20));
        votes.add(new Item(4, 4));
        votes.add(new Item(9, 9));
        votes.add(new Item(3, 3));
        votes.add(new Item(11, 11));
        votes.add(new Item(38, 38));
        votes.add(new Item(6, 6));
        votes.add(new Item(3, 3));
        votes.add(new Item(13, 13));
        votes.add(new Item(12, 12));
        votes.add(new Item(5, 5));
        votes.add(new Item(10, 10));
        votes.add(new Item(3, 3));
        votes.add(new Item(3, 3));

        final int bestSplit = bestEqualSplit(votes.stream().mapToInt(v -> v.value).toArray());
        System.out.println(bestSplit);
    }

    public static int bestEqualSplit(int[] votes) {
        final int total = Arrays.stream(votes).sum();
        final boolean[] dp = new boolean[total + 1];
        dp[0] = true;

        for (int vote : votes) {
            for (int i = total; i >= vote; --i) {
                dp[i] |= dp[i - vote];
            }
        }
        for (int i = total / 2; i >= 0; --i) {
            if (dp[i]) {
                return total - 2 * i;
            }
        }
        return 0;
    }

    private ElectoralCollege() {}
}
