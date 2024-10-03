package com.interview.systemdesign.lowleveldesign;

import java.util.ArrayList;
import java.util.List;

/*
 * Here's a complete solution for designing a Movie Ticket Booking System using object-oriented
 * principles in Java. This implementation includes classes for Movie, Show, Theater, Seat, User,
 * Booking, and Payment, providing basic functionality for searching movies, booking tickets, and
 * selecting seats.
 *
 * Explanation of the Code:
 *
 * - Movie Class:
 *    Represents a movie with attributes like title, duration, and genre.
 *
 * - Show Class:
 *    Represents a specific showtime for a movie in a theater, with attributes like movie,
 *    startTime, availableSeats, and theater.
 *
 * - Theater Class:
 *    Represents a theater that has multiple screens. Each screen hosts different shows for
 *    various movies.
 *
 * - Seat Class:
 *    Represents a seat in the theater with attributes like seatNumber, isAvailable, and
 *    methods to lock/unlock seats during booking.
 *
 * - Booking Class:
 *    Represents a booking made by a user. It includes the selected show, seats, and totalCost.
 *
 * - User Class:
 *    Represents a user who can search for shows, select seats, and make bookings.
 *
 * - Payment Class:
 *    Handles the payment and refunds for bookings.
 *
 * - MovieTicketBookingSystem Class:
 *    Manages the shows, theaters, seat availability, bookings, and payment processing.
 *
 * - Main Class:
 *    Demonstrates how the system works by creating movies, shows, users, and making bookings.
 *
 * Explanation of Key Components:
 *
 * - Real-Time Seat Selection:
 *    Seats are locked once selected by the user. In a real-world application, this would
 *    require proper synchronization and timeout mechanisms.
 *
 * - Payment Processing:
 *    Payments are processed when booking tickets, and the system handles simple transactions.
 *
 * - Booking Management:
 *    Users can book multiple seats, and the system tracks the availability of each seat.
 *
 * This solution can be further extended by adding more complex functionalities, such as
 * seat holds with timeouts, more sophisticated payment gateways, and user authentication.
 */
// Movie Class
class Movie {
    private String title;
    private String genre;
    private int duration; // in minutes

    public Movie(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }
}

// Seat Class
class Seat {
    private String seatNumber;
    private boolean isAvailable;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.isAvailable = true;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookSeat() {
        this.isAvailable = false;
    }

    public void releaseSeat() {
        this.isAvailable = true;
    }
}

// Show Class
class Show {
    private Movie movie;
    private String startTime;
    private List<Seat> seats;
    private Theater theater;

    public Show(Movie movie, String startTime, int totalSeats, Theater theater) {
        this.movie = movie;
        this.startTime = startTime;
        this.theater = theater;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat("S" + i));
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public String getStartTime() {
        return startTime;
    }

    public Theater getTheater() {
        return theater;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public boolean bookSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber) && seat.isAvailable()) {
                seat.bookSeat();
                return true;
            }
        }
        return false;
    }
}

// Theater Class
class Theater {
    private String name;
    private String location;

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}

// Booking Class
class Booking {
    private User user;
    private Show show;
    private List<Seat> bookedSeats;
    private double totalCost;

    public Booking(User user, Show show, List<Seat> bookedSeats, double totalCost) {
        this.user = user;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public double getTotalCost() {
        return totalCost;
    }
}

// User Class
class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

// Payment Class
class Payment {
    public boolean processPayment(User user, double amount) {
        // Simulate payment processing
        System.out.println("Processing payment of $" + amount + " for user: " + user.getName());
        return true;
    }

    public boolean refund(User user, double amount) {
        // Simulate refund processing
        System.out.println("Refunding $" + amount + " to user: " + user.getName());
        return true;
    }
}

// MovieTicketBookingSystem Class
class MovieTicketBookingSystem {
    private List<Show> shows;
    private Payment payment;

    public MovieTicketBookingSystem() {
        shows = new ArrayList<>();
        payment = new Payment();
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<Show> searchShows(String movieTitle) {
        List<Show> result = new ArrayList<>();
        for (Show show : shows) {
            if (show.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                result.add(show);
            }
        }
        return result;
    }

    public Booking bookTicket(User user, Show show, List<String> seatNumbers) {
        List<Seat> bookedSeats = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            if (show.bookSeat(seatNumber)) {
                bookedSeats.add(new Seat(seatNumber));
            } else {
                System.out.println("Seat " + seatNumber + " is not available.");
                return null;
            }
        }

        double totalCost = seatNumbers.size() * 10.0; // Assuming each ticket costs $10
        if (payment.processPayment(user, totalCost)) {
            return new Booking(user, show, bookedSeats, totalCost);
        }
        return null;
    }
}

// Main Class
public class PartJMovieTicketBookingSystem {
    public static void main(String[] args) {
        // Creating Movies
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("The Dark Knight", "Action", 152);

        // Creating Theaters
        Theater theater1 = new Theater("PVR Cinemas", "Downtown");

        // Creating Shows
        Show show1 = new Show(movie1, "18:00", 10, theater1);
        Show show2 = new Show(movie2, "20:00", 10, theater1);

        // Movie Ticket Booking System
        MovieTicketBookingSystem bookingSystem = new MovieTicketBookingSystem();
        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);

        // Creating a User
        User user = new User("John Doe", "john@example.com");

        // Searching for a Show
        List<Show> shows = bookingSystem.searchShows("Inception");
        if (!shows.isEmpty()) {
            Show selectedShow = shows.get(0);
            System.out.println("Booking seats for show: " + selectedShow.getMovie().getTitle());

            // Booking tickets
            List<String> seatsToBook = List.of("S1", "S2", "S3");
            Booking booking = bookingSystem.bookTicket(user, selectedShow, seatsToBook);

            if (booking != null) {
                System.out.println("Booking successful for " + booking.getBookedSeats().size() + " seats. Total cost: $" + booking.getTotalCost());
            }
        } else {
            System.out.println("No shows available for the selected movie.");
        }
    }
}
