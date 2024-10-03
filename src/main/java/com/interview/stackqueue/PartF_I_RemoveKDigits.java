package com.interview.stackqueue;
import java.util.*;
/*
 * https://leetcode.com/problems/remove-k-digits/
 * Category: Medium, Tricky, VVImp, Monotonic stack, Greedy
 * Related:
 * https://leetcode.com/problems/create-maximum-number/description/ Hard
 * https://leetcode.com/problems/monotone-increasing-digits/description/ Medium
 * https://leetcode.com/problems/find-the-most-competitive-subsequence/description/ Medium
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
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
