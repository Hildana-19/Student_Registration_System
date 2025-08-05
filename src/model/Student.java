package model;

public abstract class Student {
    protected String fullName;
    protected String dob;
    protected String gender;
    protected String phone;
    protected String email;
    protected String department;

    public Student(String fullName, String dob, String gender, String phone, String email, String department) {
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.department = department;
    }

    public abstract String getStudentType();

    // Getters
    public String getFullName() { return fullName; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
}
