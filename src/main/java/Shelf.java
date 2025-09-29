import Utilities.Code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Shelf {
    public static int SHELF_NUMBER_ = 0;
    public static int SUBJECT_ = 1;

    private HashMap<Book, Integer> books;
    private int shelfNumber;
    private String subject;

    public Code addBook(Book book) {
        if(book.getSubject() != subject) {
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }
        books.put(book, books.get(book) + 1);
        return Code.SUCCESS;
    }

    public int getBookCount() {
        return books.size();
    }

    public Code removeBook(Book book) {
        if(!books.containsKey(book)) {
            System.out.println(book.getTitle() + " is not on the shelf " + subject);
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        if(books.get(book) == 0) {
            System.out.println("No copies of " + book.getTitle() + " remain on the shelf " + subject);
            return  Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        books.put(book, books.get(book)-1);
        return Code.SUCCESS;
    }

    public String listBooks() {
        String out = "";
        out += getBookCount() + " book";
        if(getBookCount() != 1)
            out += "s";
        out += " on shelf: " + this.shelfNumber + " : " + this.subject + "\n";
        for(Map.Entry<Book, Integer> entry : books.entrySet()) {
            out += entry.getKey().toString() + " " + entry.getValue() + "\n";
        }

        return out;
    }

    public String toString() {
        return shelfNumber + " : " + subject;
    }

    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return shelfNumber == shelf.shelfNumber && Objects.equals(subject, shelf.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelfNumber, subject);
    }
}
