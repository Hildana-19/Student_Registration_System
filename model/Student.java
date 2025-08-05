package model;

public abstract class Student {
    private String name;
    private String email;
    private String phone;
    private String dob; 
    private String degreeLevel;
    private String department;
    private int completionYear;

    public Student(String name, String email, String phone, String dob, String degreeLevel, String department, int completionYear) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.degreeLevel = degreeLevel;
        this.department = department;
        this.completionYear = completionYear;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getDegreeLevel() { return degreeLevel; }
    public void setDegreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getCompletionYear() { return completionYear; }
    public void setCompletionYear(int completionYear) { this.completionYear = completionYear; }

    public abstract String getStudentType();

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone +
                ", DOB: " + dob + ", Degree: " + degreeLevel +
                ", Department: " + department + ", Completion Year: " + completionYear;
    }

    public abstract String getHighestEducation();

    public abstract String getDegree();
}
