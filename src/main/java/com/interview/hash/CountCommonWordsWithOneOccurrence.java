package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/count-common-words-with-one-occurrence/
 * Category: Medium
 * Derived: Try exactly two occurrence:
 * Related: https://leetcode.com/problems/uncommon-words-from-two-sentences/ Easy Good Question
 * https://leetcode.com/problems/kth-distinct-string-in-an-array/ Easy Good Question
 * Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.

 

Example 1:

Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
Output: 2
Explanation:
- "leetcode" appears exactly once in each of the two arrays. We count this string.
- "amazing" appears exactly once in each of the two arrays. We count this string.
- "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
- "as" appears once in words1, but does not appear in words2. We do not count this string.
Thus, there are 2 strings that appear exactly once in each of the two arrays.
Example 2:

Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
Output: 0
Explanation: There are no strings that appear in each of the two arrays.
Example 3:

Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
Output: 1
Explanation: The only string that appears exactly once in each of the two arrays is "ab".
 

Constraints:

1 <= words1.length, words2.length <= 1000
1 <= words1[i].length, words2[j].length <= 30
words1[i] and words2[j] consists only of lowercase English letters.
 */
public class CountCommonWordsWithOneOccurrence {
    /*
        🔹 Approach 1: Brute Force (Two Hash Maps)

        🚀 Idea:
        - Use two separate hash maps to count occurrences of words in both arrays.
        - Iterate over words1 and check if the word appears exactly once in both maps.

        🔹 Complexity Analysis:
        - Building Frequency Map: O(n + m)
        - Checking for Valid Words: O(n)
        - Total Time Complexity: O(n + m)
        - Space Complexity: O(n + m) (storing frequency maps)
    */
    private static class BruitForce {
        public int countWords(String[] words1, String[] words2) {
            Map<String, Integer> frequencyMap1 = new HashMap<>();
            Map<String, Integer> frequencyMap2 = new HashMap<>();

            // Count word occurrences in words1
            for (String word : words1) {
                frequencyMap1.put(word, frequencyMap1.getOrDefault(word, 0) + 1);
            }

            // Count word occurrences in words2
            for (String word : words2) {
                frequencyMap2.put(word, frequencyMap2.getOrDefault(word, 0) + 1);
            }

            int commonWordCount = 0;

            // Check for words that appear exactly once in both arrays
            for (String word : frequencyMap1.keySet()) {
                if (frequencyMap1.get(word) == 1 && frequencyMap2.getOrDefault(word, 0) == 1) {
                    commonWordCount++;
                }
            }

            return commonWordCount;
        }

        public static void main(String[] args) {
            BruitForce solver = new BruitForce();
            String[] words1 = {"apple", "banana", "apple", "orange"};
            String[] words2 = {"banana", "orange", "grape", "orange"};
            System.out.println(solver.countWords(words1, words2)); // Output: 1 ("banana")
        }
    }

/*
     🔹 Optimized Approach (Using Single Hash Map)

🚀 Idea
Instead of using two separate hash maps, we:

✅ Use one map (`wordCount`) to track occurrences across both arrays.
✅ Use another set (`uniqueWords`) to track words appearing only once in each array.
✅ Iterate once to filter words that appear exactly once.

🔹 Complexity Analysis
   - **Time Complexity:** O(n + m)
   - **Space Complexity:** O(n + m) (stores words)

🔹 Summary

| Approach     | Time Complexity | Space Complexity | Explanation |
|-------------|----------------|------------------|-------------|
| **Brute Force** | O(n + m) | O(n + m) | Uses two separate hash maps. |
| **Optimized**  | O(n + m) | O(n + m) | Uses a single map with special marking. |

*/

    private static class Optimized {
        public int countWords(String[] words1, String[] words2) {
            // Step 1: Use a map to track occurrences
            Map<String, Integer> wordCount = new HashMap<>();

            // Step 2: Count occurrences from words1
            for (String word : words1) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            // Step 3: Count occurrences from words2
            for (String word : words2) {
                // If already in the map, update its count
                if (wordCount.containsKey(word)) {
                    if (wordCount.get(word) == 1) {
                        wordCount.put(word, -1); // Mark as appearing once in both
                    } else {
                        wordCount.put(word, -2); // More than once in words1
                    }
                } else {
                    wordCount.put(word, -2); // Appears multiple times or only in words2
                }
            }

            // Step 4: Count words that appeared exactly once in both arrays
            int commonCount = 0;
            for (int count : wordCount.values()) {
                if (count == -1) { // Appears exactly once in both
                    commonCount++;
                }
            }

            return commonCount;
        }

        public static void main(String[] args) {
            Optimized solver = new Optimized();
            String[] words1 = {"apple", "banana", "apple", "orange"};
            String[] words2 = {"banana", "orange", "grape", "orange"};
            System.out.println(solver.countWords(words1, words2)); // Output: 1 ("banana")
        }
    }
    
}
