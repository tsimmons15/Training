package dev.simmons.services;

import dev.simmons.entities.Book;
import dev.simmons.utilities.lists.List;

public interface BookService {
    Book registerBook(Book book);
    Book checkIn(Book book);
    Book checkOut(Book book);
    List<Book> libraryCatalog();
}
