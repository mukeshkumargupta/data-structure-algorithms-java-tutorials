package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=qGR7KoGpqrU&list=PLIA-9QRQ0RqHcTCuIusS1G1LikGoD3WxM&index=4&t=1s
 * Reference: https://www.geeksforgeeks.org/design-movie-ticket-booking-system-like-bookmyshow/?ref=rp
 * Difficulty: Hard
 * Company: Must Do, Google, Amazon, Facebook
 */

public class PartJ_2_BookMyShowDesign {
    
/*
 * Enums, data types, and constants: Here are the required enums, data types, and constants:
 */
    public enum BookingStatus {
        REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELED, ABANDONED
      }

      public enum SeatType {
        REGULAR, PREMIUM, ACCESSIBLE, SHIPPED, EMERGENCY_EXIT, OTHER
      }

      public enum AccountStatus {
        ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
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
       * Account, Customer, Admin, FrontDeskOfficer, and Guest: These classes represent the different people that interact with our system:
// For simplicity, we are not defining getter and setter functions. The reader can
// assume that all class attributes are private and accessed through their respective
// public getter method and modified only through their public setter method.

       */
      
      public class Account {
          private String id;
          private String password;
          private AccountStatus status;

          public boolean resetPassword();
        }

        public abstract class Person {
          private String name;
          private Address address;
          private String email;
          private String phone;

          private Account account;
        }

        public class Customer extends Person {
          public boolean makeBooking(Booking booking);
          public List<Booking> getBookings();
        }

        public class Admin extends Person {
          public boolean addMovie(Movie movie);
          public boolean addShow(Show show);
          public boolean blockUser(Customer customer);
        }

        public class FrontDeskOfficer extends Person {
          public boolean createBooking(Booking booking);
        }

        public class Guest {
          public bool registerAccount();
        }

        /*
         * Show and Movie: A movie will have many shows:
         */
        public class Show {
            private int showId;
            private Date createdOn;
            private Date startTime;
            private Date endTime;
            private CinemaHall playedAt;
            private Movie movie;
          }

          public class Movie {
            private String title;
            private String description;
            private int durationInMins;
            private String language;
            private Date releaseDate;
            private String country;
            private String genre;
            private Admin movieAddedBy;

            private List<Show> shows;
            public List<Show> getShows();
          }

          /*
           * Booking, ShowSeat, and Payment: Customers will reserve seats with a booking and make a payment:
           */
          public class Booking {
              private String bookingNumber;
              private int numberOfSeats;
              private Date createdOn;
              private BookingStatus status;

              private Show show;
              private List<ShowSeat> seats;
              private Payment payment;

              public boolean makePayment(Payment payment);
              public boolean cancel();
              public boolean assignSeats(List<ShowSeat> seats);
            }

            public class ShowSeat extends CinemaHallSeat{
              private int showSeatId;
              private boolean isReserved;
              private double price;
            }

            public class Payment {
              private double amount;
              private Date createdOn;
              private int transactionId;
              private PaymentStatus status;
            }

            /*
             * City, Cinema, and CinemaHall: Each city can have many cinemas and each cinema can have many cinema halls:
             */
            public class City {
                private String name;
                private String state;
                private String zipCode;
              }

              public class Cinema {
                private String name;
                private int totalCinemaHalls;
                private Address location;

                private List<CinemaHall> halls;
              }

              public class CinemaHall {
                private String name;
                private int totalSeats;

                private List<CinemaHallSeat> seats;
                private List<Show> shows;
              }

              /*
               * Search interface and Catalog: Catalog will implement Search to facilitate searching of products.
               */
              public interface Search {
                  public List<Movie> searchByTitle(String title);
                  public List<Movie> searchByLanguage(String language);
                  public List<Movie> searchByGenre(String genre);
                  public List<Movie> searchByReleaseDate(Date relDate);
                  public List<Movie> searchByCity(String cityName);
                }

                public class Catalog implements Search {
                   HashMap<String, List<Movie>> movieTitles;
                   HashMap<String, List<Movie>> movieLanguages;
                   HashMap<String, List<Movie>> movieGenres;
                   HashMap<Date, List<Movie>> movieReleaseDates;
                   HashMap<String, List<Movie>> movieCities;

                  public List<Movie> searchByTitle(String title) {
                    return movieTitles.get(title);
                  }

                  public List<Movie> searchByLanguage(String language) {
                    return movieLanguages.get(language);
                  }

                  //...

                  public List<Movie> searchByCity(String cityName) {
                    return movieCities.get(cityName);
                  }
                }

                /*
                 * Concurrency#
How to handle concurrency; such that no two users are able to book the same seat? We can use transactions in SQL databases to avoid any clashes. For example, if we are using SQL server we can utilize Transaction Isolation Levels to lock the rows before we update them. Note: within a transaction, if we read rows we get a write-lock on them so that they can�t be updated by anyone else. Here is the sample code:
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

BEGIN TRANSACTION;

    -- Suppose we intend to reserve three seats (IDs: 54, 55, 56) for ShowID=99 
    Select * From ShowSeat where ShowID=99 && ShowSeatID in (54, 55, 56) && isReserved=0 

    -- if the number of rows returned by the above statement is NOT three, we can return failure to the user.
    update ShowSeat table...
    update Booking table ...

COMMIT TRANSACTION;
�Serializable� is the highest isolation level and guarantees safety from Dirty, Nonrepeatable, and Phantoms reads.
Once the above database transaction is successful, we can safely assume that the reservation has been marked successfully and no two customers will be able to reserve the same seat.
Here is the sample Java code:

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Customer extends Person {

  public boolean makeBooking(Booking booking) {
    List<ShowSeat> seats = booking.getSeats();
    Integer seatIds[] = new Integer[seats.size()];
    int index = 0;
    for(ShowSeat seat : seats) {
      seatIds[index++] = seat.getShowSeatId();
    }

    Connection dbConnection = null;
    try {
      dbConnection = getDBConnection();
      dbConnection.setAutoCommit(false);
      // �Serializable� is the highest isolation level and guarantees safety from
      // Dirty, Nonrepeatable, and Phantoms reads
      dbConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

      Statement st = dbConnection.createStatement();
      String selectSQL = "Select * From ShowSeat where ShowID=? && ShowSeatID in (?) && isReserved=0";
      PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
      preparedStatement.setInt(1, booking.getShow().getShowId());
      Array array = dbConnection.createArrayOf("INTEGER", seatIds);
      preparedStatement.setArray(2, array);

      ResultSet rs = preparedStatement.executeQuery();
      // With TRANSACTION_SERIALIZABLE all the read rows will have the write lock, so we can
      // safely assume that no one else is modifying them.
      if (rs.next()) {
        rs.last(); // move to the last row, to calculate the row count
        int rowCount = rs.getRow();
        // check if we have expected number of rows, if not, this means another process is
        // trying to process at least one of the same row, if that is the case we
        // should not process this booking.
        if(rowCount == seats.size()) {
          // update ShowSeat table...
          // update Booking table ...
          dbConnection.commit();
          return true;
        }
      }
    } catch (SQLException e) {
      dbConnection.rollback();
      System.out.println(e.getMessage());
    }
    return false;
  }
} 
Read JDBC Transaction Isolation Levels for details.


                 */
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
