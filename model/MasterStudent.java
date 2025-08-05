package model;

public class MasterStudent extends Student {
    private String thesisTopic;

    public MasterStudent(String name, String email, String phone, String dob, String department,
                         int completionYear, String thesisTopic) {
        super(name, email, phone, dob, "Master", department, completionYear);
        this.thesisTopic = thesisTopic;
    }

    public String getThesisTopic() { return thesisTopic; }
    public void setThesisTopic(String thesisTopic) { this.thesisTopic = thesisTopic; }

    @Override
    public String getStudentType() {
        return "Master Student";
    }

    @Override
    public String getHighestEducation() {
        return "Bachelor's Degree";
    }

    @Override
    public String getDegree() {
        return getDegreeLevel(); // Will return "Master"
    }

    @Override
    public String toString() {
        return super.toString() + ", Thesis Topic: " + thesisTopic;
    }
}
