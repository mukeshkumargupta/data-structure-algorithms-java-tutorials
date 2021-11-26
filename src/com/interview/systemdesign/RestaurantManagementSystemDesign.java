package com.interview.systemdesign;

import java.util.*;
/*
 * Category: Must Do, Amazon
 * System Requirements#
We will focus on the following set of requirements while designing the Restaurant Management System:
1.  The restaurant will have different branches.
2.  Each restaurant branch will have a menu.
3.  The menu will have different menu sections, containing different menu items.
4.  The waiter should be able to create an order for a table and add meals for each seat.
5.  Each meal can have multiple meal items. Each meal item corresponds to a menu item.
6.  The system should be able to retrieve information about tables currently available to seat walk-in customers.
7.  The system should support the reservation of tables.
8.  The receptionist should be able to search for available tables by date/time and reserve a table.
9.  The system should allow customers to cancel their reservation.
10. The system should be able to send notifications whenever the reservation time is approaching.
11. The customers should be able to pay their bills through credit card, check or cash.
12. Each restaurant branch can have multiple seating arrangements of tables.

 */

public class RestaurantManagementSystemDesign {
    /*
     * Enums, data types, and constants: Here are the required enums, data types,
     * and constants:
     */
    public enum ReservationStatus {
        REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELED, ABANDONED
    }
    
    public enum SeatType {
        REGULAR, KID, ACCESSIBLE, OTHER
    }
    
    public enum OrderStatus {
        RECEIVED, PREPARING, COMPLETED, CANCELED, NONE
    }
    
    public enum TableStatus {
        FREE, RESERVED, OCCUPIED, OTHER
    }
    
    public enum AccountStatus {
        ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED
    }
    
    public enum PaymentStatus {
        UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED
    }
    
    public class Address {
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;
        private String country;
    }
    
    /*
     * Account, Person, Employee, Receptionist, Manager, and Chef: These classes
     * represent the different people that interact with our system: // For
     * simplicity, we are not defining getter and setter functions. The reader can
     * // assume that all class attributes are private and accessed through their
     * respective // public getter methods and modified only through their public
     * setter function.
     * 
     */
    public class Account {
        private String id;
        private String password;
        private Address address;
        private AccountStatus status;
        
        public boolean resetPassword() {
            return true;
        }
    }
    
    public class Payment {
        int paumentId;
        Date cerateDate;
        double amount;
        boolean createTrasaction() {
            return true;
        }
    }
    
    public class CreaditCartTransaction extends  Payment {
        int creatditCardNumber;
        String nameOnCard;
    }
    
    public class CheckTransaction extends  Payment {
        String  bankName;
        String nameOnCard;
    }
    
    public class CahsTransaction extends  Payment {
        double  tenderAmount;

    }
    
    public class Bill {
        int billID;
        boolean createBill(Order order) {
            return true;
        }
    }
    //Printer class could be to be used by Cashier to print bill
    
    public abstract class Person {
        private String name;
        private String email;
        private String phone;
    }
    //I think Customer is also derived from Person
    
    public class Customer extends Person{
        
    }
    
    public abstract class Employee extends Person {
        private int employeeID;
        private Date dateJoined;
        
        private Account account;
    }
    
    public class Receptionist extends Employee {
        public boolean createReservation() {
            return true;
        }
        
        public List<Customer> searchCustomer(String name) {
            return null;
        }
    }
    
    public class Manager extends Employee {
        public boolean addEmployee() {
            return true;
        }
        //I think addMenu should ne aslo there
        public boolean addMenu() {
            return true;
        }
    }
    
    public class Chef extends Employee {
        public boolean takeOrder() {
            return true;
        }
    }
    
    public class Waiter extends Employee {
        public boolean takeOrder() {
            return true;
        }
    }
    /*
     * Restaurant, Branch, Kitchen, TableChart: These classes represent the
     * top-level classes of the system: Restaurant, Branch, Kitchen, TableChart:
     * These classes represent the top-level classes of the system:
     */
    
    public class Kitchen {
        private String name;
        private Chef[] chefs;
        
        private boolean assignChef() {
            return true;
        }
    }
    
    public class Branch {
        private String name;
        private Address location;
        private Kitchen kitchen;
        
        public Address addTableChart() {
            return null;
        }
    }
    
    public class Restaurant {
        private String name;
        private List<Branch> branches;
        
        public boolean addBranch(Branch branch) {
            return true;
        }
    }
    
    public class TableChart {
        private int tableChartID;
        private byte[] tableChartImage;
        
        public bool print() {
            return true;
        }
    }
    /*
     * Table, TableSeat, and Reservation: Each table can have multiple seats and
     * customers can make reservations for tables:
     */
    
    public class Table {
        private int tableID;
        private TableStatus status;
        private int maxCapacity;
        private int locationIdentifier;
        
        private List<TableSeat> seats;
        
        public boolean isTableFree() {
            return true;
        }
        
        public boolean addReservation() {
            return true;
        }
        
        public static List<Table> search(int capacity, Date startTime) {
            // return all tables with the given capacity and availability
            return null;
        }
    }
    
    public class TableSeat {
        private int tableSeatNumber;
        private SeatType type;
        
        public boolean updateSeatType(SeatType type) {
            return true;
        }
    }
    //I think need to add
    public abstract class Notification {
        int id;
        boolean sentNotification() {
            return true;
        }
        
    }
    
    public class SMSNotification {
        int mobileNumber;
        //SMS send implementation
        boolean sentNotification() {
            return true;
        }
        
    }
    
    public class MailNotification {
        int mailId;
        //Mail send implementation
        boolean sentNotification() {
            return true;
        }
        
    }
    
    //I think Reservation will be using by receptionist, this is first thinkg to resever then waiter come into picture to createOrder
    public class Reservation {
        private int reservationID;
        private Date timeOfReservation;
        private int peopleCount;
        private ReservationStatus status;
        private String notes;
        private Date checkinTime;
        private Customer customer;
        
        private Table[] tables;
        private List<Notification> notifications;
        
        public boolean updatePeopleCount(int count) {
            return true;
        }
    }
    
    /*
     * Menu, MenuSection, and MenuItem: Each restaurant branch will have its own
     * menu, each menu will have multiple menu sections, which will contain menu
     * items:
     */
    public class MenuItem {
        private int menuItemID;
        private String title;
        private String description;
        private double price;
        
        public boolean updatePrice(double price) {
            return true;
        }
    }
    
    public class MenuSection {
        private int menuSectionID;
        private String title;
        private String description;
        private List<MenuItem> menuItems;
        
        public boolean addMenuItem(MenuItem menuItem) {
            return true;
        }
    }
    //I think Menu class will be used by Manager to add Menu
    public class Menu {
        private int menuID;
        private String title;
        private String description;
        private List<MenuSection> menuSections;
        
        public boolean addMenuSection(MenuSection menuSection) {
            return true;
        }
        
        public boolean print() {
            return true;
        }
    }
    
    /*
     * Order, Meal, and MealItem: Each order will have meals for table seats:
     */
    public class MealItem {
        private int mealItemID;
        private int quantity;
        private MenuItem menuItem;
        
        public boolean updateQuantity(int quantity) {
            return true;
        }
    }
    
    public class Meal {
        private int mealID;
        private TableSeat seat;
        private List<MenuItem> menuItems;// I donw know why menuItem not mealItem
        
        public boolean addMealItem(MealItem mealItem) {
            return true;
        }
    }
    // This class will be used by Waiter
    public class Order {
        private int OrderID;
        private OrderStatus status;
        private Date creationTime;
        
        private Meal[] meals;
        private Table table;
        private Check check;
        private Waiter waiter;
        private Chef chef;
        
        public boolean addMeal(Meal meal) {
            return true;
        }
        
        public boolean removeMeal(Meal meal) {
            return true;
        }
        
        public OrderStatus getStatus() {
            return null;
        }
        
        public boolean setStatus(OrderStatus status) {
            return true;
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
