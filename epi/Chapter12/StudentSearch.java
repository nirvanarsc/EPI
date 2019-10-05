package epi.Chapter12;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class StudentSearch {

    static class Student {
        int id;
        double gpa;

        Student(int id, double gpa) {
            this.id = id;
            this.gpa = gpa;
        }
    }

    public static boolean searchStudent(List<Student> students, Student target) {
        return Collections.binarySearch(students, target, Comparator.comparingDouble(a -> a.gpa)) >= 0;
    }

    public static void main(String[] args) {
        final Random gradeGenerator = new Random();
        final List<Student> students = IntStream.rangeClosed(1, 100)
                                                .boxed()
                                                .map(i -> new Student(i, 4 * gradeGenerator.nextDouble()))
                                                .collect(Collectors.toList());
        final int index1 = gradeGenerator.nextInt(100);
        final int index2 = gradeGenerator.nextInt(100);
        final Student t1 = students.get(index1);
        final Student t2 = students.get(index2);

        students.sort(Comparator.comparingDouble(a -> a.gpa));

        System.out.println(searchStudent(students, t1));
        System.out.println(searchStudent(students, t2));
    }

    private StudentSearch() {}
}
