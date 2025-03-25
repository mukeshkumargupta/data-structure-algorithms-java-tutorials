package com.interview.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
https://leetcode.com/problems/encode-and-decode-tinyurl/description/
category: Medium, Facebook, FAANG, Tricky

Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Implement the Solution class:

Solution() Initializes the object of the system.
String encode(String longUrl) Returns a tiny URL for the given longUrl.
String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.


Example 1:

Input: url = "https://leetcode.com/problems/design-tinyurl"
Output: "https://leetcode.com/problems/design-tinyurl"

Explanation:
Solution obj = new Solution();
string tiny = obj.encode(url); // returns the encoded tiny url.
string ans = obj.decode(tiny); // returns the original url after decoding it.


Constraints:

1 <= url.length <= 104
url is guranteed to be a valid URL.
 */
public class EncodeandDecodeTinyURL {

    /*
        🔴 Approach 1: Brute Force (Using a HashMap)

        💡 Idea:
        - Store each long URL in a HashMap with an incremental counter as the key.
        - When encoding, assign an integer ID (`count`) to each URL.
        - When decoding, retrieve the corresponding long URL using the stored mapping.

        🔵 Time Complexity:
          - **Encoding**: O(1) (HashMap insert is O(1))
          - **Decoding**: O(1) (HashMap lookup is O(1))

        🔵 Space Complexity: O(n) (Each URL is stored in the HashMap.)

        ❌ Why This is Suboptimal?
        - **Scalability issues**: As the number of URLs grows, memory usage increases.
        - **Not thread-safe**: Requires handling concurrency if multiple requests come in.

        🔹 Summary Table
        ┌───────────────────────────────┬─────────────────┬─────────────────┬──────────────────────────────────────────────┐
        │ Approach                      │ Time Complexity │ Space Complexity │ Notes                                        │
        ├───────────────────────────────┼─────────────────┼─────────────────┼──────────────────────────────────────────────┤
        │ Brute Force (HashMap + Counter) │ O(1)           │ O(n)            │ Simple but predictable, unscalable           │
        │ Hashing Approach               │ O(1)           │ O(n)            │ Avoids sequence, but hash collisions possible │
        │ Base62 Encoding (Optimized)    │ O(1)           │ O(n)            │ Best for scalability, prevents collisions     │
        └───────────────────────────────┴─────────────────┴─────────────────┴──────────────────────────────────────────────┘

        ✅ Final Recommendation:
        - Use **Approach 3 (Base62 Encoding)** for a scalable solution.
        - Approach 2 (Hashing) is simple but may cause collisions.
    */
    private static class BruitForce {
        private Map<Integer, String> map = new HashMap<>();
        private int count = 0;
        private static final String BASE = "http://tinyurl.com/";

        public String encode(String longUrl) {
            count++;
            map.put(count, longUrl);
            return BASE + count;
        }

        public String decode(String shortUrl) {
            int key = Integer.parseInt(shortUrl.replace(BASE, ""));
            return map.get(key);
        }
    }


    /*
        🟡 Approach 2: Better (Using HashMap with Hashing)

        💡 Idea:
        - Instead of using an incremental counter, use a hashing function (`longUrl.hashCode()`).
        - The hash value uniquely represents the long URL.
        - Store only unique long URLs to avoid redundancy.

        🔵 Time Complexity: O(1) for both encoding and decoding.
        🔵 Space Complexity: O(n) (Each unique URL is stored in the HashMap.)

        ✅ Why This is Better?
        - Avoids simple integer sequence, making the short URL harder to guess.
        - Hashing provides uniqueness, reducing conflicts.

        ❌ Issues with Hashing Approach:
        - **Collisions possible**: Different URLs can have the same hash.
        - **Hash values can be negative**, requiring adjustments.

        🔹 Summary Table
        ┌───────────────────────────────┬─────────────────┬─────────────────┬──────────────────────────────────────────────┐
        │ Approach                      │ Time Complexity │ Space Complexity │ Notes                                        │
        ├───────────────────────────────┼─────────────────┼─────────────────┼──────────────────────────────────────────────┤
        │ Brute Force (HashMap + Counter) │ O(1)           │ O(n)            │ Simple but predictable, unscalable           │
        │ Hashing Approach               │ O(1)           │ O(n)            │ Avoids sequence, but hash collisions possible │
        │ Base62 Encoding (Optimized)    │ O(1)           │ O(n)            │ Best for scalability, prevents collisions     │
        └───────────────────────────────┴─────────────────┴─────────────────┴──────────────────────────────────────────────┘

        ✅ Final Recommendation:
        - Use **Approach 3 (Base62 Encoding)** for a scalable solution.
        - Approach 2 (Hashing) is simple but may cause collisions.
    */
    private static class Better {
        private Map<Integer, String> map = new HashMap<>();
        private static final String BASE = "http://tinyurl.com/";

        public String encode(String longUrl) {
            int hash = longUrl.hashCode(); // Generate a unique hash for the URL
            map.put(hash, longUrl);
            return BASE + hash;
        }

        public String decode(String shortUrl) {
            int key = Integer.parseInt(shortUrl.replace(BASE, ""));
            return map.get(key);
        }
    }

    /*
        🟢 Approach 3: Optimized (Using Base62 Encoding)

        💡 Idea:
        - Instead of using an integer or hash, encode the URL using Base62 encoding (characters 0-9, a-z, A-Z).
        - Maintain a random 6-character key as the short URL identifier.
        - Store the mapping in a HashMap.

        🔵 Time Complexity: O(1) for both encoding and decoding.
        🔵 Space Complexity: O(n) (Each unique URL is stored in the HashMap.)

        🚀 Why This is the Best?
        - Scalable: No incremental counter, reducing predictability.
        - Shorter URLs: Uses only 6 characters (instead of long hashes or numbers).
        - Prevents collisions: By ensuring unique random keys.

        🔹 Summary Table
        ┌───────────────────────────────┬─────────────────┬─────────────────┬───────────────────────────────────────┐
        │ Approach                      │ Time Complexity │ Space Complexity │ Notes                                 │
        ├───────────────────────────────┼─────────────────┼─────────────────┼───────────────────────────────────────┤
        │ Brute Force (HashMap + Counter) │ O(1)           │ O(n)            │ Simple but predictable, unscalable    │
        │ Hashing Approach               │ O(1)           │ O(n)            │ Avoids sequence, but hash collisions possible │
        │ Base62 Encoding (Optimized)    │ O(1)           │ O(n)            │ Best for scalability, prevents collisions │
        └───────────────────────────────┴─────────────────┴─────────────────┴───────────────────────────────────────┘

        ✅ Final Recommendation:
        - For a scalable solution, use **Approach 3 (Base62 Encoding)**.
        - For simplicity, **Approach 2 (Hashing)** can work but has collision risks.
    */
    private static class Optimal {
        private Map<String, String> map = new HashMap<>();
        private static final String BASE = "http://tinyurl.com/";
        private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private Random random = new Random();

        public String encode(String longUrl) {
            String key;
            do {
                key = getRandomKey();
            } while (map.containsKey(key)); // Ensure unique key
            map.put(key, longUrl);
            return BASE + key;
        }

        public String decode(String shortUrl) {
            String key = shortUrl.replace(BASE, "");
            return map.get(key);
        }

        private String getRandomKey() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return sb.toString();
        }
    }
}
