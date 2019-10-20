package epi.Chapter14;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.EpiTestExpectedType;
import epi.test_framework.EpiUserType;
import epi.utils.TestRunner;

public final class RemoveDuplicates {

    @EpiUserType(ctorParams = { String.class, String.class })
    public static class Name implements Comparable<Name> {
        String firstName;
        String lastName;

        public Name(String first, String last) {
            firstName = first;
            lastName = last;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Name)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            final Name name = (Name) obj;
            return firstName.equals(name.firstName) && lastName.equals(name.lastName);
        }

        @Override
        public String toString() {
            return firstName;
        }

        @Override
        public int compareTo(Name name) {
            final int cmpFirst = firstName.compareTo(name.firstName);
            if (cmpFirst != 0) {
                return cmpFirst;
            }
            return lastName.compareTo(name.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }
    }

    public static void eliminateDuplicate(List<Name> names) {
        final Set<String> map = new HashSet<>();
        for (int i = 0; i < names.size(); i++) {
            if (map.contains(names.get(i).firstName)) {
                names.set(i, null);
            } else {
                map.add(names.get(i).firstName);
            }
        }
        while (true) {
            if (!names.remove(null)) { break; }
        }
    }

    public static void eliminateDuplicate2(List<Name> names) {
        Collections.sort(names);
        int write = 1;
        for (int i = 1; i < names.size(); i++) {
            if (!names.get(i - 1).firstName.equals(names.get(i).firstName)) {
                names.set(write++, names.get(i));
            }
        }
        names.subList(write, names.size()).clear();
    }

    @EpiTest(testDataFile = "remove_duplicates.tsv")
    public static List<Name> eliminateDuplicateWrapper(List<Name> names) {
        eliminateDuplicate(names);
        return names;
    }

    @EpiTest(testDataFile = "remove_duplicates.tsv")
    public static List<Name> eliminateDuplicateWrapper2(List<Name> names) {
        eliminateDuplicate2(names);
        return names;
    }

    @EpiTestComparator
    public static BiPredicate<List<String>, List<Name>> comp = (expected, result) -> {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        if (expected.size() != result.size()) {
            return false;
        }
        for (int i = 0; i < result.size(); i++) {
            if (!expected.get(i).equals(result.get(i).firstName)) {
                return false;
            }
        }
        return true;
    };

    @EpiTestExpectedType
    public static List<String> expectedType;

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private RemoveDuplicates() {}
}
