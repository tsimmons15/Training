package dev.simmons.services;

import dev.simmons.data.BookDAO;
import dev.simmons.entities.Book;
import dev.simmons.services.BookService;
import dev.simmons.utilities.lists.List;

import java.time.LocalDate;
import java.time.Period;

public class Library implements BookService {

    private BookDAO bookDao;
    public Library(BookDAO dao) {
        this.bookDao = dao;
    }

    @Override
    public Book registerBook(Book book) {
        return this.bookDao.createBook(book);
    }

    @Override
    public Book checkIn(Book book) {
        book.setReturnDate(0);
        return this.bookDao.updateBook(book);
    }


    private static final long twoWeeks = 1209600000; // Two weeks in ms
    @Override
    public Book checkOut(Book book) {
        book.setReturnDate(System.currentTimeMillis() + twoWeeks);
        return this.bookDao.updateBook(book);
    }


    @Override
    public List<Book> libraryCatalog() {
        return this.bookDao.getAllBooks();
    }
}
