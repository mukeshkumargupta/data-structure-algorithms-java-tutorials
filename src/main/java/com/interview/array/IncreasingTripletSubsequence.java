package com.interview.array;

import java.util.Arrays;

/**
 * Date 03/06/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * Category: Medium, Tricky, Top150, triplet, lis
 * Bruitforce soln is n3 must tell then proceed optimization
 * Derived question make four or five increasing sequence make three, four variable and do, lis logic can be also applied but not optimized
 * Related: https://leetcode.com/problems/count-special-quadruplets/ Easy
 *Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 

Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
 

Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 * Find if there exists an increasing triplet subsequence.
 * Similar method to longest increasing subsequence in nlogn time.
 *
 * 
 *
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
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
Maintain two variables, first and second, to track the smallest and second smallest elements in the increasing triplet.
Traverse the array:
If the current element is smaller than or equal to first, update first.
Else if the current element is smaller than or equal to second, update second.
Else, if the current element is greater than second, we have found an increasing triplet. Return true.
If the loop completes without finding a triplet, return false.
Time Complexity:
O(n): Single traversal of the array.
Space Complexity:
O(1): Only two variables used.

Explanation of Optimized Approach:
The key observation is that we don’t need the exact subsequence, just a way to track its existence.
By keeping track of the smallest (first) and second smallest (second) values seen so far, we can efficiently determine whether a third element exists that forms the triplet.
Example Walkthrough:
Input:
nums = [1, 2, 3, 4, 5]

Optimized Approach Execution:
first = Integer.MAX_VALUE, second = Integer.MAX_VALUE.
Iterate through the array:
num = 1: Update first = 1.
num = 2: Update second = 2.
num = 3: Since 3 > second, return true.
Output:
true

Comparison of Approaches:
Approach	Time Complexity	Space Complexity	Notes
Brute Force	O(n³)	O(1)	Extremely slow for large inputs.
Better (DP)	O(n²)	O(n)	Improvement, but still not scalable.
Optimized	O(n)	O(1)	Best solution, handles large inputs well.
If you'd like, I can help further by explaining edge cases or testing specific inputs!
     */

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
        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
        int input[] = {9, 10, -2, 12, 6, 7, -1};
        System.out.print(tripletSubsequence.increasingTriplet(input));
    }
}
