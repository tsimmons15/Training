package dev.simmons.data;

import dev.simmons.entities.Employee;
import dev.simmons.exceptions.IncompleteEmployeeException;
import dev.simmons.exceptions.IncompleteExpenseException;
import org.junit.jupiter.api.*;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEmployeeDAO {
    private static EmployeeDAO empDao;
    private static Employee employee;
    @Test
    @Order(1)
    public void createAnEmployee() {
        Employee emp = new Employee();
        emp.setFirstName("Testing");
        emp.setLastName("Testing");
        Employee received = empDao.createEmployee(emp);
        Assertions.assertNotNull(received);
        Assertions.assertNotEquals(0, received.getId());
        employee = received;
    }

    @Test
    @Order(2)
    public void getEmployee() {
        Employee emp = empDao.getEmployeeById(employee.getId());
        Assertions.assertEquals(employee, emp);
    }

    @Test
    @Order(3)
    public void getAllEmployees() {
        List<Employee> emps = empDao.getAllEmployees();
        Assertions.assertNotEquals(0, emps.size());
    }

    @Test
    @Order(4)
    public void replaceEmployee() {
        Employee newEmp = new Employee();
        newEmp.setId(employee.getId());
        newEmp.setFirstName("Different");
        newEmp.setLastName("Names");
        Employee received = empDao.replaceEmployee(newEmp);
        Assertions.assertNotNull(received);
        Assertions.assertNotEquals(received, employee);
    }

    @Test
    @Order(5)
    public void deleteEmployee() {
        Assertions.assertTrue(empDao.deleteEmployee(employee.getId()));
        Assertions.assertNull(empDao.getEmployeeById(employee.getId()));
    }

    @Test
    public void incompleteEmployeeExceptionThrowsDuringInsert() {
        Employee emp = new Employee();
        int length = empDao.getAllEmployees().size();

        Assertions.assertThrows(IncompleteEmployeeException.class, () -> {
            empDao.createEmployee(emp);
        });
        emp.setFirstName("Testing");
        Assertions.assertThrows(IncompleteEmployeeException.class, () -> {
            empDao.createEmployee(emp);
        });
        emp.setLastName("Testing");

        Assertions.assertEquals(length, empDao.getAllEmployees().size());

        Employee received = empDao.createEmployee(emp);
        Assertions.assertNotNull(received);

        Assertions.assertTrue(empDao.deleteEmployee(received.getId()));
    }

    @Test
    public void incompleteEmployeeExceptionThrowsDuringReplace() {
        Employee emp = new Employee();
        emp.setFirstName("Testing");
        emp.setLastName("Testing");
        emp = empDao.createEmployee(emp);
        Assertions.assertNotNull(emp);

        final Employee newEmp = new Employee();
        Assertions.assertThrows(IncompleteEmployeeException.class, () -> {
            empDao.replaceEmployee(newEmp);
        });

        newEmp.setId(emp.getId());
        Assertions.assertThrows(IncompleteEmployeeException.class, () -> {
            empDao.replaceEmployee(newEmp);
        });

        newEmp.setFirstName("Other");
        Assertions.assertThrows(IncompleteEmployeeException.class, () -> {
            empDao.replaceEmployee(newEmp);
        });

        Employee received = empDao.getEmployeeById(newEmp.getId());
        Assertions.assertNotEquals(newEmp.getFirstName(), received.getFirstName());

        newEmp.setLastName("Name");

        received = empDao.replaceEmployee(newEmp);
        Assertions.assertNotNull(received);

        Assertions.assertTrue(empDao.deleteEmployee(received.getId()));
    }

}
