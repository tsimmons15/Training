package webapp;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Integer> temps = new ArrayList<>();
        temps.add(100);
        temps.add(-100);
        temps.add(-50);
        temps.add(50);
        temps.add(0);
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/fail", ctx -> ctx.result("No, you fail!"));
        app.get("/hello/{name}", ctx -> ctx.result("Hello to you too, " + ctx.pathParam("name")));
        app.get("/hello", ctx->ctx.result("Hello there, stranger!"));
        app.get("/temps", ctx -> ctx.json(temps));
    }
}
