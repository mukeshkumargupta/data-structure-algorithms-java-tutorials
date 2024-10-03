package com.interview.systemdesign.lowleveldesign;

/*
    Here’s a solution to the Online Library System design problem using object-oriented principles in Java:

    Key Components:
    1. **Book**: Represents a book in the library, with details like title, author, genre, and availability.
    2. **Library**: Manages the inventory of books and the overall system.
    3. **Member**: Represents a user of the library, who can borrow and return books.
    4. **BookIssue**: Represents the record of borrowed books and their return status.
    5. **Admin**: A special type of member with additional permissions like adding/removing books.

    Explanation:
    - **Book**: Represents the book entity, with attributes like title, author, genre, and availability.
    - **Library**: Manages the book inventory, performs search operations (by title, author, and genre), and handles issuing and returning books.
    - **Member**: Represents a library user who can borrow and return books. Admin extends this class with additional privileges like adding and removing books.
    - **BookIssue**: Tracks the books issued to members and their return status.
    - **Admin**: Special type of member who can manage the library’s book inventory.

    Key Considerations:
    - **Permissions**: Admins have higher privileges compared to regular members.
    - **Search**: The system supports searching by title, author, or genre.
    - **Book Availability**: Book availability is managed, ensuring books can only be borrowed if they are available.
*/
import java.util.*;

// Enum for different user types
enum UserType {
    MEMBER,
    ADMIN
}

// Enum for different genres
enum Genre {
    FICTION, NONFICTION, MYSTERY, FANTASY, SCI_FI, ROMANCE, HISTORY
}

// Book class
class Book {
    private String title;
    private String author;
    private Genre genre;
    private boolean isAvailable;

    public Book(String title, String author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;  // Book is available initially
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return title + " by " + author + " [" + genre + "]";
    }
}

// Library class to manage books
class Library {
    private List<Book> books = new ArrayList<>();
    private List<BookIssue> bookIssues = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println("Book removed: " + book);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> searchByGenre(Genre genre) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre() == genre) {
                results.add(book);
            }
        }
        return results;
    }

    public void issueBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            BookIssue issue = new BookIssue(book, member);
            bookIssues.add(issue);
            member.getIssuedBooks().add(issue);
            System.out.println("Book issued: " + book + " to " + member.getName());
        } else {
            System.out.println("Book is not available: " + book);
        }
    }

    public void returnBook(Book book, Member member) {
        for (BookIssue issue : bookIssues) {
            if (issue.getBook().equals(book) && issue.getMember().equals(member) && !issue.isReturned()) {
                book.setAvailable(true);
                issue.setReturned(true);
                System.out.println("Book returned: " + book + " by " + member.getName());
                return;
            }
        }
        System.out.println("Error: Book return failed");
    }
}

// Member class (can be extended for Admin)
class Member {
    private String name;
    private String memberId;
    private UserType userType;
    private List<BookIssue> issuedBooks;

    public Member(String name, String memberId, UserType userType) {
        this.name = name;
        this.memberId = memberId;
        this.userType = userType;
        this.issuedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public UserType getUserType() {
        return userType;
    }

    public List<BookIssue> getIssuedBooks() {
        return issuedBooks;
    }

    @Override
    public String toString() {
        return name + " (" + userType + ")";
    }
}

// Admin class, which can add/remove books
class Admin extends Member {
    public Admin(String name, String memberId) {
        super(name, memberId, UserType.ADMIN);
    }

    public void addBookToLibrary(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBookFromLibrary(Library library, Book book) {
        library.removeBook(book);
    }
}

// BookIssue class to track issued books
class BookIssue {
    private Book book;
    private Member member;
    private Date issueDate;
    private boolean isReturned;

    public BookIssue(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.issueDate = new Date();
        this.isReturned = false;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return book + " issued to " + member.getName() + " on " + issueDate;
    }
}

// Test the system
public class PartCOnlineLibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin("John", "A001");
        Member member = new Member("Alice", "M001", UserType.MEMBER);

        // Add books
        admin.addBookToLibrary(library, new Book("The Hobbit", "J.R.R. Tolkien", Genre.FANTASY));
        admin.addBookToLibrary(library, new Book("1984", "George Orwell", Genre.FICTION));

        // Search books
        System.out.println("Search by title:");
        System.out.println(library.searchByTitle("The Hobbit"));

        System.out.println("Search by author:");
        System.out.println(library.searchByAuthor("George Orwell"));

        // Issue and return books
        Book book = library.searchByTitle("1984").get(0);
        library.issueBook(book, member);

        library.returnBook(book, member);
    }
}
