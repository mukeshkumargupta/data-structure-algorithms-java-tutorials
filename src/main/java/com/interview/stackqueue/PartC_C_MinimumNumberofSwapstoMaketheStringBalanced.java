package com.interview.stackqueue;

import java.util.Stack;

/*
https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/submissions/
Category: Medium, Tricky
https://www.youtube.com/watch?v=jKu4LbGV4lU
Related: https://leetcode.com/problems/remove-invalid-parentheses/ Hard
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/ Medium
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/ Medium
https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/ Medium
You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.

A string is called balanced if and only if:

It is the empty string, or
It can be written as AB, where both A and B are balanced strings, or
It can be written as [C], where C is a balanced string.
You may swap the brackets at any two indices any number of times.

Return the minimum number of swaps to make s balanced.



Example 1:

Input: s = "][]["
Output: 1
Explanation: You can make the string balanced by swapping index 0 with index 3.
The resulting string is "[[]]".
Example 2:

Input: s = "]]][[["
Output: 2
Explanation: You can do the following to make the string balanced:
- Swap index 0 with index 4. s = "[]][][".
- Swap index 1 with index 5. s = "[[][]]".
The resulting string is "[[][]]".
Example 3:

Input: s = "[]"
Output: 0
Explanation: The string is already balanced.


Constraints:

n == s.length
2 <= n <= 106
n is even.
s[i] is either '[' or ']'.
The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 */
public class PartC_C_MinimumNumberofSwapstoMaketheStringBalanced {
    /*
    1️⃣ Brute Force Approach (Using Stack)
    We use a stack to track unmatched brackets.
    Whenever we encounter an unmatched ], we count the imbalance.
    The number of swaps required is imbalance / 2.
    TC: O(N) SC : O(N)
     */
    private static class BruitForce {
        public static int minSwaps(String s) {
            Stack<Character> stack = new Stack<>();
            int imbalance = 0;

            for (char ch : s.toCharArray()) {
                if (ch == '[') {
                    stack.push(ch);
                } else {
                    if (!stack.isEmpty()) {
                        stack.pop(); // Balance the bracket
                    } else {
                        imbalance++; // Unmatched ']'
                    }
                }
            }
            return (imbalance + 1) / 2; // Minimum swaps needed
        }

        public static void main(String[] args) {
            String s = "]]][[[";
            System.out.println(minSwaps(s)); // Output: 2
        }
    }

    /*
    2️⃣ Optimized Approach (Using Balance Count)
    We track balance instead of using a stack.
    If ] reduces balance below zero, we keep track of the minimum balance.
    The result is half of the absolute minimum balance.
    TC: O(N) SC: O(1)
     */
    private static class Optimised {
        public static int minSwaps(String s) {
            int balance = 0, minBalance = 0;

            for (char ch : s.toCharArray()) {
                if (ch == '[') {
                    balance++;
                } else {
                    balance--;
                }
                minBalance = Math.min(minBalance, balance);
            }

            return (-minBalance + 1) / 2; // Minimum swaps required
        }

        public static void main(String[] args) {
            String s = "]]][[[";
            System.out.println(minSwaps(s)); // Output: 2
        }
    }
}
