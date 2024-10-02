package com.interview.systemdesign.lowleveldesign;

public class PartALowLevelDesignQuestions {

    /*
     * Here are some of the top low-level design questions commonly asked in FAANG
     * (Facebook, Amazon, Apple, Netflix, Google) interviews. These questions
     * focus on designing small-scale systems, classes, or components, and
     * emphasize object-oriented design principles, data structures, and algorithms.
     */

    // 1. Design a Parking Lot System
    /*
     * Problem Statement:
     * Design a parking lot system where you need to manage different types of
     * vehicles (motorcycles, cars, trucks) and multiple levels of parking.
     *
     * Key Components:
     * - Vehicle classes (with subclasses for different vehicle types).
     * - Parking lot, parking space, and parking level.
     * - Functions for adding and removing vehicles.
     *
     * Considerations:
     * - Scalability (multiple levels, handling different vehicle sizes).
     * - Allocation of the nearest available parking space.
     */

    // 2. Design an Online Library System
    /*
     * Problem Statement:
     * Design a library management system where users can search for books,
     * borrow and return them, and view their history.
     *
     * Key Components:
     * - Classes for Book, Library, Member, BookIssue, etc.
     * - Methods for searching, borrowing, and returning books.
     * - Handling inventory and stock levels for books.
     *
     * Considerations:
     * - Fine-grained permission control (admin vs member).
     * - Search functionalities (by author, title, genre).
     */

    // 3. Design a URL Shortener (like bit.ly)
    /*
     * Problem Statement:
     * Design a URL shortening service where users can generate short links for
     * long URLs and retrieve original URLs.
     *
     * Key Components:
     * - URL class with methods for shortening and resolving URLs.
     * - Mapping between short and long URLs.
     *
     * Considerations:
     * - Ensuring uniqueness of short URLs.
     * - Handling scaling and URL expiry.
     */

    // 4. Design a Restaurant Reservation System
    /*
     * Problem Statement:
     * Design a restaurant reservation system that allows users to book tables,
     * check availability, and manage bookings.
     *
     * Key Components:
     * - Classes for Table, Reservation, Customer, Restaurant.
     * - Functionality for searching and booking tables.
     *
     * Considerations:
     * - Handling table availability and overbooking.
     * - Managing different time slots.
     */

    // 5. Design a Vending Machine
    /*
     * Problem Statement:
     * Design a vending machine that allows users to select items, insert money,
     * and dispense items.
     *
     * Key Components:
     * - Classes for Product, VendingMachine, Slot, Money.
     * - Methods to select an item, insert money, dispense item, and return change.
     *
     * Considerations:
     * - Handling insufficient funds.
     * - Stock and inventory management.
     */

    // 6. Design a Cab Booking System (like Uber or Lyft)
    /*
     * Problem Statement:
     * Design a cab booking system where users can request rides, and drivers can
     * accept rides.
     *
     * Key Components:
     * - Classes for User, Driver, Ride, Location.
     * - Functionality for booking rides, tracking location, and payments.
     *
     * Considerations:
     * - Optimizing ride assignment (nearest driver).
     * - Handling real-time location tracking and updates.
     */

    // 7. Design a File System
    /*
     * Problem Statement:
     * Design a file system that supports file creation, deletion, reading, and writing.
     *
     * Key Components:
     * - Classes for File, Directory, and FileSystem.
     * - Functionality for reading/writing files and managing directories.
     *
     * Considerations:
     * - Handling hierarchical structure (nested directories).
     * - File permission and ownership management.
     */

    // 8. Design a Notification System
    /*
     * Problem Statement:
     * Design a notification system where users can subscribe to different events and
     * receive notifications in real-time.
     *
     * Key Components:
     * - Notification, User, Event, Subscription.
     * - Methods for event creation, user subscription, and notification dispatch.
     *
     * Considerations:
     * - Handling different notification channels (email, SMS, push notifications).
     * - Ensuring timely delivery and retries in case of failures.
     */

    // 9. Design a Movie Ticket Booking System
    /*
     * Problem Statement:
     * Design a movie ticket booking system where users can search for movies,
     * book tickets, and select seats.
     *
     * Key Components:
     * - Movie, Show, Theater, Booking, Seat.
     * - Functionality to check availability, book tickets, and seat selection.
     *
     * Considerations:
     * - Real-time seat selection and availability.
     * - Handling payments and refunds.
     */

    // 10. Design a Social Media Feed (like Facebook or Instagram)
    /*
     * Problem Statement:
     * Design a social media feed where users can see posts from people they follow
     * in reverse chronological order.
     *
     * Key Components:
     * - User, Post, Feed, Follow.
     * - Methods for posting content, following/unfollowing users, and displaying the feed.
     *
     * Considerations:
     * - Efficiently querying posts for large numbers of users.
     * - Handling scalability with pagination and caching.
     */

    // 11. Design a Chess Game
    /*
     * Problem Statement:
     * Design the backend for a chess game that can be played between two players.
     *
     * Key Components:
     * - Board, Piece (with subclasses for each piece type), Game, Player.
     * - Methods to initialize the board, move pieces, and check game state
     *   (checkmate, stalemate).
     *
     * Considerations:
     * - Handling complex rules (castling, pawn promotion).
     * - Detecting valid and invalid moves.
     */

    // 12. Design a Rate Limiter
    /*
     * Problem Statement:
     * Design a system to throttle the number of requests a user can make to an API
     * within a given time window (e.g., 100 requests per minute).
     *
     * Key Components:
     * - Classes for Request, User, and RateLimiter.
     * - Functionality to track user requests and limit them based on the policy.
     *
     * Considerations:
     * - Handling distributed rate-limiting across multiple servers.
     * - Implementing sliding window or token bucket algorithms.
     */

    // 13. Design a Search Autocomplete System
    /*
     * Problem Statement:
     * Design a search autocomplete system where users receive suggestions as
     * they type queries.
     *
     * Key Components:
     * - Classes for Trie, Node, SearchQuery.
     * - Functionality to search and return the top N suggestions.
     *
     * Considerations:
     * - Optimizing search performance for large datasets.
     * - Ranking suggestions based on popularity or relevance.
     */

    // 14. Design a Snake Game
    /*
     * Problem Statement:
     * Design the logic for a snake game where the snake grows in length as it eats
     * food and dies if it hits itself or the wall.
     *
     * Key Components:
     * - Snake, Food, Board.
     * - Functionality for moving the snake, growing, and checking game over conditions.
     *
     * Considerations:
     * - Handling real-time game updates.
     * - Efficiently managing the snake's body and detecting collisions.
     */

    // 15. Design a Messaging System (like WhatsApp or Slack)
    /*
     * Problem Statement:
     * Design a messaging system where users can send and receive messages in real-time.
     *
     * Key Components:
     * - User, Message, Conversation, Group.
     * - Methods for sending messages, managing conversations, and handling groups.
     *
     * Considerations:
     * - Real-time message delivery and acknowledgment.
     * - Message history storage and retrieval.
     */

    /*
     * General Tips for Low-Level Design Interviews:
     * - Clarify Requirements: Always start by asking clarifying questions about the
     *   requirements and constraints.
     * - Object-Oriented Design: Focus on designing modular, maintainable, and
     *   extensible components.
     * - Data Structures: Choose the right data structures to optimize for time
     *   complexity (e.g., HashMaps for O(1) lookups, Arrays/Lists for fast access).
     * - Scalability: Discuss how your design can scale with increasing users or data.
     * - Edge Cases: Consider and handle edge cases and failure scenarios,
     *   especially when dealing with real-world problems.
     *
     * These questions test your ability to break down a problem, design effective
     * classes and methods, and optimize for performance and scalability.
     */
}
