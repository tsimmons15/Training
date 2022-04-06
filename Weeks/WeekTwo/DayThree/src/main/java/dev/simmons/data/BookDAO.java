package dev.simmons.data;

import dev.simmons.entities.Book;

public interface BookDAO {
    Book createBook(Book book);
    Book retrieveBook(int id);
    Book updateBook(Book book);
    boolean deleteBook(Book book);
    boolean deleteBook(int id);
}
