package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtil {

    private static final String LOG_FILE = "activity_log.txt";

    public static void log(String message) {
        writeToFile("INFO", message);
    }

    public static void error(String message, Exception e) {
        writeToFile("ERROR", message + " - " + e.getMessage());
        e.printStackTrace(); // optional for console
    }

    private static void writeToFile(String level, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String time = LocalDateTime.now().toString();
            writer.write("[" + time + "] [" + level + "] " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Logger failed to write to file.");
            e.printStackTrace();
        }
    }
}
