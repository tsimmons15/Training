package dev.simmons.data;

import dev.simmons.data.AccountDAO;
import dev.simmons.data.PostgresAccountDAO;
import dev.simmons.entities.Account;
import dev.simmons.entities.CheckingAccount;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDAOTests {
    private static AccountDAO accountDAO;
    private static Account checking;
    private static Account savings;
    private static Account cd;
    @Test
    @Order(1)
    public void createAccounts() {
        accountDAO = new PostgresAccountDAO();
        checking = Account.accountFactory(Account.AccountType.Checking.name());
        Assertions.assertTrue(checking instanceof CheckingAccount);
        Assertions.assertEquals(Account.AccountType.Checking, checking.getType());
        Assertions.assertEquals(0, checking.getBalance());

        savings = Account.accountFactory(Account.AccountType.Savings.name());
        Assertions.assertTrue(checking instanceof CheckingAccount);
        Assertions.assertEquals(Account.AccountType.Checking, checking.getType());

        cd = Account.accountFactory(Account.AccountType.CD.name());
        Assertions.assertTrue(checking instanceof CheckingAccount);
        Assertions.assertEquals(Account.AccountType.Checking, checking.getType());
    }
}
