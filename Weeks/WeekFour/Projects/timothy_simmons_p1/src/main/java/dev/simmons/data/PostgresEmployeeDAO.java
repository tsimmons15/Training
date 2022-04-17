package dev.simmons.data;

import dev.simmons.entities.Employee;
import dev.simmons.utilities.connection.PostgresConnection;
import dev.simmons.utilities.logging.Logger;

import java.sql.*;
import java.util.List;

public class PostgresEmployeeDAO implements EmployeeDAO{
    @Override
    public Employee createEmployee(Employee employee) {
        try (Connection conn = PostgresConnection.getConnection()) {
            String sql = "insert into employee (employee_first_name, employee_last_name) values (?,?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());

            ResultSet rs = statement.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            employee.setId(id);

            return employee;
        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public Employee replaceEmployee(Employee employee) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        try (Connection conn = PostgresConnection.getConnection()) {

        } catch (SQLException se) {
            Logger.log(Logger.Level.ERROR, se);
        }
        return false;
    }
}
