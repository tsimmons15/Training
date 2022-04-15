package dev.simmons.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.simmons.exceptions.ExpenseNotPendingException;
import dev.simmons.exceptions.IncompleteEmployeeException;
import dev.simmons.exceptions.IncompleteExpenseException;
import dev.simmons.exceptions.NegativeExpenseException;
import io.javalin.Javalin;

public class WebApp {
    public static void main(String[] args) {
        Javalin server = Javalin.create();
        Gson gson = new GsonBuilder().create();

        server.post("/employees", ctx -> {
            // createEmployee(employee);
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
            // createExpense(expense);
            // Will need to be passed employee id?
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
            // replaceExpense(index);
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
        server.exception(IncompleteEmployeeException.class, (ex, ctx) -> {
            ctx.status(400);
        });
        server.exception(IncompleteExpenseException.class, (ex, ctx) -> {
            ctx.status(400);
        });


        server.start(7070);
    }
}
