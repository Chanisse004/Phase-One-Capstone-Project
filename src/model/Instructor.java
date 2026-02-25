package model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {

    private String department;
    private List<Course> courses;

    public Instructor (String name, String id, String email, String department) {
        super(name, id, email);
        this.department = department;
        this.courses = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}
