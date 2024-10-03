package com.interview.systemdesign.lowleveldesign;

import java.util.*;
import java.util.concurrent.*;

/*
This is a Java implementation of a rate limiter system using the Token Bucket algorithm.
 The system controls the number of API requests a user can make within a specified time window.
  It consists of key components such as Request, User, and RateLimiter classes, which handle tracking and limiting requests.

Design Overview:
- User: Represents an individual making API requests.
- Request: Represents a user's API request.
- RateLimiter: Implements rate-limiting logic using the Token Bucket algorithm. Each user has a bucket that holds a fixed number of tokens
 (representing the allowed requests). Every API request consumes one token, and tokens are replenished at a steady rate over time.

Token Bucket Algorithm:
Key Components:
- Request Class: Captures the details of an API request, including the user and the requested endpoint.
- User Class: Identifies a user by their userId.
- RateLimiter Class:
  - Uses the Token Bucket algorithm to control API request rates.
  - Each user has a bucket with a limited number of tokens (`maxRequests`).
  - Requests consume tokens, and tokens are refilled periodically (`refillIntervalMillis`).
  - If the bucket is empty, further requests are rejected.
  - A scheduled task regularly refills tokens for all users.

Token Refill Mechanism:
- The system runs a scheduled task that refills each user's token bucket at defined intervals, ensuring tokens are restored up to the allowed limit (`maxRequests`).

Considerations for Distributed Rate Limiting:
In a distributed environment, user token buckets would need to be synchronized across multiple servers. This can be handled using:
- Centralized datastore: Store and synchronize user request data in a shared database (e.g., Redis).
- Distributed counters: Use systems like Redis or Kafka to maintain distributed counters for managing rate limits across different nodes.

This implementation demonstrates a basic rate-limiting mechanism for a single server setup. In a distributed setup, a consistent state store would be necessary to track token buckets across multiple servers.
*/

class Request {
    private final User user;
    private final String endpoint;

    public Request(User user, String endpoint) {
        this.user = user;
        this.endpoint = endpoint;
    }

    public User getUser() {
        return user;
    }

    public String getEndpoint() {
        return endpoint;
    }
}

class User {
    private final String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

class RateLimiter {
    private final int maxRequests;
    private final long refillIntervalMillis;
    private final Map<String, UserBucket> userBuckets = new ConcurrentHashMap<>();

    public RateLimiter(int maxRequests, long refillIntervalMillis) {
        this.maxRequests = maxRequests;
        this.refillIntervalMillis = refillIntervalMillis;
        startRefillTask();
    }

    // Core logic to limit requests per user
    public boolean allowRequest(Request request) {
        User user = request.getUser();
        String userId = user.getUserId();

        userBuckets.putIfAbsent(userId, new UserBucket(maxRequests));
        UserBucket bucket = userBuckets.get(userId);

        synchronized (bucket) {
            if (bucket.tokens > 0) {
                bucket.tokens--;
                System.out.println("Request allowed for user: " + userId);
                return true;
            } else {
                System.out.println("Rate limit exceeded for user: " + userId);
                return false;
            }
        }
    }

    // Task to periodically refill tokens for all users
    private void startRefillTask() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            for (UserBucket bucket : userBuckets.values()) {
                synchronized (bucket) {
                    bucket.tokens = Math.min(maxRequests, bucket.tokens + 1);
                }
            }
        }, refillIntervalMillis, refillIntervalMillis, TimeUnit.MILLISECONDS);
    }

    // Nested class representing a user's token bucket
    private static class UserBucket {
        private int tokens;

        public UserBucket(int tokens) {
            this.tokens = tokens;
        }
    }
}

// Main class for testing the rate limiter
public class PartMRateLimiterApp {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(5, 1000); // 5 requests per second

        User user1 = new User("User1");
        Request req1 = new Request(user1, "/api/data");

        // Simulate requests from user1
        for (int i = 0; i < 10; i++) {
            rateLimiter.allowRequest(req1);
            Thread.sleep(200); // wait between requests
        }
    }
}
