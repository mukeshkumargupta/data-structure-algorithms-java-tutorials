package com.interview.systemdesign.lowleveldesign;

import java.util.*;
/*
 * Reference: https://www.geeksforgeeks.org/design-scalable-system-like-foursquare/?ref=rp
 * Status: Done
 * 
 */

public class PartT_2_DesignCityGuideSystemlikeFoursquareDesign {
 // Java Program to illustrate the Design 
    public enum UserStatus{ 
        LOGIN, 
        LOGOUT 
        //... 
    } 
      
    public enum ReviewStatus { 
        LIKE, 
        DISLIKE 
    } 
      
    public enum LocationStatus{ 
        PUBLIC, 
        PRIVATE, 
        //... 
    } 
      
    public enum LocationCategory { 
        EVENT, 
        EDUCATION, 
        FOOD, 
        HEALTH, 
        ENTERTAINTMENT, 
        //.... 
    } 
      
      
    public class AddressDetails { 
        private String street; 
        private String city; 
        private String country; 
        private double latitude; 
        private double longitude; 
        //... 
    } 
      
    public class AccountDetails { 
        private Date createdTime; 
        private AccountStatus status; 
        private boolean updateAccountStatus(AccountStatus accountStatus); 
        //... 
    } 
      
    public class Comment { 
        private Integer id; 
        private User addedBy; 
        private Date addedDate; 
        private String comment; 
      
        public boolean updateComment(String comment); 
        //... 
    } 
      
    public class Location { 
          private Integer id; 
        private User createdBy; 
        private LocationCategory locationCategory; 
        private LocationStatus locationStatus; 
      
        private HashSet<Integer> pictures; 
        private HashSet<Integer> userLikes; 
        private HashSet<Integer> userDisLikes; 
        private HashSet<Integer> userComments; 
        //... 
    } 
      
    public class User { 
        private UserStatus userStatus; 
        private Double latitude; 
        private Double longitude; 
        private AccountDetails accountDetails; 
        private UserRelations userRelations; 
      
        public List<User> searchLocation(query); 
        //... 
    } 
      
    public LoginUser extends Users{ 
        private int id; 
        private String password; 
        private String nickname; 
        private String email; 
          
        private boolean updatePassword(); 
        public boolean addPlace(Place place); 
        public boolean updatePlace(Place place); 
        public boolean addComment(Integer placeID, String comment); 
        public boolean reviewPlace(Integer placeID, ReviewStatus status); 
        //.... 
    } 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
