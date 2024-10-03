package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
 * Date: 05/08/2020
 * Author: Mukesh Kumar Gupta
 *
 * References:
 * - https://www.andiamogo.com/S-OOD.pdf
 * - https://www.geeksforgeeks.org/design-an-online-book-reader-system/?ref=lbp
 *
 * Difficulty: Hard
 * Companies: Google, Amazon, Facebook
 *
 * Description:
 * A Low-Level Design (LLD) for an Online Book Reader involves defining the system’s core components,
 * data models, and functionalities to support features such as reading books online, user management,
 * book library management, and tracking reading progress. Here is a detailed design approach,
 * including the core components and functionality of the system.
 *
 * Core Requirements:
 * 1. User Management:
 *    - Users should be able to register, log in, manage their profile, and view reading history.
 *
 * 2. Book Management:
 *    - Books can be uploaded by admins or content providers, categorized by genres, and managed.
 *
 * 3. Book Reading:
 *    - Users can browse books, read them online, and bookmark reading positions.
 *
 * 4. Search:
 *    - Users can search books by title, author, or genre.
 *
 * 5. Progress Tracking:
 *    - The system should track the reading progress of each book for every user.
 *
 * 6. Rating and Reviews:
 *    - Users can rate and review books after reading them.
 *
 * 7. Subscription Service (optional):
 *    - Premium users can access exclusive content via subscriptions.
 *
 * Approach:
 * - The system should be designed using Service-Oriented Architecture (SOA) with modular microservices,
 *   where each service handles specific responsibilities. The application should be scalable and maintainable,
 *   with clean separation between user management, book handling, reading functionality, etc.
 *
 * Components:
 * 1. User Management
 *    - Classes: User, Profile, UserHistory
 *    - Functions:
 *      - registerUser(): Allows new users to register.
 *      - loginUser(): Handles user authentication.
 *      - editProfile(): Lets users update their profile (username, avatar, preferences).
 *      - viewReadingHistory(): Displays the user's reading history.
 *      - logout(): Ends the user session.
 *
 * 2. Book Management
 *    - Classes: Book, Genre, Author, BookUpload, BookCatalog
 *    - Functions:
 *      - uploadBook(): Allows admins or content providers to upload new books.
 *      - viewBookDetails(): Fetches metadata for a specific book (author, genre, description).
 *      - deleteBook(): Removes a book from the system.
 *      - getBooksByGenre(): Fetches books categorized by genre.
 *      - getBooksByAuthor(): Fetches books by a specific author.
 *
 * 3. Book Reading
 *    - Classes: BookReader, Bookmark, ReadingSession
 *    - Functions:
 *      - openBook(): Opens the book in an online reader format.
 *      - nextPage(): Moves to the next page in the book.
 *      - previousPage(): Moves to the previous page.
 *      - addBookmark(): Adds a bookmark for the current reading position.
 *      - resumeReading(): Resumes the book from the last bookmarked page.
 *
 * 4. Search System
 *    - Classes: Search, SearchResult, Filter
 *    - Functions:
 *      - searchByTitle(): Search books by title.
 *      - searchByAuthor(): Search books by author.
 *      - searchByGenre(): Search books by genre.
 *      - applyFilters(): Applies filters like genre, rating, and reviews to search results.
 *
 * 5. Reading Progress Tracking
 *    - Classes: UserReadingProgress, ProgressTracker, ReadingStatus
 *    - Functions:
 *      - saveProgress(): Saves the user’s progress for a specific book (last page, percentage read).
 *      - getReadingStatus(): Retrieves the current reading status (e.g., started, completed, in-progress).
 *      - resumeFromLastPage(): Resumes reading from the saved progress.
 *
 * 6. Rating and Reviews
 *    - Classes: Rating, Review, UserReview
 *    - Functions:
 *      - rateBook(): Users can rate a book out of 5 stars.
 *      - writeReview(): Allows users to write reviews for the books they've read.
 *      - viewReviews(): Fetches reviews written by other users for a book.
 *
 * 7. Subscription Service (Optional)
 *    - Classes: Subscription, PremiumContent
 *    - Functions:
 *      - subscribeUser(): Allows a user to subscribe to premium content.
 *      - checkSubscriptionStatus(): Verifies whether a user is a premium member.
 *      - accessPremiumContent(): Grants access to exclusive books for premium users.
 *
 * System Design:
 * 1. Database Design:
 *    - User Table: userId, username, email, passwordHash, avatar, createdAt, subscriptionStatus
 *    - Book Table: bookId, title, authorId, genreId, contentUrl, coverImageUrl, publishedAt
 *    - Author Table: authorId, name, bio
 *    - Genre Table: genreId, name
 *    - Bookmark Table: bookmarkId, userId, bookId, pageNumber, createdAt
 *    - Review Table: reviewId, userId, bookId, reviewText, rating, createdAt
 *    - Progress Table: progressId, userId, bookId, lastPage, percentageRead, updatedAt
 *
 * 2. Service Layer:
 *    - UserService: Handles user-related functionalities like registration, authentication, and profile management.
 *    - BookService: Manages book upload, retrieval, deletion, and catalog management.
 *    - ReaderService: Provides book reading functionalities like bookmarking, navigation, and resume reading.
 *    - SearchService: Handles searching across books, authors, and genres with filters.
 *    - ProgressService: Tracks and manages user reading progress.
 *    - ReviewService: Handles rating and review functionalities.
 *    - SubscriptionService: Manages subscription statuses and access to premium content.
 *
 * API Design:
 * - POST /register: Registers a new user.
 * - POST /login: Authenticates a user.
 * - POST /book/upload: Allows an admin to upload a book.
 * - GET /book/{bookId}: Fetches the details of a book by ID.
 * - GET /books/genre/{genreId}: Fetches books by genre.
 * - GET /books/search: Searches for books by title, author, or genre.
 * - POST /book/{bookId}/bookmark: Adds a bookmark for a book.
 * - GET /book/{bookId}/resume: Resumes reading a book from the last saved page.
 * - POST /book/{bookId}/rate: Rates a book.
 * - POST /book/{bookId}/review: Adds a review for a book.
 * - GET /reviews/{bookId}: Fetches all reviews for a book.
 * - POST /subscribe: Subscribes a user to premium content.
 *
 * Scalability Considerations:
 * - Database Sharding: Split large tables (e.g., books, bookmarks) across shards by user or geographic region.
 * - Caching: Use Redis or Memcached to cache frequently accessed data like book metadata and search results.
 * - Load Balancing: Employ load balancers to evenly distribute traffic across multiple instances of the application.
 * - Content Delivery Network (CDN): Use a CDN to store and serve book content, reducing latency for users.
 * - Microservices: Ensure modularity by using microservices for each major component (user, book, reading, etc.) to independently scale services.
 * - Asynchronous Tasks: Use message queues (RabbitMQ, Kafka) for asynchronous tasks like generating reading recommendations or sending notifications.
 *
 * Security Considerations:
 * - Authentication & Authorization: Implement OAuth2 or JWT for secure user authentication.
 * - Data Encryption: Use HTTPS for secure data transmission and encrypt sensitive user information (e.g., passwords, personal data).
 * - Rate Limiting: Apply rate limiting for API endpoints to prevent abuse or DDoS attacks.
 * - Content Moderation: Use content moderation for user-submitted reviews to ensure compliance with platform policies.
 */

class User {
    private String userId;
    private String username;
    private String email;
    private String passwordHash;
    private String avatar;
    private boolean subscriptionStatus;

    // Constructors, Getters, Setters
}

class Profile {
    private String userId;
    private String username;
    private String avatar;

    // Constructors, Getters, Setters
}

class UserHistory {
    private String userId;
    private List<Book> booksRead;
    private Map<Book, Integer> progress; // book -> last page read

    // Getters, Setters
}

class UserService {
    private Map<String, User> users = new HashMap<>();

    public User registerUser(String username, String email, String password) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, username, email, hashPassword(password), null, false);
        users.put(userId, user);
        return user;
    }

    public User loginUser(String email, String password) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email) && checkPassword(user.getPasswordHash(), password)) {
                return user;
            }
        }
        return null;
    }

    private String hashPassword(String password) {
        // Password hashing logic
        return password; // Simplified here
    }

    private boolean checkPassword(String hash, String password) {
        // Password check logic
        return hash.equals(password); // Simplified here
    }

    public void editProfile(String userId, String newUsername, String avatar) {
        User user = users.get(userId);
        if (user != null) {
            user.setUsername(newUsername);
            user.setAvatar(avatar);
        }
    }

    public UserHistory viewReadingHistory(String userId) {
        // Logic to fetch reading history
        return new UserHistory(); // Simplified here
    }

    public void logout(String userId) {
        // Invalidate user session
    }
}


class Book {
    private String bookId;
    private String title;
    private Author author;
    private Genre genre;
    private String contentUrl;
    private String coverImageUrl;

    // Constructors, Getters, Setters
}

class Author {
    private String authorId;
    private String name;
    private String bio;

    // Constructors, Getters, Setters
}

class Genre {
    private String genreId;
    private String name;

    // Constructors, Getters, Setters
}

class BookCatalog {
    private List<Book> books;

    public List<Book> getBooksByGenre(Genre genre) {
        // Filter books by genre
        return books.stream().filter(book -> book.getGenre().equals(genre)).collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(Author author) {
        // Filter books by author
        return books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public Book viewBookDetails(String bookId) {
        return books.stream().filter(book -> book.getBookId().equals(bookId)).findFirst().orElse(null);
    }
}

class AdminService {
    private BookCatalog bookCatalog;

    public void uploadBook(String title, Author author, Genre genre, String contentUrl, String coverImageUrl) {
        String bookId = UUID.randomUUID().toString();
        Book book = new Book(bookId, title, author, genre, contentUrl, coverImageUrl);
        bookCatalog.getBooks().add(book);
    }

    public void deleteBook(String bookId) {
        bookCatalog.getBooks().removeIf(book -> book.getBookId().equals(bookId));
    }
}

class Bookmark {
    private String bookmarkId;
    private String userId;
    private String bookId;
    private int pageNumber;

    // Constructors, Getters, Setters
}

class BookReader {
    private Map<String, Bookmark> bookmarks = new HashMap<>(); // userId -> bookmark

    public void openBook(String bookId) {
        System.out.println("Opening book with id: " + bookId);
        // Fetch book content logic
    }

    public void nextPage(String bookId) {
        System.out.println("Next page for book: " + bookId);
        // Logic for going to the next page
    }

    public void previousPage(String bookId) {
        System.out.println("Previous page for book: " + bookId);
        // Logic for going to the previous page
    }

    public void addBookmark(String userId, String bookId, int pageNumber) {
        Bookmark bookmark = new Bookmark(UUID.randomUUID().toString(), userId, bookId, pageNumber);
        bookmarks.put(userId, bookmark);
    }

    public Bookmark resumeReading(String userId, String bookId) {
        return bookmarks.get(userId);
    }
}


class SearchResult {
    private List<Book> books;

    // Constructors, Getters, Setters
}

class SearchService {
    private BookCatalog bookCatalog;

    public SearchResult searchByTitle(String title) {
        List<Book> result = bookCatalog.getBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        return new SearchResult(result);
    }

    public SearchResult searchByAuthor(String authorName) {
        List<Book> result = bookCatalog.getBooks().stream()
                .filter(book -> book.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                .collect(Collectors.toList());
        return new SearchResult(result);
    }

    public SearchResult searchByGenre(String genreName) {
        List<Book> result = bookCatalog.getBooks().stream()
                .filter(book -> book.getGenre().getName().toLowerCase().equals(genreName.toLowerCase()))
                .collect(Collectors.toList());
        return new SearchResult(result);
    }
}


class UserReadingProgress {
    private String userId;
    private String bookId;
    private int lastPage;
    private double percentageRead;

    // Constructors, Getters, Setters
}

class ProgressService {
    private Map<String, UserReadingProgress> progressMap = new HashMap<>(); // userId -> progress

    public void saveProgress(String userId, String bookId, int lastPage, double percentageRead) {
        UserReadingProgress progress = new UserReadingProgress(userId, bookId, lastPage, percentageRead);
        progressMap.put(userId, progress);
    }

    public UserReadingProgress getReadingStatus(String userId, String bookId) {
        return progressMap.get(userId);
    }

    public void resumeFromLastPage(String userId, String bookId) {
        UserReadingProgress progress = progressMap.get(userId);
        if (progress != null) {
            System.out.println("Resuming from page: " + progress.getLastPage());
        }
    }
}


class Review {
    private String reviewId;
    private String userId;
    private String bookId;
    private String reviewText;
    private int rating;

    // Constructors, Getters, Setters
}

class ReviewService {
    private Map<String, List<Review>> bookReviews = new HashMap<>(); // bookId -> reviews

    public void rateBook(String userId, String bookId, int rating, String reviewText) {
        Review review = new Review(UUID.randomUUID().toString(), userId, bookId, reviewText, rating);
        bookReviews.putIfAbsent(bookId, new ArrayList<>());
        bookReviews.get(bookId).add(review);
    }

    public List<Review> viewReviews(String bookId) {
        return bookReviews.getOrDefault(bookId, new ArrayList<>());
    }
}


class Subscription {
    private String userId;
    private boolean isPremium;

    // Constructors, Getters, Setters
}

class SubscriptionService {
    private Map<String, Subscription> subscriptions = new HashMap<>(); // userId -> subscription

    public void subscribeUser(String userId) {
        subscriptions.put(userId, new Subscription(userId, true));
    }

    public boolean checkSubscriptionStatus(String userId) {
        Subscription subscription = subscriptions.get(userId);
        return subscription != null && subscription.isPremium();
    }
}



public class OnlineBookReaderSystemDesign {

    
    public static void main(String[] args) {
    }
    
}
