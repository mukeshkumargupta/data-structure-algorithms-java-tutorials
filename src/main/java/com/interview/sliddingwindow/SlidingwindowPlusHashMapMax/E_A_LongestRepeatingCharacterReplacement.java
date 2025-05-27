package com.interview.sliddingwindow.SlidingwindowPlusHashMapMax;
/*
 * ----------------------------------------------------------------------------------------------------
 * 🔗 Video Explanation:
 * https://www.youtube.com/watch?v=_eNhaDCr6P0&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=8
 *
 * 🔗 LeetCode Problem Link:
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 *
 * 📂 Category: Medium, Tricky, Must Do
 *
 * 🔗 Related Problems:
 * - Longest Substring with At Most K Distinct Characters (Medium)
 *   https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
 *   [Locked on LeetCode, Unlocked on Naukri]
 *
 * - Minimum Number of Operations to Make Array Continuous (Hard)
 *   https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/description/
 *
 * - Maximize the Confusion of an Exam (Medium) – Exactly the same question with a different function name
 *   https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧠 Problem Statement:
 *
 * You are given a string `s` and an integer `k`. You can choose any character of the string
 * and change it to any other uppercase English character. You can perform this operation at most `k` times.
 *
 * Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧪 Examples:
 *
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace two 'A's with 'B's (or vice versa) to get "BBBB"
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle to get "AABBBBA"
 * Other ways may also yield the same length.
 *
 * ----------------------------------------------------------------------------------------------------
 * 🔒 Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of only uppercase English letters.
 * - 0 <= k <= s.length
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧩 Pattern:
 * Sliding Window + Frequency Count
 *
 * ✍️ Practice These Related Problems:
 *
 * | Problem                                 | Difficulty | LeetCode # |
 * |-----------------------------------------|------------|------------|
 * | Longest Repeating Character Replacement | Medium     | 424        |
 * | Maximize the Confusion of an Exam       | Medium     | 2024       |
 * | Max Consecutive Ones III                | Medium     | 1004       |
 * | Binary Subarrays With Sum               | Medium     | 930        |
 * | Subarray Product Less Than K            | Medium     | 713        |
 *
 * ----------------------------------------------------------------------------------------------------
 */
public class E_A_LongestRepeatingCharacterReplacement {
    /*
        Brute Force Approach (O(N²))
    Strategy:
    Try all substrings as potential candidates.
    For each substring, count the frequency of each character.
    Check if it can be made uniform by changing at most k characters.
    Track the maximum valid length.

    Complexity Analysis
Time Complexity: O(N²) → Checking all substrings.
Space Complexity: O(1) → Using only a fixed size frequency array of 26.
     */

    private static class BruitforceApproach {
        public int characterReplacementBruteForce(String s, int k) {
            int n = s.length();
            int maxLen = 0;

            for (int i = 0; i < n; i++) {
                int[] freq = new int[26];
                int maxFreq = 0; // Track most frequent char count

                for (int j = i; j < n; j++) {
                    freq[s.charAt(j) - 'A']++;
                    maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);

                    int length = j - i + 1;
                    int changesRequired = length - maxFreq;

                    if (changesRequired <= k) {
                        maxLen = Math.max(maxLen, length);
                    }
                }
            }

            return maxLen;
        }
    }

    private static class optimalApproach {
        /*
        Optimal Approach (Sliding Window - O(N))
        Strategy:
        Use a sliding window with two pointers (l and r).
        Keep track of max frequency of any character in the window.
        If the window length - max frequency ≤ k, it means we can convert the remaining characters to the most frequent character.
        If the condition fails, shrink the window from the left.
        Complexity Analysis
        Time Complexity: O(N) → Each character is processed at most twice (once when expanding and once when contracting).
        Space Complexity: O(1) → Using only a 26-sized array.
         */

        public int characterReplacement(String s, int k) {
            int[] freq = new int[26];
            int maxFreq = 0, left = 0, right=0,
                    maxLen = 0;
            int len = s.length();

            while (right < len) {
                freq[s.charAt(right) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

                // Condition: (window size - maxFreq) should be ≤ k
                while ((right - left + 1) - maxFreq > k) { //you can turn to if as well
                    freq[s.charAt(left) - 'A']--;
                    left++; // Shrink the window from left
                }

                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }

            return maxLen;
        }
    }
}
