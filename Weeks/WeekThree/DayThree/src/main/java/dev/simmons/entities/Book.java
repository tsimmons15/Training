package dev.simmons.entities;

public class Book implements Comparable<Book> {
    private int id;
    private String title;
    private String author;
    private long returnDate;



    public Book() {

    }

    public Book(int id, String title, String author, long returnDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(long returnDate) {
        this.returnDate = returnDate;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Book)) {
            return false;
        }

        Book other = (Book)o;
        return this.id == other.getId();
    }

    @Override
    public String toString() {
        return "Book{" +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return this.author.compareTo(o.getAuthor());
    }
}
