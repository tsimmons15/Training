package dev.simmons.data;

import dev.simmons.entities.Book;
import dev.simmons.utilities.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.*;

public class BookDAOPostgres implements BookDAO {

    @Override
    public Book createBook(Book book) {
        try {
            Connection connection = ConnectionUtil.createConnection();
            String sql = "insert into book (author, title, return_date) values (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setLong(3, book.getReturnDate());

            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt("id");
            book.setId(id);

            return book;
        } catch (SQLException se) {
            return book;
        }
    }

    @Override
    public Book retrieveBook(int id) {
        try {
            Connection connection = ConnectionUtil.createConnection();
            String sql = "select id, title, author, return_date from book where id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            rs.next();

            Book retrieved = new Book();
            retrieved.setId(rs.getInt("id"));
            retrieved.setTitle(rs.getString("title"));
            retrieved.setAuthor(rs.getString("author"));
            retrieved.setReturnDate(rs.getLong("return_date"));

            return retrieved;
        } catch (SQLException se) {
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        try {
            Connection connection = ConnectionUtil.createConnection();
            String sql = "update book set title = ?, author = ?, return_date = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setLong(3, book.getReturnDate());
            statement.setInt(4, book.getId());

            int updated = statement.executeUpdate();

            if (updated == 0) {
                return null;
            }

            return book;
        } catch (SQLException se) {
            return null;
        }
    }

    @Override
    public boolean deleteBook(Book book) {
        return deleteBook(book.getId());
    }

    @Override
    public boolean deleteBook(int id) {
        try {
            Connection connection = ConnectionUtil.createConnection();
            String sql = "delete from book where id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int updated = statement.executeUpdate();

            return updated == 1;
        } catch (SQLException se) {
            return false;
        }
    }
}
