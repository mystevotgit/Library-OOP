package system.library.models;

import java.util.*;

public class Library {

    // Fields
    private String name;
    private Liberian liberian;
    private Map<String, Integer> books;
    private Map<String, Queue<Person>> bookRequests;
    private Map<String, List<Person>> bookHolders;

    /**
     * This is the library constructor.
     * It can be used to create a library.
     * @param libraryName
     * @param liberian
     */

    public Library(String libraryName, Liberian liberian) {
        this.name = libraryName;
        this.liberian = liberian;
        this.books = new HashMap<>();
        this.bookRequests = new HashMap<String, Queue<Person>>();
        this.bookHolders = new HashMap<>();
    }

    /**
     * This method can be used to add copies of book to the library.
     * @param book
     * @param copies
     * @param <K>
     * @param <V>
     * @return
     */

    public <K, V> Integer addBook(String book, Integer copies) {
        if (books.containsKey(book)) {
            books.replace(book, books.get(book) + copies);
        } else {
            books.put(book, copies);
        }
        return books.get(book);
    }


    /**
     * This method returns the books borrowed already.
     * @param <M>
     * @param bookHolders
     * @return
     */

    public <M> Set<String> getBooksBorrowed(Map<String, List<Person>> bookHolders) {
        Set<String> keys = bookHolders.keySet();
        for  (String key : keys) {
            if (bookHolders.get(key).isEmpty()) {
                bookHolders.remove(key);
            }
        }
//        System.out.println(bookHolders.keySet());
        return bookHolders.keySet();
    }

    /**
     * This method allows the libray to lend out book acording to
     * first come, first serve.
     * @param book
     * @param requests
     * @param <String>
     * @param <Queue>
     * @return
     */

    public <String, Queue> List<String> lendBook(String book, Queue requests) {
        Integer availableCopies = 0;
        if (books.containsKey(book)) {
            availableCopies = books.get(book);
        }
        if (availableCopies == 0) {
            System.out.println("oops! the requested book is not available");
        } else {
            if (!bookHolders.containsKey(book)) {
                bookHolders.put((java.lang.String) book, new ArrayList<>());
            }
            while (books.get(book) > 0) {
                if (bookRequests.get(book).isEmpty()) {
                    break;
                }
                var bookHolder = bookRequests.get(book).remove();
                books.replace((java.lang.String) book, books.get(book) - 1);
//                System.out.println(holder);
                bookHolders.get(book).add(bookHolder);
//                System.out.println(bookRequests.get(book));
            }
        }
//        System.out.println(bookHolders.get(book));
        return (List<String>) bookHolders.get(book);
    }

    /**
     *This method is another implementation of lendBook that allows teachers to be prioritized
     * over senior students and senior students over junior students.
     * @param book
     * @param requests
     * @param <String>
     * @param <Queue>
     * @return
     */

    public <String, Object, Boolean> List<Person> lendBook(String book, List<Person> requests, Boolean comparator) {
        // Creating a Priority Queue.
        PriorityQueue<Person> queue = new PriorityQueue<Person>(10, new Comparator<Person>() {
            public int compare(Person person1, Person person2) {
                if (person1.getPriority() < person2.getPriority())
                    return -1;
                if (person1.getPriority() > person2.getPriority())
                    return 1;
                return 0;
            }
        });

        queue.addAll(requests);
        List<Person> result = new ArrayList<>();
        while(queue.iterator().hasNext()) {
            System.out.println(queue.element().getName());
            result.add(queue.remove());
        }
        return result;
    }

    public Map<String, Integer> retrieveBook(String book, String person) {
        if (bookHolders.get(book).contains(person)) {
            this.addBook(book, 1);
//            System.out.println(bookHolders.get(book));
            bookHolders.get(book).remove(person);
//            System.out.println(bookHolders.get(book));
        }
        return this.books;
    }

    /**
     * This is method can be used to add a book request to the
     * queue of those requesting for a particular book.
     * @param book
     * @param person
     * @param <String>
     * @return
     */

    public <String> Queue<Person> addBookRequest(String book, Person person){
//        Map <String, String> request = new HashMap<>();
//        request.put(person, type);
        if (bookRequests.containsKey(book)) {
            if (bookRequests.get(book).contains(person)) {
                System.out.println("This person is already on the request queue for this book");
            }
            else {
                bookRequests.get(book).add((system.library.models.Person) person);
            }
        } else {
            Queue<Person> requestQueue = new LinkedList<>();
            requestQueue.add((Person) person);
            bookRequests.put((java.lang.String) book, (Queue<system.library.models.Person>) requestQueue);
        }

        return (Queue<Person>) bookRequests.get(book);
    }


    // Getters
    public String getName() {
        return name;
    }

    public Map<String, Integer> getBooks() {
        return books;
    }

    public Map<String, List<Person>> getBookHolders() {
        return bookHolders;
    }

    public Map<String, Queue<Person>> getBookRequests() {
        return bookRequests;
    }

}
