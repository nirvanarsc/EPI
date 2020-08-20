package epi.Chapter17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import epi.Chapter17.Knapsack.Item;

public final class FractionalKnapsack {

    public static void main(String[] args) {
        final List<Item> items = new ArrayList<>();
        items.add(new Item(1, 5));
        items.add(new Item(3, 10));
        items.add(new Item(5, 15));
        items.add(new Item(4, 7));
        items.add(new Item(1, 8));
        items.add(new Item(3, 9));
        items.add(new Item(2, 4));

        System.out.println(Knapsack.optimumSubjectToCapacityBottomUp(items, 6));
        System.out.println(fractionalKnapsack(items, 6));
    }

    public static double fractionalKnapsack(List<Item> items, double capacity) {
        items.sort(Comparator.comparingDouble(a -> (double) a.weight / a.value));
        double res = 0;
        for (Item i : items) {
            if (capacity < i.weight) {
                res += (capacity / i.weight) * i.value;
                return res;
            }
            res += i.value;
            capacity -= i.weight;
        }
        return res;
    }

    private FractionalKnapsack() {}
}
