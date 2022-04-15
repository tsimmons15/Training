package dev.simmons.data;

import dev.simmons.entities.Employee;
import dev.simmons.entities.Expense;
import dev.simmons.exceptions.IncompleteExpenseException;
import dev.simmons.exceptions.NegativeExpenseException;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExpenseDAO {
    private static ExpenseDAO expDao;
    private static EmployeeDAO empDao;
    private static Expense expense;
    private static Employee employee;

    @Test
    @Order(1)
    public void createAnExpense() {
        Employee emp = new Employee();
        emp.setFirstName("Test");
        emp.setLastName("Test");
        emp = empDao.createEmployee(emp);

        Expense exp = new Expense();
        exp.setAmount(100);
        exp.setDate(100);
        exp.setStatus(Expense.Status.APPROVED);
        exp.setIssuer(emp.getId());

        expense = expDao.createExpense(exp);
        Assertions.assertNotNull(expense);
    }

    @Test
    @Order(2)
    public void getExpenseById() {
        Expense received = expDao.getExpenseById(expense.getId());
        Assertions.assertNotNull(received);
    }

    @Test
    @Order(3)
    public void getAllExpenses() {
        Assertions.assertNotEquals(0, expDao.getAllExpenses().size());
    }

    @Test
    @Order(4)
    public void getExpensesByStatus() {
        List<Expense> expenses = expDao.getExpensesByStatus(Expense.Status.APPROVED);
        Assertions.assertNotNull(expenses);
        Assertions.assertNotEquals(0, expenses.size());

        for (Expense e : expenses) {
            Assertions.assertEquals(Expense.Status.APPROVED, e.getStatus());
        }
    }

    @Test
    @Order(5)
    public void getAllExpensesByEmployee() {
        List<Expense> expenses = expDao.getAllEmployeeExpenses(employee.getId());
        Assertions.assertNotNull(expenses);
        Assertions.assertNotEquals(0, expenses.size());

        for(Expense e : expenses) {
            Assertions.assertEquals(employee.getId(), e.getIssuer());
        }
    }

    @Test
    @Order(6)
    public void replaceExpense() {
        Employee other = new Employee();
        other.setFirstName("Other");
        other.setLastName("Test");
        other = empDao.createEmployee(other);
        Assertions.assertNotNull(other);

        Expense newExpense = new Expense();
        newExpense.setId(expense.getId());
        newExpense.setIssuer(other.getId());
        newExpense.setDate(50000);
        newExpense.setAmount(80000);
        newExpense.setStatus(Expense.Status.PENDING);
        newExpense = expDao.replaceExpense(newExpense);
        Assertions.assertNotNull(newExpense);

        Assertions.assertTrue(empDao.deleteEmployee(other.getId()));
    }

    @Test
    @Order(7)
    public void deleteExpense() {
        Assertions.assertTrue(expDao.deleteExpense(expense.getId()));
        Assertions.assertNull(expDao.getExpenseById(expense.getId()));

        Assertions.assertTrue(empDao.deleteEmployee(employee.getId()));
    }

    @Test
    public void negativeExpenseThrowsExceptionDuringInsert() {
        // NegativeExpenseException thrown if you try to create a negative expense
        // Negative expense is not inserted.
        int length = expDao.getAllExpenses().size();
        Assertions.assertThrows(NegativeExpenseException.class, () -> {
           Expense exp = new Expense();
           exp.setDate(100);
           exp.setAmount(-1);
           exp.setStatus(Expense.Status.PENDING);
           exp.setIssuer(0);
           Assertions.assertNull(expDao.createExpense(exp));
        });
        Assertions.assertEquals(length, expDao.getAllExpenses().size());
    }

    @Test
    public void negativeExpenseThrowsExceptionDuringReplace() {
        // NegativeExpenseException thrown if you try to create a negative expense
        // Negative expense is not inserted.
        Expense exp = new Expense();
        exp.setDate(100);
        exp.setAmount(100);
        exp.setStatus(Expense.Status.PENDING);
        exp.setIssuer(0);
        Expense received = expDao.createExpense(exp);
        Assertions.assertNotNull(received);
        received.setAmount(-100);
        Assertions.assertThrows(NegativeExpenseException.class, () -> {
            Assertions.assertNull(expDao.replaceExpense(received));
        });
        Assertions.assertEquals(exp.getAmount(), expDao.getExpenseById(exp.getId()).getAmount());
        Assertions.assertTrue(expDao.deleteExpense(exp.getId()));
    }

    @Test
    public void incompleteExpenseThrowsExceptionDuringInsert() {
        // IncompleteExpenseException thrown if you try to insert without required fields
        // Incomplete expense is not inserted.
        Expense exp = new Expense();
        int length = expDao.getAllExpenses().size();
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
           expDao.createExpense(exp);
        });
        exp.setAmount(100);
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
            expDao.createExpense(exp);
        });
        exp.setStatus(Expense.Status.PENDING);
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
            expDao.createExpense(exp);
        });
        exp.setDate(100);

        Assertions.assertEquals(length, expDao.getAllExpenses().size());

        Expense received = expDao.createExpense(exp);
        Assertions.assertNotNull(received);
        Assertions.assertTrue(expDao.deleteExpense(exp.getId()));
    }

    @Test
    public void incompleteExpenseThrowsExceptionDuringReplacing() {
        // IncompleteExpenseException thrown if you try to insert without required fields
        // Incomplete expense is not inserted.
        Expense exp = new Expense();
        exp.setAmount(100);
        exp.setStatus(Expense.Status.PENDING);
        exp.setDate(100);

        exp = expDao.createExpense(exp);
        Assertions.assertNotNull(exp);

        final Expense newExp = new Expense();
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
            expDao.replaceExpense(newExp);
        });
        newExp.setId(exp.getId());
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
            expDao.replaceExpense(newExp);
        });
        newExp.setAmount(4000);
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
            expDao.replaceExpense(newExp);
        });
        newExp.setStatus(Expense.Status.DENIED);
        Assertions.assertThrows(IncompleteExpenseException.class, () -> {
            expDao.replaceExpense(newExp);
        });
        newExp.setDate(1000);

        Expense received = expDao.getExpenseById(exp.getId());
        Assertions.assertEquals(exp.getAmount(), received.getAmount());

        received = expDao.replaceExpense(newExp);
        Assertions.assertNotNull(received);

        Assertions.assertTrue(expDao.deleteExpense(received.getId()));
    }
}
