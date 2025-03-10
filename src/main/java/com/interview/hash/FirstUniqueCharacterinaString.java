package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/zigzag-conversion/ Medium
 * https://leetcode.com/problems/design-phone-directory/ Medium
 * https://leetcode.com/problems/design-in-memory-file-system/ Medium
 * https://leetcode.com/problems/reorganize-string/ Medium VVImp
 * https://leetcode.com/problems/longest-arithmetic-subsequence/ Medium VVImp
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/ Medium VImp
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

 

Example 1:

Input: s = "leetcode"
Output: 0
Example 2:

Input: s = "loveleetcode"
Output: 2
Example 3:

Input: s = "aabb"
Output: -1
 

Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
 * 
 */
public class FirstUniqueCharacterinaString {
    /*
     * 1. Brute Force Approach (Nested Loops)
     *
     * Approach:
     * For each character in the string, check if it appears again in the string.
     * If it appears only once, return its index.
     *
     * Time & Space Complexity:
     * - Time Complexity: O(N^2)
     *   (Due to nested loops)
     * - Space Complexity: O(1)
     *   (No extra data structures)
     *
     * ✅ Easy to understand but inefficient for large inputs.
     */
    private static class BruitForce {
        public int firstUniqChar(String s) {
            for (int i = 0; i < s.length(); i++) {
                boolean unique = true;
                for (int j = 0; j < s.length(); j++) {
                    if (i != j && s.charAt(i) == s.charAt(j)) {
                        unique = false;
                        break;
                    }
                }
                if (unique) return i;
            }
            return -1;
        }
    }

    /*
     * 2. Better Approach (Using HashMap)
     *
     * Approach:
     * Use a HashMap to store the frequency of each character.
     * Then, iterate the string again to find the first character with a frequency of 1.
     *
     * Time & Space Complexity:
     * - Time Complexity: O(N)
     *   (Two passes: one to count, one to find the unique character)
     * - Space Complexity: O(1)
     *   (At most 26 distinct characters, constant space)
     *
     * ✅ More efficient than brute force, but uses extra space.
     */
    private static class Better {

        public int firstUniqChar(String s) {
            HashMap<Character, Integer> freq = new HashMap<>();

            // Count frequency of each character
            for (char ch : s.toCharArray()) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }

            // Find the first unique character
            for (int i = 0; i < s.length(); i++) {
                if (freq.get(s.charAt(i)) == 1) {
                    return i;
                }
            }

            return -1;
        }

    }

    /*
     * 3. Optimal Approach (Using Fixed-Size Array)
     *
     * Approach:
     * Instead of using a HashMap, we use a fixed-sized int array (int[26]) since we only deal with lowercase letters.
     *
     * 1st pass: Count occurrences of each character.
     * 2nd pass: Find the first character with a count of 1.
     *
     * Time & Space Complexity:
     * - Time Complexity: O(N)
     *   (Same as HashMap, but faster lookup due to array indexing)
     * - Space Complexity: O(1)
     *   (Fixed int[26] array, constant space)
     *
     * ✅ Best approach: Faster and more memory-efficient!
     */

    private static class Optimal {
        public int firstUniqChar(String s) {
            int[] freq = new int[26];

            // Count frequency of each character
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            // Find the first character with frequency 1
            for (int i = 0; i < s.length(); i++) {
                if (freq[s.charAt(i) - 'a'] == 1) {
                    return i;
                }
            }

            return -1; // No unique character found
        }
    }
}
