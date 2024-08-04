package com.interview.systemdesign;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Date 05/08/2020
 * 
 * @author Mukesh Kumar Gupta
 *          Very good article mentioned:
 *          https://github.com/gopalbala/parkinglot
 *          
 *         Reference:https://www.youtube.com/watch?v=eV5Xh6jNfmU (read from
 *         here) Other reference
 *         https://www.geeksforgeeks.org/design-parking-lot-using-object-oriented-principles/?ref=lbp
 *         Category: Must Do, Amazon
 *         System Requirements#
We will focus on the following set of requirements while designing the parking lot:
1.  The parking lot should have multiple floors where customers can park their cars.
2.  The parking lot should have multiple entry and exit points.
3.  Customers can collect a parking ticket from the entry points and can pay the parking fee at the exit points on their way out.
4.  Customers can pay the tickets at the automated exit panel or to the parking attendant.
5.  Customers can pay via both cash and credit cards.
6.  Customers should also be able to pay the parking fee at the customer’s info portal on each floor. If the customer has paid at the info portal, they don’t have to pay at the exit.
7.  The system should not allow more vehicles than the maximum capacity of the parking lot. If the parking is full, the system should be able to show a message at the entrance panel and on the parking display board on the ground floor.
8.  Each parking floor will have many parking spots. The system should support multiple types of parking spots such as Compact, Large, Handicapped, Motorcycle, etc.
9.  The Parking lot should have some parking spots specified for electric cars. These spots should have an electric panel through which customers can pay and charge their vehicles.
10. The system should support parking for different types of vehicles like car, truck, van, motorcycle, etc.
11. Each parking floor should have a display board showing any free parking spot for each spot type.
12. The system should support a per-hour parking fee model. For example, customers have to pay $4 for the first hour, $3.5 for the second and third hours, and $2.5 for all the remaining hours.

 */

public class ParkingLotDesign {
    /*
     * Enums and Constants: Here are the required enums, data types, and constants:
     */
    public enum VehicleType {
        CAR, TRUCK, ELECTRIC, VAN, MOTORBIKE
    }
    
    public enum ParkingSpotType {
        HANDICAPPED, COMPACT, LARGE, MOTORBIKE, ELECTRIC
    }
    
    public enum AccountStatus {
        ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
    }
    
    public enum ParkingTicketStatus {
        ACTIVE, PAID, LOST
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
    
    /*
     * Account, Admin, and ParkingAttendant: These classes represent various people
     * that interact with our system: // For simplicity, we are not defining getter
     * and setter functions. The reader can // assume that all class attributes are
     * private and accessed through their respective // public getter methods and
     * modified only through their public methods function.
     * 
     */
    public abstract class Account {
        private String userName;
        private String password;
        private AccountStatus status;
        
        public boolean resetPassword();
    }
    
    public abstract class Person {
        private String name;
        private String email;
        private String phone;
    }
    
    public abstract class Employee extends Person {
        private int employeeID;
        private Date dateJoined;
        
        private Account account;
    }
    
    
    public class Admin extends Employee {
        public bool addParkingFloor(ParkingFloor floor);
        
        public bool addParkingSpot(String floorName, ParkingSpot spot);
        
        public bool addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard);
        
        public bool addCustomerInfoPanel(String floorName, CustomerInfoPanel infoPanel);
        
        public bool addEntrancePanel(EntrancePanel entrancePanel);
        
        public bool addExitPanel(ExitPanel exitPanel);
    }
    
    public class ParkingAttendant extends Employee {
        public bool processTicket(string TicketNumber);
    }
    
    /*
     * ParkingSpot: Here is the definition of ParkingSpot and all of its children
     * classes:
     */
    
    public abstract class ParkingSpot {
        private String number;
        private boolean free;
        private Vehicle vehicle;
        private final ParkingSpotType type;
        
        public boolean IsFree();
        
        public ParkingSpot(ParkingSpotType type) {
            this.type = type;
        }
        
        public boolean assignVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            free = false;
        }
        
        public boolean removeVehicle() {
            this.vehicle = null;
            free = true;
        }
    }
    
    public class HandicappedSpot extends ParkingSpot {
        public HandicappedSpot() {
            super(ParkingSpotType.HANDICAPPED);
        }
    }
    
    public class CompactSpot extends ParkingSpot {
        public CompactSpot() {
            super(ParkingSpotType.COMPACT);
        }
    }
    
    public class LargeSpot extends ParkingSpot {
        public LargeSpot() {
            super(ParkingSpotType.LARGE);
        }
    }
    
    public class MotorbikeSpot extends ParkingSpot {
        public MotorbikeSpot() {
            super(ParkingSpotType.MOTORBIKE);
        }
    }
    
    public class ElectricSpot extends ParkingSpot {
        public ElectricSpot() {
            super(ParkingSpotType.ELECTRIC);
        }
    }
    
    /*
     * Vehicle: Here is the definition for Vehicle and all of its child classes:
     */
    public abstract class Vehicle {
        private String licenseNumber;
        private final VehicleType type;
        private ParkingTicket ticket;
        
        public Vehicle(VehicleType type) {
            this.type = type;
        }
        
        public void assignTicket(ParkingTicket ticket) {
            this.ticket = ticket;
        }
    }
    
    public class Car extends Vehicle {
        public Car() {
            super(VehicleType.CAR);
        }
    }
    
    public class Van extends Vehicle {
        public Van() {
            super(VehicleType.VAN);
        }
    }
    
    public class Truck extends Vehicle {
        public Truck() {
            super(VehicleType.TRUCK);
        }
    }
    
    // Similarly we can define classes for Motorcycle and Electric vehicles
    
    /*
     * ParkingFloor: This class encapsulates a parking floor:
     */
    
    public class ParkingFloor {
        private String name;
        private HashMap<String, HandicappedSpot> handicappedSpots;
        private HashMap<String, CompactSpot> compactSpots;
        private HashMap<String, LargeSpot> largeSpots;
        private HashMap<String, MotorbikeSpot> motorbikeSpots;
        private HashMap<String, ElectricSpot> electricSpots;
        private HashMap<String, CustomerInfoPortal> infoPortals;
        private ParkingDisplayBoard displayBoard;
        
        public ParkingFloor(String name) {
            this.name = name;
        }
        
        public void addParkingSpot(ParkingSpot spot) {
            switch (spot.getType()) {
            case ParkingSpotType.HANDICAPPED:
                handicappedSpots.put(spot.getNumber(), spot);
                break;
            case ParkingSpotType.COMPACT:
                compactSpots.put(spot.getNumber(), spot);
                break;
            case ParkingSpotType.LARGE:
                largeSpots.put(spot.getNumber(), spot);
                break;
            case ParkingSpotType.MOTORBIKE:
                motorbikeSpots.put(spot.getNumber(), spot);
                break;
            case ParkingSpotType.ELECTRIC:
                electricSpots.put(spot.getNumber(), spot);
                break;
            default:
                print("Wrong parking spot type!");
            }
        }
        
        public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
            spot.assignVehicle(vehicle);
            switch (spot.getType()) {
            case ParkingSpotType.HANDICAPPED:
                updateDisplayBoardForHandicapped(spot);
                break;
            case ParkingSpotType.COMPACT:
                updateDisplayBoardForCompact(spot);
                break;
            case ParkingSpotType.LARGE:
                updateDisplayBoardForLarge(spot);
                break;
            case ParkingSpotType.MOTORBIKE:
                updateDisplayBoardForMotorbike(spot);
                break;
            case ParkingSpotType.ELECTRIC:
                updateDisplayBoardForElectric(spot);
                break;
            default:
                print("Wrong parking spot type!");
            }
        }
        
        private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
            if (this.displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
                // find another free handicapped parking and assign to displayBoard
                for (String key : handicappedSpots.keySet()) {
                    if (handicappedSpots.get(key).isFree()) {
                        this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
                    }
                }
                this.displayBoard.showEmptySpotNumber();
            }
        }
        
        private void updateDisplayBoardForCompact(ParkingSpot spot) {
            if (this.displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
                // find another free compact parking and assign to displayBoard
                for (String key : compactSpots.keySet()) {
                    if (compactSpots.get(key).isFree()) {
                        this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
                    }
                }
                this.displayBoard.showEmptySpotNumber();
            }
        }
        
        public void freeSpot(ParkingSpot spot) {
            spot.removeVehicle();
            switch (spot.getType()) {
            case ParkingSpotType.HANDICAPPED:
                freeHandicappedSpotCount++;
                break;
            case ParkingSpotType.COMPACT:
                freeCompactSpotCount++;
                break;
            case ParkingSpotType.LARGE:
                freeLargeSpotCount++;
                break;
            case ParkingSpotType.MOTORBIKE:
                freeMotorbikeSpotCount++;
                break;
            case ParkingSpotType.ELECTRIC:
                freeElectricSpotCount++;
                break;
            default:
                print("Wrong parking spot type!");
            }
        }
    }
    
    /*
     * ParkingDisplayBoard: This class encapsulates a parking display board:
     */
    public class ParkingDisplayBoard {
        private String id;
        private HandicappedSpot handicappedFreeSpot;
        private CompactSpot compactFreeSpot;
        private LargeSpot largeFreeSpot;
        private MotorbikeSpot motorbikeFreeSpot;
        private ElectricSpot electricFreeSpot;
        
        public void showEmptySpotNumber() {
            String message = "";
            if (handicappedFreeSpot.IsFree()) {
                message += "Free Handicapped: " + handicappedFreeSpot.getNumber();
            } else {
                message += "Handicapped is full";
            }
            message += System.lineSeparator();
            
            if (compactFreeSpot.IsFree()) {
                message += "Free Compact: " + compactFreeSpot.getNumber();
            } else {
                message += "Compact is full";
            }
            message += System.lineSeparator();
            
            if (largeFreeSpot.IsFree()) {
                message += "Free Large: " + largeFreeSpot.getNumber();
            } else {
                message += "Large is full";
            }
            message += System.lineSeparator();
            
            if (motorbikeFreeSpot.IsFree()) {
                message += "Free Motorbike: " + motorbikeFreeSpot.getNumber();
            } else {
                message += "Motorbike is full";
            }
            message += System.lineSeparator();
            
            if (electricFreeSpot.IsFree()) {
                message += "Free Electric: " + electricFreeSpot.getNumber();
            } else {
                message += "Electric is full";
            }
            
            Show(message);
        }
    }
    
    /*
     * ParkingLot: Our system will have only one object of this class. This can be
     * enforced by using the Singleton pattern. In software engineering, the
     * singleton pattern is a software design pattern that restricts the
     * instantiation of a class to only one object.
     */
    
    public class ParkingLot {
        private String name;
        private Location address;
        private ParkingRate parkingRate;
        
        private int compactSpotCount;
        private int largeSpotCount;
        private int motorbikeSpotCount;
        private int electricSpotCount;
        private final int maxCompactCount;
        private final int maxLargeCount;
        private final int maxMotorbikeCount;
        private final int maxElectricCount;
        
        private HashMap<String, EntrancePanel> entrancePanels;
        private HashMap<String, ExitPanel> exitPanels;
        private HashMap<String, ParkingFloor> parkingFloors;
        
        // all active parking tickets, identified by their ticketNumber
        private HashMap<String, ParkingTicket> activeTickets;
        
        // singleton ParkingLot to ensure only one object of ParkingLot in the system,
        // all entrance panels will use this object to create new parking ticket:
        // getNewParkingTicket(),
        // similarly exit panels will also use this object to close parking tickets
        private static ParkingLot parkingLot = null;
        
        // private constructor to restrict for singleton
        private ParkingLot() {
            // 1. initialize variables: read name, address and parkingRate from database
            // 2. initialize parking floors: read the parking floor map from database,
            // this map should tell how many parking spots are there on each floor. This
            // should also initialize max spot counts too.
            // 3. initialize parking spot counts by reading all active tickets from database
            // 4. initialize entrance and exit panels: read from database
        }
        
        // static method to get the singleton instance of ParkingLot
        public static ParkingLot getInstance() {
            if (parkingLot == null) {
                parkingLot = new ParkingLot();
            }
            return parkingLot;
        }
        
        // note that the following method is 'synchronized' to allow multiple entrances
        // panels to issue a new parking ticket without interfering with each other
        public synchronized ParkingTicket getNewParkingTicket(Vehicle vehicle) throws ParkingFullException {
            if (this.isFull(vehicle.getType())) {
                throw new ParkingFullException();
            }
            ParkingTicket ticket = new ParkingTicket();
            vehicle.assignTicket(ticket);
            ticket.saveInDB();
            // if the ticket is successfully saved in the database, we can increment the
            // parking spot count
            this.incrementSpotCount(vehicle.getType());
            this.activeTickets.put(ticket.getTicketNumber(), ticket);
            return ticket;
        }
        
        public boolean isFull(VehicleType type) {
            // trucks and vans can only be parked in LargeSpot
            if (type == VehicleType.Truck || type == VehicleType.Van) {
                return largeSpotCount >= maxLargeCount;
            }
            
            // motorbikes can only be parked at motorbike spots
            if (type == VehicleType.Motorbike) {
                return motorbikeSpotCount >= maxMotorbikeCount;
            }
            
            // cars can be parked at compact or large spots Imp
            if (type == VehicleType.Car) {
                return (compactSpotCount + largeSpotCount) >= (maxCompactCount + maxLargeCount);
            }
            
            // electric car can be parked at compact, large or electric spots
            return (compactSpotCount + largeSpotCount + electricSpotCount) >= (maxCompactCount + maxLargeCount
                    + maxElectricCount);
        }
        
        // increment the parking spot count based on the vehicle type
        private boolean incrementSpotCount(VehicleType type) {
            if (type == VehicleType.Truck || type == VehicleType.Van) {
                largeSpotCount++;
            } else if (type == VehicleType.Motorbike) {
                motorbikeSpotCount++;
            } else if (type == VehicleType.Car) {
                if (compactSpotCount < maxCompactCount) {
                    compactSpotCount++;
                } else {
                    largeSpotCount++;
                }
            } else { // electric car
                if (electricSpotCount < maxElectricCount) {
                    electricSpotCount++;
                } else if (compactSpotCount < maxCompactCount) {
                    compactSpotCount++;
                } else {
                    largeSpotCount++;
                }
            }
        }
        
      public boolean isFull() { //Imp
        for (String key : parkingFloors.keySet()) {
          if (!parkingFloors.get(key).isFull()) {
            return false;
          }
        }
        return true;
      }
        
        public void addParkingFloor(ParkingFloor floor) {
            /* store in database */ }
            
        public void addEntrancePanel(EntrancePanel entrancePanel) {
            /* store in database */ }
            
        public void addExitPanel(ExitPanel exitPanel) {
            /* store in database */ }
            
        /*Missing data
        here check
        on site*/
    }
    
    public static void main(String[] args) {

        
    }
}
