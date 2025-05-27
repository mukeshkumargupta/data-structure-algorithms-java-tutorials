package com.interview.recursionBacktracking;
import java.util.*;

/*
 * https://leetcode.com/problems/longest-nice-substring/
 * https://www.youtube.com/watch?v=20mjBSByOaQ
 * Category: Easy, Tricky,
 * Related: 
 * https://leetcode.com/problems/number-of-good-paths/ Hard
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

 

Example 1:

Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.

Example 2:

Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.

Example 3:

Input: s = "c"
Output: ""
Explanation: There are no nice substrings.

Example 4:

Input: s = "dDzeE"
Output: "dD"
Explanation: Both "dD" and "eE" are the longest nice substrings.
As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 */
public class LongestNiceSubstring {
    /*
    ✅ Brute Force
🔧 Idea:
Check all possible substrings of s, and for each one, verify if it is "nice".
⏱️ Time Complexity:
O(n^3) — Generating all substrings: O(n^2), Checking "nice": O(n)

💾 Space Complexity:
O(n) for the Set
     */
    private static class BruitForce {
        public String longestNiceSubstring(String s) {
            int maxLen = 0;
            String res = "";

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j <= s.length(); j++) {
                    String sub = s.substring(i, j);
                    if (isNice(sub) && sub.length() > maxLen) {
                        maxLen = sub.length();
                        res = sub;
                    }
                }
            }
            return res;
        }

        private boolean isNice(String str) {
            Set<Character> set = new HashSet<>();
            for (char c : str.toCharArray()) set.add(c);

            for (char c : str.toCharArray()) {
                if (!set.contains(Character.toLowerCase(c)) || !set.contains(Character.toUpperCase(c)))
                    return false;
            }
            return true;
        }
    }

    /*
     * ⚙️ Better Approach: Pre-check Substring Ranges
     * --------------------------------------------------
     * - While the time complexity won’t improve drastically,
     *   you could reduce constant factors using frequency arrays
     *   and avoid costly set lookups.
     * - However, it's still inefficient for long strings.
     * - So, we directly move to the optimal approach.
     *
     * 🚀 Optimal Approach: Divide and Conquer
     * --------------------------------------------------
     * 🔧 Idea:
     * - If a character violates the "nice" property (i.e., either uppercase
     *   or lowercase version is missing), it cannot be part of any nice substring.
     * - Split the string at such characters and recursively solve left and right parts.
     *
     * 🧠 Dry Run Example:
     * Input: "YazaAay"
     * - 'z' violates the nice condition since 'Z' is missing.
     * - Split: "Ya" and "aAay"
     * - "aA" is valid and longest → return "aAa"
     *
     * ⏱️ Time Complexity:
     * - Worst Case: O(2^n) due to recursive splitting (rarely happens in practice)
     * - Practical: Much faster due to pruning bad branches early
     *
     * 💾 Space Complexity:
     * - O(n) recursion stack depth
     * - O(n) set usage for checking nice property
     *
     * 🏁 Summary Table:
     * --------------------------------------------------
     * | Approach | Time    | Space | Notes                        |
     * |----------|---------|--------|------------------------------|
     * | Brute    | O(n^3)  | O(n)  | Simple but slow              |
     * | Better   | ~O(n^2) | O(n)  | Slightly optimized brute     |
     * | Optimal  | ~O(n^2) | O(n)  | Best with pruning (D&C)      |
     */

    private static class Optimal {
        public String longestNiceSubstring(String s) {
            if (s.length() < 2) return "";

            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) set.add(c);

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (set.contains(Character.toLowerCase(ch)) && set.contains(Character.toUpperCase(ch)))
                    continue;

                String left = longestNiceSubstring(s.substring(0, i));
                String right = longestNiceSubstring(s.substring(i + 1));

                return left.length() >= right.length() ? left : right;
            }
            return s;
        }
    }
    
}
