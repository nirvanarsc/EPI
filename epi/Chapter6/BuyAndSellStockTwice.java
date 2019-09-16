package epi.Chapter6;

import epi.TestRunner;
import epi.test_framework.EpiTest;

import java.util.ArrayList;
import java.util.List;

import static epi.Chapter6.BuyAndSellStock.computeMaxProfit;

public final class BuyAndSellStockTwice {

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        double maxProfit = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            final double left = computeMaxProfit(prices.subList(0, i));
            final double right = computeMaxProfit(prices.subList(i, prices.size()));
            maxProfit = Math.max(maxProfit, left + right);
        }
        return maxProfit;
    }

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice2(List<Double> prices) {
        double maxProfit = 0.0;
        double minPriceSoFar = Double.MAX_VALUE;
        double maxPriceSoFar = Double.MIN_VALUE;
        final List<Double> firstBuySellProfits = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxProfit = Math.max(maxProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfits.add(maxProfit);
        }

        for (int i = prices.size() - 1; i > 0; i--) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxProfit = Math.max(maxProfit, maxPriceSoFar - prices.get(i) + firstBuySellProfits.get(i - 1));
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        TestRunner.run(args, "epi.Chapter6.BuyAndSellStockTwice");
    }

    private BuyAndSellStockTwice() {
    }
}
