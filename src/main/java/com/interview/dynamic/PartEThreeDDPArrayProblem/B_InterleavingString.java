package com.interview.dynamic.PartEThreeDDPArrayProblem;

import java.util.HashMap;
import java.util.Map;

public class B_InterleavingString {
    /*
https://leetcode.com/problems/interleaving-string/description/?envType=study-plan-v2&envId=top-interview-150
https://www.youtube.com/watch?v=EzQ_YEmR598
Category: Medium, top150, tricky
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.


Follow up: Could you solve it using only O(s2.length) additional memory space?
*/
/*
    Derived questions:

    The LeetCode problem "Interleaving String" (Problem 97) focuses on determining if a string s3 can be
    formed by interleaving two other strings, s1 and s2. This problem is categorized under Dynamic Programming (DP)
    and shares similarities with several other problems that involve combining or partitioning strings and sequences.

    Here are some related problems that can help deepen your understanding of similar concepts:

    1. Longest Common Subsequence (LeetCode 1143):
       - Involves finding the length of the longest subsequence present in both given sequences.
       - Fundamental for understanding sequence alignment.

    2. Edit Distance (LeetCode 72):
       - Also known as Levenshtein distance.
       - Requires computing the minimum number of operations to convert one string into another.
       - Classic string transformation problem.

    3. Distinct Subsequences (LeetCode 115):
       - Asks for the number of distinct subsequences of s1 which equal s2.
       - Focuses on pattern counting within sequences.

    4. Scramble String (LeetCode 87):
       - Checks whether one string is a scrambled version of another.
       - Involves recursive partitioning and rearrangement.

    5. Minimum Deletions to Make String Balanced (LeetCode 1653):
       - Finds the minimum deletions required to make a string balanced (characters 'a' and 'b').

    6. Flatten Nested List Iterator (LeetCode 341):
       - Involves traversing nested structures.
       - Requires careful handling of multiple levels of data.

    7. Repeated Substring Pattern (LeetCode 459):
       - Checks if a string can be constructed by repeating a substring.
       - Related to pattern detection in strings.
 */
    public class InterleavingString {
        /*
        ✅ Time and Space Complexity
    ⏱️ Time Complexity: O(m * n)
    m = s1.length(), n = s2.length()

    Each state (p1, p2) is visited once.

    p3 is always p1 + p2, so doesn't add complexity.

    🧠 Space Complexity: O(m * n)
    Due to memoization (memo stores at most m * n keys).

    Recursion stack space is at most O(m + n) in the worst case (fully recursive depth).
         */
        private Map<String, Boolean> memo = new HashMap<>();

        private boolean check(String s1, String s2, String s3, int p1, int p2, int p3) {
            // If we have reached the end of s3, check if we have also finished s1 and s2
            if (p3 == s3.length()) {
                return p1 == s1.length() && p2 == s2.length();
            }

            String key = p1 + "*" + p2 + "*" + p3;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            // If s1 is exhausted, we can only try matching s2 with s3
            if (p1 == s1.length()) {
                boolean match = s2.charAt(p2) == s3.charAt(p3) && check(s1, s2, s3, p1, p2 + 1, p3 + 1);
                memo.put(key, match);
                return match;
            }

            // If s2 is exhausted, we can only try matching s1 with s3
            if (p2 == s2.length()) {
                boolean match = s1.charAt(p1) == s3.charAt(p3) && check(s1, s2, s3, p1 + 1, p2, p3 + 1);
                memo.put(key, match);
                return match;
            }

            // Try matching both s1 and s2 with s3
            boolean matchS1 = false, matchS2 = false;
            if (s1.charAt(p1) == s3.charAt(p3)) {
                matchS1 = check(s1, s2, s3, p1 + 1, p2, p3 + 1);
            }
            if (s2.charAt(p2) == s3.charAt(p3)) {
                matchS2 = check(s1, s2, s3, p1, p2 + 1, p3 + 1);
            }

            boolean result = matchS1 || matchS2;
            memo.put(key, result);
            return result;
        }

        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            return check(s1, s2, s3, 0, 0, 0);
        }
    }
}
