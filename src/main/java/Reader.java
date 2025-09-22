import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import Utilities.Code;

public class Reader {
    public static int CARD_NUMBER_;
    public static int NAME_;
    public static int PHONE_;
    public static int BOOK_COUNT_;
    public static int BOOK_START_;

    private int cardNumber;
    private String name;
    private String phone;
    private List<Book> books;

    public Reader(int cardNumber, String name, String phone) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.phone = phone;
        this.books = new ArrayList<Book>();
    }

    public Code addBook(Book book) {
        if(hasBook(book))
        {
            return Code.BOOK_ALREADY_CHECKED_OUT_ERROR;
        }
        books.add(book);
        return Code.SUCCESS;
    }

    public Code removeBook(Book book) {
        if(!hasBook(book))
        {
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }
        books.remove(book);
        return Code.SUCCESS;
    }

    public boolean hasBook(Book book) {
        if(book == null)
        {
            return false;
        }
        return books.contains(book);
    }

    public int getBookCount() {
        return books.size();
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String toString()
    {
        return name + " (#" + cardNumber + ") has checked out {" + books.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return cardNumber == reader.cardNumber && Objects.equals(name, reader.name) && Objects.equals(phone, reader.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, name, phone);
    }
}
