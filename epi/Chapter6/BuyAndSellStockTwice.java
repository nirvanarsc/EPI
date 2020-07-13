package epi.Chapter6;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class BuyAndSellStockTwice {

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        double buy1 = 1e15;
        double buy2 = 1e15;
        double profit1 = 0;
        double profit2 = 0;
        for (double p : prices) {
            buy1 = Math.min(buy1, p);
            profit1 = Math.max(profit1, p - buy1);
            buy2 = Math.min(buy2, p - profit1);
            profit2 = Math.max(profit2, p - buy2);
        }
        return profit2;
    }

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice2(List<Double> prices) {
        final int n = prices.size();
        double leftMin = prices.get(0);
        double rightMax = prices.get(n - 1);
        final double[] leftProfits = new double[n];
        final double[] rightProfits = new double[n + 1];
        double maxProfit = 0;
        for (int i = 1; i < n; i++) {
            leftProfits[i] = Math.max(leftProfits[i - 1], prices.get(i) - leftMin);
            leftMin = Math.min(leftMin, prices.get(i));

        }
        for (int i = n - 1; i >= 0; i--) {
            rightProfits[i] = Math.max(rightProfits[i + 1], rightMax - prices.get(i));
            rightMax = Math.max(rightMax, prices.get(i));
        }
        for (int i = 0; i < n; ++i) {
            maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BuyAndSellStockTwice() {}
}
