import Utilities.Code;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Library {
    public static int LENDING_LIMIT = 5;

    private HashMap<Book, Integer> books = new HashMap<>();
    private int libraryCard;
    private String name;
    private List<Reader> readers = new ArrayList<>();
    private HashMap<String, Shelf> shelves = new HashMap<>();

    public Library(String name) {
        this.name = name;
    }
    public Code addBook(Book book) {
        if(books.containsKey(book)){
            books.put(book, books.get(book)+1);
            System.out.println(books.get(book) + " copies of " book.getTitle() + " in the stacks.");
        }
        else{
            books.put(book, 1);
            System.out.println(book.getTitle() + " added to the stacks.");
        }
        for(Map.Entry<String, Shelf> entry : shelves.entrySet()){
            if(entry.getValue().getSubject().equals(book.getSubject())){
                entry.getValue().addBook(book);
                return Code.SUCCESS;
            }
        }

        System.out.println("No shelf for " + book.getSubject() + " books");
        return Code.SHELF_EXISTS_ERROR;
    }
    private Code AddBookToShelf(Book book, Shelf shelf) {
        return Code.SUCCESS;
    }
    public Code addReader(Reader reader) {
        return Code.SUCCESS;
    }
    public Code addShelf(Shelf shelf) {
        return Code.SUCCESS;
    }
    public Code addShelf(String str) {
        return Code.SUCCESS;
    }
    public Code checkOutBook(Reader reader, Book book) {
        return Code.SUCCESS;
    }
    public static LocalDate convertDate(String date, Code errorCode) {
        if(date.equals("0000")){
            return LocalDate.of(1970, 1, 1);
        }
        String[] splitString = date.split("-");
        if(splitString.length != 3)
        {
            System.out.println("ERROR: date conversion error, could not parse " + date);
            System.out.println("Using default date 01-jan-1970");
            return LocalDate.of(1970, 1, 1);
        }
        for(String str : splitString){
            if(Integer.parseInt(str) < 0){
                System.out.println("Error converting date: Year " + splitString[0]);
                System.out.println("Error converting date: Month " + splitString[1]);
                System.out.println("Error converting date: Day " + splitString[3]);
                System.out.println("Using default date 01-jan-1970");
                return LocalDate.of(1970, 1, 1);
            }
        }

        LocalDate res = LocalDate.of(1970, 1, 1);
        try {
            res = LocalDate.parse(date);
        } catch (NumberFormatException e){
            convertInt(date, errorCode);
        }

        return res;
    }
    public static int convertInt(String recordCountString, Code code) {
        int res = 0;
        try {
            res = Integer.parseInt(recordCountString);
        } catch(NumberFormatException e) {
            System.out.println("Values which caused the error: " + recordCountString);

            switch (code) {
                case Code.BOOK_COUNT_ERROR:
                    System.out.println("Error: Could not read the number of books");
                    break;
                case Code.PAGE_COUNT_ERROR:
                    System.out.println("Error: Could not parse page count");
                    break;
                case Code.DATE_CONVERSION_ERROR:
                    System.out.println("Error: Could not parse date component");
                    break;
                default:
                    System.out.println("Error: Unknown conversion error");
                    break;
            }
            return code.getCode();
        }
        return res;
    }
    private  Code errorCode(int num) {
        return Code.SUCCESS;
    }
    public Book getBookByISBN(String ISBN) {
        return new Book("0", "p", "l", 1, "p", LocalDate.of(1970, 1, 1));
    }
    public int getLibraryCardNumber() {
        return this.libraryCard;
    }
    public String getName() {
        return this.name;
    }
    public Reader getReaderByCard(int cardNumber) {
        return new Reader(1, "p", "1");
    }
    public Shelf getShelf(String str) {
        return new Shelf();
    }
    public Shelf getShelf(Integer number) {
        return new Shelf();
    }
    Code codeFromCount(int count) {
        for(Code code : Code.values()) {
            if(code.getCode() == count)
                return code;
        }
        return Code.UNKNOWN_ERROR;
    }
    public Code init(String fileName) {
        File file = new File(fileName);
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException fnf) {
            return Code.FILE_NOT_FOUND_ERROR;
        }
        int count = Integer.parseInt(sc.nextLine());
        if(count < 0) {
            return codeFromCount(count);
        }
        initBooks(count, sc);
        listBooks();

        count = Integer.parseInt(sc.nextLine());
        if(count < 0) {
            return codeFromCount(count);
        }
        initShelves(count, sc);
        listShelves();

        count = Integer.parseInt(sc.nextLine());
        if(count < 0) {
            return codeFromCount(count);
        }
        initReader(count, sc);
        listReaders();

        return Code.SUCCESS;
    }
    private Code initBooks(int bookCount, Scanner sc) {
        if(bookCount < 1){
            return Code.LIBRARY_ERROR;
        }

        for(int i = 0; i < bookCount; i++) {
            String[] fields = sc.nextLine().split(",");
            if(fields.length < 6) {
                return Code.BOOK_RECORD_COUNT_ERROR;
            }
            if(convertInt(fields[4], Code.PAGE_COUNT_ERROR) <= 0)
                return Code.PAGE_COUNT_ERROR;
            if(convertDate(fields[5], Code.DATE_CONVERSION_ERROR) == null)
                return  Code.DATE_CONVERSION_ERROR;

            Book book = new Book(fields[0], fields[1], fields[2], convertInt(fields[3], Code.PAGE_COUNT_ERROR), fields[4], convertDate(fields[5], Code.DATE_CONVERSION_ERROR));
            addBook(book);
        }

        return Code.SUCCESS;
    }
    private Code initReader(int readerCount, Scanner sc) {
        if(readerCount < 1)
            return Code.READER_COUNT_ERROR;
        for(int i = 0; i < readerCount; i++){
            String[] fields = sc.nextLine().split(",");
            int numberBooks = convertInt(fields[3], Code.BOOK_COUNT_ERROR);
            Reader reader = new Reader(convertInt(fields[0], Code.READER_CARD_NUMBER_ERROR), fields[1], fields[2]);
            for(int j = 0; j < numberBooks; j++){
                Book book = getBookByISBN(fields[j*2 +3 +1]);
                book.setDueDate(convertDate(fields[j*2 +3 +2], Code.DATE_CONVERSION_ERROR));
                checkOutBook(reader, book);
            }
        }

        return Code.SUCCESS;
    }
    private Code initShelves(int shelfCount, Scanner sc) {
        if(shelfCount < 1)
            return Code.SHELF_COUNT_ERROR;
        for(int i = 0; i < shelfCount; i++)
        {
            String[] fields = sc.nextLine().split(",");
            if (fields.length != 2)
                return Code.SHELF_COUNT_ERROR;
            if(convertInt(fields[0], Code.SHELF_NUMBER_PARSE_ERROR) <= 0)
                return Code.SHELF_NUMBER_PARSE_ERROR;

            addShelf(new Shelf(convertInt(fields[0], Code.SHELF_COUNT_ERROR), fields[1]));
        }
        if(shelves.size() != shelfCount){
            System.out.println("Number of shelves doesn't match expected");
            return Code.SHELF_COUNT_ERROR;
        }
        return Code.SUCCESS;
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
        return -1;
    }
    public int listReaders(boolean bool) {
        return -1;
    }
    public int listShelves() {
        return listShelves(false);
    }
    public int listShelves(boolean showBooks) {
        int numShelves = 0;
        for(Map.Entry<String, Shelf> entry : shelves.entrySet()) {
            numShelves ++;
            if(showBooks) {
                entry.getValue().listBooks();
            }
            else {
                System.out.println(entry.getValue().toString());
            }
        }
        return  numShelves;
    }
    public Code removeReader(Reader reader) {
        return Code.SUCCESS;
    }
    public Code returnBook(Reader reader, Book book) {
        return Code.SUCCESS;
    }
    public Code returnBook(Book book) {
        return Code.SUCCESS;
    }

}
