package com.interview.string;

/*
 * Problem: https://leetcode.com/problems/repeated-substring-pattern/
 * Video Explanation: https://www.youtube.com/watch?v=p92_kEjyJAo&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=30
 *
 * Alternative Approach:
 * - Use a map to store indices of previously seen elements.
 * - If a repeating element is found, compute the difference between indices
 *   and compare it to identify a repeating pattern.
 *
 * Category: Easy, Google, Tricky
 *
 * Related Problems:
 * - https://leetcode.com/problems/repeated-string-match/ (Medium)
 *
 * Given a string `s`, check if it can be constructed by taking a substring of it
 * and appending multiple copies of the substring together.
 *
 * Example 1:
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 *
 * Example 2:
 * Input: s = "aba"
 * Output: false
 *
 * Example 3:
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 *
 * Constraints:
 * - 1 <= s.length <= 10^4
 * - s consists of lowercase English letters.
 */
public class RepeatedSubstringPattern {

    /*
     * Optimized Approach (String Manipulation)
     *
     * Idea:
     * Instead of generating substrings, use the double-string trick:
     * 1. Create a new string: s + s, remove first and last character.
     * 2. If s exists inside this modified string, then s is a repetition of some substring.
     *
     * Time Complexity: O(N) (since contains() in Java takes linear time on average)
     *
     * Why This Works?
     * - If s is made of a repeated substring, then removing the first and last character of s + s ensures that s appears somewhere inside.
     * - If s is not repeated, contains(s) will return false.
     */
    private static class Optimal {
        public boolean repeatedSubstringPattern(String s) {
            String doubled = s + s;
            return doubled.substring(1, doubled.length() - 1).contains(s);
        }

        public static void main(String[] args) {
            Optimal solution = new Optimal();
            System.out.println(solution.repeatedSubstringPattern("abab")); // true
            System.out.println(solution.repeatedSubstringPattern("aba"));  // false
            System.out.println(solution.repeatedSubstringPattern("abcabcabcabc")); // true
        }
    }
    
}
