package com.interview.twopointer.twopointersumpattern;

import java.util.Arrays;

/*
 * Date 03/06/2017
 * Author: Mukesh Kumar Gupta
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * Category: Medium, Tricky, Top150, Triplet, LIS, Facebook, FAANG
 *
 * Related Problems:
 * - Longest Increasing Subsequence (Leetcode 300)
 * - Number of Longest Increasing Subsequences (Leetcode 673)
 * - Next Greater Element (Leetcode 496)
 * - Partition Array for Maximum Sum (Leetcode 1043)
 * - Find and Replace Pattern (Leetcode 890)
 * - Count Special Quadruplets (Leetcode 1995)
 *
 * Problem:
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k)
 * such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exist, return false.
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5]
 * Output: true
 *
 * Example 2:
 * Input: nums = [5,4,3,2,1]
 * Output: false
 *
 * Example 3:
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow-up:
 * Can you solve it in O(n) time and O(1) space?
 */
public class G_IncreasingTripletSubsequence {
    /*

        The problem "Increasing Triplet Subsequence" asks you to determine if there exists a subsequence of length three in a given array such that the elements are strictly increasing. Let's break it down step by step:

        Brute Force Approach
        Approach:
        Use three nested loops to consider every possible triplet (nums[i], nums[j], nums[k]) where i < j < k.
        Check if the triplet satisfies the condition: nums[i] < nums[j] < nums[k].
        If such a triplet exists, return true. Otherwise, after all iterations, return false.
        Time Complexity:
        O(n³): Three nested loops.
        Not efficient for large input arrays.
        Space Complexity:
        O(1): No extra space used.
     */

    public boolean increasingTripletBruteForce(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[i] < nums[j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[j] < nums[k]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
    Better Approach (Using DP)
Approach:
Use an array dp where dp[i] represents the length of the longest increasing subsequence ending at index i.
Traverse the array:
For each nums[j], check all previous elements nums[i] where i < j.
Update dp[j] = max(dp[j], dp[i] + 1) if nums[j] > nums[i].
Check if any dp[i] >= 3. If yes, return true.

Time Complexity:
O(n²): Two nested loops (not optimal for large inputs).
Space Complexity:
O(n): For the dp array.

U can use this approach not for triplet and more than triplet
     */

    public boolean increasingTripletDP(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Longest subsequence length is at least 1 for each element.

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            if (dp[j] >= 3) {
                return true;
            }
        }
        return false;
    }
/*
    Optimized Approach (Using Two Variables)

    Approach:
    - Maintain two variables, `first` and `second`, to track the smallest and second smallest elements in the increasing triplet.
    - Traverse the array:
        1. If the current element is smaller than or equal to `first`, update `first`.
        2. Else if the current element is smaller than or equal to `second`, update `second`.
        3. Else, if the current element is greater than `second`, we have found an increasing triplet. Return `true`.
    - If the loop completes without finding a triplet, return `false`.

    Time Complexity:
    - O(n): Single traversal of the array.

    Space Complexity:
    - O(1): Only two variables used.

    Explanation of Optimized Approach:
    - The key observation is that we don’t need the exact subsequence, just a way to track its existence.
    - By keeping track of the smallest (`first`) and second smallest (`second`) values seen so far, we can efficiently
      determine whether a third element exists that forms the triplet.

    Example Walkthrough:

    Input:
    nums = [1, 2, 3, 4, 5]

    Optimized Approach Execution:
    first = Integer.MAX_VALUE, second = Integer.MAX_VALUE.

    Iterate through the array:
    - num = 1: Update first = 1.
    - num = 2: Update second = 2.
    - num = 3: Since 3 > second, return true.

    Output:
    true

    Comparison of Approaches:
    --------------------------------------------------------
    | Approach      | Time Complexity | Space Complexity | Notes                    |
    --------------------------------------------------------
    | Brute Force   | O(n³)           | O(1)             | Extremely slow for large inputs. |
    | Better (DP)   | O(n²)           | O(n)             | Improvement, but still not scalable. |
    | Optimized     | O(n)            | O(1)             | Best solution, handles large inputs well. |
    --------------------------------------------------------

    Dry Run:

    Example 1: nums = [2, 1, 5, 0, 4, 6]

    --------------------------------------------------------
    | Step | num | first | second | Condition Met?       |
    --------------------------------------------------------
    | 1    | 2   | 2     | ∞      | No                   |
    | 2    | 1   | 1     | ∞      | No                   |
    | 3    | 5   | 1     | 5      | No                   |
    | 4    | 0   | 0     | 5      | No                   |
    | 5    | 4   | 0     | 4      | No                   |
    | 6    | 6   | 0     | 4      | ✅ Yes (triplet found) |
    --------------------------------------------------------

    Why `first` and `second`?
    - `first` tracks the smallest number seen so far.
    - `second` tracks the second smallest number seen so far.
    - If a number greater than `second` appears, we have found our triplet.

    Time Complexity:
    - O(n): We iterate through `nums` once.

    Space Complexity:
    - O(1): Only two extra variables `first` and `second` are used.

    Final Comparison:
    --------------------------------------------------------
    | Approach    | Time Complexity | Space Complexity | Notes |
    --------------------------------------------------------
    | Brute Force | O(n³)           | O(1)             | Very slow |
    | Better      | O(n²)           | O(1)             | Still inefficient |
    | Optimal     | O(n)            | O(1)             | 🚀 Best approach |
    --------------------------------------------------------
*/

    private static class Optimal {
        public boolean increasingTriplet(int[] nums) {
        /*
         * Bruitforce: in N3, generate all sequence and check condition if found then return true
         * Other optimization: Use LIS technics to find length of LIS if greater than tripple length then return true,
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Increasing Triplet Subsequence.
Memory Usage: 80.5 MB, less than 78.22% of Java online submissions for Increasing Triplet Subsequence.
Time complexity is O(n)
 * Space complexity is O(1)
         */
            int first = Integer.MAX_VALUE;
            int second = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= first) {//111111 case so equal required
                    first = nums[i];
                } else if (nums[i] <= second) {
                    second = nums[i];
                } else {
                    return true;
                }

            }
            return false;

        }

        public static void main(String args[]) {
            Optimal tripletSubsequence = new Optimal();
            int input[] = {9, 10, -2, 12, 6, 7, -1};
            System.out.print(tripletSubsequence.increasingTriplet(input));
        }
    }

    /*
        Extended version
        Yes, you can extend this pattern to detect an increasing quadruplet by tracking one more variable
         — i.e., maintaining three smallest increasing values (first, second, and third) instead of two.
     */

    public boolean increasingQuadruplet(int[] nums) {
        /*
         * Extension of the increasing triplet pattern
         * Track first, second, and third smallest increasing values
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        int third = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num; // smallest so far
            } else if (num <= second) {
                second = num; // next bigger than first
            } else if (num <= third) {
                third = num; // next bigger than second
            } else {
                // Found num > third > second > first
                return true;
            }
        }

        return false;
    }



}
