import java.util.HashMap;
import java.util.List;

public class Library {
    public static int LENDING_LIMIT;

    private HashMap<Book, Integer> books;
    private int libraryCard;
    private String name;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;
}
