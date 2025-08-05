package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:master_students.db";
    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            createMasterStudentsTable();
        }
        return connection;
    }

    private static void createMasterStudentsTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS master_students (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "full_name TEXT NOT NULL," +
                "dob TEXT NOT NULL," +
                "gender TEXT NOT NULL," +
                "phone TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "department TEXT NOT NULL," +
                "university_name TEXT NOT NULL," +
                "degree_program TEXT NOT NULL," +
                "cgpa REAL NOT NULL," +
                "graduation_year INTEGER NOT NULL" +
                ");";

        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Error creating master_students table: " + e.getMessage());
        }
    }
}
