package com.interview.binarysearch;

/*
 *
 * https://leetcode.com/problems/sqrtx/
 * Very good explanation is given
 * https://www.youtube.com/watch?v=fItuKa_tIpY
 * Reference Video: https://www.youtube.com/watch?v=H7WymG3gab0
 * This is same as getNthRoot here it is asked square root
 * Category: Easy, Top150, Must Do
 * Related:
 * https://leetcode.com/problems/valid-perfect-square/ Easy
 * Tricky
 */
//Working code for https://leetcode.com/problems/sqrtx/ but difivult to understand
public class SquareRootOfNumber {


    public static class Bruitforce {
        /*
         * Brute Force Solution
         *
         * A straightforward approach is to try each integer from 1 up to x, squaring each integer
         * until the square exceeds x. This is simple but inefficient for large values of x.
         *
         * Time Complexity: O(x) – We iterate up to the square root of x, so in the worst case,
         *                  we may perform x checks.
         *
         * Explanation:
         * - We incrementally check each integer starting from 1, squaring it to see if it is less
         *   than or equal to x.
         * - The first integer whose square exceeds x gives us the floor value of the square root.
         *
         * Division Instead of Multiplication:
         * - To avoid overflow, instead of checking `result * result <= x`, we use `result <= x / result`.
         * - This method remains within the `int` range, even for large values of x.
         *
         * Example for Large Values:
         * - For large inputs like x = 2147395600, this approach correctly avoids overflow by comparing
         *   within the `int` range, yielding the correct result of 46340.
         */
        public int mySqrt(int x) {
            if (x == 0 || x == 1) return x; // handle edge cases

            int result = 1;
            while (result <= x / result) { // avoid overflow by using division
                result++;
            }
            return result - 1; // subtract 1 as we go one step beyond the result
        }
    }

    /*
     * Binary Search Solution (Optimized)
     *
     * We can optimize the square root calculation using binary search. Since the square root of x will lie
     * between 1 and x, we can treat this range as our search space. By calculating the midpoint of this range,
     * squaring it, and comparing it with x, we can decide whether to continue searching in the lower or upper half.
     *
     * Time Complexity: O(log x) – Binary search reduces the search range by half with each iteration,
     * resulting in a logarithmic time complexity.
     *
     * Explanation:
     * - Initialize the search range with `left = 1` and `right = x`.
     * - Calculate the midpoint, `mid`, and check if `mid * mid` is less than, equal to, or greater than x.
     * - If `mid * mid` is equal to x, then mid is the square root of x.
     * - If `mid * mid` is less than x, move the search to the right half (`left = mid + 1`).
     * - If `mid * mid` is greater than x, move the search to the left half (`right = mid - 1`).
     * - Continue narrowing down the range until `left` exceeds `right`, and return the largest integer `mid`
     *   that satisfies `mid * mid <= x`.
     */

    public static class BetterApproach {
        public static int mySqrt(int x) {
            if (x == 0 || x == 1) return x; // edge cases

            int left = 1, right = x, result = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (mid <= x / mid) { // equivalent to mid * mid <= x, but prevents overflow
                    result = mid; // mid is a candidate
                    left = mid + 1; // search right half
                } else {
                    right = mid - 1; // search left half
                }
            }
            return result;
        }

        public static void main(String[] args) {
            int number = 9;
            //int number = 1;
            System.out.println("Square root of " + number + " = " + mySqrt(number));
            System.out.println("Square root of " + number + " = " + mySqrt(number));
            //System.out.println("Square root of " + number + " = " + mySqrt_v1(number));
        }
    }

    /*
     * Newton's Method (Most Optimized Approach)
     *
     * Newton's method is an iterative approach to approximate the square root using a mathematical formula.
     * It starts with an initial guess and refines this guess until it converges to an integer value close to the square root of x.
     *
     * Time Complexity: O(log x) – Although it has a logarithmic complexity, it often converges faster in practice than binary search.
     *
     * Explanation:
     * - Start with x as an initial approximation.
     * - Repeatedly refine this approximation by taking the average of `approx` and `x / approx`.
     * - This process continues until approx squared is less than or equal to x.
     *
     * Summary of Time Complexities:
     * - Brute Force: O(x) – Linear complexity, not efficient for large x.
     * - Binary Search: O(log x) – Faster, divides the search space in half each step.
     * - Newton’s Method: O(log x) – Even faster convergence in practice.
     */
    public static class OptimizedAPproach {
        public int mySqrt(int x) {
            if (x == 0) return 0;

            long approx = x;
            while (approx * approx > x) {
                approx = (approx + x / approx) / 2;
            }
            return (int) approx;
        }
    }
    

    
}


