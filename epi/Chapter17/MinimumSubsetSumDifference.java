package epi.Chapter17;

public final class MinimumSubsetSumDifference {

    public static int canPartition(int[] num) {
        int sum = 0;
        for (int value : num) { sum += value; }

        return canPartitionRecursive(new Integer[num.length][sum + 1], num, 0, 0, 0);
    }

    private static int canPartitionRecursive(Integer[][] dp, int[] num, int currentIndex, int sum1, int sum2) {
        if (currentIndex == num.length) { return Math.abs(sum1 - sum2); }

        if (dp[currentIndex][sum1] == null) {
            final int diff1 = canPartitionRecursive(dp, num, currentIndex + 1, sum1 + num[currentIndex], sum2);
            final int diff2 = canPartitionRecursive(dp, num, currentIndex + 1, sum1, sum2 + num[currentIndex]);
            dp[currentIndex][sum1] = Math.min(diff1, diff2);
        }

        return dp[currentIndex][sum1];
    }

    public static int canPartitionBottomUp(int[] num) {
        int sum = 0;
        for (int value : num) {
            sum += value;
        }

        final boolean[][] dp = new boolean[num.length][sum / 2 + 1];

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (int i = 0; i < num.length; i++) {
            dp[i][0] = true;
        }

        // with only one number, we can form a subset only when the required sum is equal to that number
        for (int s = 1; s <= sum / 2; s++) {
            dp[0][s] = num[0] == s;
        }

        // process all subsets for all sums
        for (int i = 1; i < num.length; i++) {
            for (int s = 1; s <= sum / 2; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s];
                } else if (s >= num[i]) {
                    // else include the number and see if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i - 1][s - num[i]];
                }
            }
        }

        int sum1 = 0;
        // Find the largest index in the last row which is true
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[num.length - 1][i]) {
                sum1 = i;
                break;
            }
        }

        final int sum2 = sum - sum1;
        return Math.abs(sum2 - sum1);
    }

    public static void main(String[] args) {
        System.out.println(String.format("%4d%4d", canPartition(new int[] { 1, 2, 3, 9 }),
                                         canPartitionBottomUp(new int[] { 1, 2, 3, 9 })));
        System.out.println(String.format("%4d%4d", canPartition(new int[] { 1, 2, 3, 9 }),
                                         canPartitionBottomUp(new int[] { 1, 2, 3, 9 })));
        System.out.println(String.format("%4d%4d", canPartition(new int[] { 1, 2, 7, 1, 5 }),
                                         canPartitionBottomUp(new int[] { 1, 2, 7, 1, 5 })));
        System.out.println(String.format("%4d%4d", canPartition(new int[] { 1, 3, 100, 4 }),
                                         canPartitionBottomUp(new int[] { 1, 3, 100, 4 })));
    }

    private MinimumSubsetSumDifference() {}
}
