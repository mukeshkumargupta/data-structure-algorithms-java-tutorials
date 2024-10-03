package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
 * Here’s a complete solution for designing a Social Media Feed system using object-oriented principles in Java.
 * This implementation includes classes for User, Post, Feed, and Follow, providing the functionality to post content,
 * follow/unfollow users, and view the feed in reverse chronological order.
 *
 * Explanation of the Code:
 *
 * User Class:
 * - Represents a user in the system who can post content and follow/unfollow other users.
 *
 * Post Class:
 * - Represents a post made by a user, with attributes like content, timestamp, and the user who posted it.
 *
 * Feed Class:
 * - Manages the feed for a user, fetching posts from the people the user follows in reverse chronological order.
 *
 * Follow Class:
 * - Manages the follow relationships between users, allowing users to follow and unfollow each other.
 *
 * SocialMediaFeedSystem Class:
 * - Coordinates the system, handles users, posts, and follows, and displays the feed for each user.
 *
 * Key Considerations:
 *
 * Efficient Querying:
 * - In a real-world system with millions of users, retrieving posts efficiently is crucial.
 *   Techniques like pagination (loading posts in chunks) and caching (storing frequently requested data in memory)
 *   would improve scalability.
 *
 * Scalability:
 * - As the system grows, sharding (distributing data across servers), load balancing, and using distributed databases
 *   like Cassandra can be used to handle large-scale traffic.
 *
 * Reverse Chronological Order:
 * - Posts from followed users are sorted based on their timestamp, ensuring that the most recent posts appear first.
 *
 * Follow/Unfollow:
 * - Users can follow or unfollow other users dynamically. This can be expanded to include notifications when new posts
 *   are made by followed users.
 *
 * Real-Time Updates:
 * - For a fully-fledged system, you would need real-time data synchronization (e.g., WebSockets) to update the feed
 *   when new posts are made by followed users.
 *
 * Extensions and Enhancements:
 *
 * User Authentication:
 * - Adding login/signup functionality with authentication tokens.
 *
 * Comment and Like System:
 * - Allow users to comment on and like posts.
 *
 * Recommendation Algorithm:
 * - Suggest users to follow based on interests or connections.
 *
 * Notifications:
 * - Notify users when someone they follow posts something.
 *
 * This solution provides a strong foundation for a social media feed system and can be further enhanced with
 * more complex features as needed.
 */
// Represents a post made by a user
class Post {
    private final String content;
    private final Date timestamp;
    private final User user;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        this.timestamp = new Date();
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }
}

// Represents a user who can follow other users and post content
class User {
    private final String name;
    private final List<Post> posts;
    private final Set<User> following;

    public User(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
        this.following = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void addPost(String content) {
        Post post = new Post(content, this);
        posts.add(post);
    }

    public void follow(User user) {
        following.add(user);
    }

    public void unfollow(User user) {
        following.remove(user);
    }
}

// Manages the feed for a user by collecting posts from followed users
class Feed {
    public List<Post> getFeed(User user) {
        List<Post> feed = new ArrayList<>();
        for (User followedUser : user.getFollowing()) {
            feed.addAll(followedUser.getPosts());
        }
        feed.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp())); // Reverse chronological order
        return feed;
    }
}

// Main system class that manages users and posts
class SocialMediaFeedSystem {
    private final Map<String, User> users;

    public SocialMediaFeedSystem() {
        this.users = new HashMap<>();
    }

    public User createUser(String name) {
        User user = new User(name);
        users.put(name, user);
        return user;
    }

    public User getUser(String name) {
        return users.get(name);
    }

    public void showFeed(User user) {
        Feed feed = new Feed();
        List<Post> userFeed = feed.getFeed(user);

        System.out.println(user.getName() + "'s Feed:");
        for (Post post : userFeed) {
            System.out.println(post.getUser().getName() + " posted: " + post.getContent() + " at " + post.getTimestamp());
        }
    }
}

// Demonstration of the social media feed system
public class PartKSocialMediaFeedSystem {
    public static void main(String[] args) {
        SocialMediaFeedSystem system = new SocialMediaFeedSystem();

        // Create users
        User alice = system.createUser("Alice");
        User bob = system.createUser("Bob");
        User charlie = system.createUser("Charlie");

        // Users follow each other
        alice.follow(bob);
        alice.follow(charlie);
        bob.follow(charlie);

        // Users create posts
        bob.addPost("Bob's first post!");
        charlie.addPost("Charlie says hello!");
        bob.addPost("Bob's second post!");

        // Display Alice's feed
        system.showFeed(alice);

        // Display Bob's feed
        system.showFeed(bob);
    }
}
