package com.interview.bits;
/*
 * Reference: https://leetcode.com/problems/missing-number/
 * Category: Easy
 *
 * Problem Statement:
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 *
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation:
 * n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
 * 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation:
 * n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
 * 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation:
 * n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
 * 8 is the missing number in the range since it does not appear in nums.
 */

import java.util.Arrays;
import java.util.HashSet;

public class MissingNumbers {
/*
    1. Brute Force Approach (Using Sorting)

    Idea:
    - Sort the array.
    - Iterate from 0 to n and check if the number at index i is equal to i.
    - If a mismatch is found, return i as the missing number.

    Complexity Analysis:
    - Time Complexity: O(N log N) (due to sorting)
    - Space Complexity: O(1) (no extra space used)
*/

    private static class BruitForce {
        public int missingNumber(int[] nums) {
            Arrays.sort(nums); // O(N log N)

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i) {
                    return i;
                }
            }

            return nums.length; // If no missing number found in loop, return last one
        }
    }

/*
    2. Better Approach (Using Hash Set)

    Idea:
    - Store all numbers in a HashSet for quick lookup.
    - Iterate from 0 to n and check which number is missing.

    Complexity Analysis:
    - Time Complexity: O(N) (single pass to insert + single pass to check)
    - Space Complexity: O(N) (due to HashSet storage)
*/

    private static class Better {
        public int missingNumber(int[] nums) {
            HashSet<Integer> set = new HashSet<>();

            for (int num : nums) {
                set.add(num);
            }

            for (int i = 0; i <= nums.length; i++) {
                if (!set.contains(i)) {
                    return i;
                }
            }

            return -1;
        }
    }

    /*
        3. Optimal Approach (Using Sum Formula)

        Idea:
        - Compute the expected sum using the formula:
          S_n = n(n + 1) / 2
        - Compute the actual sum from the given array.
        - The missing number is calculated as:
          Missing = S_n - sum(nums)

        Complexity Analysis:
        - Time Complexity: O(N) (single pass)
        - Space Complexity: O(1) (no extra space used)
    */
    private static class OptimalButCanBeOverflow {
        public int missingNumber(int[] nums) {
            int n = nums.length;
            int expectedSum = (n * (n + 1)) / 2;
            int actualSum = 0;

            for (int num : nums) {
                actualSum += num;
            }

            return expectedSum - actualSum;
        }
    }

    /*
        4. Most Optimal Approach (Using XOR)

        Idea:
        - XOR all numbers from 0 to n.
        - XOR all numbers in the array.
        - The missing number will be the result of:

          Missing = (0 ⊕ 1 ⊕ 2 ⊕ ... ⊕ n) ⊕ (nums[0] ⊕ nums[1] ⊕ ... ⊕ nums[n-1])

        - Since a ⊕ a = 0, all existing numbers cancel out, leaving only the missing number.

        Complexity Analysis:
        - Time Complexity: O(N) (single pass)
        - Space Complexity: O(1) (no extra space used)

        Comparison of Approaches:
        -------------------------------------------------
        | Approach       | Time Complexity | Space Complexity | Notes                      |
        |---------------|----------------|----------------|----------------------------|
        | Brute Force (Sorting) | O(N log N)  | O(1)         | Sorts then searches, slower |
        | Hash Set       | O(N)          | O(N)          | Uses extra space            |
        | Sum Formula    | O(N)          | O(1)          | Simple and fast             |
        | XOR Trick      | O(N)          | O(1)          | Best approach, avoids overflow |
        -------------------------------------------------
    */
    private static class MostOptimal {
        public int missingNumber(int[] nums) {
            int xor = 0, n = nums.length;

            for (int i = 0; i <= n; i++) {
                xor ^= i;
            }

            for (int num : nums) {
                xor ^= num;
            }

            return xor;
        }
    }

}
