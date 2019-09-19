package epi.Chapter6;

import epi.utils.TestRunner;
import epi.test_framework.EpiTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("rawtypes")
public final class EvenOddArray {

    public static void evenOdd(List<Integer> a) {
        for (int i = 0, j = a.size() - 1; i < j; i++) {
            if ((a.get(i) & 1) != 0) {
                while (j >= 0 && (a.get(j) & 1) > 0) {
                    j--;
                }
                if (i > j) {
                    break;
                }
                final int temp = a.get(i);
                a.set(i, a.get(j));
                a.set(j--, temp);
            }
        }
    }

    public static void evenOdd2(List<Integer> a) {
        int nextEven = 0, nextOdd = a.size() - 1;
        while (nextEven < nextOdd) {
            if ((a.get(nextEven) & 1) == 0) {
                nextEven++;
            } else {
                final int temp = a.get(nextEven);
                a.set(nextEven, a.get(nextOdd));
                a.set(nextOdd--, temp);
            }
        }
    }

    @EpiTest(testDataFile = "even_odd_array.tsv")
    public static void evenOddWrapper(TimedExecutor executor, List<Integer> a) throws Exception {
        final Class[] parameterTypes = new Class[1];
        parameterTypes[0] = List.class;
        final Method first = EvenOddArray.class.getMethod("evenOdd", parameterTypes);
        evenOddWrapper(executor, a, first);
    }

    @EpiTest(testDataFile = "even_odd_array.tsv")
    public static void evenOddWrapper2(TimedExecutor executor, List<Integer> a) throws Exception {
        final Class[] parameterTypes = new Class[1];
        parameterTypes[0] = List.class;
        final Method second = EvenOddArray.class.getMethod("evenOdd2", parameterTypes);
        evenOddWrapper(executor, a, second);
    }

    public static void evenOddWrapper(TimedExecutor executor, List<Integer> a, Method m) throws Exception {
        final EvenOddArray obj = new EvenOddArray();
        final List<Integer> before = new ArrayList<>(a);
        executor.run(() -> m.invoke(obj, a));

        boolean inOdd = false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) % 2 == 0) {
                if (inOdd) {
                    System.out.println(a);
                    throw new TestFailure("Even elements appear in odd part" + i);
                }
            } else {
                inOdd = true;
            }
        }
        final List<Integer> after = new ArrayList<>(a);
        Collections.sort(before);
        Collections.sort(after);
        if (!before.equals(after)) {
            throw new TestFailure("Elements mismatch");
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }
}
