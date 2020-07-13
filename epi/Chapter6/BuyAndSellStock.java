package epi.Chapter6;

import java.util.List;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class BuyAndSellStock {

    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        double profit = 0.0;
        double minPrice = Double.MAX_VALUE;
        for (double price : prices) {
            profit = Math.max(profit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }

        return profit;
    }

    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfitSpace(List<Double> prices) {
        final double[] max = new double[prices.size()];
        for (int i = prices.size() - 1; i >= 0; i--) {
            max[i] = Math.max(prices.get(i), i == prices.size() - 1 ? 0 : max[i + 1]);
        }
        double res = 0;
        for (int i = 0; i < prices.size(); i++) {
            res = Math.max(res, max[i] - prices.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BuyAndSellStock() {}
}
