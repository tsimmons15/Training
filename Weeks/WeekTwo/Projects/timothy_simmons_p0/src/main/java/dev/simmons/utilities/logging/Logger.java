package dev.simmons.utilities.logging;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Logger {
    public enum Level {
        INFO, DEBUG, WARNING, ERROR;
    }

    public static final String LOG_PATH = "./logs/logs.log";
    public static void log(@NotNull Level level, @NotNull String message) {
        // I was always told my imagination was my best quality.
        try {
            Path path = Paths.get(LOG_PATH);
            String info = String.format("%s :: %s :: %s\n", level.name(), LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), message);
            Files.write(path, info.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException ioe) {
            // How do you handle an exception in your logger?
            ioe.printStackTrace();
        }
    }

    public static void log(@NotNull Level level, @NotNull Exception ex) {
        log(level, ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
    }
}