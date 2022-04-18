package dev.simmons.data;

import dev.simmons.entities.Expense;
import dev.simmons.exceptions.ExpenseNotPendingException;
import dev.simmons.utilities.connection.PostgresConnection;
import dev.simmons.utilities.logging.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.List;

public class PostgresExpenseDAO implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "insert into expense (amount, status, date, issuer) values (?,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, (long)(expense.getAmount()*100));
            statement.setString(2, expense.getStatus().name());
            statement.setLong(3, expense.getDate());
            if (expense.getIssuer() == 0) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, expense.getIssuer());
            }

            ResultSet rs = statement.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            expense.setId(id);

            return expense;
        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public List<Expense> getExpensesByStatus(Expense.Status status) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public List<Expense> getAllEmployeeExpenses(int employeeId) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public Expense replaceExpense(Expense expense) throws ExpenseNotPendingException {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "update expense set amount = ?, status = ?, " +
                    "date = ?, issuer = ? where expense_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1,(long)(expense.getAmount()*100));
            statement.setString(2, expense.getStatus().name());
            statement.setLong(3, expense.getDate());
            if (expense.getIssuer() == 0) {
                statement.setNull(4, Types.BIGINT);
            } else {
                statement.setInt(4, expense.getIssuer());
            }
            statement.setInt(5, expense.getId());

            int updated = statement.executeUpdate();
            if (updated != 1) {
                Logger.log(Logger.Level.WARNING, "Unable to update " + expense + ".");
                return null;
            }

            return expense;
        } catch (PSQLException pse) {
            Logger.log(Logger.Level.WARNING, "Attempt to replace contents of non-pending expense.");
            throw new ExpenseNotPendingException(expense.getId());
        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return false;
    }
}
