package epi.utils;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public final class TestRunner {

    public static <T extends Comparable<T>> BiPredicate<List<T>, List<T>> getSimpleComp() {
        return (expected, result) -> {
            if (result == null) {
                return false;
            }
            Collections.sort(expected);
            Collections.sort(result);
            return expected.equals(result);
        };
    }

    public static <T extends Comparable<T>> BiPredicate<List<List<T>>, List<List<T>>> getComp(boolean nested) {
        return (List<List<T>> expected, List<List<T>> result) -> {
            if (result == null) {
                return false;
            }
            if (nested) {
                for (List<T> l : expected) {
                    Collections.sort(l);
                }
            }
            expected.sort(new LexicographicalListComparator<>());
            if (nested) {
                for (List<T> l : result) {
                    Collections.sort(l);
                }
            }
            result.sort(new LexicographicalListComparator<>());
            return expected.equals(result);
        };
    }

    public static void run(String[] args) {
        final String className = System.getProperty("sun.java.command");
        try {
            System.exit(
                    GenericTest.runFromAnnotations(args, className, Class.forName(className)).ordinal());
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + className + " not found.");
        }
    }

    private TestRunner() {}
}
