package system.library.tests;

import org.junit.jupiter.api.Test;
import system.library.models.Person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void updateBooksBorrowed() {
        Person person = new Person(108, "Victor", "senior student", 2, 1994);

        List<String> list0 = Arrays.asList();
        List<String> list1 = Arrays.asList("Pale Fire by Vladimir Nabokov");
        List<String> list2 = Arrays.asList("Pale Fire by Vladimir Nabokov", "In Search of Lost Time by Marcel Proust");
        List<String> list3 = Arrays.asList("Pale Fire by Vladimir Nabokov", "In Search of Lost Time by Marcel Proust",
                "Pride and Prejudice by Jane Austen");
        List<String> list4 = Arrays.asList("Pale Fire by Vladimir Nabokov", "In Search of Lost Time by Marcel Proust",
                "Pride and Prejudice by Jane Austen", "David Copperfield by Charles Dickens");

        Set<String> expected0 = new HashSet<>(list0);
        Set<String> expected1 = new HashSet<>(list1);
        Set<String> expected2 = new HashSet<>(list2);
        Set<String> expected3 = new HashSet<>(list3);
        Set<String> expected4 = new HashSet<>(list4);

        assertEquals(expected1, person.updateBooksBorrowed("Pale Fire by Vladimir Nabokov", true));
        assertEquals(expected2, person.updateBooksBorrowed("In Search of Lost Time by Marcel Proust", true));
        assertEquals(expected3, person.updateBooksBorrowed("Pride and Prejudice by Jane Austen", true));
        assertEquals(expected4, person.updateBooksBorrowed("David Copperfield by Charles Dickens", true));

        assertEquals(expected3, person.updateBooksBorrowed("David Copperfield by Charles Dickens", false));
        assertEquals(expected2, person.updateBooksBorrowed("Pride and Prejudice by Jane Austen", false));
        assertEquals(expected1, person.updateBooksBorrowed("In Search of Lost Time by Marcel Proust", false));
        assertEquals(expected0, person.updateBooksBorrowed("Pale Fire by Vladimir Nabokov", false));
    }
}