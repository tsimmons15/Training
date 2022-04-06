package dev.simmons.daotests;

import dev.simmons.data.BookDAO;
import dev.simmons.data.BookDAOPostgres;
import dev.simmons.entities.Book;
import dev.simmons.utilities.lists.List;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Period;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDAOTests {
    static BookDAO bookDao = null;
    static Book testBook = null;
    @Test
    @Order(1)
    public void createABook() {
        bookDao = new BookDAOPostgres();
        Book book = new Book();
        book.setId(0);
        book.setTitle("The Prince");
        book.setAuthor("Machiavelli");
        LocalDate returnDate = LocalDate.now().plus(Period.of(0,0,14));
        book.setReturnDate(returnDate.toEpochDay());

        BookDAOTests.testBook = bookDao.createBook(book);
        Assertions.assertTrue(testBook.getId() > 0);
    }

    @Test
    @Order(2)
    public void getBookById() {
        bookDao = new BookDAOPostgres();
        Book retrieved = bookDao.retrieveBook(BookDAOTests.testBook.getId());
        Assertions.assertEquals(BookDAOTests.testBook.getTitle(), retrieved.getTitle());
    }

    @Test
    @Order(3)
    public void updateTestBook() {
        BookDAOTests.testBook.setTitle("Il Principe");
        bookDao.updateBook(testBook);
        Book retrievedBook = bookDao.retrieveBook(testBook.getId());
        Assertions.assertEquals("Il Principe", retrievedBook.getTitle());
    }

    @Test
    @Order(4)
    public void deleteBook() {
        boolean result = bookDao.deleteBook(BookDAOTests.testBook);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(5)
    public void manyBooks() {
        for(int i = 0; i < 5; i++) {
            bookDao.createBook(new Book(0, "Test", "Tester", 0));
        }
        List<Book> books = bookDao.getAllBooks();
        Assertions.assertTrue(books.length() > 5);

        for(int i = 0; i < books.length(); i++) {
            bookDao.deleteBook(books.get(i));
        }
    }
}
