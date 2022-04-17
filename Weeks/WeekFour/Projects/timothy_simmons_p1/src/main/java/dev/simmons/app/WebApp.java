package dev.simmons.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.simmons.data.PostgresEmployeeDAO;
import dev.simmons.data.PostgresExpenseDAO;
import dev.simmons.entities.Employee;
import dev.simmons.entities.Expense;
import dev.simmons.exceptions.ExpenseNotPendingException;
import dev.simmons.exceptions.NegativeExpenseException;
import dev.simmons.service.ExpensesService;
import dev.simmons.service.ExpensesServiceImpl;
import io.javalin.Javalin;

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
            // getAllEmployees();
        });
        server.get("/employees/{index}", ctx -> {
            // getEmployeeByIndex(index);
        });

        server.put("/employees/{index}", ctx -> {
            // replaceEmployee(employee);
        });
        server.delete("/employees/{index}", ctx -> {
            // deleteEmployee(employee);
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
                    ctx.result("{\"result\":\"Created new employee, " + received.getId() + "\"}");
                }
            }
        });
        server.post("/employees/{index}/expenses", ctx -> {
            // Adds an expense for employee id={index}
            // createExpense(expense)
        });

        server.get("/expenses", ctx -> {
            // getAllExpenses();
        });
        server.get("/expenses/{index}", ctx -> {
            // getExpenseById(index);
        });
        server.get("/employees/{index}/expenses", ctx -> {
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
            // approveExpense(index);
        });
        server.patch("/expenses/{index}/deny", ctx -> {
            // denyExpense(index);
        });

        server.delete("/expenses/{index}", ctx -> {
            // deleteExpense(index);
        });

        server.exception(ExpenseNotPendingException.class, (ex, ctx) -> {
            ctx.status(400); // One of the 400s since the user is making a mistake
        });
        server.exception(NegativeExpenseException.class, (ex, ctx) -> {
            ctx.status(400); // One of the 400s since the user is making a mistake
        });


        server.start(7070);
    }
}
