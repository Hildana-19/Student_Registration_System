package ui.master;

import database.DBConnection;
import exceptions.InvalidEmailException;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.*;
import model.MasterStudent;
import util.LogHelper;

public class MasterRegistrationForm extends JFrame {

    private JTextField txtFullName, txtDOB, txtPhone, txtEmail, txtUniversity, txtDegreeProgram, txtCGPA, txtGradYear;
    private JComboBox<String> cmbGender, cmbDepartment;
    private JLabel logoLabel;

    public MasterRegistrationForm() {
        setTitle("Master's Student Registration Form");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load logo image
        try {
            ImageIcon logoIcon = new ImageIcon("resources/BITS_logo.png");
            Image img = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            logoLabel = new JLabel("College Logo");
        }

        add(logoLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(new JLabel("Full Name:"));
        txtFullName = new JTextField();
        formPanel.add(txtFullName);

        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        txtDOB = new JTextField();
        formPanel.add(txtDOB);

        formPanel.add(new JLabel("Gender:"));
        cmbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        formPanel.add(cmbGender);

        formPanel.add(new JLabel("Phone Number:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Department:"));
        cmbDepartment = new JComboBox<>(new String[]{
                "Bachelor of Information Technology System (ITS)",
                "Software Engineering"
        });
        formPanel.add(cmbDepartment);

        formPanel.add(new JLabel("University Name:"));
        txtUniversity = new JTextField();
        formPanel.add(txtUniversity);

        formPanel.add(new JLabel("Degree Program:"));
        txtDegreeProgram = new JTextField();
        formPanel.add(txtDegreeProgram);

        formPanel.add(new JLabel("CGPA:"));
        txtCGPA = new JTextField();
        formPanel.add(txtCGPA);

        formPanel.add(new JLabel("Graduation Year:"));
        txtGradYear = new JTextField();
        formPanel.add(txtGradYear);

        JButton btnSubmit = new JButton("Submit");
        formPanel.add(btnSubmit);

        JButton btnClear = new JButton("Clear");
        formPanel.add(btnClear);

        add(formPanel, BorderLayout.CENTER);

        // Button actions
        btnSubmit.addActionListener(e -> {
            try {
                submitForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                        "Submission Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnClear.addActionListener(e -> clearForm());
    }

    private void submitForm() throws InvalidEmailException, SQLException {
        String fullName = txtFullName.getText().trim();
        String dob = txtDOB.getText().trim();
        String gender = (String) cmbGender.getSelectedItem();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String department = (String) cmbDepartment.getSelectedItem();
        String university = txtUniversity.getText().trim();
        String degreeProgram = txtDegreeProgram.getText().trim();
        String cgpaStr = txtCGPA.getText().trim();
        String gradYearStr = txtGradYear.getText().trim();

        if (fullName.isEmpty() || dob.isEmpty() || phone.isEmpty() || email.isEmpty()
                || university.isEmpty() || degreeProgram.isEmpty() || cgpaStr.isEmpty() || gradYearStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Invalid email format.");
        }

        double cgpa;
        int gradYear;

        try {
            cgpa = Double.parseDouble(cgpaStr);
            gradYear = Integer.parseInt(gradYearStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "CGPA must be a number and Graduation Year must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MasterStudent student = new MasterStudent(fullName, dob, gender, phone, email, department,
                university, degreeProgram, cgpa, gradYear);

        saveToDatabase(student);
        LogHelper.logRegistration("Registered: " + fullName + ", Email: " + email + ", Dept: " + department);

        JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

    private void saveToDatabase(MasterStudent student) throws SQLException {
        String insertSQL = "INSERT INTO master_students (full_name, dob, gender, phone, email, department, university_name, degree_program, cgpa, graduation_year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, student.getFullName());
            pstmt.setString(2, student.getDob());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getEmail());
            pstmt.setString(6, student.getDepartment());
            pstmt.setString(7, student.getUniversityName());
            pstmt.setString(8, student.getDegreeProgram());
            pstmt.setDouble(9, student.getCgpa());
            pstmt.setInt(10, student.getGraduationYear());

            pstmt.executeUpdate();
        }
    }

    private boolean isValidEmail(String email) {
        // Simple email regex
      String regex = "^[\\w\\-\\.]+@([\\w\\-]+\\.)+[\\w\\-]{2,4}$";
        return Pattern.matches(regex, email);
    }

    private void clearForm() {
        txtFullName.setText("");
        txtDOB.setText("");
        cmbGender.setSelectedIndex(0);
        txtPhone.setText("");
        txtEmail.setText("");
        cmbDepartment.setSelectedIndex(0);
        txtUniversity.setText("");
        txtDegreeProgram.setText("");
        txtCGPA.setText("");
        txtGradYear.setText("");
    }
}
