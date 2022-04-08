package dev.simmons.utilities.data;

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

    @Test
    @Order(2)
    public void handleCheckingTransactions() {
        Assertions.assertFalse(checking.withdraw(100));
        Assertions.assertFalse(checking.withdraw(-100));
        Assertions.assertEquals(0, checking.getBalance());

        checking.deposit(-100);
        Assertions.assertEquals(0, checking.getBalance());

        checking.deposit(Double.MAX_VALUE);
        Assertions.assertEquals(0, checking.getBalance());

        checking.deposit(500000);
        Assertions.assertEquals(500000, checking.getBalance(), .01);

        Assertions.assertTrue(checking.withdraw(1000));
        Assertions.assertEquals(499000, checking.getBalance(), .01);
    }

    @Test
    @Order(3)
    public void handleSavingsTransactions() {
        Assertions.assertFalse(savings.withdraw(100));
        Assertions.assertFalse(savings.withdraw(-100));
        Assertions.assertEquals(0, savings.getBalance());

        savings.deposit(-100);
        Assertions.assertEquals(0, savings.getBalance());

        savings.deposit(Double.MAX_VALUE);
        Assertions.assertEquals(0, savings.getBalance());

        savings.deposit(500000);
        Assertions.assertEquals(500000*(1.00 + Account.AccountType.Savings.interest), savings.getBalance(), .01);

        double balance = savings.getBalance();
        Assertions.assertTrue(savings.withdraw(1000));
        Assertions.assertEquals(balance - 1000, savings.getBalance(), .01);
    }

    @Test
    @Order(4)
    public void handleCDTransactions() {
        Assertions.assertFalse(cd.withdraw(100));
        Assertions.assertFalse(cd.withdraw(-100));
        Assertions.assertEquals(0, cd.getBalance());

        cd.deposit(-100);
        Assertions.assertEquals(0, cd.getBalance());

        cd.deposit(Double.MAX_VALUE);
        Assertions.assertEquals(0, cd.getBalance());

        cd.deposit(500000);
        Assertions.assertEquals(500000*(1.00 + Account.AccountType.CD.interest), cd.getBalance(), .01);

        double balance = cd.getBalance();
        Assertions.assertTrue(cd.withdraw(1000));
        Assertions.assertEquals(balance - 1000 * (1 + Account.AccountType.CD.interest * 1.5), cd.getBalance(), .01);
    }
}
