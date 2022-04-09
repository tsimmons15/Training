package dev.simmons.app;

import org.junit.jupiter.api.Test;

public class AppTests {
    /*@Test
    public void loginClient() {
        String username = client.getClientUsername();
        String wrongUsername = "doesntexist";
        String password = HashUtil.hashSaltedString("testing1", client.getClientSalt());
        Assertions.assertNotEquals("testing1", password);
        Assertions.assertEquals(password, client.getClientPassword());
        String wrongPassword = HashUtil.hashSaltedString("TeStInG1", client.getClientSalt());
        Assertions.assertNotEquals("TeStInG1", wrongPassword);
        Assertions.assertNotEquals(wrongPassword, password);

        Client correct = clientDAO.getClient(username);
        Assertions.assertEquals(correct, client);
        Assertions.assertTrue(client.getClientPassword().equals(correct.getClientPassword()));

        Client incorrect = clientDAO.getClient(wrongUsername);
        Assertions.assertNull(incorrect);
    }*/
}
