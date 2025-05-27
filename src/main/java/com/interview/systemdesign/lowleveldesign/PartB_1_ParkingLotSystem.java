package com.interview.systemdesign.lowleveldesign;

/*
        Here’s a solution to the Parking Lot system design problem using object-oriented principles in Java.

        Components:
        1. Vehicle: Abstract base class, with subclasses for Motorcycle, Car, and Truck.
        2. ParkingLot: Manages multiple levels and overall parking.
        3. ParkingLevel: Represents a floor in the parking lot with parking spaces.
        4. ParkingSpace: Represents an individual parking spot.
        5. VehicleType: Enum to categorize vehicles.

        Design:
        1. Vehicle Class and Subclasses:
           - Represents different vehicle types like motorcycles, cars, and trucks.

        2. ParkingLot:
           - Manages multiple parking levels.

        3. ParkingLevel:
           - Manages parking spaces on each level.

        4. ParkingSpace:
           - Represents individual parking spots.

        5. Parking Management Methods:
           - Handles adding and removing vehicles.

        Key Considerations:
        1. Scalability:
           - The design allows easy addition of new parking levels or spaces.

        2. Vehicle Size Handling:
           - Each ParkingSpace can check if a vehicle fits based on its size.

        3. Nearest Available Parking:
           - Parking is allocated in the nearest available space.

        4. Space Allocation:
           - Parking space types are allocated based on vehicle size.

        Improvements:
        1. Add features like automated parking fee calculation.
        2. Optimize the search for available spaces.
        3. Handle cases like reserving spaces for VIPs or handicapped parking.
*/
// Enum to define the types of vehicles
enum VehicleType {
    MOTORCYCLE, CAR, TRUCK
}

// Vehicle base class
abstract class Vehicle {
    protected VehicleType type;
    protected String licensePlate;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}

// Subclasses for different vehicle types
class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}

// Parking space class
class ParkingSpace {
    private int spaceId;
    private VehicleType size;
    private Vehicle currentVehicle;

    public ParkingSpace(int spaceId, VehicleType size) {
        this.spaceId = spaceId;
        this.size = size;
        this.currentVehicle = null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return currentVehicle == null && size.ordinal() >= vehicle.getType().ordinal();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (canFitVehicle(vehicle)) {
            this.currentVehicle = vehicle;
            return true;
        }
        return false;
    }

    public void removeVehicle() {
        currentVehicle = null;
    }

    public boolean isAvailable() {
        return currentVehicle == null;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}

// Parking level class
class ParkingLevel {
    private int levelId;
    private ParkingSpace[] spaces;

    public ParkingLevel(int levelId, int numSpaces) {
        this.levelId = levelId;
        spaces = new ParkingSpace[numSpaces];

        // Initialize spaces, e.g., half for cars, some for motorcycles, some for trucks
        for (int i = 0; i < numSpaces; i++) {
            if (i < numSpaces / 4) {
                spaces[i] = new ParkingSpace(i, VehicleType.MOTORCYCLE);
            } else if (i < numSpaces / 2) {
                spaces[i] = new ParkingSpace(i, VehicleType.CAR);
            } else {
                spaces[i] = new ParkingSpace(i, VehicleType.TRUCK);
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpace space : spaces) {
            if (space.parkVehicle(vehicle)) {
                System.out.println("Vehicle parked at level " + levelId + ", space ID: " + space);
                return true;
            }
        }
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpace space : spaces) {
            if (!space.isAvailable() && space.getCurrentVehicle().equals(vehicle)) {
                space.removeVehicle();
                System.out.println("Vehicle removed from level " + levelId);
                break;
            }
        }
    }
}

// Parking lot class to manage multiple levels
class ParkingLot {
    private ParkingLevel[] levels;

    public ParkingLot(int numLevels, int spacesPerLevel) {
        levels = new ParkingLevel[numLevels];

        // Initialize levels
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new ParkingLevel(i, spacesPerLevel);
        }
    }

    // Park vehicle in the nearest available spot
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }
        System.out.println("Parking lot is full.");
        return false;
    }

    // Remove vehicle from parking lot
    public void removeVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            level.removeVehicle(vehicle);
        }
    }
}

// Example usage
public class PartB_1_ParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, 10); // 3 levels, 10 spaces per level

        Vehicle car1 = new Car("ABC123");
        Vehicle truck1 = new Truck("DEF456");
        Vehicle motorcycle1 = new Motorcycle("GHI789");

        parkingLot.parkVehicle(car1);          // Park a car
        parkingLot.parkVehicle(truck1);        // Park a truck
        parkingLot.parkVehicle(motorcycle1);   // Park a motorcycle

        parkingLot.removeVehicle(car1);        // Remove the car
    }
}
