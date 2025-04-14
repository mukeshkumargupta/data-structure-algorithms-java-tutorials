package com.interview.math;

/*

https://leetcode.com/problems/find-three-consecutive-integers-that-sum-to-a-given-number/description/
Category: Medium, Tricky
Note: this can be generalized like for 4, 5 so on it can given just you need to extend it
Given an integer num, return three consecutive integers (as a sorted array) that sum to num. If num cannot be expressed as the sum of three consecutive integers, return an empty array.



Example 1:

Input: num = 33
Output: [10,11,12]
Explanation: 33 can be expressed as 10 + 11 + 12 = 33.
10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12].
Example 2:

Input: num = 4
Output: []
Explanation: There is no way to express 4 as the sum of 3 consecutive integers.

 */
public class FindThreeConsecutiveIntegersThatSumtoaGivenNumber {
    /*
    🔹 Approach 1: Brute Force (Try All Possible Consecutive Triples)
🚀 Idea

    Try every possible set of three consecutive numbers.

    Check if their sum is equal to num.

    This is inefficient for large values.

    ⏳ Complexity Analysis

    Time Complexity: O(N), since we iterate through all possible triples.

    Space Complexity: O(1), since we use only a few variables.
     */
    private static class BruitForce{
        public long[] sumOfThree(long num) {
            for (long x = -1_000_000_000; x <= num; x++) {
                if (x + (x + 1) + (x + 2) == num) {
                    return new long[]{x, x + 1, x + 2};
                }
            }
            return new long[]{}; // No solution
        }
    }

    /*
        🔹 Approach 2: Better Using Formula (Math)

        🚀 Idea
        - Suppose three consecutive integers are `x, x+1, x+2`.
        - Their sum can be written as:

            x + (x + 1) + (x + 2) = 3x + 3 = num

        - Rearranging the equation:

            x = (num - 3) / 3

        - If `(num - 3) % 3 == 0`, then `x` is an integer, and we return `{x, x+1, x+2}`.

        ⏳ Complexity Analysis
        - Time Complexity: O(1), since we use a direct formula.
        - Space Complexity: O(1), since we only store three numbers.
    */
    private static class Better {//Prefer this approach
        public long[] sumOfThree(long num) {
            if (num % 3 != 0) return new long[]{}; // No valid solution

            long x = num / 3 - 1;
            return new long[]{x, x + 1, x + 2};
        }
    }

    /*
        🔹 Approach 3: Optimized (Cleaner Formula)

        🚀 Idea
        - Instead of using `num / 3 - 1`, directly calculate `num / 3`.
        - If `num % 3 == 0`, return `{x-1, x, x+1}`.

        ⏳ Complexity Analysis
        - Time Complexity: O(1), since we use a direct formula.
        - Space Complexity: O(1), since we only store three numbers.

        🔹 Summary

        | Approach        | Time Complexity | Space Complexity | Key Idea                         |
        |----------------|----------------|------------------|----------------------------------|
        | Brute Force    | O(N)           | O(1)             | Try all possible x, x+1, x+2    |
        | Better (Math)  | O(1)           | O(1)             | Use (num - 3) / 3 formula       |
        | Optimized      | O(1)           | O(1)             | Use num / 3 directly            |
    */
    private static class Optimized {
        public long[] sumOfThree(long num) {
            if (num % 3 != 0) return new long[]{}; // No solution

            long x = num / 3;
            return new long[]{x - 1, x, x + 1};
        }
    }
}
