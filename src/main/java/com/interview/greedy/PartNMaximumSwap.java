package com.interview.greedy;

/*
Category: Medium, Facebook, Tricky
https://leetcode.com/problems/maximum-swap/description/?envType=problem-list-v2&envId=7p59281&utm_source=chatgpt.com
https://www.youtube.com/watch?v=YDzytGV2Z5M
https://leetcode.com/problems/create-maximum-number/ Hard
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.


Constraints:

0 <= num <= 108
 */
public class PartNMaximumSwap {
    private static class Optimized {
        public int maximumSwap(int num) {
            // If the number is 10 or less, no swap is possible.
            if (num <= 10) return num;

            // Convert the number to a string to manipulate digits.
            char[] digits = Integer.toString(num).toCharArray();
            int n = digits.length;

            // Initialize pointers for the maximum value's index, and swap positions.
            int maxValIdx = n - 1;
            int swapLeft = -1;
            int swapRight = n - 1;

            // Traverse from right to left to find the optimal swap.
            for (int i = n - 2; i >= 0; --i) {
                if (digits[maxValIdx] < digits[i]) {
                    // Update maxValIdx if a larger digit is found on the left.
                    maxValIdx = i;
                } else if (digits[maxValIdx] > digits[i]) {
                    // Record the positions to swap if a better digit is found.
                    swapRight = maxValIdx;
                    swapLeft = i;
                }
            }

            // If a valid swap is found, swap the digits.
            if (swapLeft != -1) {
                char temp = digits[swapLeft];
                digits[swapLeft] = digits[swapRight];
                digits[swapRight] = temp;
            }

            // Convert the modified character array back to an integer and return.
            return Integer.parseInt(new String(digits));
        }
    }

}
