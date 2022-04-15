package dev.simmons.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dev.simmons.data.BookDAOPostgres;
import dev.simmons.entities.Book;
import dev.simmons.exceptions.ResourceNotFoundException;
import dev.simmons.exceptions.ResourcesNotFoundException;
import dev.simmons.services.BookService;
import dev.simmons.services.Library;
import dev.simmons.utilities.lists.List;
import io.javalin.Javalin;

public class WebApp {
    public static BookService service = new Library(new BookDAOPostgres());

    public static void main(String[] args) {
        Javalin server = Javalin.create();
        Gson gson = new GsonBuilder().create();

        // Create
        server.post("/books", ctx-> {
            String body = ctx.body();
            Book book = gson.fromJson(body, Book.class);
            if (book == null) {
                System.out.println("No book given in create.");
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\":\"No book provided.\"}");
            } else {
                if (service.registerBook(book) == null) {
                    ctx.status(500);
                    ctx.contentType("text/JSON");
                    ctx.result("{\"error\":\"Unable to save book.\"}");
                } else {
                    ctx.status(201);
                    ctx.contentType("text/JSON");
                    ctx.result(gson.toJson(book));
                }
            }
        });

        // Retrieve
        server.get("/books", ctx -> {
            if (ctx.queryString() == null) {
                List<Book> books = service.libraryCatalog();
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result(gson.toJson(books));
            } else {
                // Get title
                // Filter by title.
            }
        });
        server.get("/books/{id}", ctx -> {
            String param = ctx.pathParam("id");
            int id = Integer.parseInt(param);
            Book book = service.retrieveById(id);

            ctx.status(200);
            ctx.result(gson.toJson(service.retrieveById(id)));

        });

        // Update
        server.put("/books/{id}", ctx -> {
            String param = ctx.body();
            Book updatedBook = gson.fromJson(param, Book.class);
            param = ctx.pathParam("id");
            int id = Integer.parseInt(param);
            updatedBook.setId(id);

            if (service.updateBook(updatedBook)) {
                ctx.status(204);
            } else {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to update the book.\"}");
            }
        });
        server.patch("/books/{id}/checkout", ctx -> {
            String param = ctx.pathParam("id");
            int id = Integer.parseInt(param);
            if (service.checkOutById(id) == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to check out the book.\"}");
            } else {
                ctx.status(204); // No content to return
            }
        });
        server.patch("/books/{id}/checkin", ctx -> {
            String param = ctx.pathParam("id");
            int id = Integer.parseInt(param);
            if (service.checkInById(id) == null) {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to check out the book.\"}");
            } else {
                ctx.status(204); // No content to return
            }
        });

        // Delete
        server.delete("/books/{id}", ctx -> {
            String param = ctx.pathParam("id");
            int id = Integer.parseInt(param);
            if (service.deleteBookById(id)) {
                ctx.status(204);
            } else {
                ctx.status(500);
                ctx.result("{\"error\":\"Unable to delete the book.\"}");
            }
        });

        server.exception(ResourceNotFoundException.class, (ex, ctx) -> {
            ctx.status(404);
            ctx.contentType("text/JSON");
            ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });
        server.exception(ResourcesNotFoundException.class, (ex, ctx) -> {
            ctx.status(404);
            ctx.contentType("text/JSON");
            ctx.result("{\"error\":\"" + ex.getMessage() + "\"}");
        });
        server.exception(JsonSyntaxException.class, (ex, ctx) -> {
            ctx.status(400);
            ctx.result("{\"error\":\"Invalid JSON syntax.\"}");
        });
        server.exception(NumberFormatException.class, (ex, ctx) -> {
            ctx.status(400);
            ctx.result("{\"error\":\"Unable to parse argument.\"}");
        });

        server.start(7070);
    }
}
