package webapp.Suggestion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import webapp.Suggestion.Suggestion;

import java.util.ArrayList;
import java.util.List;

public class SuggestionBox {
    public static void main(String[] args) {
        Javalin server = Javalin.create();

        List<Suggestion> suggestions = new ArrayList<>();

        Gson builder = new GsonBuilder().create();

        server.get("/", ctx -> ctx.result("To use: /suggestions or /suggestions/add"));
        server.get("/suggestions", ctx -> {
            ctx.contentType("text/JSON");
            ctx.result(builder.toJson(suggestions));
        });
        server.post("/suggestions/add", ctx -> {
            try {
                Suggestion suggestion = builder.fromJson(ctx.body(), Suggestion.class);
                suggestions.add(suggestion);
                ctx.status(200);
                ctx.result("Suggestion added!");
            } catch (Exception e) {
                ctx.status(400);
                ctx.result("Error adding Suggestion!");
            }
        });

        server.start(7070);
    }
}
