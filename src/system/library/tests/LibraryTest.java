package system.library.tests;

import system.library.models.Liberian;
import system.library.models.Library;
import system.library.models.Person;

import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Liberian liberian = new Liberian(1, "John Doe", "liberian", 1990);

    Library library = new Library("Decagon Library", liberian);


    @org.junit.jupiter.api.Test
    void addBook() {
        library.addBook("The History of Tom Jones, a Foundling by Henry Fielding", 3);
        library.addBook("Pride and Prejudice by Jane Austen", 5);
        library.addBook("The Red and the Black by Stendhal", 2);
        library.addBook("David Copperfield by Charles Dickens", 3);
        library.addBook("In Search of Lost Time by Marcel Proust", 7);
        library.addBook("To The Lighthouse by Virginia Woolf", 10);
        library.addBook("The Sound and the Fury by William Faulkner", 6);
        library.addBook("Pale Fire by Vladimir Nabokov", 5);

        assertEquals(7, library.addBook("Harry Potter and the Sorcerer's Stone by J. K. Rowling", 7));
        assertEquals(9, library.addBook("Pale Fire by Vladimir Nabokov", 4));
        assertEquals(8, library.addBook("The Sound and the Fury by William Faulkner", 2));
        assertEquals(15, library.addBook("To The Lighthouse by Virginia Woolf", 5));
        assertEquals(10, library.addBook("In Search of Lost Time by Marcel Proust", 3));
        assertEquals(4, library.addBook("The Great Gatsby by F. Scott Fitzgerald", 4));

    }

    @org.junit.jupiter.api.Test
    void getBooks() {
        library.addBook("Pale Fire by Vladimir Nabokov", 5);
        assertEquals("{Pale Fire by Vladimir Nabokov=5}", library.getBooks().toString());

        library.addBook("The Odyssey by Homer", 3);
        assertEquals("{Pale Fire by Vladimir Nabokov=5, The Odyssey by Homer=3}", library.getBooks().toString());
    }

    @org.junit.jupiter.api.Test
    void getBooksBorrowed() {
    }

    @org.junit.jupiter.api.Test
    void getBookHolders() {
    }

    @org.junit.jupiter.api.Test
    void addBookRequest() {
        Map requests = library.getBookRequests();
        System.out.println(requests);
        Person student1 = new Person(101, "Samuel", "junior student", 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1985);

        Queue queue1 = library.addBookRequest("Pale Fire by Vladimir Nabokov", student1.getName());
        assertEquals(queue1, library.addBookRequest("Pale Fire by Vladimir Nabokov", student1.getName()));
        assertEquals(2, library.addBookRequest("Pale Fire by Vladimir Nabokov", student2.getName()).size());

        library.addBookRequest("Pale Fire by Vladimir Nabokov", student3.getName());
        assertEquals(4, library.addBookRequest("Pale Fire by Vladimir Nabokov", teacher1.getName()).size());
//        requests = library.getBookRequests();
//        System.out.println(requests.toString());

        Queue queue2 = library.addBookRequest("In Search of Lost Time by Marcel Proust", student1.getName());
        assertEquals(queue2, library.addBookRequest("In Search of Lost Time by Marcel Proust", student4.getName()));

        library.addBookRequest("David Copperfield by Charles Dickens", student3.getName());
        assertEquals(1, library.addBookRequest("Pride and Prejudice by Jane Austen", student2.getName()).size());

        assertEquals(3, library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2.getName()).size());
//        requests = library.getBookRequests();
//        System.out.println(requests.toString());
    }

    @org.junit.jupiter.api.Test
    void getBookRequests() {
        Map requests = library.getBookRequests();
        Person student1 = new Person(101, "Samuel", "junior student", 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1985);

        Queue queue2 = library.addBookRequest("In Search of Lost Time by Marcel Proust", student1.getName());
        assertEquals(queue2, library.addBookRequest("In Search of Lost Time by Marcel Proust", student4.getName()));

        library.addBookRequest("David Copperfield by Charles Dickens", student3.getName());
        assertEquals(1, library.addBookRequest("Pride and Prejudice by Jane Austen", student2.getName()).size());
        assertEquals(2, library.addBookRequest("Pride and Prejudice by Jane Austen", student1.getName()).size());
        assertEquals(3, library.addBookRequest("Pride and Prejudice by Jane Austen", teacher1.getName()).size());

        assertEquals(3, library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2.getName()).size());
        requests = library.getBookRequests();
        System.out.println(requests.toString());
    }


}