package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 * Category: Medium, Tricky Open Text
 * Related:
 * https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/ Medium
 * https://leetcode.com/problems/removing-minimum-and-maximum-from-array/ Medium
 * https://leetcode.com/problems/remove-letter-to-equalize-frequency/ Easy
 * https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/ Medium
 * 
 * A string s is called good if there are no two different characters in s that have the same frequency.

Given a string s, return the minimum number of characters you need to delete to make s good.

The frequency of a character in a string is the number of times it appears in the string.
* For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

 

Example 1:

Input: s = "aab"
Output: 0
Explanation: s is already good.
Example 2:

Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
Example 3:

Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 */
public class MinDeletion {
    /*
        🔹 Approach 1: Brute Force (Try All Possibilities)

        🚀 Idea:
        - Count the frequency of each character.
        - Sort the frequencies in descending order.
        - Try reducing duplicate frequencies manually by iterating and decrementing.

        ⏳ Complexity Analysis:
        - Time Complexity: O(N log N) due to sorting.
        - Space Complexity: O(1) (only uses an array of size 26).
    */
    private static class BruitForce {
        public int minDeletions(String s) {
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            Arrays.sort(freq); // Sort the frequency array
            int deletions = 0;

            for (int i = 24; i >= 0; i--) {
                if (freq[i] == 0) break; // Stop if remaining frequencies are 0
                if (freq[i] >= freq[i + 1]) {
                    int newFreq = Math.max(0, freq[i + 1] - 1);
                    deletions += (freq[i] - newFreq);
                    freq[i] = newFreq;
                }
            }
            return deletions;
        }
    }

    /*
        🔹 Approach 2: Better (Using a Set)

        🚀 Idea:
        - Use a Set to store used frequencies.
        - If a frequency is already used, decrement it until it becomes unique.

        ⏳ Complexity Analysis:
        - Time Complexity: O(N) (one pass through the string and one pass through frequencies).
        - Space Complexity: O(26) = O(1) (we store at most 26 unique frequencies).
    */
    private static class Better {
        public int minDeletions(String s) {
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            Set<Integer> usedFrequencies = new HashSet<>();
            int deletions = 0;

            for (int count : freq) {
                while (count > 0 && usedFrequencies.contains(count)) {
                    count--; // Reduce frequency
                    deletions++;
                }
                usedFrequencies.add(count);
            }
            return deletions;
        }
    }

    /*
        🔹 Approach 3: Optimized (Using Priority Queue)

        🚀 Idea:
        - Use a Max Heap (Priority Queue) to process higher frequencies first.
        - Remove duplicates efficiently.

        ⏳ Complexity Analysis:
        - Time Complexity: O(N + log C), where C is the highest frequency.
        - Space Complexity: O(26) = O(1).
    */
    private static class Optimized {
        public int minDeletions(String s) {
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int count : freq) {
                if (count > 0) pq.add(count);
            }

            int deletions = 0;
            while (pq.size() > 1) {
                int top = pq.poll(); // Highest frequency
                if (pq.peek() != null && pq.peek() == top) { // If next highest is same
                    if (top - 1 > 0) pq.add(top - 1); // Reduce and push back
                    deletions++;
                }
            }
            return deletions;
        }
    }
    
}
