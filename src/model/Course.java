package model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String courseCode;
    private String courseName;
    private int credits;
    private int maxCapacity;

    private List<Student> roster;

    public Course(String courseCode, String courseName, int credits, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.roster = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    public List<Student> getRoster() {
        return roster;
    }

    public boolean isFull() {
        return roster.size() >= maxCapacity;
    }

    public boolean isEnrolled(Student student) {
        return roster.contains(student);
    }

    public void addStudent(Student student) {
        roster.add(student);
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName;
    }


}