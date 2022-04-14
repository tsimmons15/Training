package webapp.Receipts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceiptServer {
    public static void main(String[] args) {
        Javalin server = Javalin.create();
        List<Receipt> items = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        server.get("/", ctx->{
            ctx.status(200);
            ctx.contentType("text/JSON");
            ctx.result("{\"message\": \"To use: /items to get/add/remove items. /total to get the total.\"}");
        });
        server.get("/items", ctx -> {
            ctx.status(200);
            ctx.contentType("text/JSON");
            ctx.result(gson.toJson(items));
        });
        server.get("/items/{index}", ctx->{
            try {
                int index = Integer.parseInt(ctx.pathParam("index"));
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result(gson.toJson(items.get(index)));
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Not a valid index: " + ctx.pathParam("index") + "\"}");
            }
        });
        server.get("/total", ctx -> {
            double sum = items.stream().map(Receipt::getPrice).reduce(0.0, Double::sum);
            ctx.status(200);
            ctx.contentType("text/JSON");
            ctx.result("{\"total\": " + sum + "}");
        });
        server.delete("/items", ctx-> {

            int from = 0;
            int to = 0;
            try {
                String fromParam = ctx.queryParam("from");
                String toParam = ctx.queryParam("to");

                if (fromParam == null || toParam == null) {
                    items.clear();
                    ctx.status(200);
                    ctx.contentType("text/JSON");
                    ctx.result("{\"result\": \"All items cleared.\"}");
                } else {
                    from = Integer.parseInt(fromParam)-1;
                    to = Integer.parseInt(toParam)-1;
                    int max = Math.max(from, to);
                    int min = Math.min(from, to);

                    if (max >= items.size()) {
                        throw new IndexOutOfBoundsException(max + " is out of bounds");
                    }

                    items.subList(min, max + 1).clear();

                    ctx.status(200);
                    ctx.contentType("text/JSON");
                    ctx.result("{\"removed\": Indices {"+ min + ", " + max + "}");
                }

            } catch (NumberFormatException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\":\"Error parsing indices.\"}");
            } catch (IndexOutOfBoundsException ioobe) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Indices ("+ from + ":" + to + ") out of bounds.\"}");
            }
        });
        server.delete("/items/{index}", ctx -> {
            try {
                int index = Integer.parseInt(ctx.pathParam("index"));
                Receipt receipt = items.remove(index);
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"removed\": " + gson.toJson(receipt) + "}");
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Not a valid index: " + ctx.pathParam("index") + "\"}");
            }
        });
        server.post("/items", ctx-> {
            try {
                Receipt[] newItems = gson.fromJson(ctx.body(), Receipt[].class);
                items.addAll(Arrays.asList(newItems));
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"added\" : \"" + newItems.length + "\"}");
            } catch (Exception e) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Error parsing item.\"}");
            }
        });
        server.patch("/items/{index}", ctx->{
            try {
                Receipt item = gson.fromJson(ctx.body(), Receipt.class);
                int index = Integer.parseInt(ctx.pathParam("index"));
                items.set(index, item);
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"updated\": " + gson.toJson(item) + "}");
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Not a valid index: " + ctx.pathParam("index") + "\"}");
            }
        });


        server.start(7070);
    }
}
