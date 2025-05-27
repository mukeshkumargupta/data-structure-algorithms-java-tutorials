package com.interview.sliddingwindow;

/*
The problem of finding the Minimum Window Substring involves determining the smallest substring of a given string s that contains all the characters of string t. This problem can be solved using a brute force, better, and optimal approach.

Problem Breakdown:
Given two strings s and t, find the smallest substring in s that contains all the characters from t (including duplicates). If no such substring exists, return an empty string.
https://www.youtube.com/watch?v=WJaij9ffOIY&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=12
https://leetcode.com/problems/minimum-window-substring/submissions/1423080303/
Category: Hard, Must Do
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

    /**
     * Optimal Approach (Sliding Window) - O(n)
     *
     * ### Approach:
     * - Use a sliding window with two pointers (`left` and `right`) to find the minimum window containing all characters of `t`.
     * - Maintain a frequency count of characters in `t` and the current window.
     * - Expand the window by moving the `right` pointer, and when a valid window is found, shrink it by moving the `left` pointer to minimize its size.
     *
     * ### Explanation:
     *
     * #### Sliding Window:
     * - Maintain two pointers `left` and `right`, representing the current window.
     * - Expand the window by moving `right`, and when the window is valid (i.e., it contains all characters of `t`), try shrinking it by moving `left`.
     *
     * #### Frequency Count:
     * - Maintain two arrays:
     *   - `countT`: Frequency of characters in `t`
     *   - `countWindow`: Frequency of characters in the current window.
     * - A variable `formed` keeps track of how many characters of `t` are currently satisfied in the window.
     *
     * #### Minimizing the Window:
     * - Once a valid window is found, attempt to shrink it to get the minimum possible window that contains all characters of `t`.
     *
     * ### Time Complexity:
     * - **O(n)**: Both `left` and `right` pointers traverse the string `s` exactly once.
     *
     * ---
     *
     * ## Tabular Explanation of the Optimal Approach
     *
     * ### **Input:**
     * `s = "ADOBECODEBANC", t = "ABC"`
     *
     * **Initialization:**
     * - `countT`: Frequency map of `t` → `{A:1, B:1, C:1}`
     * - `left = 0`, `right = 0`, `minLen = ∞`, `start = 0`, `required = 3`, `formed = 0`
     * - `countWindow`: Empty frequency map `{}`
     *
     * **Step-by-Step Execution:**
     *
     * | Step | Left | Right | Current Window | countWindow            | Formed | Min Length | Start | Action          |
     * |------|------|-------|---------------|------------------------|--------|------------|--------|----------------|
     * | 1    | 0    | 0     | "A"           | {A:1}                  | 1      | ∞          | -      | Expand Right   |
     * | 2    | 0    | 1     | "AD"          | {A:1, D:1}             | 1      | ∞          | -      | Expand Right   |
     * | 3    | 0    | 2     | "ADO"         | {A:1, D:1, O:1}        | 1      | ∞          | -      | Expand Right   |
     * | 4    | 0    | 3     | "ADOB"        | {A:1, D:1, O:1, B:1}   | 2      | ∞          | -      | Expand Right   |
     * | 5    | 0    | 4     | "ADOBE"       | {A:1, D:1, O:1, B:1, E:1} | 2  | ∞          | -      | Expand Right   |
     * | 6    | 0    | 5     | "ADOBEC"      | {A:1, D:1, O:1, B:1, E:1, C:1} | 3 | 6 | 0 | Valid Window Found |
     * | 7    | 1    | 5     | "DOBEC"       | {A:0, D:1, O:1, B:1, E:1, C:1} | 2 | 6 | 0 | Shrink Left |
     * | 8    | 1    | 6     | "DOBECO"      | {A:0, D:1, O:2, B:1, E:1, C:1} | 2 | 6 | 0 | Expand Right |
     * | 9    | 1    | 7     | "DOBECOD"     | {A:0, D:2, O:2, B:1, E:1, C:1} | 2 | 6 | 0 | Expand Right |
     * | 10   | 1    | 8     | "DOBECODE"    | {A:0, D:2, O:2, B:1, E:2, C:1} | 2 | 6 | 0 | Expand Right |
     * | 11   | 1    | 9     | "DOBECODEB"   | {A:0, D:2, O:2, B:2, E:2, C:1} | 2 | 6 | 0 | Expand Right |
     * | 12   | 1    | 10    | "DOBECODEBA"  | {A:1, D:2, O:2, B:2, E:2, C:1} | 3 | 6 | 0 | Valid Window Found |
     * | 13   | 2    | 10    | "OBECODEBA"   | {A:1, D:1, O:2, B:2, E:2, C:1} | 3 | 6 | 0 | Shrink Left |
     * | 14   | 3    | 10    | "BECODEBA"    | {A:1, D:0, O:2, B:2, E:2, C:1} | 3 | 6 | 0 | Shrink Left |
     * | 15   | 4    | 10    | "ECODEBA"     | {A:1, D:0, O:2, B:2, E:1, C:1} | 3 | 6 | 0 | Shrink Left |
     * | 16   | 5    | 10    | "CODEBA"      | {A:1, D:0, O:1, B:2, E:1, C:1} | 3 | 6 | 0 | Shrink Left |
     * | 17   | 6    | 10    | "ODEBA"       | {A:1, D:0, O:1, B:2, E:1, C:0} | 2 | 6 | 0 | Shrink Left (Invalid) |
     * | 18   | 6    | 11    | "ODEBAN"      | {A:1, D:0, O:1, B:1, E:1, C:0, N:1} | 2 | 6 | 0 | Expand Right |
     * | 19   | 6    | 12    | "ODEBANC"     | {A:1, D:0, O:1, B:1, E:1, C:1, N:1} | 3 | 4 | 6 | Valid Window Found |
     * | 20   | 7    | 12    | "DEBANC"      | {A:1, D:0, O:0, B:1, E:1, C:1, N:1} | 3 | 4 | 6 | Shrink Left |
     * | 21   | 8    | 12    | "EBANC"       | {A:1, D:0, O:0, B:1, E:0, C:1, N:1} | 3 | 4 | 6 | Shrink Left |
     * | 22   | 9    | 12    | "BANC"        | {A:1, D:0, O:0, B:1, E:0, C:1, N:1} | 3 | 4 | 9 | Shrink Left (Final Answer) |
     *
     * **Final Output:** `"BANC"`
     *
     * ---
     *
     * ### Key Takeaways:
     * - Expand the window (`right` pointer) until all required characters are found.
     * - Shrink the window (`left` pointer) to find the minimum valid window.
     * - Update the minimum length whenever a valid window is found.
     * - The best window found is `"BANC"`, with length `4`.
     *
     * 🚀 **Time Complexity:** `O(N)`
     * 🚀 **Space Complexity:** `O(1)` (Fixed-size arrays `countT` and `countWindow` of size 256)
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
