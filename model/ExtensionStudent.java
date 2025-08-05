package model;

public class ExtensionStudent extends Student {
    private String highestEducation;
    private String additionalNotes;

    public ExtensionStudent(String name, String email, String phone, String dob, String department,
                            int completionYear, String highestEducation, String additionalNotes) {
        super(name, email, phone, dob, "Extension", department, completionYear);
        this.highestEducation = highestEducation;
        this.additionalNotes = additionalNotes;
    }

    public String getHighestEducation() { return highestEducation; }
    public void setHighestEducation(String highestEducation) { this.highestEducation = highestEducation; }

    public String getAdditionalNotes() { return additionalNotes; }
    public void setAdditionalNotes(String additionalNotes) { this.additionalNotes = additionalNotes; }

    @Override
    public String getStudentType() {
        return "Extension Student";
    }

    @Override
    public String toString() {
        return super.toString() + ", Highest Education: " + highestEducation + ", Notes: " + additionalNotes;
    }

    @Override
    public String getDegree() {
        return "Extension";
    }
}
   

