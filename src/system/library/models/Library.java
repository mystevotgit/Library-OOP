package system.library.models;

import java.util.*;

public class Library {
    private String name;
    private Liberian liberian;
    private Map<String, Integer> books;
    private Map<String, Queue<String>> bookRequests;
    private Map<String, List<Person>> bookHolders;

    public Library(String libraryName, Liberian liberian) {
        this.name = libraryName;
        this.liberian = liberian;
        this.books = new HashMap<>();
        this.bookRequests = new HashMap<String, Queue<String>>();
        this.bookHolders = new HashMap<>();
    }

    public <K, V> Integer addBook(String book, Integer copies) {
        if (books.containsKey(book)) {
            books.replace(book, books.get(book) + copies);
        } else {
            books.put(book, copies);
        }
        return books.get(book);
    }

    public Map<String, Integer> getBooks() {
        return books;
    }

    public <M> Set<String> getBooksBorrowed(Map<String, List<Person>> bookHolders) {
        return bookHolders.keySet();
    }

    public Map<String, List<Person>> getBookHolders() {
        return bookHolders;
    }

    public <String> Queue<String> addBookRequest(String book, String person){
//        Map <String, String> request = new HashMap<>();
//        request.put(person, type);
        if (bookRequests.containsKey(book)) {
            if (bookRequests.get(book).contains(person)) {
                System.out.println("This person is already on the request queue for this book");
            }
            else {
                bookRequests.get(book).add((java.lang.String) person);
            }
        } else {
            Queue<String> requestQueue = new LinkedList<>();
            requestQueue.add(person);
            bookRequests.put((java.lang.String) book, (Queue<java.lang.String>) requestQueue);
        }

        return (Queue<String>) bookRequests.get(book);
    }

    public Map<String, Queue<String>> getBookRequests() {
        return bookRequests;
    }

}
