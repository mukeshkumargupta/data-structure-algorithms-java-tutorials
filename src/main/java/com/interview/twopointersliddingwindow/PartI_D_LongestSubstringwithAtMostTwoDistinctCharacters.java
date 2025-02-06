package com.interview.twopointersliddingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/ (Medium) Locked it is same as k distinc but here not to try goal and goal -1
https://www.naukri.com/code360/problems/longest-substring-with-at-most-two-distinct-characters_3125863?leftPanelTabValue=SUBMISSION
    Problem: Longest Substring with At Most Two Distinct Characters
    Brute Force Approach (O(n²))
    Approach
    Iterate over all possible substrings.
    For each substring, check if it contains at most two distinct characters.
    Keep track of the maximum length found.
    Time Complexity:
    O(n²): We iterate through all substrings (nested loop).
    O(n) extra space: HashSet for tracking distinct characters.
    Why is it inefficient?
    It unnecessarily checks all substrings, even if a character sequence is valid.
 */
public class PartI_D_LongestSubstringwithAtMostTwoDistinctCharacters {

    public static class Bruitforce {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int maxLen = 0;

            for (int i = 0; i < s.length(); i++) {
                Set<Character> distinctChars = new HashSet<>();
                for (int j = i; j < s.length(); j++) {
                    distinctChars.add(s.charAt(j));

                    // If more than two distinct characters, break
                    if (distinctChars.size() > 2) break;

                    // Update max length
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }

            return maxLen;
        }
    }

    public static class Optimal {
        /*Optimal Approach (Sliding Window) - O(n)
        Approach
        Use two pointers (left, right) and a HashMap to store character frequencies.
                Expand right to include new characters.
                If more than two distinct characters, shrink left until only two remain.
                Maintain the maximum length found.*

         */

        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int left = 0, maxLen = 0;
            Map<Character, Integer> freqMap = new HashMap<>();
            int right = 0;
            int len = s.length();

            while (right < len) {
                char rightChar = s.charAt(right);
                freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);

                while (freqMap.size() > 2) { // More than 2 distinct characters
                    char leftChar = s.charAt(left);
                    freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                    if (freqMap.get(leftChar) == 0) freqMap.remove(leftChar);
                    left++; // Shrink window
                }

                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }

            return maxLen;
        }
    }
}
