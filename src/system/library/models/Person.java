package system.library.models;

import java.util.HashSet;
import java.util.Set;

public class Person{
    private int id;
    private String name;
    private String type;
    private int priority;
    private int year_of_birth;
    private Set<String> booksBorrowed;

    /**
     * This is the Constructor of the Person's class.
     * It can be used to create a person.
     * @param id
     * @param name
     * @param type
     * @param priority
     * @param year_of_birth
     */

    public Person(int id, String name, String type, int priority, int year_of_birth) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.year_of_birth = year_of_birth;
        this.booksBorrowed = new HashSet<String>();
    }

    /**
     * This method updates users borrowedbooks.
     * @param book
     * @param action
     * @param <String>
     * @return
     */

    public Set<String> updateBooksBorrowed(String book, boolean action) {
        if (action) {
            this.booksBorrowed.add(book);
        } else {
            this.booksBorrowed.remove(book);
        }
        return this.booksBorrowed;
    }

    // Getters

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPriority() {
        return this.priority;
    }

    public Set<String> getBooksBorrowed() {
        return booksBorrowed;
    }
}
