package com.interview.systemdesign.lowleveldesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Table class to represent a restaurant table
class Table {
    private int tableNumber;
    private int capacity;
    private boolean isAvailable;

    public Table(int tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.isAvailable = true; // By default, a table is available
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Customer class to represent a restaurant customer
class Customer {
    private String name;
    private String contact;

    public Customer(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

// TimeSlot class to manage time slots for reservations
class TimeSlot {
    private String time;

    public TimeSlot(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}

// Reservation class to manage reservations
class Reservation {
    private Customer customer;
    private Table table;
    private TimeSlot timeSlot;

    public Reservation(Customer customer, Table table, TimeSlot timeSlot) {
        this.customer = customer;
        this.table = table;
        this.timeSlot = timeSlot;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Table getTable() {
        return table;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}

// Restaurant class to manage tables and reservations
class Restaurant {
    private List<Table> tables;
    private Map<String, List<Reservation>> reservations; // Reservations by time slot

    public Restaurant() {
        tables = new ArrayList<>();
        reservations = new HashMap<>();
    }

    // Add a table to the restaurant
    public void addTable(Table table) {
        tables.add(table);
    }

    // Check table availability for a given time slot
    public List<Table> checkAvailability(TimeSlot timeSlot) {
        List<Table> availableTables = new ArrayList<>();
        List<Reservation> reservedTables = reservations.getOrDefault(timeSlot.getTime(), new ArrayList<>());

        for (Table table : tables) {
            boolean isTableReserved = reservedTables.stream()
                    .anyMatch(reservation -> reservation.getTable().getTableNumber() == table.getTableNumber());

            if (!isTableReserved) {
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    // Book a table for a customer
    public boolean bookTable(Customer customer, TimeSlot timeSlot, int tableNumber) {
        List<Table> availableTables = checkAvailability(timeSlot);

        for (Table table : availableTables) {
            if (table.getTableNumber() == tableNumber) {
                Reservation reservation = new Reservation(customer, table, timeSlot);
                reservations.computeIfAbsent(timeSlot.getTime(), k -> new ArrayList<>()).add(reservation);
                table.setAvailable(false);
                System.out.println("Reservation successful for " + customer.getName() + " at table " + tableNumber);
                return true;
            }
        }
        System.out.println("Table " + tableNumber + " is not available at " + timeSlot.getTime());
        return false;
    }

    // Cancel a reservation
    public void cancelReservation(Customer customer, TimeSlot timeSlot, int tableNumber) {
        List<Reservation> reservationList = reservations.get(timeSlot.getTime());
        if (reservationList != null) {
            reservationList.removeIf(reservation -> reservation.getCustomer().getName().equals(customer.getName())
                    && reservation.getTable().getTableNumber() == tableNumber);
            System.out.println("Reservation cancelled for " + customer.getName() + " at table " + tableNumber);
        }
    }
}

// Main class to test the functionality
public class PartERestaurantReservationSystem {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // Add tables to the restaurant
        restaurant.addTable(new Table(1, 4));
        restaurant.addTable(new Table(2, 4));
        restaurant.addTable(new Table(3, 2));

        // Create customers
        Customer customer1 = new Customer("Alice", "1234567890");
        Customer customer2 = new Customer("Bob", "0987654321");

        // Create time slots
        TimeSlot slot1 = new TimeSlot("7:00 PM");
        TimeSlot slot2 = new TimeSlot("8:00 PM");

        // Check availability and book tables
        restaurant.bookTable(customer1, slot1, 1); // Successful booking
        restaurant.bookTable(customer2, slot1, 1); // Table 1 already booked

        // Check available tables
        List<Table> availableTables = restaurant.checkAvailability(slot1);
        System.out.println("Available tables for 7:00 PM: " + availableTables.size());

        // Cancel a reservation
        restaurant.cancelReservation(customer1, slot1, 1);

        // Re-check available tables after cancellation
        availableTables = restaurant.checkAvailability(slot1);
        System.out.println("Available tables for 7:00 PM after cancellation: " + availableTables.size());
    }
}
