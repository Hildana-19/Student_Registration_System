package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger {
    public static void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("activity_log.txt", true))) {
            writer.write(LocalDateTime.now() + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}