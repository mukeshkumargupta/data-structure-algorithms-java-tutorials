package com.interview.systemdesign.lowleveldesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
    To design a URL shortener service, like bit.ly, we need to create a system
    that maps long URLs to short, unique URLs and provides a way to retrieve
    the original long URL when the short one is accessed.

    Here's a breakdown of the key components and considerations for this design:

    1. Core Requirements
       - Shorten URLs: Given a long URL, generate a unique shortened version.
       - Resolve URLs: Given a short URL, retrieve the original long URL.
       - Uniqueness: Ensure that each shortened URL is unique.
       - Scaling: Design the system to handle a large number of URL shortening requests.
       - Expiry (Optional): URLs might expire after a certain time, so the system
         must handle expired URLs.

    2. Approach
       - Use a hash or an incremental ID to generate short URLs.
       - Store a mapping of short URLs to long URLs in a database.
       - Use base62 encoding (or a similar system) to convert IDs or hashes into
         a URL-friendly format (shorter length, using alphanumeric characters).

    3. Components of the URL Shortener
       - URL Mapping Table: A database or key-value store to store mappings
         between short and long URLs.
       - Base62 Encoding: A method to shorten numeric IDs into URL-friendly short
         codes using alphanumeric characters.
       - Services:
         - shortenURL: Converts a long URL to a short one.
         - resolveURL: Retrieves the long URL given a short one.

    4. Explanation
       - Base62 Encoding: This is used to generate short URLs that contain
         characters from [a-zA-Z0-9]. This allows 62 possible characters for each
         position, making it more compact and URL-safe compared to using only numbers.

       - Random URL Generation: The method generateShortURL() creates a random
         6-character string using alphanumeric characters. This ensures that the
         shortened URLs are relatively short while maintaining uniqueness (with collision handling).

       - Mapping Storage: The service stores the mappings between short and long
         URLs in two maps (shortToLongMap and longToShortMap), ensuring efficient lookups and reverse lookups.

    5. Scalability Considerations
       - Database: In a production environment, the mappings should be stored in
         a persistent database (e.g., NoSQL for scalability).
       - Distributed Systems: Use distributed key generation techniques (like
         Zookeeper, Redis, or a dedicated ID generator service) for scaling the
         service across multiple nodes.
       - Cache: You could use a caching layer (like Redis) to reduce database load
         for frequently accessed short URLs.

    6. Handling URL Expiry
       - Introduce a TTL (time-to-live) for each shortened URL to expire old or
         unused URLs.
       - You could store a timestamp with each URL and periodically remove expired
         URLs from the database.

    7. Security Considerations
       - Implement rate limiting to avoid abuse of the service.
       - Validate URLs to ensure that they are well-formed before shortening.
       - Optionally, introduce user authentication to allow users to manage their shortened URLs.

    This system provides a basic yet scalable approach to URL shortening.
    You can extend it further with features like analytics, custom short URLs,
    and URL expiry.
*/
class URLShortenerService {
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private Map<String, String> shortToLongMap; // Mapping of short URL to long URL
    private Map<String, String> longToShortMap; // Mapping of long URL to short URL
    private Random random;

    public URLShortenerService() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
        this.random = new Random();
    }

    // Method to shorten a long URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL); // Return the existing short URL if already shortened
        }

        String shortURL = generateShortURL();
        while (shortToLongMap.containsKey(shortURL)) {
            shortURL = generateShortURL(); // Ensure uniqueness of short URL
        }

        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);
        return shortURL;
    }

    // Method to retrieve the original long URL from the short URL
    public String resolveURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, null); // Returns null if not found
    }

    // Helper method to generate a random short URL
    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(BASE62_CHARS.length());
            shortURL.append(BASE62_CHARS.charAt(index));
        }
        return shortURL.toString();
    }
}

public class PartDURLShortenerApp {
    public static void main(String[] args) {
        URLShortenerService urlShortener = new URLShortenerService();

        // Shortening URLs
        String shortURL1 = urlShortener.shortenURL("https://www.example.com/very/long/url/1");
        System.out.println("Shortened URL 1: " + shortURL1);

        String shortURL2 = urlShortener.shortenURL("https://www.example.com/very/long/url/2");
        System.out.println("Shortened URL 2: " + shortURL2);

        // Resolving URLs
        String originalURL1 = urlShortener.resolveURL(shortURL1);
        System.out.println("Resolved URL 1: " + originalURL1);

        String originalURL2 = urlShortener.resolveURL(shortURL2);
        System.out.println("Resolved URL 2: " + originalURL2);
    }
}
