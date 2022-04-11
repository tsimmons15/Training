package dev.simmons.app;

import dev.simmons.entities.Client;
import dev.simmons.service.Bank;
import dev.simmons.service.Banking;
import dev.simmons.utilities.hashing.HashUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTests {
    @Test
    public void loginClient() {
        Bank bank = new Banking();

        Client client = new Client();
        client.setClientName("name");
        client.setClientUsername("namename");
        client.hashClientPassword("usingname");
        Assertions.assertTrue(bank.registerClient(client));

        Client other = new Client();
        other.setClientName("othername");
        other.setClientUsername("othername");
        other.hashClientPassword("otherPassword");
        Assertions.assertTrue(bank.registerClient(other));

        String invalidUsername = "doesntexist";

        String username = client.getClientUsername();



        Assertions.assertTrue(bank.closeClient(client));
        Assertions.assertTrue(bank.closeClient(other));
    }
}
