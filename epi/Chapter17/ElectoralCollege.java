package epi.Chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        int total = 0;
        for (Item i : votes) {
            total += i.value;
        }

        IntStream.rangeClosed(1, total)
                 .boxed()
                 .collect(Collectors.toMap(Function.identity(), v -> Knapsack.knapsackSpaceC(votes, v)))
                 .entrySet()
                 .stream()
                 .filter(i -> !i.getKey().equals(i.getValue()))
                 .forEach(t -> System.out.println(t.getKey() + " " + t.getValue()));
    }
}
