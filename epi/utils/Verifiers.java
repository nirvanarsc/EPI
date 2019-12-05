package epi.utils;

import static epi.Chapter18.RefuelingSchedule.MPG;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import epi.test_framework.TestFailure;

public final class Verifiers {

    private static final Pattern PATTERN = Pattern.compile("\\s");

    public static void verifyDecomposableIntoWords(String domain,
                                                   Set<String> dictionary,
                                                   Boolean decomposable,
                                                   List<String> result) throws TestFailure {
        if (!decomposable) {
            if (!result.isEmpty()) {
                throw new TestFailure("domain is not decomposable");
            }
            return;
        }

        if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
            throw new TestFailure("Result uses words not in dictionary");
        }

        if (!String.join("", result).equals(domain)) {
            throw new TestFailure("Result is not composed into domain");
        }
    }

    public static void verifyWordBreak(String domain,
                                       Set<String> dictionary,
                                       Boolean decomposable,
                                       List<String> result) throws TestFailure {
        if (!decomposable) {
            if (!result.isEmpty()) {
                throw new TestFailure("domain is not decomposable");
            }
            return;
        }

        for (String string : result) {
            final String[] split = PATTERN.split(string);
            if (!String.join("", split).equals(domain)) {
                throw new TestFailure("Result is not composed into domain");
            }
            if (Arrays.stream(split).anyMatch(s -> !dictionary.contains(s))) {
                throw new TestFailure("Result uses words not in dictionary");
            }
        }
    }

    public static void verifyRefuelingSchedule(List<Integer> gallons, List<Integer> distances, int result)
            throws TestFailure {
        final int numCities = gallons.size();
        int tank = 0;
        for (int i = 0; i < numCities; ++i) {
            final int city = (result + i) % numCities;
            tank += gallons.get(city) * MPG - distances.get(city);
            if (tank < 0) {
                throw new TestFailure(String.format("Out of gas on city %d", city));
            }
        }
    }

    private Verifiers() {}
}
