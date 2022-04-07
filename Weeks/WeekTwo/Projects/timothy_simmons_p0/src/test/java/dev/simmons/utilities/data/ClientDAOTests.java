package dev.simmons.utilities.data;

import dev.simmons.data.ClientDAO;
import dev.simmons.data.PostgresClientDAO;
import dev.simmons.entities.Client;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDAOTests {
    private static Client client;
    private static ClientDAO clientDAO;

    @Test
    @Order(1)
    public void createClient() {
        ClientDAOTests.clientDAO = new PostgresClientDAO();
        ClientDAOTests.client = new Client();
        client.setClientName("Testing1");
        client.setClientUsername("testing1");
        client.setClientPassword("testing1");
        Client received = clientDAO.createClient(client);
        Assertions.assertNotNull(received);
        client.setClientId(received.getClientId());
        Assertions.assertTrue(received.getClientId() > 0);
        Assertions.assertEquals(received.getClientName(), client.getClientName());
        Assertions.assertNotEquals("testing1", client.getClientPassword());
        Assertions.assertNotNull(received.getClientSalt());
    }

    @Test
    @Order(2)
    public void updateClient() {
        ClientDAOTests.client.setClientName("Testing2");
        ClientDAOTests.client.setClientUsername("testing2");
        client.setClientPassword("testing2");
        Client received = clientDAO.updateClient(client);
        Assertions.assertNotNull(received);
    }

    @Test
    @Order(3)
    public void deleteClient() {
        Assertions.assertTrue(ClientDAOTests.clientDAO.deleteClient(ClientDAOTests.client));
        Assertions.assertNull(clientDAO.getClient(client.getClientId()));
    }
}
