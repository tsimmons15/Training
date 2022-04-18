package dev.simmons.app;

import dev.simmons.data.PostgresEmployeeDAO;
import dev.simmons.data.PostgresExpenseDAO;
import dev.simmons.entities.Employee;
import dev.simmons.entities.Expense;
import dev.simmons.exceptions.*;
import dev.simmons.service.ExpensesService;
import dev.simmons.service.ExpensesServiceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import java.util.List;

public class WebApp {
    private static ExpensesService service;
    public static void main(String[] args) {
        service = new ExpensesServiceImpl(new PostgresEmployeeDAO(), new PostgresExpenseDAO());
        Javalin server = Javalin.create();
        Gson gson = new GsonBuilder().create();

        server.post("/employees", ctx -> {
            Employee emp = gson.fromJson(ctx.body(), Employee.class);
            if (emp == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to parse the provided employee. Check the syntax: '" + ctx.body() + "'\"}");
            } else {
                Employee received = service.createEmployee(emp);
                if (received == null) {
                    ctx.status(500);
                    ctx.result("{\"error\":\"Unable to save the provided employee: " + emp + "\"}");
                } else {
                    ctx.status(201);
                    ctx.result("{\"result\":\"Created new employee, " + received.getId() + "\"}");
                }
            }
        });

        server.get("/employees", ctx -> {
            List<Employee> employeeList = service.getAllEmployees();
            if (employeeList == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to retrieve employee list.\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": " +  gson.toJson(employeeList) + "}");
            }
        });
        server.get("/employees/{index}", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            Employee emp = service.getEmployeeById(id);
            if (emp == null) {
                throw new NoSuchEmployeeException("Unable to find employee with id " + id);
            }
            ctx.status(200);
            ctx.result("{\"result\": " + gson.toJson(emp) + "}");
        });

        server.put("/employees/{index}", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            Employee emp = gson.fromJson(ctx.body(), Employee.class);
            emp.setId(id);
            emp = service.replaceEmployee(emp);
            if (emp == null) {
                ctx.status(500);
                ctx.result("{\"error\": \"Unable to update the employee.\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": " + gson.toJson(emp) + "}");
            }
        });
        server.delete("/employees/{index}", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            if (service.deleteEmployee(id)) {
                ctx.status(200);
                ctx.result("{\"result\":\"Successfully deleted employee " + id + "\"}");
            } else {
                ctx.status(500);
                ctx.result("{\"error\": \"Unable to delete employee " + id + "\"}");
            }
        });

        server.post("/expenses", ctx -> {
            Expense exp = gson.fromJson(ctx.body(), Expense.class);
            if (exp == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to parse the provided expense. Check the syntax: '" + ctx.body() + "'\"}");
            } else {
                Expense received = service.createExpense(exp);
                if (received == null) {
                    ctx.status(500);
                    ctx.result("{\"error\":\"Unable to save the provided expense: " + exp + "\"}");
                } else {
                    ctx.status(201);
                    ctx.result("{\"result\":\"Created new expense, " + received.getId() + "\"}");
                }
            }
        });
        server.post("/employees/{index}/expenses", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            Expense exp = gson.fromJson(ctx.body(), Expense.class);
            if (exp == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to parse the provided expense. Check the syntax: '" + ctx.body() + "'\"}");
            } else {
                exp.setIssuer(id);
                Expense received;
                if (exp.getId() > 0) {
                    received = service.replaceExpense(exp);
                } else {
                    received = service.createExpense(exp);
                }
                if (received == null) {
                    ctx.status(500);
                    ctx.result("{\"error\":\"Unable to assign the provided expense, " + exp + ", to employee " + id + "\"}");
                } else {
                    ctx.status(201);
                    ctx.result("{\"result\":\"Expense, " + received.getId() + ", successfully assigned to employee " + id + ".\"}");
                }
            }
        });

        server.get("/expenses", ctx -> {
            List<Expense> expenses = service.getAllExpenses();
            if (expenses == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to retrieve all expenses.\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": " + gson.toJson(expenses)  + "}");
            }
        });
        server.get("/expenses/{index}", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            Expense exp = service.getExpenseById(id);
            if (exp == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to get the expense " + id + ".\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": " + gson.toJson(exp) + "}");
            }
        });
        server.get("/employees/{index}/expenses", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            List<Expense> expenses = service.getExpensesByEmployee(id);
            if (expenses == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to get the list of expenses for employee " + id + ".\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": " + gson.toJson(expenses) + "}");
            }
            // Gets all expenses for employee id={index}
            // getExpensesByEmployee(index);
        });

        server.put("/expenses/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            Expense expense = gson.fromJson(ctx.body(), Expense.class);
            if (expense == null) {
                ctx.status(404);
                ctx.result("{\"error\":\"Unable to parse the provided expense. Check the sent expense.\"}");
            } else {
                expense.setId(index);
                Expense received = service.replaceExpense(expense);
                if (received == null) {
                    ctx.status(500);
                    ctx.result("{\"error\":\"Was unable to update the provided expense: " + expense + ".\"}");
                } else {
                    ctx.status(201);
                    ctx.result("{\"result\":\"Expense updated\"}");
                }
            }
        });
        server.patch("/expenses/{index}/approve", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            Expense exp = service.getExpenseById(id);
            if (exp == null) {
                throw new NoSuchExpenseException("Unable to find expense with id " + id);
            }
            exp.setStatus(Expense.Status.APPROVED);
            exp = service.replaceExpense(exp);
            if (exp == null) {
                ctx.status(500);
                ctx.result("{\"error\": \"Unable to approve expense " + id + ".\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": \"Successfully approved expense " + id + ".\"}");
            }
        });
        server.patch("/expenses/{index}/deny", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            Expense exp = service.getExpenseById(id);
            if (exp == null) {
                throw new NoSuchExpenseException("Unable to find expense with id " + id);
            }
            exp.setStatus(Expense.Status.DENIED);
            exp = service.replaceExpense(exp);
            if (exp == null) {
                ctx.status(500);
                ctx.result("{\"error\": \"Unable to deny expense " + id + ".\"}");
            } else {
                ctx.status(200);
                ctx.result("{\"result\": \"Successfully denied expense " + id + ".\"}");
            }
        });

        server.delete("/expenses/{index}", ctx -> {
            String param = ctx.pathParam("index") + "";
            int id = Integer.parseInt(param);
            if (service.deleteExpense(id)) {
                ctx.status(200);
                ctx.result("{\"result\":\"Successfully deleted expense " + id + "\"}");
            } else {
                ctx.status(500);
                ctx.result("{\"error\": \"Unable to delete expense " + id + "\"}");
            }
        });

        /*
         * EXCEPTION HANDLING RESPONSES
         */
        server.exception(NumberFormatException.class, (ex, ctx) -> {
            ctx.status(400);
            ctx.result("{\"error\":\"Unable to parse the index provided: " + ctx.path() + "\"}");
        });
        server.exception(ExpenseNotPendingException.class, (ex, ctx) -> {
            ctx.status(400); // One of the 400s since the user is making a mistake
            ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });
        server.exception(NonpositiveExpenseException.class, (ex, ctx) -> {
            ctx.status(400); // One of the 400s since the user is making a mistake
            ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });
        server.exception(InvalidExpenseException.class, (ex, ctx) -> {
           ctx.status(400);
           ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });
        server.exception(NoSuchExpenseException.class, (ex, ctx) -> {
           ctx.status(404);
           ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });
        server.exception(NoSuchEmployeeException.class, (ex, ctx) -> {
            ctx.status(404);
            ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });


        server.start(7070);
    }
}
