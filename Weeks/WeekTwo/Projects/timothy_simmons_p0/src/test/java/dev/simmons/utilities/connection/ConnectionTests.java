package dev.simmons.utilities.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionTests {
    @Test
    public void canConnect() {
        DataConnection connection = new PostgresConnection();
        Assertions.assertNotNull(connection);
    }
}
