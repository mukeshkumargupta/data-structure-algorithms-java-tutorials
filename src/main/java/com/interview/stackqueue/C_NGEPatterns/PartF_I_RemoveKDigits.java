package com.interview.stackqueue.C_NGEPatterns;
import java.util.*;
/*
 * Problem: Remove K Digits (LeetCode 402)
 * Reference: https://leetcode.com/problems/remove-k-digits/
 *
 * Category: Medium, Tricky, Very Important, Monotonic Stack, Greedy
 *
 * Related Problems:
 * - Create Maximum Number (Hard) - https://leetcode.com/problems/create-maximum-number/description/
 * - Monotone Increasing Digits (Medium) - https://leetcode.com/problems/monotone-increasing-digits/description/
 * - Find the Most Competitive Subsequence (Medium) - https://leetcode.com/problems/find-the-most-competitive-subsequence/description/
 * https://leetcode.com/problems/append-k-integers-with-minimal-sum/ Medium
 * https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/ Easy
 * https://leetcode.com/problems/minimum-operations-to-make-a-special-number/ Medium
 *
 * Problem Statement:
 * Given a string `num` representing a non-negative integer, and an integer `k`,
 * return the smallest possible integer after removing `k` digits from `num`.
 *
 * Example Walkthrough:
 * 1. Input: num = "1432219", k = 3
 *    Output: "1219"
 *    Explanation: Remove the digits 4, 3, and 2 to form the smallest number "1219".
 *
 * 2. Input: num = "10200", k = 1
 *    Output: "200"
 *    Explanation: Remove the leading '1' to get "200", ensuring no leading zeros in the result.
 *
 * 3. Input: num = "10", k = 2
 *    Output: "0"
 *    Explanation: After removing all digits, the result is "0".
 *
 * Constraints:
 * - 1 <= k <= num.length <= 10^5
 * - num consists of only digits.
 * - num does not have any leading zeros except for the zero itself.
 *
 * Approach:
 * - We use a **monotonic stack** to maintain the smallest possible number.
 * - Traverse through the digits of `num` and use a stack to ensure that each digit in the stack
 *   is in increasing order.
 * - Remove digits from the stack if the current digit is smaller than the top of the stack (greedy approach).
 * - Continue removing elements from the stack until we've removed `k` digits, and return the result as a string.
 */
public class PartF_I_RemoveKDigits {
    /*
    Explanation:
        Real Stack:

        We now use a Stack<Character> to store the digits while processing the string. This allows us to push and pop digits explicitly, which mimics the stack behavior more directly.
        We use stack.pop() to remove larger digits from the stack when needed.
        Building the Result:

        After processing the digits, we collect the remaining elements from the stack into a StringBuilder, reversing it since the stack pops the elements in reverse order.
        Handling Leading Zeros:

        We strip any leading zeros from the final result, and if the result is empty, we return "0".
        Time Complexity:
        O(n) where n is the length of num. Each digit is pushed and popped from the stack at most once.
        Space Complexity:
        O(n) for the stack that stores the digits.
        This implementation gives you a clear and explicit stack-based approach to solving the "Remove K Digits" problem.
     */

    /*
        Dry Run of removeKdigits for num = "1432219", k = 3

        | Iteration | Digit | Stack (before pushing) | Action                                                  | Stack (after pushing) | Remaining k |
        |-----------|-------|------------------------|---------------------------------------------------------|-----------------------|-------------|
        | 1         | 1     | []                     | Push '1' into the stack.                                | ['1']                 | 3           |
        | 2         | 4     | ['1']                  | Push '4' into the stack.                                | ['1', '4']            | 3           |
        | 3         | 3     | ['1', '4']             | Pop '4' (because '3' < '4'), decrement k.               | ['1']                 | 2           |
        | 4         | 2     | ['1']                  | Pop '3' (because '2' < '3'), decrement k.               | ['1']                 | 1           |
        | 5         | 2     | ['1']                  | Push '2' into the stack.                                | ['1', '2']            | 1           |
        | 6         | 1     | ['1', '2']             | Pop '2' (because '1' < '2'), decrement k.               | ['1']                 | 0           |
        | 7         | 9     | ['1']                  | Push '9' into the stack.                                | ['1', '9']            | 0           |

        Final Stack: ['1', '9']

        Final Result:
        Reverse the stack: ['9', '1']
        Result = "1219"

        Summary:
        Time Complexity: O(n) (each digit is pushed and popped once)
        Space Complexity: O(n) (space used by the stack)
    */
    public static class RemoveKDigits {

        public String removeKdigits(String num, int k) {
            int n = num.length();
            if (k == n) return "0"; // If k is equal to the length of the number, return "0"

            // Stack to store the resulting digits
            Stack<Character> stack = new Stack<>();

            // Traverse each digit in the number
            for (char digit : num.toCharArray()) {
                // Remove the larger elements from the stack if k > 0
                while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                    stack.pop();  // Remove the top element
                    k--;          // We removed one digit, so decrease k
                }
                stack.push(digit);  // Add the current digit to the stack
            }

            // If k is still greater than 0, remove digits from the end of the stack
            while (k > 0) {
                stack.pop();
                k--;
            }

            // Build the final result from the stack
            StringBuilder result = new StringBuilder();
            while (!stack.isEmpty()) {
                result.append(stack.pop());
            }

            // Reverse the result since the stack outputs the digits in reverse order
            result.reverse();

            // Remove leading zeros
            while (result.length() > 0 && result.charAt(0) == '0') {
                result.deleteCharAt(0);
            }

            // If the result is empty after removing leading zeros, return "0"
            return result.length() == 0 ? "0" : result.toString();
        }

        public static void main(String[] args) {
            RemoveKDigits solution = new RemoveKDigits();
            System.out.println(solution.removeKdigits("1432219", 3));  // Output: "1219"
            System.out.println(solution.removeKdigits("10200", 1));    // Output: "200"
            System.out.println(solution.removeKdigits("10", 2));       // Output: "0"
        }
    }
}
