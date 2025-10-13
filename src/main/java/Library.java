import Utilities.Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Library {
    public static int LENDING_LIMIT = 5;

    private HashMap<Book, Integer> books;
    private int libraryCard;
    private String name;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;

    public Library(String name) {
        this.name = name;
    }
    public Code addBook(Book book) {
        //todo
    }
    private Code AddBookToShelf(Book book, Shelf shelf) {
        //todo
    }
    public Code addReader(Reader reader) {
        //todo
    }
    public Code addShelf(Shelf shelf) {
        //todo
    }
    public Code addShelf(String str) {
        //todo
    }
    public Code checkOutBook(Reader reader, Book book) {
        //todo
    }
    public LocalDate convertDate(String str, Code code) {
        //todo
    }
    public int convertInt(String recordCountString, Code code) {
        //todo
    }
    private  Code errorCode(int) {
        //todo
    }
    public Book getBookByISBN(String ISBN) {
        //todo
    }
    public int getLibraryCardNumber() {
        return this.libraryCard;
    }
    public String getName() {
        return this.name;
    }
    public Reader getReaderByCard(int cardNumber) {
        //todo
    }
    public Shelf getShelf(String str) {
        //todo
    }
    public Shelf getShelf(Integer number) {
        //todo
    }
    public Code init(String fileName) {
        File file = new File(fileName);
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException fnf) {
            return Code.FILE_NOT_FOUND_ERROR;
        }
        int bookCount = Integer.parseInt(sc.nextLine());
        if(bookCount < 0) {
            for(Code code : Code.values()){
                if(code.getCode() == bookCount) {
                    return code;
                }
            }
            return Code.UNKNOWN_ERROR;
        }
        initBooks(bookCount, sc);
        listBooks();
        return Code.SUCCESS;
    }
    private Code initBooks(int bookCount, Scanner sc) {
        for(int i = 0; i < bookCount; i++) {
            String[] fields = sc.nextLine().split(",");
            Book book = new Book(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), fields[4], LocalDate.parse(fields[5]));
            if(!books.containsKey(book))
            {
                books.put(book, 0);
            }
            books.put(book, books.get(book)+1);
        }

        return Code.SUCCESS;
    }
    private Code initReader(int readerCount, Scanner sc) {
        //todo
    }
    private Code initShelves(int shelfCount, Scanner sc) {
        //todo
    }
    public int listBooks() {
        int totalBooks = 0;
        for(Map.Entry<Book, Integer> entry : books.entrySet()) {
            totalBooks += entry.getValue();
            System.out.println(entry.getValue() + " copies of " + entry.getKey().toString());
        }
        return totalBooks;
    }
    public int listReaders() {
        //todo
    }
    public int listReaders(boolean bool) {
        //todo
    }
    public int listShelves() {
        //todo
    }
    public int listShelves(boolean bool) {
        //todo
    }
    public Code removeReader(Reader reader) {
        //todo
    }
    public Code returnBook(Reader reader, Book book) {
        //todo
    }
    public Code returnBook(Book book) {
        //todo
    }

}
