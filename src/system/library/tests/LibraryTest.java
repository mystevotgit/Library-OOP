package system.library.tests;

import system.library.models.Liberian;
import system.library.models.Library;
import system.library.models.Person;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    Liberian liberian = new Liberian(1, "John Doe", "liberian", 1, 1990);

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
    void addUser() {
        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        assertEquals(student1, library.addUser(student1));
        assertEquals(student2, library.addUser(student2));
        assertEquals(teacher1, library.addUser(teacher1));
        assertEquals(student3, library.addUser(student3));
        assertEquals(student4, library.addUser(student4));
        assertEquals(teacher2, library.addUser(teacher2));
    }

    @org.junit.jupiter.api.Test
    void getBooks() {
        library.addBook("Pale Fire by Vladimir Nabokov", 5);
        assertEquals("{Pale Fire by Vladimir Nabokov=5}", library.getBooks().toString());

        library.addBook("The Odyssey by Homer", 3);
        assertEquals("{Pale Fire by Vladimir Nabokov=5, The Odyssey by Homer=3}", library.getBooks().toString());
    }

    @org.junit.jupiter.api.Test
    void lendBook_and_retrieveBook() {
        Map requests = library.getBookRequests();

        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        library.addBook("The History of Tom Jones, a Foundling by Henry Fielding", 3);
        library.addBook("Pride and Prejudice by Jane Austen", 5);
        library.addBook("The Red and the Black by Stendhal", 2);
        library.addBook("David Copperfield by Charles Dickens", 3);
        library.addBook("In Search of Lost Time by Marcel Proust", 7);
        library.addBook("To The Lighthouse by Virginia Woolf", 10);
        library.addBook("The Sound and the Fury by William Faulkner", 6);
        library.addBook("Pale Fire by Vladimir Nabokov", 2);

        library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student2);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student3);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", teacher1);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", student1);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", student4);
        library.addBookRequest("David Copperfield by Charles Dickens", student3);
        library.addBookRequest("Pride and Prejudice by Jane Austen", student2);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2);

        List<Person> expected1 = Arrays.asList(student1, student2);
        List<Person> expected2 = Arrays.asList(student1, student4, teacher2);
        List<Person> expected3 = Arrays.asList(student2);
        List<Person> expected4 = Arrays.asList(student3);


        Map<String, Integer> expectedBooks1 = library.getBooks();
        assertEquals(expected1, library.lendBook("Pale Fire by Vladimir Nabokov", requests.get("Pale Fire by Vladimir Nabokov")));

        Map<String, Integer> expectedBooks2 = library.getBooks();
        assertEquals(expected2, library.lendBook("In Search of Lost Time by Marcel Proust", requests.get("In Search of Lost Time by Marcel Proust")));

        Map<String, Integer> expectedBooks3 = library.getBooks();
        assertEquals(expected3, library.lendBook("Pride and Prejudice by Jane Austen", requests.get("Pride and Prejudice by Jane Austen")));
        assertEquals(expected4, library.lendBook("David Copperfield by Charles Dickens", requests.get("David Copperfield by Charles Dickens")));

        assertEquals(expectedBooks1, library.retrieveBook("Pale Fire by Vladimir Nabokov", "Tunji"));
        assertEquals(expectedBooks2, library.retrieveBook("In Search of Lost Time by Marcel Proust", "Daniel"));
        assertEquals(expectedBooks3, library.retrieveBook("Pride and Prejudice by Jane Austen", "Tunji"));

        System.out.println(requests.toString());
        System.out.println(library.getBooks());

        List<Person> expected10 = Arrays.asList(teacher2, student3, student1, student4);
        List<Person> bookRequests = Arrays.asList(student1, student4, teacher2, student3);
        assertEquals(expected10, library.lendBook("Pale Fire by Vladimir Nabokov", bookRequests, true));
        System.out.println(library.getBooks());
    }


    @org.junit.jupiter.api.Test
    void getBooksBorrowed() {
        Map requests = library.getBookRequests();

        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        library.addBook("The History of Tom Jones, a Foundling by Henry Fielding", 3);
        library.addBook("Pride and Prejudice by Jane Austen", 5);
        library.addBook("The Red and the Black by Stendhal", 2);
        library.addBook("David Copperfield by Charles Dickens", 3);
        library.addBook("In Search of Lost Time by Marcel Proust", 7);
        library.addBook("To The Lighthouse by Virginia Woolf", 10);
        library.addBook("The Sound and the Fury by William Faulkner", 6);
        library.addBook("Pale Fire by Vladimir Nabokov", 2);

        library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student2);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student3);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", teacher1);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", student1);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", student4);
        library.addBookRequest("David Copperfield by Charles Dickens", student3);
        library.addBookRequest("Pride and Prejudice by Jane Austen", student2);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2);

        List<String> list1 = Arrays.asList("Pale Fire by Vladimir Nabokov");
        List<String> list2 = Arrays.asList("Pale Fire by Vladimir Nabokov", "In Search of Lost Time by Marcel Proust");
        List<String> list3 = Arrays.asList("Pale Fire by Vladimir Nabokov", "In Search of Lost Time by Marcel Proust",
                "Pride and Prejudice by Jane Austen");
        List<String> list4 = Arrays.asList("Pale Fire by Vladimir Nabokov", "In Search of Lost Time by Marcel Proust",
                "Pride and Prejudice by Jane Austen", "David Copperfield by Charles Dickens");

        Set<String> expected1 = new HashSet<>(list1);
        Set<String> expected2 = new HashSet<>(list2);
        Set<String> expected3 = new HashSet<>(list3);
        Set<String> expected4 = new HashSet<>(list4);

        Map <String, List<Person>> holders = library.getBookHolders();
        library.lendBook("Pale Fire by Vladimir Nabokov", requests.get("Pale Fire by Vladimir Nabokov"));
        assertEquals(expected1, library.getBooksBorrowed(holders));

        library.lendBook("In Search of Lost Time by Marcel Proust", requests.get("In Search of Lost Time by Marcel Proust"));
        assertEquals(expected2, library.getBooksBorrowed(holders));

        library.lendBook("Pride and Prejudice by Jane Austen", requests.get("Pride and Prejudice by Jane Austen"));
        assertEquals(expected3, library.getBooksBorrowed(holders));

        library.lendBook("David Copperfield by Charles Dickens", requests.get("David Copperfield by Charles Dickens"));
        assertEquals(expected4, library.getBooksBorrowed(holders));
    }

    @org.junit.jupiter.api.Test
    void getBookHolders() {
        Map requests = library.getBookRequests();

        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        library.addBook("The History of Tom Jones, a Foundling by Henry Fielding", 3);
        library.addBook("Pride and Prejudice by Jane Austen", 5);
        library.addBook("The Red and the Black by Stendhal", 2);
        library.addBook("David Copperfield by Charles Dickens", 3);
        library.addBook("In Search of Lost Time by Marcel Proust", 7);
        library.addBook("To The Lighthouse by Virginia Woolf", 10);
        library.addBook("The Sound and the Fury by William Faulkner", 6);
        library.addBook("Pale Fire by Vladimir Nabokov", 2);

        library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student2);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", student3);
        library.addBookRequest("Pale Fire by Vladimir Nabokov", teacher1);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", student1);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", student4);
        library.addBookRequest("David Copperfield by Charles Dickens", student3);
        library.addBookRequest("Pride and Prejudice by Jane Austen", student2);
        library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2);

        List<Person> expected1 = Arrays.asList(student1, student2);
        List<Person> expected2 = Arrays.asList(student1, student4, teacher2);
        List<Person> expected3 = Arrays.asList(student2);
        List<Person> expected4 = Arrays.asList(student3);

        library.lendBook("Pale Fire by Vladimir Nabokov", requests.get("Pale Fire by Vladimir Nabokov"));
        library.lendBook("In Search of Lost Time by Marcel Proust", requests.get("In Search of Lost Time by Marcel Proust"));
        library.lendBook("Pride and Prejudice by Jane Austen", requests.get("Pride and Prejudice by Jane Austen"));
        library.lendBook("David Copperfield by Charles Dickens", requests.get("David Copperfield by Charles Dickens"));

        assertEquals(expected1, library.getBookHolders().get("Pale Fire by Vladimir Nabokov"));
        assertEquals(expected2, library.getBookHolders().get("In Search of Lost Time by Marcel Proust"));
        assertEquals(expected3, library.getBookHolders().get("Pride and Prejudice by Jane Austen"));
        assertEquals(expected4, library.getBookHolders().get("David Copperfield by Charles Dickens"));

    }

    @org.junit.jupiter.api.Test
    void addBookRequest() {
        Map requests = library.getBookRequests();

        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        Queue queue1 = library.addBookRequest("Pale Fire by Vladimir Nabokov", student1);
        assertEquals(queue1, library.addBookRequest("Pale Fire by Vladimir Nabokov", student1));
        assertEquals(2, library.addBookRequest("Pale Fire by Vladimir Nabokov", student2).size());

        library.addBookRequest("Pale Fire by Vladimir Nabokov", student3);
        assertEquals(4, library.addBookRequest("Pale Fire by Vladimir Nabokov", teacher1).size());
//        requests = library.getBookRequests();
//        System.out.println(requests.toString());

        Queue queue2 = library.addBookRequest("In Search of Lost Time by Marcel Proust", student1);
        assertEquals(queue2, library.addBookRequest("In Search of Lost Time by Marcel Proust", student4));

        library.addBookRequest("David Copperfield by Charles Dickens", student3);
        assertEquals(1, library.addBookRequest("Pride and Prejudice by Jane Austen", student2).size());

        assertEquals(3, library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2).size());
//        requests = library.getBookRequests();
//        System.out.println(requests.toString());
    }

    @org.junit.jupiter.api.Test
    void getBookRequests() {
        Map requests = library.getBookRequests();
        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        Queue queue2 = library.addBookRequest("In Search of Lost Time by Marcel Proust", student1);
        assertEquals(queue2, library.addBookRequest("In Search of Lost Time by Marcel Proust", student4));

        library.addBookRequest("David Copperfield by Charles Dickens", student3);
        assertEquals(1, library.addBookRequest("Pride and Prejudice by Jane Austen", student2).size());
        assertEquals(2, library.addBookRequest("Pride and Prejudice by Jane Austen", student1).size());
        assertEquals(3, library.addBookRequest("Pride and Prejudice by Jane Austen", teacher1).size());

        assertEquals(3, library.addBookRequest("In Search of Lost Time by Marcel Proust", teacher2).size());
        requests = library.getBookRequests();
        System.out.println(requests.toString());
    }


}