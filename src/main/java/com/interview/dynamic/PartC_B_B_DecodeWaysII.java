package com.interview.dynamic;

import java.util.Arrays;

/*
Category: Hard, Facebook, FAANG
https://www.youtube.com/watch?v=rGtGWRdTtMM
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s consisting of digits and '*' characters, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.



Example 1:

Input: s = "*"
Output: 9
Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
Hence, there are a total of 9 ways to decode "*".
Example 2:

Input: s = "1*"
Output: 18
Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
Example 3:

Input: s = "2*"
Output: 15
Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".


Constraints:

1 <= s.length <= 105
s[i] is a digit or '*'.
 */
public class PartC_B_B_DecodeWaysII {

    /*
        Explanation of the Code

        Base Cases:
        - If we reach the end of the string, return 1 (valid decoding found).
        - If a segment starts with '0', return 0 (invalid leading zero case).

        Memoization Check:
        - If we have already computed the result for index, return memo[index].

        Single-Digit Decoding:
        - If the character is '*', it can be any digit from 1-9 → ways += 9 * decodeHelper(s, index + 1, memo).
        - Otherwise, if it's a valid digit 1-9, just take decodeHelper(s, index + 1, memo).

        Two-Digit Decoding:
        - If both digits are '*' → They can form 15 combinations (11-19, 21-26).
        - If the first digit is '*' → Consider both 1X and 2X cases.
        - If the second digit is '*' → Consider 1* (11-19) or 2* (21-26).
        - If both digits are numbers, check if they form a valid number (10-26).

        Time & Space Complexity:
        - Time Complexity: O(n) → Each index is computed once.
        - Space Complexity: O(n) → Memoization array.
    */
    private static class Better {//Memoization
        private static final int MOD = 1_000_000_007;

        public int numDecodings(String s) {
            int n = s.length();
            int[] memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return decodeHelper(s, 0, memo);
        }

        private int decodeHelper(String s, int index, int[] memo) {
            // Base cases
            if (index == s.length()) return 1;  // Successfully decoded the entire string
            if (s.charAt(index) == '0') return 0;  // Leading zero is not valid

            // If already computed, return memoized result
            if (memo[index] != -1) return memo[index];

            long ways = 0;

            // Case 1: Single-digit decoding
            if (s.charAt(index) == '*') {
                ways = (ways + 9L * decodeHelper(s, index + 1, memo)) % MOD;  // '*' can be 1-9
            } else {
                ways = (ways + decodeHelper(s, index + 1, memo)) % MOD;  // Regular digit 1-9
            }

            // Case 2: Two-digit decoding
            if (index + 1 < s.length()) {
                char first = s.charAt(index);
                char second = s.charAt(index + 1);

                if (first == '*' && second == '*') {
                    ways = (ways + 15L * decodeHelper(s, index + 2, memo)) % MOD;  // '**' → 11-19, 21-26 (15 cases)
                } else if (first == '*') {
                    if (second >= '0' && second <= '6') {
                        ways = (ways + 2L * decodeHelper(s, index + 2, memo)) % MOD;  // *[0-6] → (10, 20)
                    } else {
                        ways = (ways + decodeHelper(s, index + 2, memo)) % MOD;  // *[7-9] → (17-19)
                    }
                } else if (second == '*') {
                    if (first == '1') {
                        ways = (ways + 9L * decodeHelper(s, index + 2, memo)) % MOD;  // 1* → 11-19
                    } else if (first == '2') {
                        ways = (ways + 6L * decodeHelper(s, index + 2, memo)) % MOD;  // 2* → 21-26
                    }
                } else {
                    int twoDigit = Integer.parseInt(s.substring(index, index + 2));
                    if (twoDigit >= 10 && twoDigit <= 26) {
                        ways = (ways + decodeHelper(s, index + 2, memo)) % MOD;
                    }
                }
            }

            return memo[index] = (int) ways;
        }

        public static void main(String[] args) {
            Better decoder = new Better();
            System.out.println(decoder.numDecodings("*"));       // Output: 9
            System.out.println(decoder.numDecodings("1*"));      // Output: 18
            System.out.println(decoder.numDecodings("2*"));      // Output: 15
            System.out.println(decoder.numDecodings("*1*1*0"));  // Output: ?
        }
    }
}
