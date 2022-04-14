package webapp.Receipts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.javalin.Javalin;

import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceiptServer {
    public static void main(String[] args) {
        Javalin server = Javalin.create();
        //List<Receipt> items = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        server.get("/", ctx->{
            ctx.status(200);
            ctx.contentType("text/JSON");
            ctx.result("{\"message\": \"To use: /items to get/add/remove/update items. /total to get the total.\"}");
        });
        server.get("/items", ctx -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD")))  {
                PreparedStatement statement = conn.prepareStatement("select * from receipt;");
                ResultSet rs = statement.executeQuery();
                List<Receipt> items = new ArrayList<>();
                Receipt receipt = null;
                while(rs.next()) {
                    receipt = new Receipt();
                    receipt.setId(rs.getInt("id"));
                    receipt.setDescription(rs.getString("description"));
                    receipt.setPrice(rs.getDouble("price"));
                    receipt.setTimestamp(LocalDateTime.ofEpochSecond(rs.getLong("datetime"), 0, ZoneOffset.ofHours(0)));
                    items.add(receipt);
                }
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result(gson.toJson(items));
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });
        server.get("/items/{index}", ctx->{
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD"))) {
                int index = Integer.parseInt(ctx.pathParam("index"));

                PreparedStatement statement = conn.prepareStatement("select * from receipt where id = ?;");
                statement.setInt(1, index);

                ResultSet rs = statement.executeQuery();
                Receipt receipt = null;
                rs.next();

                receipt = new Receipt();
                receipt.setId(rs.getInt("id"));
                receipt.setDescription(rs.getString("description"));
                receipt.setPrice(rs.getDouble("price"));
                receipt.setTimestamp(LocalDateTime.ofEpochSecond(rs.getLong("datetime"), 0, ZoneOffset.ofHours(0)));

                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result(gson.toJson(receipt));
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Not a valid index: " + ctx.pathParam("index") + "\"}");
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });
        server.get("/total", ctx -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD"))) {
                PreparedStatement statement = conn.prepareStatement("select * from receipt;");
                ResultSet rs = statement.executeQuery();
                List<Receipt> items = new ArrayList<>();
                Receipt receipt = null;
                while (rs.next()) {
                    receipt = new Receipt();
                    receipt.setId(rs.getInt("id"));
                    receipt.setDescription(rs.getString("description"));
                    receipt.setPrice(rs.getDouble("price"));
                    receipt.setTimestamp(LocalDateTime.ofEpochSecond(rs.getLong("datetime"), 0, ZoneOffset.ofHours(0)));
                    items.add(receipt);
                }
                double sum = items.stream().map(Receipt::getPrice).reduce(0.0, Double::sum);
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"total\": " + sum + "}");
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });
        server.delete("/items", ctx-> {

            int from = 0;
            int to = 0;
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD"))) {
                PreparedStatement statement = null;
                String fromParam = ctx.queryParam("from");
                String toParam = ctx.queryParam("to");

                if (fromParam == null || toParam == null) {
                    statement = conn.prepareStatement("delete from receipt where true;");
                    int updated = statement.executeUpdate();
                    if (updated == 0) {
                        throw new SQLException("Update failed. Check that there were items entered.");
                    }
                    ctx.status(200);
                    ctx.contentType("text/JSON");
                    ctx.result("{\"result\": \"All items cleared.\"}");
                } else {
                    from = Integer.parseInt(fromParam)-1;
                    to = Integer.parseInt(toParam)-1;
                    int max = Math.max(from, to);
                    int min = Math.min(from, to);

                    statement = conn.prepareStatement("delete from receipt where id between ? and ?;");
                    statement.setInt(1, from);
                    statement.setInt(2, to);

                    int updated = statement.executeUpdate();

                    if (updated == 0) {
                        throw new SQLException("Update failed. Check that there were items entered in the given range: " + from + ":" + to);
                    }

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
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });
        server.delete("/items/{index}", ctx -> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD"))) {
                int index = Integer.parseInt(ctx.pathParam("index"));
                PreparedStatement statement = conn.prepareStatement("delete from receipt where id = ?;");
                statement.setInt(1, index);
                int updated = statement.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Deletion from receipts failed. Check to make sure a receipt exists with id=" + index);
                }
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"removed\": " + index + "}");
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Not a valid index: " + ctx.pathParam("index") + "\"}");
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });
        server.post("/items", ctx-> {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD"))) {
                Receipt[] newItems = gson.fromJson(ctx.body(), Receipt[].class);
                PreparedStatement statement = conn.prepareStatement("insert into receipt (description, price, datetime) values (?,?,?);", Statement.RETURN_GENERATED_KEYS);
                for(Receipt r : newItems) {
                    statement.setString(1, r.getDescription());
                    statement.setDouble(2, r.getPrice());
                    statement.setLong(3, r.getTimestamp().atZone(ZoneId.systemDefault()).toEpochSecond());
                    statement.addBatch();
                }
                int[] updated = statement.executeBatch();
                int totalUpdated = Arrays.stream(updated).reduce(0, Integer::sum);
                if (totalUpdated < newItems.length) {
                    throw new SQLException("Failed inserting items. Successfully inserted: " + updated + " of " + newItems.length);
                }
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"added\" : \"" + newItems.length + "\"}");
            } catch (JsonSyntaxException e) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Error parsing item(s).\"}");
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });
        server.patch("/items/{index}", ctx->{
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/receipts",
                    System.getenv("POSTGRES_USERNAME"),
                    System.getenv("POSTGRES_PASSWORD"))){
                Receipt item = gson.fromJson(ctx.body(), Receipt.class);
                int index = Integer.parseInt(ctx.pathParam("index"));
                PreparedStatement statement = conn.prepareStatement("update receipt set description = ?, price = ?, datetime = ? where id = ?;");
                statement.setString(1, item.getDescription());
                statement.setDouble(2, item.getPrice());
                statement.setLong(3, item.getTimestamp().atZone(ZoneId.systemDefault()).toEpochSecond());
                statement.setInt(4, index);
                int updated = statement.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Failed updating item at index=" + index + " with values: " + item);
                }
                ctx.status(200);
                ctx.contentType("text/JSON");
                ctx.result("{\"updated\": " + gson.toJson(item) + "}");
            } catch (JsonSyntaxException e) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Error parsing item.\"}");
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"Not a valid index: " + ctx.pathParam("index") + "\"}");
            } catch (SQLException se) {
                ctx.status(400);
                ctx.contentType("text/JSON");
                ctx.result("{\"error\": \"" + se.getMessage() + "\"}");
            }
        });


        server.start(7070);
    }
}
