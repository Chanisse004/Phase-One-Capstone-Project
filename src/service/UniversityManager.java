package service;
import exception.CourseFullException;
import exception.StudentAlreadyEnrolledException;
import model.Course;
import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class UniversityManager {

    private Map<String, Student> students;

    private Map<String, Course> courses;


    public UniversityManager() {

        students = new HashMap<>();
        courses = new HashMap<>();
    }

    public void registerStudent(Student student) {

        students.put(student.getStudentID(), student);
        System.out.println(" Student registered: " + student.getName());
    }


    public void createCourse(Course course) {

        courses.put(course.getCourseCode(), course);
        System.out.println(" Course created: " + course.getCourseName());
    }

    public void enrollStudentInCourse(String studentID, String courseCode)
            throws StudentAlreadyEnrolledException, CourseFullException {


        Student student = students.get(studentID);
        Course course = courses.get(courseCode);


        if (student == null || course == null) {
            System.out.println(" Student or Course not found!");
            return;
        }


        if (course.isEnrolled(student)) {
            throw new StudentAlreadyEnrolledException(
                    student.getName() + " is already enrolled in " + course.getCourseName());
        }

        if (course.isFull()) {
            throw new CourseFullException(
                    course.getCourseName() + " is full! Max: " + course.getMaxCapacity());
        }


        course.addStudent(student);
        student.addCourseGrade(course, 0.0);

        System.out.println("yes " + student.getName() + " enrolled in " + course.getCourseName());
    }

    public void assignGrade(String studentID, String courseCode, double grade) {
        Student student = students.get(studentID);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            student.addCourseGrade(course, grade);
            System.out.println(" Grade assigned: " + grade);
        } else {
            System.out.println(" Student or Course not found!");
        }
    }

    public Student getStudent(String studentID) {
        return students.get(studentID);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public double getAverageGPAByDepartment(String department) {
        return students.values().stream().filter(s -> s.getDepartment().equals(department))
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }


    public Student getTopStudent() {
        return students.values().stream()
                .max(Comparator.comparingDouble(Student::getGPA))
                .orElse(null);
    }


    public List<Student> getDeansList() {
        return students.values().stream()
                .filter(s -> s.getGPA() > 3.5)
                .sorted((s1, s2) -> Double.compare(s2.getGPA(), s1.getGPA()))
                .collect(Collectors.toList());
    }


    public Collection<Student> getAllStudents() {
        return students.values();
    }


    public Collection<Course> getAllCourses() {
        return courses.values();
    }
}
