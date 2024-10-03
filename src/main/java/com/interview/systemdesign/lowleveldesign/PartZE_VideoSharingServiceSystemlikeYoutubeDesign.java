package com.interview.systemdesign.lowleveldesign;

import java.util.*;
/*
Designing a Video Sharing Service like YouTube involves several key components such as
video upload and storage, user management, video streaming, search functionality,
recommendations, and more. Here's a low-level design outline for building a scalable
video sharing platform.

Low-Level Design for Video Sharing Service

1. Key Components
- User Management: Handles registration, login, authentication, profile management,
  and user subscriptions.
- Video Management: Manages uploading, transcoding, storage, and retrieval of videos.
- Streaming Service: Streams videos to users in multiple formats and qualities based
  on their device and bandwidth.
- Content Delivery Network (CDN): Distributes video content to users across different
  geographic locations efficiently.
- Search and Recommendation: Enables users to search for videos and provides
  personalized video recommendations based on preferences and behavior.
- Comment and Rating System: Allows users to comment on and rate videos.
- Notifications and Subscriptions: Sends notifications when subscribed channels
  upload new videos.
- Monetization: Implements advertisements and revenue-sharing models for content
  creators.

2. User Management
Responsibilities:
- User registration, login, logout, and profile management.
- Authentication (OAuth, JWT) and authorization.
- Managing user subscriptions and playlists.

3. Video Management
Responsibilities:
- Video upload, storage, and retrieval.
- Transcoding videos to different formats (e.g., 480p, 720p, 1080p).
- Managing metadata (title, description, tags).

4. Video Streaming and CDN
Responsibilities:
- Deliver video content via streaming to users.
- Adjust video quality based on user’s bandwidth.
- CDN to distribute videos geographically for optimal latency.
CDN and Streaming:
- Use HTTP Live Streaming (HLS) for adaptive streaming.
- Distribute video content using a CDN to reduce latency.

5. Search and Recommendation System
Responsibilities:
- Implement search functionality to find videos by title, tags, and metadata.
- Provide personalized recommendations based on user behavior and video history.

6. Comment and Rating System
Responsibilities:
- Allow users to comment on videos.
- Users can like/dislike videos, affecting the video's rating and recommendation score.

7. Notification and Subscription System
Responsibilities:
- Notify users when a channel they’re subscribed to uploads a new video.
- Notify users about comments, likes, and replies on their uploaded videos.

8. Monetization and Ad System
Responsibilities:
- Display advertisements in videos.
- Implement revenue-sharing models between the platform and content creators.

System Architecture:
- Frontend: A web or mobile interface for users to upload, view, and interact with
  videos.
- Backend: RESTful APIs to handle user requests for video streaming, searching,
  uploading, and managing metadata.
- Database: Stores user information, video metadata, comments, and user activity.
    - SQL for structured data (users, video metadata).
    - NoSQL (e.g., MongoDB) for large-scale data like comments, likes, etc.
- File Storage: A distributed file storage system (e.g., Amazon S3) for storing video
  files.
- CDN: Distribute video content globally to minimize latency.

Scalability Considerations:
- Microservices Architecture: Use microservices for different components (user
  service, video service, recommendation service) to allow independent scaling.
- Database Sharding: Distribute large datasets (like comments and video metadata)
  across multiple servers.
- Load Balancer: Use load balancers to distribute incoming traffic across multiple
  instances of the application.
- Cache Layer: Use caching (e.g., Redis) to cache frequently accessed data like
  popular videos and search results.

Security Considerations:
- Authentication and Authorization: Use OAuth2 or JWT for secure user authentication.
- Data Encryption: Encrypt sensitive user data (e.g., passwords, payment info).
- Rate Limiting: Prevent abuse by limiting the number of API requests per user/IP.
- Input Validation: Prevent SQL injection and XSS by sanitizing all user inputs.

Monitoring and Logging:
- Error Tracking: Implement a logging system to track system errors and performance
  issues.
- Performance Metrics: Monitor response times for video loading and streaming.
- User Behavior Tracking: Collect data on user behavior for recommendation purposes
  and to improve service quality.

Conclusion:
This low-level design outlines the core components and interactions required to build
a scalable, secure, and efficient video-sharing platform like YouTube. The design can
be extended further by adding advanced features such as live streaming, video editing,
social features, and analytics.
*/


class User {
    private String userId;
    private String name;
    private String email;
    private String password; // hashed for security
    private List<String> subscribedChannels;
    private List<String> playlists;

    // Getters and setters
}

class AuthService {
    public boolean login(String email, String password);
    public void register(String name, String email, String password);
    public void logout(String userId);
}


class Video {
    private String videoId;
    private String title;
    private String description;
    private String uploaderId;  // User who uploaded
    private long uploadTimestamp;
    private String videoURL;    // Link to video location
    private Map<String, String> qualityVersions; // 480p, 720p, 1080p URLs
    private List<String> tags;
    private long views;
    private int likes;
    private int dislikes;

    // Getters and setters
}

class VideoService {
    public Video uploadVideo(String title, String description, String uploaderId, File videoFile);
    public Video getVideoById(String videoId);
    public List<Video> getVideosByUploader(String uploaderId);
}


class CDNService {
    public String getVideoURL(String videoId, String quality);
    public void distributeToCDN(String videoFilePath);
}

class StreamingService {
    public void streamVideo(String videoId, String quality, String userId);
}


class SearchService {
    public List<Video> searchVideos(String query);
}

class RecommendationService {
    public List<Video> getRecommendedVideos(String userId);
}


class Comment {
    private String commentId;
    private String videoId;
    private String userId;
    private String commentText;
    private long timestamp;

    // Getters and setters
}

class CommentService {
    public void addComment(String videoId, String userId, String commentText);
    public List<Comment> getComments(String videoId);
}

class RatingService {
    public void likeVideo(String videoId, String userId);
    public void dislikeVideo(String videoId, String userId);
}


class Notification {
    private String notificationId;
    private String userId;
    private String message;
    private long timestamp;

    // Getters and setters
}

class NotificationService {
    public void notifyUser(String userId, String message);
}

class SubscriptionService {
    public void subscribeToChannel(String userId, String channelId);
    public void unsubscribeFromChannel(String userId, String channelId);
}


class Ad {
    private String adId;
    private String adContent;
    private long duration;

    // Getters and setters
}

class MonetizationService {
    public void insertAdIntoVideo(String videoId, Ad ad);
    public void processRevenueShare(String uploaderId, double adRevenue);
}

public class VideoSharingServiceSystemlikeYoutubeDesign {

    
}
