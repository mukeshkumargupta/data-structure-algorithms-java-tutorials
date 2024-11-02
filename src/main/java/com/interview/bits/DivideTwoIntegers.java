package com.interview.bits;

/*
 * Problem: Divide Two Integers
 * Link: https://leetcode.com/problems/divide-two-integers/
 * Category: Medium, Top150
 * Related Problems:
 * - Minimum Number of K Consecutive Bit Flips: https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/ (Hard, Very Important)
 * - Robot Bounded In Circle: https://leetcode.com/problems/robot-bounded-in-circle/ (Medium, Very Important)
 * - Find XOR Sum of All Pairs Bitwise AND: https://leetcode.com/problems/find-xor-sum-of-all-pairs-bitwise-and/ (Hard, Important)
 *
 * Description:
 * Given two integers, `dividend` and `divisor`, divide them without using multiplication, division, or mod operators.
 * The division result should be truncated toward zero (e.g., 8.345 → 8, -2.7335 → -2).
 *
 * Constraints:
 * - If the quotient is greater than 2^31 - 1, return 2^31 - 1.
 * - If the quotient is less than -2^31, return -2^31.
 * - Both `dividend` and `divisor` are within the 32-bit signed integer range: [−2^31, 2^31 − 1].
 * - `divisor` will never be zero.
 *
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10 / 3 = 3.33333.. truncated to 3.
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7 / -3 = -2.33333.. truncated to -2.
 *
 */
public class DivideTwoIntegers {

    /*
     * Approach Overview for LeetCode Problem: "Divide Two Integers"
     *
     * In this problem, we explore three levels of solutions:
     * brute force, improved, and optimized. Each approach has different
     * time complexities and uses various techniques to perform division
     * without multiplication, division, or modulus operations.
     *
     * Problem Description:
     * Given two integers, dividend and divisor, perform integer division
     * without using multiplication, division, or modulus. The result must
     * truncate towards zero and fit within the 32-bit signed integer range
     * [−2^31, 2^31 − 1].
     *
     * Brute Force Approach:
     *
     * Explanation:
     * The brute force method increments a counter by repeatedly subtracting
     * the divisor from the dividend until the dividend becomes less than the
     * divisor. This process counts how many times the divisor can be subtracted,
     * which gives the resulting quotient.
     *
     * Time Complexity:
     * O(|dividend|): In the worst case, this approach may take
     * O(|dividend| / |divisor|) steps, making it inefficient for large values.
     */
    public static class Bruitforce {
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE; // Handle overflow case
            }

            int quotient = 0;
            int sign = (dividend < 0) == (divisor < 0) ? 1 : -1; // Determine if result is positive or negative

            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);

            while (dividend - divisor >= 0) {
                dividend -= divisor;
                quotient++;
            }

            return sign * quotient;
        }
    }

    /*
     * Improved Approach with Exponential Increment
     *
     * Explanation:
     * This approach improves on the brute force method by using exponential increments.
     * Instead of repeatedly subtracting the divisor one unit at a time, we double the divisor
     * (exponentially increasing it) to quickly approach the dividend with larger multiples of the divisor.
     * When the doubled divisor exceeds the remaining dividend, we reset to smaller increments
     * and continue the process until we reach the closest quotient.
     *
     * Time Complexity:
     * O(log(dividend)) - This approach requires fewer steps, as it reduces the difference exponentially
     * with each increment, making it much faster than linear subtraction.
     */
    public static class BetterApproach {
        public int divideExponential(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE; // Handle overflow case
            }

            int sign = (dividend < 0) == (divisor < 0) ? 1 : -1;
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);

            int quotient = 0;
            while (dividend - divisor >= 0) {
                int tempDivisor = divisor, multiple = 1;
                while (dividend - (tempDivisor << 1) >= 0) {
                    tempDivisor <<= 1;
                    multiple <<= 1;
                }
                dividend -= tempDivisor;
                quotient += multiple;
            }

            return sign * quotient;
        }
    }

    /*
     * Optimized Approach with Bit Manipulation (Binary Search-like)
     *
     * Explanation:
     * This approach uses bit manipulation to perform division efficiently by
     * repeatedly doubling (left-shifting) the divisor. Each left shift approximates
     * multiplying the divisor by powers of 2, allowing a binary search-like narrowing
     * down. By adding these powers of two, we obtain the final quotient.
     *
     * Steps:
     * 1. Shift the divisor left until it’s larger than the dividend.
     * 2. Reverse the process, subtracting the divisor at each step and accumulating the result.
     *
     * Time Complexity:
     * O(log(dividend)) - Only a few shifts and subtractions are needed, making it very efficient.
     *
     * Summary of Approaches:
     * | Approach              | Explanation                                  | Time Complexity        |
     * |-----------------------|----------------------------------------------|------------------------|
     * | Brute Force           | Repeated subtraction                         | O(dividend)            |
     * | Exponential Increment | Double divisor and subtract                  | O(log(dividend))       |
     * | Optimized (Bit Shift) | Binary search-like approach using bit shifts | O(log(dividend))       |
     *
     * The optimized approach with bit manipulation is the most efficient, as it
     * significantly reduces the number of steps needed by leveraging exponential
     * growth with left shifts.
     */
    public static class OptimizedApproach {
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE; // Handle overflow case
            }

            int sign = (dividend < 0) == (divisor < 0) ? 1 : -1;
            long absDividend = Math.abs((long) dividend);
            long absDivisor = Math.abs((long) divisor);

            int quotient = 0;
            while (absDividend >= absDivisor) {
                long tempDivisor = absDivisor, multiple = 1;
                while (absDividend >= (tempDivisor << 1)) {
                    tempDivisor <<= 1;
                    multiple <<= 1;
                }
                absDividend -= tempDivisor;
                quotient += multiple;
            }

            return sign * quotient;
        }
    }

    
}
