import java.time.LocalDate;
import java.util.Objects;

public class Book {
    public static  int ISBN_;
    public static int TITLE_;
    public static int SUBJECT_;
    public static int PAGE_COUNT_;
    public static int AUTHOR_;
    public static int DUE_DATE_;

    private String author;
    private LocalDate dueDate;
    private String isbn;
    private int pageCount;
    private String subject;
    private String title;

    public Book(String author, String isbn, String subject, int pageCount, String title, LocalDate dueDate) {
        this.author = author;
        this.isbn = isbn;
        this.subject = subject;
        this.pageCount = pageCount;
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString()
    {
        return title + " by " + author + " ISBN: " + isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(subject, book.subject) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, isbn, pageCount, subject, title);
    }
}
