package model;

public class UndergraduateStudent extends Student {

    public static final double FLAT_LATE = 5000.0;

    public UndergraduateStudent (String name, String studentId, String email, String department){
        super(name, studentId, email, department);
    }

    @Override

    public double calculateTuition(){
        return FLAT_LATE;
    }
}

