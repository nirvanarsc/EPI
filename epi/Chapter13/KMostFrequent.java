package epi.Chapter13;

import static epi.Chapter12.KthLargestInArray.findKthLargestIndex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class KMostFrequent {

    public static List<String> kMostFrequent(List<String> strings, int k) {
        final Map<String, Integer> frequencyMap = new HashMap<>();
        final PriorityQueue<Entry<String, Integer>> min =
                new PriorityQueue<>(k, Comparator.comparing(Entry::getValue));

        for (String s : strings) {
            frequencyMap.merge(s, 1, Integer::sum);
        }

        final Iterator<Entry<String, Integer>> iterator = frequencyMap.entrySet().iterator();
        for (int i = 0; i < k; i++) {
            min.add(iterator.next());
        }

        while (iterator.hasNext()) {
            final Entry<String, Integer> curr = iterator.next();
            if (!min.isEmpty() && min.peek().getValue() < curr.getValue()) {
                min.poll();
                min.add(curr);
            }
        }

        return min.stream().map(Entry::getKey).collect(Collectors.toList());
    }

    public static List<String> kMostFrequentInPlace(List<String> strings, int k) {
        final Map<String, Integer> frequencyMap = new HashMap<>();
        for (String s : strings) {
            frequencyMap.merge(s, 1, Integer::sum);
        }
        final List<Entry<String, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        final int newIdx = findKthLargestIndex(k, entries, Comparator.comparing(Entry::getValue));

        return entries.subList(0, newIdx + 1).stream().map(Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) throws FileNotFoundException {
        final File file = new File("test_data/k_most_frequent.txt");
        final Scanner sc = new Scanner(file);
        sc.useDelimiter("[^A-Za-z0-9]+");

        final List<String> strings = new ArrayList<>();
        while (sc.hasNext()) {
            strings.add(sc.next());
        }

        final List<String> mostFrequent1 = kMostFrequent(strings, 10);
        final List<String> mostFrequent2 = kMostFrequentInPlace(strings, 10);

        System.out.println(mostFrequent1);
        System.out.println(mostFrequent2);
        System.out.println(mostFrequent1.containsAll(mostFrequent2));
        System.out.println(mostFrequent2.containsAll(mostFrequent1));
    }

    private KMostFrequent() {}
}
