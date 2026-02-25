package model;
import java.util.HashMap;
import java.util.Map;

public abstract class Student extends Person {

    private String studentID;
    private double GPA;
    private String department;

    private Map<Course, Double> courseGrades;


    public Student(String name, String studentID, String email, String department) {

        super(name, studentID, email);
        this.studentID = studentID;
        this.department = department;
        this.GPA = 0.0;
        this.courseGrades = new HashMap<>();

    }

    public String getStudentID() {

        return studentID;
    }

    public double getGPA() {

        return GPA;
    }

    public String getDepartment() {
        return department;
    }

    public Map<Course, Double> getCourseGrades() {
        return courseGrades;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void addCourseGrade(Course course, double grade) {
        courseGrades.put(course, grade);
        calculateGPA();
    }

    private void calculateGPA() {
        if (courseGrades.isEmpty()) {
            GPA = 0.0;
            return;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Map.Entry<Course, Double> entry : courseGrades.entrySet()) {
            Course course = entry.getKey();
            double grade = entry.getValue();

            double gradePoint = convertToGradePoint(grade);

            totalPoints += gradePoint * course.getCredits();
            totalCredits += course.getCredits();
        }
        if (totalCredits > 0) {
            GPA = totalPoints / totalCredits;
        }
    }

    private double convertToGradePoint(double grade) {
        if (grade >= 90) return 4.0;
        else if (grade >= 80) return 3.0;
        else if (grade >= 70) return 2.0;
        else if (grade >= 60) return 1.0;

        else return 0.0;


    }

    public abstract double calculateTuition();


}

