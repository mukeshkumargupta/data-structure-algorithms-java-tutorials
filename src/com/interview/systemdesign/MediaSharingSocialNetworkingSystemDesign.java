package com.interview.systemdesign;

import java.util.*;
/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.geeksforgeeks.org/design-scalable-system-like-instagram/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Done
 */
public class MediaSharingSocialNetworkingSystemDesign {
 // Java Program to explain the design 
    
    public enum InvitationStatus{ 
      PENDING, 
      ACCEPTED, 
      REJECTED, 
      CANCELED 
    } 
      
    public enum AccountStatus{ 
      PUBLIC, 
      PRIVATE, 
      CLOSED 
    } 
      
    public enum MediaStatus { 
      PUBLIC, 
      PRIVATE 
    } 
      
    public enum MediaType { 
      PHOTO, 
      VIDEO 
    } 
      
    public class AddressDetails { 
      private String streetAddress; 
      private String city; 
      private String country; 
      //... 
    } 
      
    public class AccountDetails { 
      private Date createdTime; 
      private AccountStatus status; 
      private boolean updateAccountStatus(AccountStatus accountStatus); 
      //... 
    } 
      
    public class Invitation { 
      private Integer userID; 
      private InvitationStatus status; 
      private Date sentDate; 
      
      public boolean updateInvitation(InvitationStatus status); 
      //... 
    } 
      
    public class PendingInvitation extends Invitation{ 
      public boolean acceptConnection(); 
      public boolean rejectConnection(); 
      //... 
    } 
      
    public class UserRelations { 
      private HashSet<Integer> userFollower; 
      private HashSet<Integer> userFollowing; 
      private HashSet<ConnectionInvitation> connectionInvitations; 
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
      
    public class Media { 
      private Integer id; 
      private User createdBy; 
      private MediaType mediaType; 
      private String path; 
      private MediaStatus mediaStatus; 
      private int viewsCount; 
      
      private HashSet<Integer> userLikes; 
      private HashSet<Integer> userComments; 
      //... 
    } 
      
    public class User { 
      private int id; 
      private String password; 
      private String nickname; 
      private String email; 
      private AddressDetails addressDetails; 
      private AccountDetails accountDetails; 
      private UserRelations userRelations; 
      private HashSet<ConnectionInvitation> invitationsByMe; 
      private HashSet<ConnectionInvitation> invitationsToMe; 
      
      public boolean updatePassword(); 
      public boolean createMedia(Media media); 
      public boolean updateMedia(int mediaId, MediaStatus mediaStatus); 
      public boolean sendInvitation(ConnectionInvitation invitation); 
      public List<User> searchUser(string term); 
      public List<Media> searchMedia(string term); 
      //... 
    } 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
