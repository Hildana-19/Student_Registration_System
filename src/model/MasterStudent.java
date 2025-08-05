package model;

public class MasterStudent extends Student {
    private String universityName;
    private String degreeProgram;
    private double cgpa;
    private int graduationYear;

    public MasterStudent(String fullName, String dob, String gender, String phone, String email, String department,
                         String universityName, String degreeProgram, double cgpa, int graduationYear) {
        super(fullName, dob, gender, phone, email, department);
        this.universityName = universityName;
        this.degreeProgram = degreeProgram;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
    }

    @Override
    public String getStudentType() {
        return "Master";
    }

    public String getUniversityName() { return universityName; }
    public String getDegreeProgram() { return degreeProgram; }
    public double getCgpa() { return cgpa; }
    public int getGraduationYear() { return graduationYear; }
}
