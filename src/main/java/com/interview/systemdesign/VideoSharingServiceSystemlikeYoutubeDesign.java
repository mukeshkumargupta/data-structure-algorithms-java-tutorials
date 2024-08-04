package com.interview.systemdesign;

import java.util.*;
/*
 * Reference: https://www.geeksforgeeks.org/design-video-sharing-system-like-youtube/?ref=rp
 * Status: done
 */



public class VideoSharingServiceSystemlikeYoutubeDesign {
    
 // Java program for design 
    public enum AccountStatus{ 
      PUBLIC, 
      PRIVATE, 
      CLOSED 
    } 
      
    public enum VideoContentStatus { 
      PENDING, 
      PROCESSED, 
      FAIL, 
      REJECTED 
    } 
      
    public enum VideoStatus { 
      PUBLIC, 
      PRIVATE, 
      DELETED 
    } 
      
    public enum VideoQuality { 
      LOW, 
      MIDDLE, 
      HIGH 
    } 
      
    public class AddressDetails { 
      private String street; 
      private String city; 
      private String country; 
      //... 
    } 
      
    public class AccountDetails { 
      private Date createdTime; 
      private AccountStatus status; 
      private boolean updateAccountStatus(AccountStatus accountStatus) {
          
      }
      
      //... 
      } 
      
    public class Comment { 
      private Integer id; 
      private User addedBy; 
      private Date addedDate; 
      private String comment; 
      
      public boolean updateComment(String comment) {
          
      }

    } 
      
    public class Video { 
      private Integer id; 
      private User createdBy; 
      private String path; 
      private VideoStatus videoStatus; 
      private VideoContentStatus videoContentStatus; 
      private int viewsCount; 
      
      private HashSet<Integer> userLikes; 
      private HashSet<Integer> userDisLikes; 
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
      
      private boolean updatePassword(); 
      public boolean uploadVideo(Video video); 
      public List<Videos> getVideos(); 
      //... 
    } 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
