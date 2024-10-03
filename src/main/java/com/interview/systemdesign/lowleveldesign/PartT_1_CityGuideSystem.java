package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
 * For a City Guide System like Foursquare, we will design it to provide recommendations for places,
 * restaurants, and points of interest based on user preferences, reviews, and locations. The system
 * will focus on handling venue searches, user reviews, check-ins, and rating systems while ensuring
 * scalability and performance.
 *
 * Here’s how we can break down the key components and structure the design:
 *
 * 1. Core Requirements:
 * - Venue Management: Add, update, and manage places of interest (e.g., restaurants, parks, malls).
 * - User Reviews & Ratings: Allow users to leave reviews and rate venues.
 * - Check-ins: Users can check in to venues, marking their current location.
 * - Search & Recommendations: Provide venue recommendations based on location, category, ratings,
 *   or user preferences.
 * - Uniqueness: Ensure each venue has a unique identifier.
 * - Optional: Support social interactions like following friends or tracking their check-ins.
 *
 * 2. Approach:
 * - Store venue details, reviews, and check-ins in a database or distributed key-value store.
 * - Implement services to handle venue search, review submission, and check-ins.
 * - Use geo-location APIs to manage and filter venues based on user proximity.
 * - Optimize for scalability using NoSQL databases and caching for frequently accessed data.
 * - Optionally support a social network layer to allow users to follow friends and view their activity.
 *
 * 3. Components of the City Guide System:
 * - Venue Table: A database or key-value store to store venue details (name, location, category, ratings).
 * - Review Table: Stores user reviews, ratings, and timestamps for each venue.
 * - Check-in Table: Logs user check-ins to venues, with timestamps and user IDs.
 * - Services:
 *   - searchVenues(location, category): Search venues based on user location or a category.
 *   - submitReview(userId, venueId, rating, comment): Allow users to leave reviews and rate a venue.
 *   - checkIn(userId, venueId): Logs user check-ins at a venue.
 *   - getRecommendations(userId, location): Provide venue recommendations based on previous user
 *     activity and location.
 * - Security: Secure user authentication, handle permissions, and prevent spam reviews.
 *
 * 4. Services:
 * - Venue Search:
 *   - searchVenues(location, category): Search for nearby venues based on the user’s current location
 *     or a selected category. The service will filter and rank results based on user ratings and proximity.
 * - Review Submission:
 *   - submitReview(userId, venueId, rating, comment): Allows users to submit a review, which will be
 *     stored along with a rating and timestamp for the selected venue.
 * - Check-in Service:
 *   - checkIn(userId, venueId): Log the user’s check-in at a specific venue, marking their visit. This data
 *     can be used for recommendations or showing friends’ activity.
 * - Recommendation Engine:
 *   - getRecommendations(userId, location): Generate venue recommendations for the user based on
 *     their previous visits, preferences, and nearby venues.
 *
 * 5. Scalability Considerations:
 * - Use NoSQL databases (e.g., MongoDB or DynamoDB) for storing venue, check-in, and review data,
 *   allowing scalability with a growing number of users.
 * - Implement caching (e.g., using Redis) to store frequently accessed venues or user check-in data.
 * - Optimize queries with geo-indexing to efficiently filter venues by proximity.
 * - Use distributed systems and microservices architecture to scale the system horizontally and handle
 *   high traffic loads.
 *
 * 6. Security Considerations:
 * - Authentication: Secure user logins using OAuth or JWT tokens to ensure only authenticated users
 *   can post reviews or check in.
 * - Spam Prevention: Implement rate-limiting and content validation to prevent spam reviews or
 *   fraudulent check-ins.
 * - Review Moderation: Use AI or manual moderation to detect inappropriate content in user reviews.
 *
 * 7. Explanation:
 * - Venue Management: Each venue has a unique ID, location, category (e.g., restaurant, park), and
 *   ratings. This data is stored in the Venue Table.
 * - Review & Rating System: Users can post reviews with ratings (1-5 stars) for each venue, and these
 *   are stored in the Review Table.
 * - Check-in System: Users can check in to venues to track their visits or share them with friends.
 * - Search & Recommendations: The search system allows users to find venues based on their preferences
 *   (e.g., location or category), while the recommendation engine suggests venues based on previous
 *   check-ins or user preferences.
 *
 * 8. Additional Features:
 * - Personalized Recommendations: The system can generate personalized recommendations using
 *   collaborative filtering or content-based filtering.
 * - Notifications: Users can receive notifications when friends check in to venues or when there are
 *   new reviews for their favorite places.
 * - Social Features: Users can follow friends to view their check-in history and recommendations.
 *
 * Key Points:
 * - Venue Management: Manage venue information including ratings and categories.
 * - User Reviews & Check-ins: Users can post reviews and check in to track their activity.
 * - Recommendations: Suggest venues to users based on location, preferences, and past behavior.
 * - Scalability: Use NoSQL databases, caching, and microservices for efficient handling of high volumes
 *   of data and requests.
 *
 * This example showcases a basic City Guide System with venue search, check-in functionality, and a simple
 * rating and review system. You can expand it by adding features like personalized recommendations and
 * integrating real-world APIs for geo-location services.
 */
class Venue {
    private String id;
    private String name;
    private String category;
    private double latitude;
    private double longitude;
    private double averageRating;
    private List<Review> reviews;

    public Venue(String id, String name, String category, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reviews = new ArrayList<>();
        this.averageRating = 0.0;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public double getAverageRating() { return averageRating; }

    public void addReview(Review review) {
        reviews.add(review);
        updateAverageRating();
    }

    private void updateAverageRating() {
        double totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        this.averageRating = totalRating / reviews.size();
    }
}

class Review {
    private String userId;
    private String venueId;
    private int rating; // 1 to 5 stars
    private String comment;

    public Review(String userId, String venueId, int rating, String comment) {
        this.userId = userId;
        this.venueId = venueId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() { return rating; }
}

class CityGuideService {
    private Map<String, Venue> venues = new HashMap<>();
    private Map<String, List<String>> checkIns = new HashMap<>(); // Stores user check-ins

    // Add new venue
    public void addVenue(String id, String name, String category, double lat, double lon) {
        Venue venue = new Venue(id, name, category, lat, lon);
        venues.put(id, venue);
    }

    // Search venues by category or location (simple radius search)
    public List<Venue> searchVenues(String category, double userLat, double userLon, double radius) {
        List<Venue> result = new ArrayList<>();
        for (Venue venue : venues.values()) {
            if (venue.getCategory().equalsIgnoreCase(category) &&
                    distance(userLat, userLon, venue.getLatitude(), venue.getLongitude()) <= radius) {
                result.add(venue);
            }
        }
        return result;
    }

    // Calculate distance between two points (simple distance formula)
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        // Using a simple distance formula for this example
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

    // Check in to a venue
    public void checkIn(String userId, String venueId) {
        checkIns.putIfAbsent(userId, new ArrayList<>());
        checkIns.get(userId).add(venueId);
    }

    // Submit a review for a venue
    public void submitReview(String userId, String venueId, int rating, String comment) {
        Venue venue = venues.get(venueId);
        if (venue != null) {
            Review review = new Review(userId, venueId, rating, comment);
            venue.addReview(review);
        }
    }
}

public class PartTCityGuideSystem {
    public static void main(String[] args) {
        CityGuideService guideService = new CityGuideService();

        // Add venues
        guideService.addVenue("1", "Central Park", "Park", 40.785091, -73.968285);
        guideService.addVenue("2", "Empire State Building", "Landmark", 40.748817, -73.985428);

        // User checks in to Central Park
        guideService.checkIn("user1", "1");

        // Submit a review for Central Park
        guideService.submitReview("user1", "1", 5, "Beautiful park!");

        // Search for venues in category "Park" within a radius of 10 km
        List<Venue> parks = guideService.searchVenues("Park", 40.784, -73.968, 10);
        for (Venue park : parks) {
            System.out.println(park.getName() + " - Rating: " + park.getAverageRating());
        }
    }
}
