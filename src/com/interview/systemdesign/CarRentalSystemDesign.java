package com.interview.systemdesign;

import java.util.*;
/*
 * Category: Amazon, Must Do
 * 
 * System Requirements#
We will focus on the following set of requirements while designing our Car Rental System:
1.  The system will support the renting of different automobiles like cars, trucks, SUVs, vans, and motorcycles.
2.  Each vehicle should be added with a unique barcode and other details, including a parking stall number which helps to locate the vehicle.
3.  The system should be able to retrieve information like which member took a particular vehicle or what vehicles have been rented out by a specific member.
4.  The system should collect a late-fee for vehicles returned after the due date.
5.  Members should be able to search the vehicle inventory and reserve any available vehicle.
6.  The system should be able to send notifications whenever the reservation is approaching the pick-up date, as well as when the vehicle is nearing the due date or has not been returned within the due date.
7.  The system will be able to read barcodes from vehicles.
8.  Members should be able to cancel their reservations.
9.  The system should maintain a vehicle log to track all events related to the vehicles.
10. Members can add rental insurance to their reservation.
11. Members can rent additional equipment, like navigation, child seat, ski rack, etc.
12. Members can add additional services to their reservation, such as roadside assistance, additional driver, wifi, etc.

 */
public class CarRentalSystemDesign {
    /*Here is the high-level definition for the classes described above.
    Enums, data types and constants: Here are the required enums, data types, and constants:*/
    public enum BillItemType {
      BASE_CHARGE, ADDITIONAL_SERVICE, FINE, OTHER
    }

    public enum VehicleLogType {
      ACCIDENT, FUELING, CLEANING_SERVICE, OIL_CHANGE, REPAIR, OTHER
    }

    public enum VanType {
      PASSENGER, CARGO
    }

    public enum CarType {
      ECONOMY, COMPACT, INTERMEDIATE, STANDARD, FULL_SIZE, PREMIUM, LUXURY
    }

    public enum VehicleStatus {
      AVAILABLE, RESERVED, LOANED, LOST, BEING_SERVICED, OTHER
    }

    public enum ReservationStatus {
      ACTIVE, PENDING, CONFIRMED, COMPLETED, CANCELLED, NONE
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

    public class Person {
      private String name;
      private Address address;
      private String email;
      private String phone;
    }
    Account, Member, Receptionist, and Additional Driver: These classes represent different people that interact with our system:
    // For simplicity, we are not defining getter and setter functions. The reader can
    // assume that all class attributes are private and accessed through their respective
    // public getter method and modified only through their public setter method.

    public abstract class Account {
      private String id;
      private String password;
      private AccountStatus status;
      private Person person;

      public boolean resetPassword();
    }

    public class Member extends Account {
      private int totalVehiclesReserved;

      public List<VehicleReservation> getReservations();
    }

    public class Receptionist extends Account {
      private Date dateJoined;

      public List<Member> searchMember(String name);
    }

    public class AdditionalDriver {
      private String driverID;
      private Person person;
    }
    //CarRentalSystem and CarRentalLocation: These classes represent the top level classes:
    public class CarRentalLocation {
      private String name;
      private Address location;

      public Address getLocation();
    }

    public class CarRentalSystem {
      private String name;
      private List<CarRentalLocation> locations;

      public boolean addNewLocation(CarRentalLocation location);
    }
    //Vehicle, VehicleLog, and VehicleReservation: To encapsulate a vehicle, log, and reservation. The VehicleReservation class will be responsible for processing the reservation and return of a vehicle:
    public abstract class Vehicle {
      private String licenseNumber;
      private String stockNumber;
      private int passengerCapacity;
      private String barcode;
      private boolean hasSunroof;
      private VehicleStatus status;
      private String model;
      private String make;
      private int manufacturingYear;
      private int mileage;

      private List<VehicleLog> log;

      public boolean reserveVehicle();

      public boolean returnVehicle();
    }

    public class Car extends Vehicle {
      private CarType type;
    }

    public class Van extends Vehicle {
      private VanType type;
    }

    public class Truck extends Vehicle {
      private String type;
    }

    // We can have similar definition for other vehicle types

    //...

    public class VehicleLog {
      private String id;
      private VehicleLogType type;
      private String description;
      private Date creationDate;

      public bool update();

      public List<VehicleLogType> searchByLogType(VehicleLogType type);
    }

    public class VehicleReservation {
      private String reservationNumber;
      private Date creationDate;
      private ReservationStatus status;
      private Date dueDate;
      private Date returnDate;
      private String pickupLocationName;
      private String returnLocationName;

      private int customerID;
      private Vehicle vehicle;
      private Bill bill;
      private List<AdditionalDriver> additionalDrivers;
      private List<Notification> notifications;
      private List<RentalInsurance> insurances;
      private List<Equipment> equipments;
      private List<Service> services;

      public static VehicleReservation fetchReservationDetails(String reservationNumber);

      public List<Passenger> getAdditionalDrivers();
    }
    //VehicleInventory and Search: VehicleInventory will implement an interface ‘Search’ to facilitate the searching of vehicles:
    public interface Search {
      public List<Vehicle> searchByType(String type);
      public List<Vehicle> searchByModel(String model);
    }

    public class VehicleInventory implements Search {
      private HashMap<String, List<Vehicle>> vehicleTypes;
      private HashMap<String, List<Vehicle>> vehicleModels;

    //Note: Why two map, why not list of vehical, and from vecal class model and type can be checked?

      public List<Vehicle> searchByType(String query) {
        // return all vehicles of the given type.
        return vehicleTypes.get(query);
      }

      public List<Vehicle> searchByModel(String query) {
        // return all vehicles of the given model.
        return vehicleModels.get(query);
      }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
