package com.interview.systemdesign;

import java.util.*;

/*
 * Reference: https://www.geeksforgeeks.org/design-online-hotel-booking-system-like-oyo-rooms/?ref=rp
 * Status: done
 * Main Classes :
1. User
2. Room
3. Hotel
4. Booking
5. Address
6. Facilities
 */
public class OnlineHotelBookingSystemLikeOYORoomsDesign {
 // Java code skeleton to design an online hotel 
 // booking system. 
 public enum RoomStatus { 
     EMPTY, 
     NOT_EMPTY 
 } 
   
 public enum RoomType { 
     SINGLE, 
     DOUBLE, 
     TRIPLE 
 } 
   
 public enum PaymentStatus { 
     PAID, 
     UNPAID 
 } 
   
 public enum Facility { 
     LIFT,
     POWER_BACKUP, 
     HOT_WATERR, 
     BREAKFAST_FREE,
     SWIMMING_POOL 
 } 
   
 class User { 
     int userId; 
     String name; 
     Date dateOfBirth; 
     String mobNo; 
     String emailId; 
     String sex; 
 } 
   
 // For the room in any hotel 
 class Room { 
     int roomId; // roomNo 
     int hotelId; 
     RoomType roomType; 
     RoomStatus roomStatus; 
 } 
   
 class Hotel { 
   
     int hotelId; 
     String hotelName; 
     Address adress; 
   
     // hotel contains the list of rooms 
     List<Room> rooms; 
     float rating; 
     Facilities facilities; 
 } 
   
 // a new booking is created for each booking  
 // done by any user 
 class Booking { 
     int bookingId; 
     int userId; 
     int hotelId; 
   
     // We are assuming that in a single  
     // booking we can book only the rooms  
     // of a single hotel 
     //List<Room> bookedRooms;  I think from hotel u can fetch all room so it is not required.
       
     int amount; 
     PaymentStatus status_of_payment; 
     Date bookingTime; 
     Duration duration; 
 } 
   
 class Address { 
     String city; 
     String pinCode; 
     String state; 
     String streetNo; 
     String landmark; 
 } 
   
 class Duration { 
     Date from; 
     Date to; 
 } 
   
 class Facilities { 
     List<Facility> facilitiesList; 
 } 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
