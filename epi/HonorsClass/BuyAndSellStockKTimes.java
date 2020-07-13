package epi.HonorsClass;

import java.util.Arrays;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class BuyAndSellStockKTimes {

    @EpiTest(testDataFile = "buy_and_sell_stock_k_times.tsv")
    public static double buyAndSellStockKTimes(List<Double> prices, int k) {
        if (k > prices.size() / 2) {
            double res = 0;
            for (int i = 1; i < prices.size(); i++) {
                res += Math.max(0, prices.get(i) - prices.get(i - 1));
            }
            return res;
        }
        final double[] buy = new double[k];
        final double[] profit = new double[k];
        Arrays.fill(buy, 1e15);
        for (double p : prices) {
            buy[0] = Math.min(buy[0], p);
            profit[0] = Math.max(profit[0], p - buy[0]);
            for (int i = 1; i < k; i++) {
                buy[i] = Math.min(buy[i], p - profit[i - 1]);
                profit[i] = Math.max(profit[i], p - buy[i]);
            }
        }
        return profit[k - 1];
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BuyAndSellStockKTimes() {}
}
