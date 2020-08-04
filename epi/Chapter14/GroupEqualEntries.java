package epi.Chapter14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        final Map<Integer, List<Person>> map = new HashMap<>();
        for (Person p : people) {
            map.computeIfAbsent(p.age, v -> new ArrayList<>()).add(p);
        }
        people.clear();
        for (Map.Entry<Integer, List<Person>> e : map.entrySet()) {
            people.addAll(e.getValue());
        }
    }

    public static void groupByAgeCS(List<Person> people) {
        final Map<Integer, Integer> ageToCount = new HashMap<>();
        final Map<Integer, Integer> ageToOffset = new HashMap<>();
        int offset = 0;
        for (Person p : people) {
            ageToCount.merge(p.age, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> e : ageToCount.entrySet()) {
            ageToOffset.put(e.getKey(), offset);
            offset += e.getValue();
        }
        while (!ageToOffset.isEmpty()) {
            final Map.Entry<Integer, Integer> from = ageToOffset.entrySet().iterator().next();
            final Integer toAge = people.get(from.getValue()).age;
            final Integer swapTo = ageToOffset.get(toAge);
            Collections.swap(people, from.getValue(), swapTo);
            // Use ageToCount to see when we are finished with a particular age.
            final int count = ageToCount.merge(toAge, -1, Integer::sum);
            if (count > 0) {
                ageToOffset.put(toAge, swapTo + 1);
            } else {
                ageToOffset.remove(toAge);
            }
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
    public static void groupByAgeWrapperCS(TimedExecutor executor, List<Person> people) throws Exception {
        if (people.isEmpty()) {
            return;
        }
        final Map<Person, Long> values = buildMultiset(people);
        executor.run(() -> groupByAgeCS(people));
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
