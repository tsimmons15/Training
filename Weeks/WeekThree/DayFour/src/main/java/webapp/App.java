package webapp;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/fail", ctx -> ctx.result("No, you fail!"));
        app.get("/hello/{name}", ctx -> ctx.result("Hello to you too, " + ctx.pathParam("name")));
        app.get("/hello", ctx->ctx.result("Hello there, stranger!"));
    }
}
