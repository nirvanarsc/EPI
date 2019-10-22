package epi.Chapter14;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;

public final class GroupEqualEntries {

    @EpiUserType(ctorParams = { Integer.class, String.class })
    public static class Person {
        public Integer age;
        public String name;

        public Person(Integer k, String n) {
            age = k;
            name = n;
        }

        @Override
        public String toString() {
            return name + ' ' + age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            final Person person = (Person) o;

            if (!age.equals(person.age)) { return false; }
            return name.equals(person.name);
        }

        @Override
        public int hashCode() {
            int result = age.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    public static void groupByAge(List<Person> people) {
        final List<Person> byAge = people.stream()
                                         .collect(Collectors.groupingBy(person -> person.age))
                                         .values()
                                         .stream()
                                         .flatMap(Collection::stream)
                                         .collect(Collectors.toList());
        people.clear();
        people.addAll(byAge);
    }

    public static void groupByAge2(List<Person> people) {
        final Map<Integer, Integer> ageToCount = new HashMap<>();
        final Map<Integer, Integer> ageToOffset = new HashMap<>();
        int offset = 0;
        for (Person p : people) {
            ageToCount.merge(p.age, 1, Integer::sum);
        }
        for (Entry<Integer, Integer> kc : ageToCount.entrySet()) {
            ageToOffset.put(kc.getKey(), offset);
            offset += kc.getValue();
        }
        while (!ageToOffset.isEmpty()) {
            final Entry<Integer, Integer> from = ageToOffset.entrySet().iterator().next();
            final Integer toAge = people.get(from.getValue()).age;
            final Integer swapTo = ageToOffset.get(toAge);
            Collections.swap(people, from.getValue(), swapTo);
            // Use ageToCount to see when we are finished with a particular age.
            final Integer count = ageToCount.compute(toAge, (k, v) -> v - 1);
            ageToOffset.compute(toAge, (k, v) -> count > 0 ? swapTo + 1 : null);
        }
    }

    @EpiTest(testDataFile = "group_equal_entries.tsv")
    public static void groupByAgeWrapper(TimedExecutor executor, List<Person> people) throws Exception {
        if (people.isEmpty()) {
            return;
        }
        final Map<Person, Long> values = buildMultiset(people);
        executor.run(() -> groupByAge(people));
        final Map<Person, Long> newValues = buildMultiset(people);
        verify(people, values, newValues);
    }

    @EpiTest(testDataFile = "group_equal_entries.tsv")
    public static void groupByAgeWrapper2(TimedExecutor executor, List<Person> people) throws Exception {
        if (people.isEmpty()) {
            return;
        }
        final Map<Person, Long> values = buildMultiset(people);
        executor.run(() -> groupByAge2(people));
        final Map<Person, Long> newValues = buildMultiset(people);
        verify(people, values, newValues);
    }

    private static Map<Person, Long> buildMultiset(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static void verify(List<Person> people,
                               Map<Person, Long> values,
                               Map<Person, Long> newValues) throws TestFailure {
        if (!values.equals(newValues)) {
            throw new TestFailure("Entry set changed");
        }
        int lastAge = people.get(0).age;
        final Set<Integer> ages = new HashSet<>();

        for (Person p : people) {
            if (ages.contains(p.age)) {
                throw new TestFailure("Entries are not grouped by age");
            }
            if (p.age != lastAge) {
                ages.add(lastAge);
                lastAge = p.age;
            }
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private GroupEqualEntries() {}
}
