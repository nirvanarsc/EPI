package epi.Chapter14;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class SortByGPA {

    static class Student implements Comparable<Student> {
        String name;
        double gpa;

        Student(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }

        @Override
        public int compareTo(Student o) {
            return Double.compare(o.gpa, gpa);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Student)) { return false; }
            return compareTo((Student) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, gpa);
        }
    }

    public static void sortByGPA(List<Student> students) {
        Collections.sort(students);
    }

    public static void sortByName(List<Student> students) {
        students.sort(Comparator.comparing(a -> a.name));
    }

    private SortByGPA() {}
}
