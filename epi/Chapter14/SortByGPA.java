package epi.Chapter14;

import java.util.Comparator;
import java.util.List;

public class SortByGPA {

    static class Student {
        String name;
        double gpa;

        Student(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }
    }

    public void sortByGPA(List<Student> students) {
        students.sort(Comparator.comparingDouble(a -> a.gpa));
    }

    public void sortByName(List<Student> students) {
        students.sort(Comparator.comparing(a -> a.name));
    }
}
