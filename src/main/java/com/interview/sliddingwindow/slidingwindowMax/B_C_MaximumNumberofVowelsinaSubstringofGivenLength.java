package com.interview.sliddingwindow.slidingwindowMax;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * ----------------------------------------------------------------------------------------------------
 * 🔗 LeetCode Problem:
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * 📂 Category: Medium
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧠 Problem Statement:
 *
 * Given:
 * - A string `s`
 * - An integer `k`
 *
 * Task:
 * Return the **maximum number of vowel letters** in any **substring of length `k`**.
 *
 * Vowel letters: `'a'`, `'e'`, `'i'`, `'o'`, `'u'`
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧪 Examples:
 *
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: Substring "iii" contains 3 vowels.
 *
 * Example 2:
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: Substrings "lee", "eet", and "ode" contain 2 vowels.
 *
 * ----------------------------------------------------------------------------------------------------
 * 🔒 Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of only lowercase English letters
 * - 1 <= k <= s.length
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧩 Related Problems:
 *
 * | Problem                                                             | Difficulty | Link                                                                 |
 * |---------------------------------------------------------------------|------------|----------------------------------------------------------------------|
 * | Maximum White Tiles Covered by a Carpet                            | Medium     | https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/ |
 * | Minimum Recolors to Get K Consecutive Black Blocks                 | Easy       | https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/ |
 * | Length of the Longest Alphabetical Continuous Substring            | Medium     | https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/ |
 *
 * ----------------------------------------------------------------------------------------------------
 */
public class B_C_MaximumNumberofVowelsinaSubstringofGivenLength {
    /*
        ✅ Time Complexity: O(n)
        Where n is the length of the input string `s`.

        - We traverse the string once using a sliding window.
        - Both left and right ends of the window move from 0 to n-1.
        - Each character is processed at most twice (once when entering the window, once when leaving).

        👉 Therefore:
        Final Time Complexity = O(n)

        ✅ Space Complexity: O(1)
        - The set of vowels is a fixed-size container (`Set.of('a', 'e', 'i', 'o', 'u')`) with just 5 elements → constant space.
        - Other variables like `count`, `max`, and loop indices use constant space.

        👉 Therefore:
        Final Space Complexity = O(1)

        🧪 Example Dry Run:
        Input:
        s = "abciiidef", k = 3

        Index   char    count (vowels in window)    max
        -----------------------------------------------
        0       a       1                           1
        1       b       1                           1
        2       c       1                           1
        3       i       2                           2  (window: "bci")
        4       i       3                           3  (window: "cii")
        5       i       3                           3  (window: "iii")
        6       d       2                           3  (window: "iid")
        7       e       2                           3  (window: "ide")
        8       f       1                           3  (window: "def")

        ✅ Output: 3
        Explanation: The substring "iii" contains 3 vowels, which is the max in any substring of length 3.
    */
    public int maxVowels(String s, int k) {
        int left = 0, right = 0, len = s.length();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        int result = 0, currentVowelCount = 0;

        while (right < len) {
            if (vowels.contains(s.charAt(right))) {
                currentVowelCount++;
            }

            if (right - left + 1 > k) {
                if (vowels.contains(s.charAt(left))) {
                    currentVowelCount--;
                }
                left++;
            }

            result = Math.max(result, currentVowelCount);
            if (result == k) break; // optional optimization

            right++;
        }

        return result;
    }
    /*
    Category Tricky: Derived Question
    Sure! Here's your modified version of the maxVowels method to return the maximum number of unique vowels in a substring of length k,
     following your preferred sliding window code pattern exactly:

     📊 Time & Space Complexity:
    ✅ Time Complexity: O(n)
    - We iterate through the string once → O(n)
    - Map operations are O(1) due to limited vowel set (max 5 elements)

    ✅ Final Time Complexity: O(n)

    ✅ Space Complexity: O(1)
    - The map stores at most 5 characters (vowels), so it's constant space

    ✅ Final Space Complexity: O(1)

    🧪 Example Dry Run:
    Input: s = "abciiidef", k = 3

    right | Window | Vowel Map         | Unique Count | Result
    ------|--------|-------------------|---------------|--------
    0     | a      | {a:1}             | 1             | 1
    1     | ab     | {a:1}             | 1             | 1
    2     | abc    | {a:1}             | 1             | 1
    3     | bci    | {i:1}             | 1             | 1
    4     | cii    | {i:2}             | 1             | 1
    5     | iii    | {i:3}             | 1             | 1
    6     | iid    | {i:2}             | 1             | 1
    7     | ide    | {i:1, e:1}        | 2             | 2 ← max
    8     | def    | {e:1}             | 1             | 2

    ✅ Output: 2
*/


    private static class DerivedMaxUniqueVowels {
        public int maxUniqueVowels(String s, int k) {
            int left = 0, right = 0, len = s.length();
            Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
            Map<Character, Integer> vowelFreq = new HashMap<>();

            int result = 0;

            while (right < len) {
                char currentChar = s.charAt(right);
                if (vowels.contains(currentChar)) {
                    vowelFreq.put(currentChar, vowelFreq.getOrDefault(currentChar, 0) + 1);
                }

                if (right - left + 1 > k) {
                    char leftChar = s.charAt(left);
                    if (vowels.contains(leftChar)) {
                        vowelFreq.put(leftChar, vowelFreq.get(leftChar) - 1);
                        if (vowelFreq.get(leftChar) == 0) {
                            vowelFreq.remove(leftChar);
                        }
                    }
                    left++;
                }

                result = Math.max(result, vowelFreq.size());
                if (result == 5) break; // early exit optimization

                right++;
            }

            return result;
        }
    }
}
