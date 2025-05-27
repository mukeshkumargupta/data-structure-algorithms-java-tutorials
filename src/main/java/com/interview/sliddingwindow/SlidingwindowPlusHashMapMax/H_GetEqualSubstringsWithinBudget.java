package com.interview.sliddingwindow.SlidingwindowPlusHashMapMax;

/*
 * https://leetcode.com/problems/get-equal-substrings-within-budget/description/
 *
 * Category: Medium
 * Tags: Sliding Window, String, Two Pointers
 *
 * 🎯 Problem:
 * You are given two strings s and t of the same length and an integer maxCost.
 * Changing the ith character of s to the ith character of t costs |s[i] - t[i]|
 * (absolute difference of ASCII values).
 *
 * Return the maximum length of a substring of s that can be changed to match
 * the corresponding substring of t with a total cost <= maxCost.
 *
 * 🧪 Examples:
 * Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" -> "bcd" costs 3.
 *
 * Input: s = "abcd", t = "cdef", maxCost = 3
 * Output: 1
 * Explanation: Every char costs 2, so max length = 1.
 *
 * Input: s = "abcd", t = "acde", maxCost = 0
 * Output: 1
 * Explanation: No changes allowed, longest same-char substring is 1.
 *
 * 🔗 Related:
 * https://leetcode.com/problems/longest-nice-subarray/ Medium
 *
 * ✅ Constraints:
 * - 1 <= s.length <= 10^5
 * - t.length == s.length
 * - 0 <= maxCost <= 10^6
 * - s and t consist of lowercase English letters only.
 */
public class H_GetEqualSubstringsWithinBudget {
    /*
     * 🧠 Explanation:
     * Use a sliding window approach to maintain a valid window where the total transformation
     * cost is within maxCost.
     *
     * If cost exceeds maxCost, move the left pointer to shrink the window.
     *
     * Track the maximum window size where the cost remains valid.
     *
     * 🕰️ Time Complexity:
     * O(n) — Each character is processed at most twice (once when added, once when removed).
     *
     * 🧠 Space Complexity:
     * O(1) — Constant space used for pointers and cost tracking.
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, right = 0;
        int n = s.length();
        int currentCost = 0;
        int maxLength = 0;

        while (right < n) {
            currentCost += Math.abs(s.charAt(right) - t.charAt(right));

            // Shrink window if cost exceeds budget
            while (currentCost > maxCost) {
                currentCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Update max length of valid window
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
