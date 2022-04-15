package dev.simmons.connectiontests;

import dev.simmons.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTests {
    @Test
    void canConnect() {
        Connection conn = ConnectionUtil.createConnection();
        Assertions.assertNotNull(conn);
    }
}
