package dev.simmons.loggers;

import dev.simmons.utilities.Logger;
import org.junit.jupiter.api.Test;

public class LoggerTests {
    @Test
    public void infoLogTest() {
        Logger.logInfo("Hello");
        Logger.logInfo("Testing");
    }
}
