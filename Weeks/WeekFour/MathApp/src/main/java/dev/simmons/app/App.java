package dev.simmons.app;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        app.get("/add/{num1}/{num2}", (ctx) -> {
            int num1 = Integer.parseInt(ctx.pathParam("num1"));
            int num2 = Integer.parseInt(ctx.pathParam("num2"));
            System.out.println("Received: " + num1 + " " + num2);
            ctx.result(""+(num1+num2));
        });

        app.start(7070);
    }
}
