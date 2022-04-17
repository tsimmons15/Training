package dev.simmons.service;

import dev.simmons.data.EmployeeDAO;
import dev.simmons.data.ExpenseDAO;
import dev.simmons.entities.Employee;
import dev.simmons.entities.Expense;

import java.util.List;

public class ExpensesServiceImpl implements ExpensesService{
    private EmployeeDAO empDao;
    private ExpenseDAO expDao;

    public ExpensesServiceImpl(EmployeeDAO empDao, ExpenseDAO expDao) {
        this.empDao = empDao;
        this.expDao = expDao;
    }

    @Override
    public Expense createExpense(Expense expense) {
        return expDao.createExpense(expense);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return empDao.createEmployee(employee);
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public List<Expense> getExpensesByStatus(Expense.Status status) {
        return null;
    }

    @Override
    public List<Expense> getExpensesByEmployee(int employeeId) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee replaceEmployee(Employee employee) {
        return null;
    }

    @Override
    public Expense replaceExpense(Expense expense) {
        return expDao.replaceExpense(expense);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
