package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;

import java.util.List;

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

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private BuyAndSellStock() {
    }
}
