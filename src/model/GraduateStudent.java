package model;

public class GraduateStudent extends Student{

    private static final double PER_CREDIT_RATE = 500.0;
    private static final double RESEARCH_FEE = 2000.0;

    public GraduateStudent(String name, String studentId, String email, String department) {
        super(name, studentId, email, department);
    }

    @Override
    public double calculateTuition() {
        int totalCredits = 0;

        for (Course course : getCourseGrades().keySet()) {
            totalCredits += course.getCredits();
        }

        double tuition = (totalCredits * PER_CREDIT_RATE) + RESEARCH_FEE;
        return tuition;
    }
}

