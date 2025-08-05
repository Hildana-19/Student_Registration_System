package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogHelper {

    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = "logs/registration_log.txt";

    static {
        // Create logs directory if it doesn't exist
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void logRegistration(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}
