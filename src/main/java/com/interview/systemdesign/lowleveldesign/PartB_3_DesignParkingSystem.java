package com.interview.systemdesign.lowleveldesign;

/*
 * Reference: https://leetcode.com/problems/design-parking-system
 * Category: Easy
 *
 * Design a parking system for a parking lot. The parking lot has three kinds of parking spaces:
 * big, medium, and small, with a fixed number of slots for each size.
 *
 * Implement the ParkingSystem class:
 *
 * ParkingSystem(int big, int medium, int small):
 * Initializes the ParkingSystem object with the number of slots for each parking space provided
 * as part of the constructor.
 *
 * boolean addCar(int carType):
 * Checks whether there is a parking space of carType for the car that wants to get into the
 * parking lot. carType can be of three kinds: big, medium, or small, represented by 1, 2,
 * and 3 respectively. A car can only park in a parking space of its carType. If there is no
 * space available, return false; otherwise, park the car in that size space and return true.
 *
 * Example 1:
 *
 * Input:
 * ["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
 * [[1, 1, 0], [1], [2], [3], [1]]
 *
 * Output:
 * [null, true, true, false, false]
 *
 * Explanation:
 * ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
 * parkingSystem.addCar(1); // return true because there is 1 available slot for a big car
 * parkingSystem.addCar(2); // return true because there is 1 available slot for a medium car
 * parkingSystem.addCar(3); // return false because there is no available slot for a small car
 * parkingSystem.addCar(1); // return false because there is no available slot for a big car.
 *                            // It is already occupied.
 *
 * Constraints:
 * 0 <= big, medium, small <= 1000
 * carType is 1, 2, or 3
 * At most 1000 calls will be made to addCar.
 *
 * Note: Deried question
 * A small car can fit in medium or big spaces if the small space is not available,
 * and the same applies for medium cars fitting in big spaces.
 */
public class PartB_3_DesignParkingSystem {
    private int big;
    private int medium;
    private int small;

    public PartB_3_DesignParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
        
    }
    
    public boolean addCar(int carType) {
        if (carType == 1 && big > 0) {
            big--;
            return true;
            
        } else if (carType == 2 && medium > 0) {
            medium--;
            return true;
            
        } else if (carType == 3 && small > 0) {
            small--;
            return true;
            
        } else {
            return false;
        }
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
