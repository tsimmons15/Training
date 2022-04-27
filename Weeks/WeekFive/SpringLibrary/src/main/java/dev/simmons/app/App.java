package dev.simmons.app;

import dev.simmons.configs.BookConfig;
import dev.simmons.data.BookDAOPostgres;
import dev.simmons.entities.Book;
import dev.simmons.services.BookService;
import dev.simmons.services.Library;
import dev.simmons.utilities.lists.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    // I should make a private static BookService as well, but
    // the ugly calls to appContext.getBean(...) demonstrate the true value
    // to the appContext. Now, I can get copies of the Bean BookService whenever
    // I need it, such as in larger, distributed projects.
    private static ApplicationContext appContext;
    public static void main(String[] args) {
        appContext = new AnnotationConfigApplicationContext(BookConfig.class);
        Scanner in = new Scanner(System.in);
        int input = -1;
        do {
            System.out.println("Welcome to the Library!");
            System.out.println("1.) Register New Book");
            System.out.println("2.) Checkout a Book");
            System.out.println("3.) Checkin a Book");
            System.out.println("4.) View All Books");
            System.out.println("5.) Exit");
            System.out.print("> ");
            try {
                input = in.nextInt();
                handleInput(input, in);
            } catch (InputMismatchException ime) {
                String mismatch = in.nextLine();
                System.out.println("Invalid input: " + mismatch);
                System.out.println("Please enter from the choices given.");
            }
        } while (input != 5);

        System.out.println("Thank you for using this terminal!");
    }

    public static void handleInput(int index, Scanner in) {
        switch(index) {
            case 1:
                registerBook(in);
                break;
            case 2:
                checkoutBook(in);
                break;
            case 3:
                checkinBook(in);
                break;
            case 4:
                viewAll(in);
                break;
            case 5:

                break;
        }
    }

    public static void registerBook(Scanner in) {
        Book book = new Book();
        System.out.print("New book author: ");
        String author = in.nextLine();
        System.out.print("New book title: ");
        String title = in.nextLine();
        book.setAuthor(author);
        book.setTitle(title);
        System.out.println(book);
        System.out.println("Correct? (Y/N)");
        String correct = in.nextLine().toLowerCase();
        if (correct.startsWith("y")) {
            appContext.getBean("Service", BookService.class).registerBook(book);
        }
    }

    public static void checkoutBook(Scanner in) {
        List<Book> books = appContext.getBean("Service", BookService.class).libraryCatalog();
        if (books.length() == 0) {
            System.out.println("Sorry, no books available.");
            return;
        }

        Book curr = null;
        int input = 0;
        do {
            for(int i = 0; i < books.length(); i++) {
                curr = books.get(i);
                if (curr.getReturnDate() == 0) {
                    String out = String.format("%d.) %s by %s", i + 1, curr.getTitle(), curr.getAuthor());
                    System.out.println(out);
                } else {
                    books.remove(i);
                    i--;
                }
            }
            System.out.println((books.length() + 1) + ".) Repeat");
            System.out.println((books.length() + 2) + ".) Exit");
            System.out.print("> ");
            try {
                input = in.nextInt() - 1;
            } catch (InputMismatchException ime) {
                String line = in.nextLine();
                System.out.println("Invalid input: " + line);
                System.out.println("Please enter one of the book entries above.");
            }
        } while (input == books.length());

        if (input < books.length()) {
            appContext.getBean("Service", BookService.class).checkOut(books.get(input));
        }
    }

    public static void checkinBook(Scanner in) {
        List<Book> books = appContext.getBean("Service", BookService.class).libraryCatalog();
        if (books.length() == 0) {
            System.out.println("Sorry, no books available.");
            return;
        }

        Book curr = null;
        int input = -1;
        do {
            for (int i = 0; i < books.length(); i++) {
                curr = books.get(i);
                if (curr.getReturnDate() == 0) {
                    books.remove(i);
                    i--;
                } else {
                    String out = String.format("%d.) %s by %s", i + 1, curr.getTitle(), curr.getAuthor());
                    System.out.println(out);
                }
            }
            System.out.println((books.length() + 1) + ".) Repeat");
            System.out.println((books.length() + 2) + ".) Exit");
            System.out.print("> ");
            try {
                input = in.nextInt() - 1;
            } catch (InputMismatchException ime) {
                String line = in.nextLine();
                System.out.println("Invalid input: " + line);
                System.out.println("Please enter one of the book entries above.");
            }
        } while (input == books.length());

        if (input < books.length()) {
            appContext.getBean("Service", BookService.class).checkIn(books.get(input));
        }
    }

    public static void viewAll(Scanner in) {
        List<Book> books = appContext.getBean("Service", BookService.class).libraryCatalog();
        if (books.length() == 0) {
            System.out.println("Sorry, no books available.");
            return;
        }

        Book curr = null;
        for(int i = 0; i < books.length(); i++) {
            curr = books.get(i);
            String out = String.format("%d.) %s by %s (%s)", i + 1,
                    curr.getTitle(), curr.getAuthor(),
                    (curr.getReturnDate() == 0) ? "Available" : "Unavailable");
            System.out.println(out);
        }
        System.out.println("Continue? [Enter]");
        in.nextLine();
    }
}
