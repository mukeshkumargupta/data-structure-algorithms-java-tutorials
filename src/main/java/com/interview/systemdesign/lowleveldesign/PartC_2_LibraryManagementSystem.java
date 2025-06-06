package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
 * Category: Must Do, Amazon
 * System Requirements#
Always clarify requirements at the beginning of the interview. Be sure to ask questions to find the exact scope of the system that the interviewer has in mind.
We will focus on the following set of requirements while designing the Library Management System:
1.  Any library member should be able to search books by their title, author, subject category as well by the publication date.
2.  Each book will have a unique identification number and other details including a rack number which will help to physically locate the book.
3.  There could be more than one copy of a book, and library members should be able to check-out and reserve any copy. We will call each copy of a book, a book item.
4.  The system should be able to retrieve information like who took a particular book or what are the books checked-out by a specific library member.
5.  There should be a maximum limit (5) on how many books a member can check-out.
6.  There should be a maximum limit (10) on how many days a member can keep a book.
7.  The system should be able to collect fines for books returned after the due date.
8.  Members should be able to reserve books that are not currently available.
9.  The system should be able to send notifications whenever the reserved books become available, as well as when the book is not returned within the due date.
10. Each book and member card will have a unique barcode. The system will be able to read barcodes from books and members� library cards.

 */
public class PartC_2_LibraryManagementSystem {
    /*
     * Enums and Constants: Here are the required enums, data types, and constants:
     */
    public enum BookFormat {
        HARDCOVER, PAPERBACK, AUDIO_BOOK, EBOOK, NEWSPAPER, MAGAZINE, JOURNAL
    }
    
    public enum BookStatus {
        AVAILABLE, RESERVED, LOANED, LOST
    }
    
    public enum ReservationStatus {
        WAITING, PENDING, CANCELED, NONE
    }
    
    public enum AccountStatus {
        ACTIVE, CLOSED, CANCELED, BLACKLISTED, NONE
    }
    
    public class Address {
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;
        private String country;
    }
    
    public class Person {
        private String name;
        private Address address;
        private String email;
        private String phone;
    }
    
    public class Constants {
        public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
        public static final int MAX_LENDING_DAYS = 10;
    }
    
    /*
     * Account, Member, and Librarian: These classes represent various people that
     * interact with our system: // For simplicity, we are not defining getter and
     * setter functions. The reader can // assume that all class attributes are
     * private and accessed through their respective // public getter methods and
     * modified only through their public methods function.
     * 
     */
    public abstract class Account {
        private String id;
        private String password;
        private AccountStatus status;
        private Person person;
        
        public boolean resetPassword();
    }
    
    public class Librarian extends Account {
        public boolean addBookItem(BookItem bookItem);
        
        public boolean blockMember(Member member);
        
        public boolean unBlockMember(Member member);
    }
    
    public class Member extends Account {
        private Date dateOfMembership;
        private int totalBooksCheckedout;
        
        public int getTotalBooksCheckedout();
        
        public boolean reserveBookItem(BookItem bookItem);
        
        private void incrementTotalBooksCheckedout();
        
        public boolean checkoutBookItem(BookItem bookItem) {
            if (this.getTotalBooksCheckedOut() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
                ShowError("The user has already checked-out maximum number of books");
                return false;
            }
            BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
            if (bookReservation != null && bookReservation.getMemberId() != this.getId()) {
                // book item has a pending reservation from another user
                ShowError("This book is reserved by another member");
                return false;
            } else if (bookReservation != null) {
                // book item has a pending reservation from the give member, update it
                bookReservation.updateStatus(ReservationStatus.COMPLETED);
            }
            
            if (!bookItem.checkout(this.getId())) {
                return false;
            }
            
            this.incrementTotalBooksCheckedout();
            return true;
        }
        
        private void checkForFine(String bookItemBarcode) {
            BookLending bookLending = BookLending.fetchLendingDetails(bookItemBarcode);
            Date dueDate = bookLending.getDueDate();
            Date today = new Date();
            // check if the book has been returned within the due date
            if (today.compareTo(dueDate) > 0) {
                long diff = todayDate.getTime() - dueDate.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                Fine.collectFine(memberId, diffDays);
            }
        }
        
        public void returnBookItem(BookItem bookItem) {
            this.checkForFine(bookItem.getBarcode());
            BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
            if (bookReservation != null) {
                // book item has a pending reservation
                bookItem.updateBookItemStatus(BookStatus.RESERVED);
                bookReservation.sendBookAvailableNotification();
            }
            bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
        }
        
        public bool renewBookItem(BookItem bookItem) {
            this.checkForFine(bookItem.getBarcode());
            BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
            // check if this book item has a pending reservation from another member
            if (bookReservation != null && bookReservation.getMemberId() != this.getMemberId()) {
                ShowError("This book is reserved by another member");
                member.decrementTotalBooksCheckedout();
                bookItem.updateBookItemState(BookStatus.RESERVED);
                bookReservation.sendBookAvailableNotification();
                return false;
            } else if (bookReservation != null) {
                // book item has a pending reservation from this member
                bookReservation.updateStatus(ReservationStatus.COMPLETED);
            }
            BookLending.lendBook(bookItem.getBarCode(), this.getMemberId());
            bookItem.updateDueDate(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS));
            return true;
        }
    }
    
    /*
     * BookReservation, BookLending, and Fine: These classes represent a book
     * reservation, lending, and fine collection, respectively.
     */
    public class BookReservation {
        private Date creationDate;
        private ReservationStatus status;
        private String bookItemBarcode;
        private String memberId;
        
        public static BookReservation fetchReservationDetails(String barcode);
    }
    
    public class BookLending {
        private Date creationDate;
        private Date dueDate;
        private Date returnDate;
        private String bookItemBarcode;
        private String memberId;
        
        public static boolean lendBook(String barcode, String memberId);
        
        public static BookLending fetchLendingDetails(String barcode);
    }
    
    public class Fine {
        private Date creationDate;
        private double bookItemBarcode;
        private String memberId;
        
        public static void collectFine(String memberId, long days) {
        }
    }
    
    /*
     * BookItem: Encapsulating a book item, this class will be responsible for
     * processing the reservation, return, and renewal of a book item.
     */
    public abstract class Book {
        private String ISBN;
        private String title;
        private String subject;
        private String publisher;
        private String language;
        private int numberOfPages;
        private List<Author> authors;
    }
    
    public class BookItem extends Book {
        private String barcode;
        private boolean isReferenceOnly;
        private Date borrowed;
        private Date dueDate;
        private double price;
        private BookFormat format;
        private BookStatus status;
        private Date dateOfPurchase;
        private Date publicationDate;
        private Rack placedAt;
        
        public boolean checkout(String memberId) {
            if (bookItem.getIsReferenceOnly()) {
                ShowError("This book is Reference only and can't be issued");
                return false;
            }
            if (!BookLending.lendBook(this.getBarCode(), memberId)) {
                return false;
            }
            this.updateBookItemStatus(BookStatus.LOANED);
            return true;
        }
    }
    
    public class Rack {
        private int number;
        private String locationIdentifier;
    }
    
    /*
     * Search interface and Catalog: The Catalog class will implement the Search
     * interface to facilitate searching of books.
     */
    public interface Search {
        public List<Book> searchByTitle(String title);
        
        public List<Book> searchByAuthor(String author);
        
        public List<Book> searchBySubject(String subject);
        
        public List<Book> searchByPubDate(Date publishDate);
    }
    
    public class Catalog implements Search {
        private HashMap<String, List<Book>> bookTitles;
        private HashMap<String, List<Book>> bookAuthors;
        private HashMap<String, List<Book>> bookSubjects;
        private HashMap<String, List<Book>> bookPublicationDates;
        
        public List<Book> searchByTitle(String query) {
            // return all books containing the string query in their title.
            return bookTitles.get(query);
        }
        
        public List<Book> searchByAuthor(String query) {
            // return all books containing the string query in their author's name.
            return bookAuthors.get(query);
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
