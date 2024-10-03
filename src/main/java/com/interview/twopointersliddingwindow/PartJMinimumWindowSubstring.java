package com.interview.twopointersliddingwindow;

/*
The problem of finding the Minimum Window Substring involves determining the smallest substring of a given string s that contains all the characters of string t. This problem can be solved using a brute force, better, and optimal approach.

Problem Breakdown:
Given two strings s and t, find the smallest substring in s that contains all the characters from t (including duplicates). If no such substring exists, return an empty string.
https://www.youtube.com/watch?v=WJaij9ffOIY&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=12
https://leetcode.com/problems/minimum-window-substring/submissions/1423080303/
Category: Hard
Related:
https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/ Hard
https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/description/ Hard
https://leetcode.com/problems/minimum-window-subsequence/description/ Hard
 */
public class PartJMinimumWindowSubstring {
    /*
    1. Brute Force Approach (O(n^3)):
Approach:
Generate all possible substrings of s.
For each substring, check if it contains all the characters of t.
Track the minimum-length valid substring.
     */

    public String minWindowBruteForce(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String result = "";

        // Generate all substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);

                // Check if the substring contains all characters of t
                if (containsAllChars(sub, t)) {
                    if (sub.length() < minLen) {
                        minLen = sub.length();
                        result = sub;
                    }
                }
            }
        }
        return result;
    }

    // Helper function to check if substring contains all characters of t
    private boolean containsAllChars(String sub, String t) {
        int[] countT = new int[256];  // Frequency array for t
        for (char c : t.toCharArray()) {
            countT[c]++;
        }

        int[] countSub = new int[256];  // Frequency array for substring
        for (char c : sub.toCharArray()) {
            countSub[c]++;
        }

        // Check if substring has all characters of t
        for (char c : t.toCharArray()) {
            if (countSub[c] < countT[c]) {
                return false;
            }
        }
        return true;
    }


    /*
    2. Better Approach (O(n^2)):
Approach:
Use a sliding window approach to avoid generating all substrings.
For each starting point of the window, expand the window to find the first valid substring.
Once a valid substring is found, shrink the window from the left until it is no longer valid.
Explanation:
Instead of generating all substrings, we use a sliding window.
For each starting index i, we expand the window with a second pointer j and check if the window contains all characters of t.
Once a valid window is found, we check if it's the smallest so far.
Time Complexity: O(n^2), as we iterate over all starting points and expand the window for each.


     */
    public String minWindowBetter(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            int[] countT = new int[256];
            for (char c : t.toCharArray()) {
                countT[c]++;
            }

            int[] countWindow = new int[256];
            int distinct = 0;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                countWindow[c]++;

                if (countWindow[c] <= countT[c]) {
                    distinct++;
                }

                // Check if valid window
                if (distinct == t.length()) {
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                    break;
                }
            }
        }

        return result;
    }

    /*
    3. Optimal Approach (Sliding Window) (O(n)):
Approach:
Use a sliding window with two pointers (left and right) to find the minimum window containing all characters of t.
Maintain a frequency count of characters in t and the current window.
Expand the window by moving the right pointer, and when a valid window is found, shrink it by moving the left pointer to minimize its size.

Explanation:
Sliding Window:

We maintain two pointers left and right, which represent the current window.
We expand the window by moving right, and when the window is valid (i.e., it contains all characters of t), we try shrinking it by moving left.
Frequency Count:

We maintain two arrays, countT (frequency of characters in t) and countWindow (frequency of characters in the current window).
The variable formed keeps track of how many characters of t are currently satisfied in the window.
Minimizing the Window:

Once a valid window is found, we attempt to shrink it to get the minimum possible window that contains all the characters of t.
Time Complexity:

O(n), as both left and right pointers traverse the string s exactly once.
     */

    public String minWindowOptimal(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] countT = new int[256];  // Frequency of characters in t
        for (char c : t.toCharArray()) {
            countT[c]++;
        }

        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;  // To store the starting index of the result
        int required = t.length();  // Total characters to match
        int formed = 0;  // To keep track of matched characters in the window

        int[] countWindow = new int[256];  // Frequency of characters in the current window

        while (right < s.length()) {
            // Expand the window by including the right character
            char cRight = s.charAt(right);
            countWindow[cRight]++;

            // If the character is in t and we match the required count, increment `formed`
            if (countWindow[cRight] <= countT[cRight]) {
                formed++;
            }

            // When the window is valid, try shrinking it from the left
            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Shrink the window by excluding the left character
                char cLeft = s.charAt(left);
                countWindow[cLeft]--;
                if (countWindow[cLeft] < countT[cLeft]) {
                    formed--;
                }
                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
