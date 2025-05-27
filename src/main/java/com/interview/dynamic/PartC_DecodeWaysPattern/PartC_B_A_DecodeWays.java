package com.interview.dynamic.PartC_DecodeWaysPattern;

import java.util.Arrays;

/*
 * LeetCode Problem: Decode Ways
 *
 * Problem Link: https://leetcode.com/problems/decode-ways/
 *
 * Categories: Medium, Facebook, Tricky, Top150, VImp
 * Related Problems:
 * - Decode Ways II: https://leetcode.com/problems/decode-ways-ii/ (Hard)
 * - Number of Ways to Separate Numbers: https://leetcode.com/problems/number-of-ways-to-separate-numbers/ (Hard)
 * https://leetcode.com/problems/count-number-of-texts/ Medium
 *
 *
 * Problem Description:
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 *
 * To decode an encoded message, all digits must be grouped and then mapped back into letters
 * using the reverse of the above mapping (there may be multiple ways).
 * For example, the encoded string "11106" can be decoded in the following ways:
 *
 * - "AAJF" with the grouping (1 1 10 6)
 * - "KJF" with the grouping (11 10 6)
 *
 * Note: The grouping (1 11 06) is invalid because "06" cannot be mapped into 'F'.
 *
 * Given a string `s` containing only digits, the goal is to return the number of ways to decode it.
 * The answer is guaranteed to fit within a 32-bit integer.
 *
 * Example Cases:
 * 1. Input: s = "12"
 *    Output: 2
 *    Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * 2. Input: s = "226"
 *    Output: 3
 *    Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * 3. Input: s = "0"
 *    Output: 0
 *    Explanation: There are no characters mapped to a number starting with 0.
 *
 * 4. Input: s = "06"
 *    Output: 0
 *    Explanation: "06" cannot be mapped to "F" due to the leading zero.
 *
 * Constraints:
 * - 1 <= s.length <= 100
 * - s contains only digits and may contain leading zeros.
 */

public class PartC_B_A_DecodeWays {

    /*
    1. Brute Force Approach

    Explanation:
    The brute force approach involves recursively attempting to decode the string by evaluating all possible partitions into one-digit and two-digit numbers. It counts the number of valid decodings that can be generated until reaching the end of the string. This method lacks optimization techniques, resulting in substantial redundancy in calculations.

    Time Complexity:
    O(2^n): Each character can branch into two possibilities in the recursive tree (one for single digits and one for two digits), which leads to exponential growth in function calls.

    Space Complexity:
    O(n): This is attributed to the recursion stack, where n represents the length of the input string.
    */
public static class BruitForce {
        public int numDecodings(String s) {
            return decodeHelper(s, 0);
        }

        private int decodeHelper(String s, int index) {
            // Base cases
            if (index == s.length()) return 1; // Reached the end
            if (s.charAt(index) == '0') return 0; // Invalid leading zero

            // Single digit decode
            int count = decodeHelper(s, index + 1);

            // Two digits decode
            if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
                count += decodeHelper(s, index + 2);
            }

            return count;
        }



}

    /*
    2. Improved Approach (Memoization)

    Explanation:
    This approach builds upon the brute force method by utilizing a hash map to store the results of previously computed states (memoization). When the algorithm encounters a state that has already been evaluated, it retrieves the result from the hash map instead of recalculating it. This significantly reduces the number of computations required, enhancing the overall speed of the algorithm.

    Time Complexity:
    O(n): The time complexity is linear, as each state is computed at most once, leading to efficient performance.

    Space Complexity:
    O(n): The space complexity arises from the need for storage in the memoization hash map and the recursion stack.
    */
public static class BetterApproach {
    public int numDecodings(String s) {
        int[] memo = new int[s.length()+1];
        Arrays.fill(memo, -1);
        return decodeHelper(s, 0, memo);
    }

    private int decodeHelper(String s, int index, int[] memo) {
        // Base cases
        if (index == s.length()) return 1; // Reached the end
        if (s.charAt(index) == '0') return 0; // Invalid leading zero

        // Check if already computed
        if (memo[index] != -1) {
            return  memo[index];
        }

        // Single digit decode
        int count = decodeHelper(s, index + 1, memo);

        // Two digits decode
        if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            count += decodeHelper(s, index + 2, memo);
        }
        return memo[index] = count;
    }
}

    /*
    To convert the memoization approach to a tabulation approach (bottom-up) for the "Decode Ways" problem, we will fill a dynamic programming table iteratively from the end of the string towards the beginning. This allows us to visualize the process in a way similar to what the "Take You Forward" channel might demonstrate.

    **Tabulation Approach (Bottom-Up)**

    **Explanation:**
    In this approach, we maintain a `dp` array where each index represents the number of ways to decode the substring of `s` from that index to the end of the string. We start from the last character of the string and work our way back to the beginning, using the same logic to determine the number of valid decodings based on the current character and the previous one.

    1. **Initialization:**
       Set up the `dp` array where `dp[i]` will store the number of ways to decode the substring starting at index `i`. Initialize `dp[n] = 1`, representing one way to decode an empty string.

    2. **Filling the Table:**
       - If the current character is not '0', then it can contribute to the decoding.
       - Check if the last two characters form a valid two-digit number (between 10 and 26), and update the count accordingly.

    **Time Complexity:**
    O(n): Each character is processed once.

    **Space Complexity:**
    O(n): The space complexity is due to the `dp` array.

    **Java Code:**
    Here’s how you can implement the tabulation approach:
    */
public static class OptimizedApproach {
        public int numDecodings(String s) {
            int n = s.length();

            // Create a dp array
            int[] dp = new int[n + 1];
            dp[n] = 1; // Base case: one way to decode an empty string

            for (int i = n - 1; i >= 0; i--) {
                // Single digit decode
                if (s.charAt(i) != '0') {
                    dp[i] += dp[i + 1];
                }

                // Two digits decode
                if (i + 1 < n) {
                    int twoDigit = Integer.parseInt(s.substring(i, i + 2));
                    if (twoDigit >= 10 && twoDigit <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }

            return dp[0]; // The number of ways to decode the entire string
        }

    }
}

