package epi.Chapter13;

import static epi.Chapter12.KthLargestInArray.findKthLargestIndex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class KMostFrequent {

    public static List<String> kMostFrequent(List<String> strings, int k) {
        final Map<String, Integer> freq = new HashMap<>();
        final PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>(k, Map.Entry.comparingByValue());
        for (String str : strings) {
            freq.merge(str, 1, Integer::sum);
        }
        for (Map.Entry<String, Integer> curr : freq.entrySet()) {
            pq.add(curr);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        return pq.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static List<String> kMostFrequentInPlace(List<String> strings, int k) {
        final Map<String, Integer> freq = new HashMap<>();
        for (String str : strings) {
            freq.merge(str, 1, Integer::sum);
        }
        final List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());

        final int newIdx = findKthLargestIndex(k, entries, Map.Entry.comparingByValue());

        return entries.subList(0, newIdx + 1).stream().map(Map.Entry::getKey).collect(Collectors.toList());
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
