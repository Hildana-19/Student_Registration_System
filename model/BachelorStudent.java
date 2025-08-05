package model;

public class BachelorStudent extends Student {
    private String highestEducation; 

    public BachelorStudent(String name, String email, String phone, String dob, String department,
                           int completionYear, String highestEducation) {
        super(name, email, phone, dob, "Bachelor", department, completionYear);
        this.highestEducation = highestEducation;
    }

    public String getHighestEducation() { return highestEducation; }
    public void setHighestEducation(String highestEducation) { this.highestEducation = highestEducation; }

    @Override
    public String getStudentType() {
        return "Bachelor Student";
    }

    @Override
    public String toString() {
        return super.toString() + ", Highest Education: " + highestEducation;
    }

    @Override
    public String getDegree() {
    return getDegreeLevel();  // or just return "Bachelor";
}

}
