package com.interview.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
🔗 Problem: https://leetcode.com/problems/custom-sort-string/description/
📌 Category: Medium, Facebook
https://www.youtube.com/watch?v=eAU3snVZs5Q
🔗 Related: https://leetcode.com/problems/sort-the-students-by-their-kth-score/ (Medium)

📝 Problem Description:
You are given two strings `order` and `s`. All characters in `order` are unique and sorted in a custom order.
Rearrange the characters in `s` such that they appear in the same order as `order`.
Characters in `s` that are not in `order` can appear in any position.

🔹 Example 1:
Input: order = "cba", s = "abcd"
Output: "cbad"

🔹 Example 2:
Input: order = "bcafg", s = "abcd"
Output: "bcad"

🔹 Constraints:
- 1 <= order.length <= 26
- 1 <= s.length <= 200
- `order` and `s` consist of lowercase English letters.
- All characters in `order` are unique.
*/
public class CustomSortString {
private static class BruitForce {
    /*
        1️⃣ Brute Force (O(N log N)) - Custom Comparator
        We can sort `s` using a custom comparator based on the `order` string.

        📝 Algorithm:
        1. Store order's character priorities in a HashMap.
        2. Sort `s` based on order's priorities.
        3. Return the sorted string.

        📊 Complexity Analysis:
        - Sorting takes O(N log N) where N is the length of `s`.
        - Space Complexity: O(N) (for storing the sorted array).

        🔍 Comparison of Approaches:
        | Approach             | Time Complexity | Space Complexity | Notes                                  |
        |----------------------|----------------|------------------|----------------------------------------|
        | Brute Force (Sorting)| O(N log N)      | O(N)             | Uses sorting with a custom comparator |
        | Optimized (Counting) | O(N)            | O(1)             | Uses frequency counting and string construction |

        ✅ The Optimized Approach (O(N)) is recommended for better efficiency! 🚀
        Would you like a dry-run example for better understanding? 😊
    */
    public static String customSortStringBrute(String order, String s) {
        // Step 1: Store priority in a HashMap
        Map<Character, Integer> priority = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            priority.put(order.charAt(i), i);
        }

        // Step 2: Convert s to character array and sort it
        Character[] chars = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }

        Arrays.sort(chars, (a, b) -> priority.getOrDefault(a, 26) - priority.getOrDefault(b, 26));

        // Step 3: Convert back to string
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(customSortStringBrute("cba", "abcd")); // Output: "cbad"
    }
}
    /*
        2️⃣ Optimized Approach (O(N)) - Frequency Counting
        Instead of sorting, we can use a frequency count of `s` and construct the result directly in O(N) time.

        📝 Algorithm:
        1. Count the frequency of each character in `s` using an array `freq[]` (since there are only 26 lowercase letters).
        2. Add characters from `order` first (in the given order).
        3. Add remaining characters from `s` that were not in `order`.

        📊 Complexity Analysis:
        - Building frequency array: O(N)
        - Constructing the result: O(N)
        - Overall Complexity: O(N)
        - Space Complexity: O(1) (since `freq[]` is a fixed size of 26)

        🔍 Comparison of Approaches:
        | Approach             | Time Complexity | Space Complexity | Notes                                  |
        |----------------------|----------------|------------------|----------------------------------------|
        | Brute Force (Sorting)| O(N log N)      | O(N)             | Uses sorting with a custom comparator |
        | Optimized (Counting) | O(N)            | O(1)             | Uses frequency counting and string construction |

        ✅ Final Recommendation:
        Use the Optimized Approach (O(N)) for efficiency! 🚀
        Would you like a dry-run example for better understanding? 😊
    */
    private static class Optimized {
        public static String customSortStringOptimized(String order, String s) {
            int[] freq = new int[26]; // Step 1: Count frequency
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder result = new StringBuilder();

            // Step 2: Add characters from `order` in correct order
            for (char c : order.toCharArray()) {
                while (freq[c - 'a'] > 0) {
                    result.append(c);
                    freq[c - 'a']--;
                }
            }

            // Step 3: Add remaining characters
            for (char c = 'a'; c <= 'z'; c++) {
                while (freq[c - 'a'] > 0) {
                    result.append(c);
                    freq[c - 'a']--;
                }
            }

            return result.toString();
        }

        public static void main(String[] args) {
            System.out.println(customSortStringOptimized("cba", "abcd")); // Output: "cbad"
        }
    }
}
