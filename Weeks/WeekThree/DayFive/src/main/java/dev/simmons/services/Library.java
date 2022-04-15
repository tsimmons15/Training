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

    @Override
    public Book checkInById(int id) {
        Book book = this.bookDao.retrieveBook(id);
        return (book == null) ? null : checkIn(book);
    }


    private static final long twoWeeks = 1209600000; // Two weeks in ms
    @Override
    public Book checkOut(Book book) {
        book.setReturnDate(System.currentTimeMillis() + twoWeeks);
        return this.bookDao.updateBook(book);
    }

    @Override
    public Book checkOutById(int id) {
        Book newBook = retrieveById(id);
        return (newBook == null) ? null : checkOut(newBook);
    }

    @Override
    public Book retrieveById(int id) {
        return this.bookDao.retrieveBook(id);
    }


    @Override
    public List<Book> libraryCatalog() {
        return this.bookDao.getAllBooks();
    }

    @Override
    public boolean deleteBookById(int id) {
        return this.bookDao.deleteBook(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return this.bookDao.updateBook(book) != null;
    }
}
