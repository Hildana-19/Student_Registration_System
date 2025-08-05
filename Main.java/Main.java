package Main;

import java.util.ArrayList;
import java.util.List;
import dao.DBConnection;
import dao.StudentDAO;
import model.BachelorStudent;
import model.ExtensionStudent;
import model.MasterStudent;
import model.Registrable;
import ui.LoginPage;

public class Main {
    public static void main(String[] args) {
        DBConnection.initializeDatabase();
        new LoginPage();
        //List<Registrable> students = new ArrayList<>();
        //students.add(new BachelorStudent("Hosie", "h@example.com", "123456789", "2002-01-01", "Software Engineering", 2025, "AI"));
       // students.add(new MasterStudent("Yeab", "y@example.com", "987654321", "1999-05-10", "Cyber Security", 2024, "Deepfake Detection"));
        //students.add(new ExtensionStudent("Debora", "d@example.com", "111222333", "1990-03-05", "ITS", 2023, "Bachelor’s Degree", "Prefers weekend"));

       // @SuppressWarnings("unused")
        //StudentDAO dao = new StudentDAO();
        
    }
}

