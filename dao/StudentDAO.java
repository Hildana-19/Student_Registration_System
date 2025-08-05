package dao;

import model.Student;
import model.Registrable;
import util.LoggerUtil;

import java.sql.*;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, phone, dob, degree, department, completionYear) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getDob());
            pstmt.setString(5, student.getDegree());
            pstmt.setString(6, student.getDepartment());
            pstmt.setInt(7, student.getCompletionYear());

            pstmt.executeUpdate();

            LoggerUtil.log("Student saved: " + student.getRegistrationInfo());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LoggerUtil.log("Failed to save student: " + student.getName());
            return false;
        }
    }

    public void saveAll(List<Registrable> students) {
        for (Registrable s : students) {
            LoggerUtil.log("Saving: " + s.getRegistrationInfo());

            if (s instanceof Student) {
                addStudent((Student) s);
            } else {
                LoggerUtil.log("Skipped: Not a valid Student object - " + s.getRegistrationInfo());
            }
        }
    }
}
