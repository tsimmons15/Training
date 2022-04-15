package dev.simmons.data;

import dev.simmons.entities.Book;
import dev.simmons.utilities.lists.List;

public interface BookDAO {
    Book createBook(Book book);
    Book retrieveBook(int id);
    Book updateBook(Book book);
    boolean deleteBook(Book book);
    boolean deleteBook(int id);
    List<Book> getAllBooks();
}
