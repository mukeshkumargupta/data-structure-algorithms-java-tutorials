package com.interview.mathematical;

import java.math.BigInteger;

/*
 * Problem: Factorial Trailing Zeroes
 * Link: https://leetcode.com/problems/factorial-trailing-zeroes/
 * Video Explanation: https://www.youtube.com/watch?v=fx8rUY_iIms
 * Category: Medium, Top150
 *
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 * Examples:
 *
 * Example 1:
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 *
 * Example 3:
 * Input: n = 0
 * Output: 0
 *
 * Constraints:
 * 0 <= n <= 10^4
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 */
public class FactorialTrailingZeroes {

    /*
     * Problem Understanding
     *
     * The number of trailing zeros in n! (n factorial) is determined by the number of times 10 is a factor in the product of all integers from 1 to n.
     * Since 10 = 2 × 5, and there are usually more factors of 2 than 5, the count of trailing zeros is determined by the number of times 5 is a factor in the numbers up to n.
     *
     * Brute Force Approach
     *
     * Brute Force Explanation:
     *
     * Calculate n! directly.
     * Count the number of trailing zeros by repeatedly dividing the factorial result by 10 until it's no longer divisible.
     *
     * Drawbacks:
     *
     * This approach is inefficient for large n due to the rapid growth of factorial values, leading to potential overflow and excessive computation time.
     *
     * Time Complexity: O(n) to calculate n!
     * Space Complexity: O(1) if we use integer calculations without storing the entire factorial.
     */
    public static class FactorialTrailingZeroesBruiteForce {
        public static int trailingZeroesBruteForce(int n) {
            // Calculate n!
            BigInteger factorial = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            // Count trailing zeros
            int count = 0;
            while (factorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
                count++;
                factorial = factorial.divide(BigInteger.TEN);
            }
            return count;
        }

        public static void main(String[] args) {
            System.out.println(trailingZeroesBruteForce(5)); // Output: 1
        }
    }

    /*
     * Optimal Approach
     *
     * Optimal Explanation:
     *
     * To count trailing zeros without calculating n!, count the number of factors of 5 in all numbers from 1 to n.
     * This includes multiples of 5 (one 5 each), multiples of 25 (an additional 5 each), multiples of 125, and so on.
     *
     * The formula is:
     *
     * count = ⌊n/5⌋ + ⌊n/25⌋ + ⌊n/125⌋ + ...
     * until 5^k > n.
     *
     * Time Complexity: O(log₅(n))
     * Space Complexity: O(1)
     */

    public static class FactorialTrailingZeroesBruiteForceBetter {
        public static int trailingZeroes(int n) {
            int count = 0;
            while (n > 0) {
                n /= 5;
                count += n;
            }
            return count;
        }

        public static void main(String[] args) {
            System.out.println(trailingZeroes(5)); // Output: 1
            System.out.println(trailingZeroes(10)); // Output: 2
        }
    }


    
}
