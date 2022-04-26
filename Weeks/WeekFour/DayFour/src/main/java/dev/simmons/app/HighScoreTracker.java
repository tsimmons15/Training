package dev.simmons.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.simmons.models.Score;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class HighScoreTracker {
    private static List<Score> scoreList = new ArrayList<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create();
        Gson gson = new GsonBuilder().create();

        app.get("/scores", ctx -> {
            ctx.status(200);
            ctx.result(gson.toJson(scoreList));
        });

        app.post("/scores", ctx -> {
            Score score = gson.fromJson(ctx.body(), Score.class);
            scoreList.add(score);
            ctx.status(201);
            ctx.result("Score " + score + " was added.");
        });

        app.exception(Exception.class, (err, ctx) -> {
            System.out.println("Oops... ");
            System.out.println(err.getMessage());
        });


        app.start(5000);
    }
}
