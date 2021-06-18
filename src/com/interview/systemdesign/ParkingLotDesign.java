package com.interview.systemdesign;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date 05/08/2020
 * 
 * @author Mukesh Kumar Gupta
 *
 * Reference:https://www.youtube.com/watch?v=eV5Xh6jNfmU (read from here)
 * Other reference https://www.geeksforgeeks.org/design-parking-lot-using-object-oriented-principles/?ref=lbp
 */

class ParkingSlot {
    long id;
    VehicalType type;
    State state;
    long floorNo;
}

enum VehicalType {
    Small, Medium, Large
}

enum State {
    Occupied, unoccupied, Underrepair
}

class Ticket {
    long id;
    Date entryTime, exitTime;
    ParkingSlot slot;
    String vehicalNumber;
    
}

class ParkingService {
    // returns ticket
    public Ticket entry(VehicalType type, String vehicalNumber) {
        return null;
        
    }
    
    // return price
    double exit(long ticketId) {
        return 0;
    }
}


class PriceService {
    
    public double calculate(VehicalType type, Date entryTime, Date exitTime ) {
        return 0;
        
    }
}

//Need to handle concurrency
class SlotService {
    //Better take concurrent hash map to handle concurrency
    //Here Long is index when it was inserted in slotList 
    //First init map with unoccupied slot and allocate if free in map
    Map<Long, ParkingSlot> statusMap = new ConcurrentHashMap<>();
    //For fast lookup, take priority queue(heap) which contains only free slot, sort it by first floor no and then by slot no.
    public ParkingSlot allocate(VehicalType type) {
          return null;
          
      }
    
    public boolean unallocate(long parkingSlotId) {
        return false;
        
    }
}

//To handle distributed environment
//Large number of parking lot
//Add parkingLotId in Ticket and ParkingSlot and do sharding based on parkingLotid

public class ParkingLotDesign {
    
    public static void main(String[] args) {
        ParkingService ps = new ParkingService();
        ps.entry(VehicalType.Small, "124");
        
    }
}
