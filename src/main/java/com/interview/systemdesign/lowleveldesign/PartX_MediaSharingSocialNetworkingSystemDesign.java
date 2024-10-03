package com.interview.systemdesign.lowleveldesign;

import java.util.*;
/*
 * Date: 05/08/2020
 * Author: Mukesh Kumar Gupta
 *
 * Reference: https://www.geeksforgeeks.org/design-scalable-system-like-instagram/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Done
 *
 * A Low-Level Design (LLD) for a Media Sharing Social Networking Platform requires
 * defining the system's core components, functionalities, interactions, and detailed
 * design decisions for features like user management, media sharing, feeds, notifications,
 * and security. Here's an overview of the core requirements, approach, components, system
 * design, and scalability considerations.
 *
 * Core Requirements:
 * - User Management: Registration, authentication, profile management, follow/unfollow functionality.
 * - Media Sharing: Allow users to upload images, videos, and other media.
 * - Feed: Provide a personalized feed of media from followed users.
 * - Likes and Comments: Users can like and comment on posts.
 * - Notifications: Alert users when someone likes, comments, or follows them.
 * - Search: Users can search for other users or media by hashtags or keywords.
 * - Direct Messaging: Private message functionality between users.
 * - Analytics: Track user activity, post engagement, and trends.
 *
 * Approach:
 * Design the system as a service-oriented architecture (SOA) with modular microservices.
 * Each component will have its own service to ensure scalability, maintainability, and
 * flexibility for future updates.
 *
 * Components:
 * 1. User Management
 *    - Classes: User, Profile, Follow
 *    - Functions:
 *      - register(): Creates a new user account.
 *      - login(): Authenticates the user.
 *      - editProfile(): Allows users to update their profile.
 *      - followUser(): Follows another user.
 *      - unfollowUser(): Unfollows a user.
 *      - viewProfile(): View user's profile information.
 *
 * 2. Media Management
 *    - Classes: Media, Post, Comment, Like
 *    - Functions:
 *      - uploadMedia(): Allows users to upload media (images, videos, etc.).
 *      - createPost(): Create a post with media and captions.
 *      - likePost(): Like a post.
 *      - commentOnPost(): Comment on a post.
 *      - deletePost(): Deletes the user’s own post.
 *      - tagUser(): Tag other users in a post.
 *
 * 3. Feed Management
 *    - Classes: Feed, Post, UserFeed
 *    - Functions:
 *      - generateFeed(): Generates a personalized feed for a user, showing posts from followed users.
 *      - fetchPost(): Fetches a specific post by ID.
 *      - loadMorePosts(): Loads additional posts when scrolling through the feed.
 *
 * 4. Search System
 *    - Classes: Search, UserSearchResult, PostSearchResult, Hashtag
 *    - Functions:
 *      - searchUsers(): Searches users by name or username.
 *      - searchPosts(): Searches posts by hashtags or keywords.
 *      - searchTrendingHashtags(): Provides a list of trending hashtags.
 *
 * 5. Direct Messaging
 *    - Classes: Message, Chat, UserChat
 *    - Functions:
 *      - sendMessage(): Sends a direct message to another user.
 *      - viewChatHistory(): Retrieves the message history with a user.
 *      - deleteMessage(): Allows users to delete messages (from their side of the chat).
 *
 * 6. Notifications System
 *    - Classes: Notification, LikeNotification, CommentNotification, FollowNotification
 *    - Functions:
 *      - sendNotification(): Sends a notification to users (e.g., likes, comments, follows).
 *      - viewNotifications(): Allows users to view their notifications.
 *      - markAsRead(): Marks a notification as read.
 *      - pushNotification(): Push notification for real-time alerts.
 *
 * 7. Analytics and Reporting
 *    - Classes: Analytics, UserActivity, EngagementReport
 *    - Functions:
 *      - trackUserEngagement(): Tracks user activities (likes, comments, post views).
 *      - generateEngagementReport(): Generates reports on user interaction and content performance.
 *
 * 8. Admin Management
 *    - Classes: Admin, Moderation, Ban, Report
 *    - Functions:
 *      - moderatePost(): Admin can remove posts violating guidelines.
 *      - banUser(): Bans users violating terms of service.
 *      - reviewReports(): Handles user reports on inappropriate content.
 *
 * System Design:
 * 1. Database Design
 *    - User Table: userId, username, email, passwordHash, profilePicture, bio, createdAt
 *    - Post Table: postId, userId, mediaUrl, caption, createdAt
 *    - Media Table: mediaId, mediaType (image/video), url, size, metadata
 *    - Follow Table: followerId, followingId, createdAt
 *    - Like Table: likeId, userId, postId, createdAt
 *    - Comment Table: commentId, postId, userId, commentText, createdAt
 *    - Message Table: messageId, senderId, receiverId, messageText, timestamp
 *    - Notification Table: notificationId, userId, type, message, read, createdAt
 *
 * 2. Service Layer
 *    - UserService: Handles all user-related activities such as registration, login, profile management, and following/unfollowing users.
 *    - MediaService: Manages the upload, retrieval, and deletion of media (images/videos) and related posts.
 *    - FeedService: Generates and provides users with their personalized media feed based on the accounts they follow.
 *    - NotificationService: Manages the sending of notifications for actions like likes, comments, follows, etc.
 *    - SearchService: Enables users to search for other users, posts, or trending topics.
 *    - ChatService: Manages direct messaging between users.
 *    - AnalyticsService: Tracks engagement metrics and generates reports.
 *
 * 3. API Design:
 *    - POST /register: Registers a new user.
 *    - POST /login: Authenticates a user.
 *    - POST /upload: Uploads media to the platform.
 *    - GET /feed: Fetches the personalized feed for a user.
 *    - POST /post/{postId}/like: Likes a post.
 *    - POST /post/{postId}/comment: Adds a comment to a post.
 *    - GET /notifications: Fetches all notifications for a user.
 *    - GET /search: Searches for users, posts, or hashtags.
 *    - POST /message: Sends a direct message.
 *    - GET /chat/{userId}: Retrieves chat history with a user.
 *
 * Scalability Considerations:
 * 1. Database Sharding: Split large tables, such as Post or Like, across multiple shards based on user or geographic region to handle large amounts of data.
 * 2. Caching: Use a cache like Redis for frequently accessed data, such as user profiles, media files, and popular posts. Cache user feeds to avoid regenerating them repeatedly.
 * 3. Load Balancing: Use load balancers to evenly distribute traffic across multiple instances of the application and services. Enable autoscaling to dynamically add or remove resources based on traffic.
 * 4. Content Delivery Network (CDN): Utilize a CDN for storing and serving media files (images/videos) to minimize latency and bandwidth costs.
 * 5. Media Storage: Use a distributed object storage system like Amazon S3 or Google Cloud Storage for storing user-uploaded media.
 * 6. Asynchronous Task Queues: Implement message queues (e.g., RabbitMQ, Kafka) for asynchronous tasks, such as processing notifications or updating feeds.
 * 7. Microservices: Each major feature (user, post, comment, feed, etc.) is a microservice that can be developed, deployed, and scaled independently.
 *
 * Security Considerations:
 * 1. Authentication & Authorization: Implement OAuth2.0 or JWT tokens for secure user authentication. Ensure role-based access control for admin features (moderation, banning, etc.).
 * 2. Data Encryption: Use HTTPS for secure communication. Encrypt sensitive data such as passwords and personal information using AES or RSA.
 * 3. Content Moderation: Use automated content moderation tools (e.g., AI-based content filters) to flag inappropriate media. Enable users to report posts or users for violating guidelines.
 * 4. Rate Limiting & DDOS Protection: Implement rate limiting for critical endpoints (e.g., login, media upload) to prevent abuse. Use DDOS protection services like Cloudflare to mitigate traffic spikes.
 *
 * Additional Features:
 * - Real-time Notifications: Implement real-time notifications using WebSockets or Push Notifications.
 * - Trending Hashtags: Track and display trending hashtags based on user activity.
 * - Social Sharing: Allow users to share posts to other social networks.
 * - Explore Page: Suggest trending or recommended posts for users to discover new content.
 * - Analytics Dashboard: Provide an analytics dashboard for users to view their post engagement.
 *
 * This low-level design covers the foundational components required to build a scalable,
 * efficient media-sharing social networking platform. Each service can be independently
 * deployed and scaled to handle large numbers of users and media.
 */


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;
    private String profilePicture;
    private String bio;

    // Getters and Setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Hash password (using BCrypt for example)
        user.setPasswordHash(hashPassword(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && checkPassword(password, user.get().getPasswordHash())) {
            return user;
        }
        return Optional.empty();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String mediaUrl;
    private String caption;
    private LocalDateTime createdAt;

    // Getters and Setters
}


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}


@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> getUserPosts(User user) {
        return postRepository.findByUser(user);
    }
}


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Post> posts = postService.getUserPosts(user);
        return ResponseEntity.ok(posts);
    }
}


@Service
public class FeedService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Post> generateFeed(Long userId) {
        // Get all users the current user is following
        List<User> followedUsers = userRepository.findFollowedUsers(userId);
        // Fetch posts of followed users
        List<Post> feedPosts = new ArrayList<>();
        followedUsers.forEach(user -> feedPosts.addAll(postRepository.findByUser(user)));
        // Sort posts by createdAt
        feedPosts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return feedPosts;
    }
}


@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getUserFeed(@PathVariable Long userId) {
        List<Post> feed = feedService.generateFeed(userId);
        return ResponseEntity.ok(feed);
    }
}


@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private LocalDateTime createdAt;

    // Getters and Setters
}


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String text;
    private LocalDateTime createdAt;

    // Getters and Setters
}


@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public Like likePost(User user, Post post) {
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        like.setCreatedAt(LocalDateTime.now());
        return likeRepository.save(like);
    }
}

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(User user, Post post, String text) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setText(text);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}


@RestController
@RequestMapping("/api/interactions")
public class InteractionController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/like")
    public ResponseEntity<Like> likePost(@RequestParam Long userId, @RequestParam Long postId) {
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Post post = postService.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        Like like = likeService.likePost(user, post);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> commentOnPost(@RequestParam Long userId, @RequestParam Long postId, @RequestParam String text) {
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Post post = postService.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        Comment comment = commentService.addComment(user, post, text);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}


@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;

    // Getters and Setters
}


@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification sendNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotifications(User user) {
        return notificationRepository.findByUserAndIsRead(user, false);
    }
}


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Notification> notifications = notificationService.getNotifications(user);
        return ResponseEntity.ok(notifications);
    }
}


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/register", "/api/users/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add JWT Filter (optional, if JWT-based auth is used)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure authentication manager with custom user details service, e.g., from database
    }
}


public class MediaSharingSocialNetworkingSystemDesign {

    
}
