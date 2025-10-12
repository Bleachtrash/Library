import Utilities.Code;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static int LENDING_LIMIT;

    private HashMap<Book, Integer> books;
    private int libraryCard;
    private String name;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;

    public Library(String str) {
        //todo
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
    public int convertInt(String str, Code code) {
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
    public Code init(String str) {
        //todo
    }
    private Code initBooks(int num, Scanner sc)
    {
        //todo
    }
    private Code initReader(int num, Scanner sc) {
        //todo
    }
    private Code initShelves(int num, Scanner sc) {
        //todo
    }
    public int listBook() {
        //todo
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
