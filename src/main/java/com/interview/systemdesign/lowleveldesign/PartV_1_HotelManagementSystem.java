package com.interview.systemdesign.lowleveldesign;

/*
 * Hotel Management System Design
 *
 * The Hotel Management System will be designed to manage hotel operations such as room reservations,
 * check-in and check-out, billing, staff management, and room services. The system will allow users
 * (guests and hotel staff) to perform various operations efficiently and ensure a smooth hotel management process.
 *
 * 1. Core Features:
 * - Room Booking: Allow customers to search for available rooms and book them for specific dates.
 * - Check-in & Check-out: Enable hotel staff to manage guest check-ins and check-outs, updating room status.
 * - Room Management: Manage room details like room types (single, double, suite), availability, and pricing.
 * - Billing & Payments: Generate bills based on room charges, additional services, and manage payments.
 * - Staff Management: Handle staff roles, assignments, and schedules.
 * - Services: Enable users to request additional services like food, laundry, etc.
 * - Feedback & Reviews: Allow guests to provide feedback on their stay and rate the hotel services.
 *
 * 2. Approach:
 * - Use a relational database (e.g., MySQL or PostgreSQL) to store room availability, reservations, staff, and billing information.
 * - Implement services to handle room search, booking, check-in/check-out, and billing.
 * - Use APIs for online payment processing (e.g., Stripe, PayPal).
 * - Design for scalability to handle multiple bookings and concurrent operations.
 *
 * 3. Key Components:
 * - Room Table: Stores room details (room number, type, price, availability).
 * - Reservation Table: Stores booking details including guest information, room booked, check-in/check-out dates, and reservation status.
 * - Guest Table: Stores guest details such as name, contact info, and past booking history.
 * - Staff Table: Stores staff details (name, role, contact info, and assigned duties).
 * - Service Table: Stores additional services offered by the hotel (e.g., room service, laundry, spa).
 * - Billing Table: Stores guest bills including room charges, additional service charges, and payment status.
 *
 * 4. Services:
 * - Room Search:
 *   - searchAvailableRooms(startDate, endDate, roomType): Allow customers to search for available rooms based on date and room type.
 * - Room Booking:
 *   - bookRoom(guestId, roomId, startDate, endDate): Handle the booking of rooms, storing reservation details in the Reservation Table.
 * - Check-in:
 *   - checkIn(guestId, reservationId): Manage guest check-ins, updating room availability and marking the reservation as checked-in.
 * - Check-out:
 *   - checkOut(guestId, reservationId): Manage guest check-outs, updating room availability and generating the final bill.
 * - Billing:
 *   - generateBill(reservationId): Calculate room charges and additional services used by the guest, generating the final bill.
 *   - processPayment(reservationId, paymentDetails): Process the guest's payment through online payment gateways.
 * - Staff Management:
 *   - assignStaff(staffId, task): Assign staff to specific duties (e.g., housekeeping, reception) and update schedules.
 * - Services:
 *   - requestService(guestId, serviceId): Allow guests to request additional services like food, laundry, etc.
 *
 * 5. Scalability Considerations:
 * - Use a relational database with indexing for faster queries, especially for room availability checks.
 * - Implement caching (e.g., Redis) to store frequently accessed data like available rooms.
 * - Consider sharding the database if the system grows large enough, dividing it by regions or hotel branches.
 * - Use a microservices architecture for each module (e.g., booking, billing, staff management) to scale independently.
 *
 * 6. Security Considerations:
 * - Authentication: Use secure authentication (e.g., OAuth or JWT tokens) to ensure only authorized users can perform actions.
 * - Role-Based Access Control: Implement role-based access (guests, staff, admins) to restrict actions (e.g., only admins can manage staff).
 * - Data Encryption: Encrypt sensitive data (e.g., guest details, payment information) to ensure privacy.
 *
 * 7. Explanation:
 * - Room Booking: Customers can search for rooms, view availability, and book rooms based on their preferences.
 * - Check-in/Check-out: Hotel staff can handle guest check-ins, updating room statuses to reflect occupancy, and manage check-outs to update availability.
 * - Room Management: The system allows hotel staff to update room details, pricing, and availability.
 * - Billing: The billing module generates the final bill based on room charges and any additional services used by the guest. Payments are processed through a secure payment gateway.
 * - Staff Management: Hotel managers can assign tasks to staff, and staff roles are defined in the system (e.g., housekeeping, receptionist, etc.).
 *
 * 8. Additional Features:
 * - Notifications: Send guests reminders or notifications about their booking, check-in time, or outstanding payments.
 * - Feedback System: Allow guests to provide feedback after their stay, rating the hotel services.
 * - Reports: Generate reports for hotel managers to review bookings, revenue, and staff performance.
 * - Multi-Branch Support: If the hotel has multiple branches, the system can be extended to manage operations across different locations.
 *
 * Key Points:
 * - Room Management: Manage room availability, pricing, and room type.
 * - Booking System: Allow guests to search for rooms and book them based on availability.
 * - Check-in/Check-out: Manage guest check-ins and check-outs, updating room status and generating bills.
 * - Billing: Handle billing for rooms and services, integrating with online payment systems.
 * - Staff Management: Manage staff schedules, assignments, and roles.
 *
 * This example showcases a basic Hotel Management System that handles bookings, guest check-ins, room management, and billing. Additional features like feedback, reporting, and multi-branch support can be implemented as needed.
 * Explanation of Components:
    Room Management: This allows managing rooms in the hotel, checking availability, and assigning bookings.
    Reservation Management: Handles guest reservations, check-ins, and check-outs.
    Billing: Calculates the bill based on room price and the duration of stay.
    Payment Processing: Simulates the payment process.
    Main: The main flow includes adding rooms, booking, check-in, check-out, billing, and payment.
    You can extend the system by adding features like staff management, additional services (room service, laundry), and feedback handling.
 */
// Hotel Management System Design

import java.util.*;

// Enum for Room Types
enum RoomType {
    SINGLE, DOUBLE, SUITE
}

// Class representing a Room
class Room {
    private int roomId;
    private RoomType type;
    private double price;
    private boolean isAvailable;

    public Room(int roomId, RoomType type, double price) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    // Getters and setters
    public int getRoomId() { return roomId; }
    public RoomType getType() { return type; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", type=" + type +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

// Class representing a Guest
class Guest {
    private int guestId;
    private String name;

    public Guest(int guestId, String name) {
        this.guestId = guestId;
        this.name = name;
    }

    // Getters
    public int getGuestId() { return guestId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                '}';
    }
}

// Class representing a Reservation
class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private boolean isCheckedIn;

    public Reservation(int reservationId, Guest guest, Room room, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isCheckedIn = false;
    }

    // Getters and setters
    public int getReservationId() { return reservationId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public Date getCheckInDate() { return checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
    public boolean isCheckedIn() { return isCheckedIn; }

    public void checkIn() {
        this.isCheckedIn = true;
    }

    public void checkOut() {
        this.room.setAvailability(true);
        this.isCheckedIn = false;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", guest=" + guest +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", isCheckedIn=" + isCheckedIn +
                '}';
    }
}

// Class for managing Rooms
class RoomManagement {
    private List<Room> rooms;

    public RoomManagement() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(RoomType type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType() == type) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }
}

// Class for managing Reservations
class ReservationManagement {
    private List<Reservation> reservations;

    public ReservationManagement() {
        reservations = new ArrayList<>();
    }

    public Reservation bookRoom(Guest guest, Room room, Date checkInDate, Date checkOutDate) {
        if (room.isAvailable()) {
            room.setAvailability(false);
            Reservation reservation = new Reservation(reservations.size() + 1, guest, room, checkInDate, checkOutDate);
            reservations.add(reservation);
            return reservation;
        }
        return null;
    }

    public void checkIn(int reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null && !reservation.isCheckedIn()) {
            reservation.checkIn();
            System.out.println("Guest checked in successfully.");
        } else {
            System.out.println("Invalid reservation or already checked in.");
        }
    }

    public void checkOut(int reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null && reservation.isCheckedIn()) {
            reservation.checkOut();
            System.out.println("Guest checked out successfully.");
        } else {
            System.out.println("Invalid reservation or not checked in.");
        }
    }

    public Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}

// Billing and Payment Processing
class BillingService {
    public double generateBill(Reservation reservation) {
        long diffInMillies = Math.abs(reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime());
        long diff = diffInMillies / (1000 * 60 * 60 * 24);
        return reservation.getRoom().getPrice() * diff;
    }

    public void processPayment(double amount) {
        // Simulating payment processing
        System.out.println("Payment of $" + amount + " processed successfully.");
    }
}

// Main Class to run the Hotel Management System
public class PartVHotelManagementSystem {
    public static void main(String[] args) {
        // Setup room management
        RoomManagement roomManagement = new RoomManagement();
        roomManagement.addRoom(new Room(101, RoomType.SINGLE, 100.0));
        roomManagement.addRoom(new Room(102, RoomType.DOUBLE, 150.0));
        roomManagement.addRoom(new Room(103, RoomType.SUITE, 250.0));

        // Setup guest and reservation management
        Guest guest1 = new Guest(1, "John Doe");
        ReservationManagement reservationManagement = new ReservationManagement();

        // Booking a room
        List<Room> availableRooms = roomManagement.searchAvailableRooms(RoomType.SINGLE);
        if (!availableRooms.isEmpty()) {
            Room room = availableRooms.get(0);
            Reservation reservation = reservationManagement.bookRoom(guest1, room, new Date(), new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000)); // 2 days later
            if (reservation != null) {
                System.out.println("Room booked successfully: " + reservation);
            }
        }

        // Check-in and check-out
        reservationManagement.checkIn(1);
        reservationManagement.checkOut(1);

        // Billing
        Reservation reservation = reservationManagement.getReservationById(1);
        BillingService billingService = new BillingService();
        double billAmount = billingService.generateBill(reservation);
        System.out.println("Generated Bill: $" + billAmount);

        // Payment
        billingService.processPayment(billAmount);
    }
}
