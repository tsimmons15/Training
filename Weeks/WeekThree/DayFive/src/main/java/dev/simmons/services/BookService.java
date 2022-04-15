package dev.simmons.services;

import dev.simmons.entities.Book;
import dev.simmons.utilities.lists.List;

public interface BookService {
    Book registerBook(Book book);
    Book checkIn(Book book);
    Book checkInById(int id);
    Book checkOut(Book book);
    Book checkOutById(int id);
    Book retrieveById(int id);
    List<Book> libraryCatalog();
    boolean deleteBookById(int id);
    boolean updateBook(Book book);
}
