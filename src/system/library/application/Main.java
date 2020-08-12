package system.library.application;

import system.library.models.Liberian;
import system.library.models.Library;
import system.library.models.Person;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Library library;
        //  print the Library menu for the user.
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Liberian liberian = new Liberian(1, "auto", "liberian", 1, 2020);
        library = new Library("decagon_library", liberian);

        library.addBook("The History of Tom Jones, a Foundling by Henry Fielding", 3);
        library.addBook("Pride and Prejudice by Jane Austen", 5);
        library.addBook("The Red and the Black by Stendhal", 2);
        library.addBook("David Copperfield by Charles Dickens", 3);
        library.addBook("In Search of Lost Time by Marcel Proust", 7);
        library.addBook("To The Lighthouse by Virginia Woolf", 10);
        library.addBook("The Sound and the Fury by William Faulkner", 6);
        library.addBook("Pale Fire by Vladimir Nabokov", 5);

        Person student1 = new Person(101, "Samuel", "junior student", 3, 1995);
        Person student2 = new Person(102, "Tunji", "senior student", 2, 1993);
        Person teacher1 = new Person(103, "David", "teacher", 1,1990);
        Person student3 = new Person(104, "Emmanuel", "senior student", 2, 1992);
        Person student4 = new Person(105, "Daniel", "junior student", 3, 1998);
        Person teacher2 = new Person(106, "Chibueze", "teacher", 1, 1985);

        library.addUser(student1);
        library.addUser(student2);
        library.addUser(student3);
        library.addUser(student4);
        library.addUser(teacher1);
        library.addUser(teacher2);

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

        System.out.println("====================== ABOUT APP ==========================" +
                "\n" +
                "\n" +
                "This a simple library management system. It allows adding of users, adding of books, \n" +
                "adding of book requests, lending of books to users and retrieving books from users that borrowed books.\n" +
                "the library can be used by selecting/typing actions from the menu...\n");

        do {
            System.out.println("Select and type an option from the menu: " +
                    "( add_user  view_users  add_book  view_books  add_book_request  view_bookRequest  lend_book   retrieve_book   )");
            userInput = scanner.next();

            if (userInput.equals("add_user")) {

                System.out.println("===========ADD USER==========");
                Person user = addUser();
                library.addUser(user);
                System.out.println("....................>>>  " + user.getName() + " has been added to the library successfully.\n");
            } else if (userInput.equals("view_users")) {

                System.out.println("==============USERS==============");
                for (Person user : library.getUsers()) {
                    System.out.println(user.getName());
                }

            }else if (userInput.equals("view_books")) {

                System.out.println("==============BOOKS==============\n" + library.getBooks().toString() + "\n");

            } else if (userInput.equals("add_book")) {

                System.out.println("===========ADD BOOK==========");
                String book = getBook();
                System.out.print("Number of Copies): ");
                int copies = scanner.nextInt();
                library.addBook(book,  copies);
                System.out.println("....................>>>  " + copies + " copies of " + book + " has been added to the library successfully.\n");
            } else if (userInput.equals("add_book_request")) {

                System.out.println("===========ADD BOOK REQUEST==========");
                System.out.print("Name of user): ");
                String userName = scanner.next();
                System.out.print("Name of book): ");
                String bookName = scanner.next();
                System.out.print("Enter Authur's Name (Use underscore as space): ");
                String authur = scanner.next();
                String book = bookName + " by " + authur;
                Person user;
                List<Person> persons = library.getUsers();
                for (Person person : persons) {
                    if (person.getName().equals(userName)) {
                        user = person;
                        library.addBookRequest(book,  user);
                        System.out.println("....................>>>  " + user.getName() + " has been added to the Request Queue of the library successfully.\n");
                    }
                }
            }
        } while(!userInput.equals("exit"));
        System.out.println(userInput);
    }


    private static String getBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book Name (Use underscore as space): ");
        String bookName = scanner.next();

        System.out.print("Enter Authur's Name (Use underscore as space): ");
        String authur = scanner.next();

        String joined = bookName + " by " + authur;

        return joined;
    }


    public static Person addUser(){

        Scanner scanner = new Scanner(System.in);
//        String userInput = scanner.next();
        Person user;
        try {
            System.out.print("Enter users  ID number: ");
            int user_id = scanner.nextInt();

            System.out.print("Enter User's Name: ");
            String users_name = scanner.next();

            System.out.print("Enter User Type (Teacher, Senior or Junior): ");
            String users_type = scanner.next();

            System.out.print("Enter User's  Year of Birth: ");
            int year = scanner.nextInt();

            System.out.print("Enter User's  Priority: ");
            int priority = scanner.nextInt();

            user = new Person(user_id, users_name, users_type, priority, year);
            return user;
            //            System.out.println(library.addUser(user).getName() + " have been added succssfully.");
        } catch (InputMismatchException e) {
            System.out.println("You entered the wrong type. Enter number for user_id, year and priority.");
        }
        return null;
    }
}