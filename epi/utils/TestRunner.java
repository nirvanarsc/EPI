package epi.utils;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public final class TestRunner {

    public static final
    BiPredicate<List<List<Integer>>, List<List<Integer>>> COMP = (expected, result) -> {
        if (result == null) {
            return false;
        }
        expected.sort(new LexicographicalListComparator<>());
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static final
    BiPredicate<List<List<Integer>>, List<List<Integer>>> NESTED_COMP = (expected, result) -> {
        if (result == null) {
            return false;
        }
        for (List<Integer> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<Integer> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    };

    public static void run(String[] args) {
        final String className = System.getProperty("sun.java.command");
        try {
            System.exit(GenericTest.runFromAnnotations(args, className, Class.forName(className)).ordinal());
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + className + " not found.");
        }
    }

    private TestRunner() {
    }
}
