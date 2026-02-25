import exception.CourseFullException;
import exception.StudentAlreadyEnrolledException;
import model.Course;
import model.GraduateStudent;
import model.Student;
import model.UndergraduateStudent;
import service.FileManager;
import service.UniversityManager;

import java.util.Map;
import java.util.Scanner;

public class Main {


    private static UniversityManager manager = new UniversityManager();
    private static FileManager fileManager = new FileManager();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        fileManager.loadData(manager);

        boolean running = true;


        while (running) {
            showMenu();
            int choice = getChoice();


            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    enrollInCourse();
                    break;
                case 3:
                    viewStudentRecord();
                    break;
                case 4:
                    generateDeansList();
                    break;
                case 5:
                    saveAndExit();
                    running = false;
                    break;
                case 9:
                    addTestData();
                    break;
                default:
                    System.out.println(" Invalid choice!");
            }
        }

        scanner.close();
    }


    private static void showMenu() {
        System.out.println("\n====================================");
        System.out.println("  UNIVERSITY MANAGEMENT SYSTEM");
        System.out.println("====================================");
        System.out.println("1. Register Student");
        System.out.println("2. Enroll in Course");
        System.out.println("3. View Student Record");
        System.out.println("4. Generate Dean's List");
        System.out.println("5. Save and Exit");
        System.out.println("9. Add Test Data");
        System.out.println("====================================");
        System.out.print("Enter choice: ");
    }


    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    private static void registerStudent() {
        System.out.println("\n--- Register Student ---");

        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Department: ");
        String dept = scanner.nextLine();

        System.out.print("Type (1=Undergrad, 2=Grad): ");
        int type = Integer.parseInt(scanner.nextLine());


        Student student;
        if (type == 1) {
            student = new UndergraduateStudent(name, id, email, dept);
        } else {
            student = new GraduateStudent(name, id, email, dept);
        }

        manager.registerStudent(student);
    }


    private static void enrollInCourse() {
        System.out.println("\n--- Enroll in Course ---");

        System.out.print("Student ID: ");
        String studentID = scanner.nextLine();

        System.out.print("Course Code: ");
        String courseCode = scanner.nextLine();

        try {

            manager.enrollStudentInCourse(studentID, courseCode);


            System.out.print("Enter grade (0-100): ");
            double grade = Double.parseDouble(scanner.nextLine());
            manager.assignGrade(studentID, courseCode, grade);

        } catch (StudentAlreadyEnrolledException e) {

            System.out.println("✗ ERROR: " + e.getMessage());
        } catch (CourseFullException e) {

            System.out.println("✗ ERROR: " + e.getMessage());
        }
    }


    private static void viewStudentRecord() {
        System.out.println("\n--- View Student Record ---");

        System.out.print("Student ID: ");
        String id = scanner.nextLine();

        Student student = manager.getStudent(id);

        if (student == null) {
            System.out.println(" Student not found!");
            return;
        }


        System.out.println("\n====================================");
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getStudentID());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("GPA: " + String.format("%.2f", student.getGPA()));
        System.out.println("Tuition: $" + String.format("%.2f", student.calculateTuition()));
        System.out.println("====================================");


        System.out.println("\nCourses:");
        if (student.getCourseGrades().isEmpty()) {
            System.out.println("  No courses yet.");
        } else {
            for (Map.Entry<Course, Double> entry : student.getCourseGrades().entrySet()) {
                System.out.println("  " + entry.getKey() + " - Grade: " + entry.getValue());
            }
        }
    }


    private static void generateDeansList() {
        System.out.println("\n--- Dean's List (GPA > 3.5) ---");

        java.util.List<Student> deansList = manager.getDeansList();

        if (deansList.isEmpty()) {
            System.out.println("No students qualify.");
            return;
        }

        System.out.println("\n====================================");
        for (Student s : deansList) {
            System.out.println(s.getName() + " - GPA: " +
                    String.format("%.2f", s.getGPA()) +
                    " (" + s.getDepartment() + ")");
        }
        System.out.println("====================================");
        System.out.println("Total: " + deansList.size() + " students");
    }


    private static void saveAndExit() {
        System.out.println("\nSaving data...");
        fileManager.saveData(manager.getAllStudents(), manager.getAllCourses());
        System.out.println("\nThank you for using the system!");
        System.out.println("Goodbye! 👋");
    }


    private static void addTestData() {
        System.out.println("\nAdding test data...");


        Course cs101 = new Course("CS101", "Programming 101", 3, 30);
        Course cs201 = new Course("CS201", "Data Structures", 4, 25);
        Course math101 = new Course("MATH101", "Calculus", 4, 35);

        manager.createCourse(cs101);
        manager.createCourse(cs201);
        manager.createCourse(math101);


        Student s1 = new UndergraduateStudent("Umuhoza chanisse", "S001", "chanisse@uni.edu", "CS");
        Student s2 = new GraduateStudent("Uwase Gisele", "S002", "Gisele@uni.edu", "CS");
        Student s3 = new UndergraduateStudent("NIYIREMA Clesence", "S003", "Clesence2@uni.edu", "Math");

        manager.registerStudent(s1);
        manager.registerStudent(s2);
        manager.registerStudent(s3);


        try {
            manager.enrollStudentInCourse("S001", "CS101");
            manager.assignGrade("S001", "CS101", 92);

            manager.enrollStudentInCourse("S001", "MATH101");
            manager.assignGrade("S001", "MATH101", 88);

            manager.enrollStudentInCourse("S002", "CS201");
            manager.assignGrade("S002", "CS201", 95);

            manager.enrollStudentInCourse("S003", "MATH101");
            manager.assignGrade("S003", "MATH101", 85);

            System.out.println(" Test data added successfully!");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}

