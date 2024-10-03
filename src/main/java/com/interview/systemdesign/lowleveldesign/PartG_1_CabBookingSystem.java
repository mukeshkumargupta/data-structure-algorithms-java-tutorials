package com.interview.systemdesign.lowleveldesign;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Here’s a complete solution for designing a cab booking system similar to Uber or Lyft using object-oriented principles in Java.
 * This implementation includes the necessary classes and methods to support user and driver interactions, ride management, and basic location handling.
 *
 * Explanation of the Code:
 *
 * 1. Location Class:
 *    - Represents geographical coordinates and includes a method to calculate the distance between two locations using the Haversine formula.
 *
 * 2. User Class:
 *    - Represents a user who can request rides. It maintains a ride history and allows location updates.
 *
 * 3. Driver Class:
 *    - Represents a driver with attributes for their vehicle and availability status. It includes methods to update their location and availability.
 *
 * 4. Ride Class:
 *    - Represents a ride request, including user and driver information, pickup and drop-off locations, and methods to calculate the fare and update the ride status.
 *
 * 5. CabBookingSystem Class:
 *    - Manages drivers and rides, allowing users to request rides, drivers to accept them, and to complete rides.
 *
 * 6. Main Class:
 *    - Demonstrates usage of the system by creating drivers, a user, and handling a ride request and acceptance.
 *
 * Considerations:
 * - Optimizing Ride Assignment: The system finds the nearest available driver to the user's pickup location when a ride is requested.
 * - Real-time Location Tracking: The current design allows for location updates, but in a real-world scenario, this would likely be handled with a more complex solution using APIs for real-time tracking.
 * - Error Handling: The example code does basic checks but could be expanded with more robust error handling in a production environment.
 *
 * This solution provides a solid foundation for a cab booking system and can be expanded further based on specific requirements and use cases.
 */

// Class to represent geographical coordinates
class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // Calculate distance between two locations (Haversine formula)
    public double calculateDistanceComplexMethod(Location other) {
        final int R = 6371; // Radius of the Earth in km
        double latDistance = Math.toRadians(other.getLatitude() - this.latitude);
        double lonDistance = Math.toRadians(other.getLongitude() - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    // Calculate distance between two locations (Euclidean formula)
    public double calculateDistance(Location other) {
        double latDistance = other.getLatitude() - this.latitude;
        double lonDistance = other.getLongitude() - this.longitude;
        return Math.sqrt(latDistance * latDistance + lonDistance * lonDistance); // Distance in arbitrary units
    }
}

// Class to represent a User
class User {
    private String userId;
    private String name;
    private Location location;
    private List<Ride> rideHistory;

    public User(String name, Location location) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.rideHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void updateLocation(Location newLocation) {
        this.location = newLocation;
    }

    public void addRideToHistory(Ride ride) {
        rideHistory.add(ride);
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }
}

// Class to represent a Driver
class Driver {
    private String driverId;
    private String name;
    private String vehicle;
    private Location location;
    private boolean isAvailable;

    public Driver(String name, String vehicle, Location location) {
        this.driverId = UUID.randomUUID().toString();
        this.name = name;
        this.vehicle = vehicle;
        this.location = location;
        this.isAvailable = true;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public Location getLocation() {
        return location;
    }

    public void updateLocation(Location newLocation) {
        this.location = newLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean availability) {
        this.isAvailable = availability;
    }
}

// Class to represent a Ride
class Ride {
    private String rideId;
    private User user;
    private Driver driver;
    private Location pickupLocation;
    private Location dropoffLocation;
    private double fare;
    private String status; // e.g., "Requested", "In Progress", "Completed"

    public Ride(User user, Driver driver, Location pickupLocation, Location dropoffLocation) {
        this.rideId = UUID.randomUUID().toString();
        this.user = user;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.fare = 0.0;
        this.status = "Requested";
    }

    public String getRideId() {
        return rideId;
    }

    public User getUser() {
        return user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void calculateFare() {
        double distance = pickupLocation.calculateDistance(dropoffLocation);
        this.fare = distance * 2.0; // For example, $2.0 per km
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }
}

// Class to represent the Cab Booking System
class CabBookingSystem {
    private List<Driver> drivers;
    private List<Ride> rides;

    public CabBookingSystem() {
        this.drivers = new ArrayList<>();
        this.rides = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public Ride requestRide(User user, Location pickupLocation, Location dropoffLocation) {
        // Find the nearest available driver
        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                double distance = driver.getLocation().calculateDistance(pickupLocation);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestDriver = driver;
                }
            }
        }

        if (nearestDriver == null) {
            System.out.println("No drivers available at the moment.");
            return null;
        }

        // Create a new ride
        Ride ride = new Ride(user, nearestDriver, pickupLocation, dropoffLocation);
        rides.add(ride);
        nearestDriver.setAvailability(false); // Mark driver as unavailable
        user.addRideToHistory(ride); // Add ride to user's history
        System.out.println("Ride requested successfully with Driver: " + nearestDriver.getName());

        return ride;
    }

    public void acceptRide(Driver driver, Ride ride) {
        if (ride.getDriver() == driver) {
            ride.updateStatus("In Progress");
            System.out.println("Driver " + driver.getName() + " accepted the ride.");
            // Calculate fare
            ride.calculateFare();
        }
    }

    public void completeRide(Ride ride) {
        ride.updateStatus("Completed");
        System.out.println("Ride completed. Total fare: $" + ride.getFare());
        // Mark driver as available again
        for (Driver driver : drivers) {
            if (driver.getDriverId().equals(ride.getDriver().getDriverId())) {
                driver.setAvailability(true);
                break;
            }
        }
    }
}

// Example usage
public class PartGCabBookingSystem {
    public static void main(String[] args) {
        CabBookingSystem cabBookingSystem = new CabBookingSystem();

        // Create some drivers
        Driver driver1 = new Driver("John Doe", "Toyota Camry", new Location(40.7128, -74.0060));
        Driver driver2 = new Driver("Jane Smith", "Honda Accord", new Location(40.7138, -74.0070));

        cabBookingSystem.addDriver(driver1);
        cabBookingSystem.addDriver(driver2);

        // Create a user
        User user = new User("Alice", new Location(40.7100, -74.0000));

        // User requests a ride
        Ride ride = cabBookingSystem.requestRide(user, user.getLocation(), new Location(40.7140, -74.0020));

        // Driver accepts the ride
        if (ride != null) {
            cabBookingSystem.acceptRide(driver1, ride);
            // Complete the ride
            cabBookingSystem.completeRide(ride);
        }
    }
}
