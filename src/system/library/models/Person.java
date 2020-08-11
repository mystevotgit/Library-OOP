package system.library.models;

import java.util.HashSet;
import java.util.Set;

public class Person{
    private int id;
    private String name;
    private String type;
    private int year_of_birth;
    private Set<String> booksBorrowed;

    public Person(int id, String name, String type, int year_of_birth) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year_of_birth = year_of_birth;
        this.booksBorrowed = new HashSet<String>();
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Set<String> getBooksBorrowed() {
        return booksBorrowed;
    }
}
