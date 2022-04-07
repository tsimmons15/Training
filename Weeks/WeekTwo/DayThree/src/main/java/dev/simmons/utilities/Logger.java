package dev.simmons.utilities;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;

public class Logger {
    public enum Level {
        INFO, DEBUG, WARNING, ERROR
    }

    public static void log(@NotNull Level level, String message) {
        String logMessage = level.name() + " :: " + LocalTime.now() + " :: " + message;

        try {
            Files.write(Paths.get("./logs/log.txt"),
                    logMessage.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(@NotNull Level level, @NotNull Exception ex) {
        log(level, ex.getMessage() + " :: " + ex.getStackTrace());
    }

    public static void logInfo(String message) {
        try {
            String logMessage = "INFO :: " + LocalTime.now().toString() + " :: " + message + "\n";
            Files.write(Paths.get("./logs/log.txt"),
                    message.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
