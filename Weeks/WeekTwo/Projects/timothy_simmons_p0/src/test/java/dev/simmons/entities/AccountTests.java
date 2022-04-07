package dev.simmons.entities;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation)
public class AccountTests {
    @Test
    public void createAccounts() {
        Account account = null;
        Client c = new Client();
        account.addOwner(c);
    }

    @Test
}
