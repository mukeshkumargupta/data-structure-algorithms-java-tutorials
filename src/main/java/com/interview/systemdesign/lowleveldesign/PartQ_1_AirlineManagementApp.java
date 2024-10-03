package com.interview.systemdesign.lowleveldesign;

// Airline Management System

import java.util.*;

/*
    For an Airline Management System, using a similar design pattern as the URL Shortener Service, we would structure it by focusing
     on mapping and efficient management of different components, like flights, passengers, and bookings. Here's a breakdown of the
     key components and how we can apply a similar approach to manage an airline's operations:

    1. Core Requirements:
       - Flight Management: Add, update, and remove flight information.
       - Passenger Management: Add passengers, retrieve their details, and handle bookings.
       - Booking Management: Create bookings by mapping passengers to flights and provide booking references.
       - Uniqueness: Ensure each flight, passenger, and booking has unique IDs.
       - Scaling: Handle a large number of flight and booking requests.
       - Optional Expiry: Allow bookings to expire or be canceled after a certain period.

    2. Approach:
       - Use a Flight ID and Booking Reference (similar to the shortened URL) to represent flights and bookings.
       - Store mappings of flights to passengers in a database or key-value store.
       - Use Base62 encoding (or similar) to generate unique booking references.
       - Create services to add flights, passengers, and manage bookings.

    3. Components of the Airline Management System:
       - Flight Table: A database or key-value store to store information about flights.
       - Passenger Table: A database to manage passengers and their details.
       - Booking Table: A mapping between passengers and their flights.
       - Base62 Encoding: A method to shorten numeric IDs into booking reference codes using alphanumeric characters.

    4. Services:
       - addFlight(): Add a new flight to the system.
       - addPassenger(): Add a passenger to the system.
       - createBooking(): Create a booking for a passenger on a flight and generate a booking reference.
       - cancelBooking(): Cancel a booking by reference.
       - getFlightDetails(): Retrieve flight information by ID.
       - getBookingDetails(): Retrieve booking details by booking reference.

    5. Scalability Considerations:
       - Use a NoSQL database to store flight and passenger details to handle a large number of requests efficiently.
       - Use distributed key generation techniques (e.g., Zookeeper, Redis) to scale across multiple nodes.
       - Implement caching using Redis or similar for frequently accessed flight and booking data.

    7. Explanation:
       - Flight Management: You can add flights with a unique flight ID, source, and destination.
       - Passenger Management: You can add passengers to the system with a unique ID and name.
       - Booking Management: Bookings are created by mapping passengers to flights and generating a unique booking reference using Base62 encoding.
       - Scaling: The booking system is designed to scale by using random booking references and storing mappings in a HashMap (which can be replaced by a database in a production environment).

    8. Additional Features:
       - Implement rate limiting to prevent overloading the system with booking requests.
       - Introduce caching to reduce load on the database for frequently accessed flights and bookings.
       - Implement expiry for bookings that are not confirmed or used within a certain time.
*/
class AirlineManagementSystem {
    private Map<String, Flight> flights;
    private Map<String, Passenger> passengers;
    private Map<String, Booking> bookings;

    public AirlineManagementSystem() {
        this.flights = new HashMap<>();
        this.passengers = new HashMap<>();
        this.bookings = new HashMap<>();
    }

    // Adds a new flight to the system
    public Flight addFlight(String flightId, String source, String destination) {
        Flight flight = new Flight(flightId, source, destination);
        flights.put(flightId, flight);
        return flight;
    }

    // Adds a passenger to the system
    public Passenger addPassenger(String passengerId, String name) {
        Passenger passenger = new Passenger(passengerId, name);
        passengers.put(passengerId, passenger);
        return passenger;
    }

    // Creates a booking for a passenger on a flight
    public Booking createBooking(String flightId, String passengerId) {
        Flight flight = flights.get(flightId);
        Passenger passenger = passengers.get(passengerId);
        if (flight == null || passenger == null) {
            throw new IllegalArgumentException("Flight or Passenger not found.");
        }
        String bookingReference = Base62.encode(new Random().nextInt(1000000));
        Booking booking = new Booking(bookingReference, flight, passenger);
        bookings.put(bookingReference, booking);
        flight.addPassenger(passenger);
        return booking;
    }

    // Retrieves booking details by reference
    public Booking getBookingDetails(String bookingReference) {
        return bookings.get(bookingReference);
    }

    // Cancels a booking
    public void cancelBooking(String bookingReference) {
        Booking booking = bookings.get(bookingReference);
        if (booking != null) {
            Flight flight = booking.getFlight();
            flight.removePassenger(booking.getPassenger());
            bookings.remove(bookingReference);
        }
    }

    // Retrieves flight details by flight ID
    public Flight getFlightDetails(String flightId) {
        return flights.get(flightId);
    }
}

// Flight class
class Flight {
    private String flightId;
    private String source;
    private String destination;
    private List<Passenger> passengers;

    public Flight(String flightId, String source, String destination) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.passengers = new ArrayList<>();
    }

    public String getFlightId() {
        return flightId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String toString() {
        return "Flight ID: " + flightId + ", Source: " + source + ", Destination: " + destination;
    }
}

// Passenger class
class Passenger {
    private String passengerId;
    private String name;

    public Passenger(String passengerId, String name) {
        this.passengerId = passengerId;
        this.name = name;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Passenger ID: " + passengerId + ", Name: " + name;
    }
}

// Booking class
class Booking {
    private String bookingReference;
    private Flight flight;
    private Passenger passenger;

    public Booking(String bookingReference, Flight flight, Passenger passenger) {
        this.bookingReference = bookingReference;
        this.flight = flight;
        this.passenger = passenger;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String toString() {
        return "Booking Reference: " + bookingReference + ", Flight: " + flight + ", Passenger: " + passenger;
    }
}

// Base62 Encoding class (for unique booking reference generation)
class Base62 {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(int value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt(value % 62));
            value /= 62;
        }
        return sb.reverse().toString();
    }
}

// Main class to simulate the Airline Management System
public class PartQAirlineManagementApp {
    public static void main(String[] args) {
        AirlineManagementSystem system = new AirlineManagementSystem();

        // Add flights
        system.addFlight("F101", "New York", "Los Angeles");
        system.addFlight("F102", "London", "Paris");

        // Add passengers
        system.addPassenger("P001", "Alice");
        system.addPassenger("P002", "Bob");

        // Create bookings
        Booking booking1 = system.createBooking("F101", "P001");
        Booking booking2 = system.createBooking("F102", "P002");

        // Retrieve and display booking details
        System.out.println(system.getBookingDetails(booking1.getBookingReference()));
        System.out.println(system.getBookingDetails(booking2.getBookingReference()));

        // Cancel a booking
        system.cancelBooking(booking1.getBookingReference());

        // Retrieve flight details
        System.out.println(system.getFlightDetails("F101"));
    }
}
