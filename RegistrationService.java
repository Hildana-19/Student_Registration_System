import java.io.File;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private static final List<String[]> registeredUsers = new ArrayList<>();

    public void register(String name, String day, String month, String year, String gmail, String phone, String program, File file) {
        // Here you can add business logic, validation, saving to DB, etc.
        // For now, just print the data to console (or extend as needed)
        System.out.println("Name: " + name);
        System.out.println("DOB: " + day + "-" + month + "-" + year);
        System.out.println("Gmail: " + gmail);
        System.out.println("Phone: " + phone);
        System.out.println("Program: " + program);
        System.out.println("File: " + (file != null ? file.getAbsolutePath() : "No file"));
        // Add user to list
        registeredUsers.add(new String[]{name, day+"-"+month+"-"+year, gmail, phone, program, file != null ? file.getAbsolutePath() : "No file", "Pending"});
    }

    public static List<String[]> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void removeUser(int index) {
        if (index >= 0 && index < registeredUsers.size()) {
            registeredUsers.remove(index);
        }
    }

    public static void updateUser(int index, String[] updated) {
        if (index >= 0 && index < registeredUsers.size()) {
            registeredUsers.set(index, updated);
        }
    }

    public static void approveUser(int index) {
        if (index >= 0 && index < registeredUsers.size()) {
            String[] user = registeredUsers.get(index);
            user[user.length - 1] = "Approved";
        }
    }
}
