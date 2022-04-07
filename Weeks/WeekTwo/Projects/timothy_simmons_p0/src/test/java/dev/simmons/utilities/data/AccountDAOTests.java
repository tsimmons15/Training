package dev.simmons.utilities.data;

import dev.simmons.data.AccountDAO;
import dev.simmons.data.PostgresAccountDAO;
import dev.simmons.entities.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDAOTests {
    private static AccountDAO accountDAO;
    private static Account account;
    @Test
    @Order(1)
    public void createAccount() {
        accountDAO = new PostgresAccountDAO();

    }
}
