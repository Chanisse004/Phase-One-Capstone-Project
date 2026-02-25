package service;

import model.Course;
import model.GraduateStudent;
import model.Student;
import model.UndergraduateStudent;

import java.io.*;
import java.util.*;


public class FileManager {


    public void saveData(Collection<Student> students, Collection<Course> courses) {
        saveStudents(students);
        saveCourses(courses);
        saveEnrollments(students);
        System.out.println(" All data saved!");
    }

    private void saveStudents(Collection<Student> students) {
        try {

            PrintWriter writer = new PrintWriter(new FileWriter("students.txt"));


            for (Student s : students) {

                String type = (s instanceof UndergraduateStudent) ? "UNDERGRAD" : "GRAD";

                String line = s.getStudentID() + "," + s.getName() + "," +
                        s.getEmail() + "," +
                        s.getDepartment() + "," +
                        s.getGPA() + "," +
                        type;

                writer.println(line);
            }

            writer.close();
        } catch (IOException e) {

            System.out.println(" Error saving students: " + e.getMessage());
        }
    }


    private void saveCourses(Collection<Course> courses) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("courses.txt"));

            for (Course c : courses) {

                String line = c.getCourseCode() + "," +
                        c.getCourseName() + "," +
                        c.getCredits() + "," +
                        c.getMaxCapacity();

                writer.println(line);
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(" Error saving courses: " + e.getMessage());
        }
    }


    private void saveEnrollments(Collection<Student> students) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("enrollments.txt"));


            for (Student s : students) {

                for (Map.Entry<Course, Double> entry : s.getCourseGrades().entrySet()) {

                    String line = s.getStudentID() + "," +
                            entry.getKey().getCourseCode() + "," +
                            entry.getValue();

                    writer.println(line);
                }
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(" Error saving enrollments: " + e.getMessage());
        }
    }

    public void loadData(UniversityManager manager) {
        System.out.println("Loading data...\n");
        Map<String, Student> students = loadStudents(manager);
        Map<String, Course> courses = loadCourses(manager);
        loadEnrollments(students, courses);
        System.out.println(" Data loaded!\n");
    }


    private Map<String, Student> loadStudents(UniversityManager manager) {
        Map<String, Student> students = new HashMap<>();

        try {
            File file = new File("students.txt");


            if (!file.exists()) {
                System.out.println("No student data found.");
                return students;
            }


            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;


            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");


                String id = parts[0];
                String name = parts[1];
                String email = parts[2];
                String dept = parts[3];
                double gpa = Double.parseDouble(parts[4]);
                String type = parts[5];


                Student student;
                if (type.equals("UNDERGRAD")) {
                    student = new UndergraduateStudent(name, id, email, dept);
                } else {
                    student = new GraduateStudent(name, id, email, dept);
                }


                manager.registerStudent(student);
                students.put(id, student);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No previous student data.");
        }

        return students;
    }


    private Map<String, Course> loadCourses(UniversityManager manager) {
        Map<String, Course> courses = new HashMap<>();

        try {
            File file = new File("courses.txt");
            if (!file.exists()) {
                System.out.println("No course data found.");
                return courses;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String code = parts[0];
                String name = parts[1];
                int credits = Integer.parseInt(parts[2]);
                int capacity = Integer.parseInt(parts[3]);

                Course course = new Course(code, name, credits, capacity);
                manager.createCourse(course);
                courses.put(code, course);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No previous course data.");
        }

        return courses;
    }


    private void loadEnrollments(Map<String, Student> students, Map<String, Course> courses) {
        try {
            File file = new File("enrollments.txt");
            if (!file.exists()) {
                System.out.println("No enrollment data found.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String studentID = parts[0];
                String courseCode = parts[1];
                double grade = Double.parseDouble(parts[2]);


                Student student = students.get(studentID);
                Course course = courses.get(courseCode);


                if (student != null && course != null) {
                    course.addStudent(student);
                    student.addCourseGrade(course, grade);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No previous enrollment data.");
        }
    }
}

