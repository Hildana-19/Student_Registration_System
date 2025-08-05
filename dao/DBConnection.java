package dao;

import java.sql.*;

import model.BachelorStudent;


public class DBConnection {
    private static final String URL = "jdbc:sqlite:student_registration.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT UNIQUE NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "phone TEXT," +
                    "password TEXT NOT NULL" +
                    ");";

            String studentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "phone TEXT NOT NULL," +
                    "dob TEXT NOT NULL," +
                    "degree TEXT NOT NULL," +
                    "department TEXT NOT NULL," +
                    "completionYear INTEGER NOT NULL" +
                    ");";

            stmt.execute(userTable);
            stmt.execute(studentsTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    public void insertStudent(BachelorStudent student) {
        throw new UnsupportedOperationException("Unimplemented method 'insertStudent'");
    }
}
