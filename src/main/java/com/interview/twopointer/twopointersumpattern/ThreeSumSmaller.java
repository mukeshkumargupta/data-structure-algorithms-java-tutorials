package com.interview.twopointer.twopointersumpattern;

import java.util.Arrays;
/**
 * Given an array of n integers nums and a target, find the number of index triplets i, left, right
 * with 0 <= i < left < right < n that satisfy the condition nums[i] + nums[left] + nums[right] < target.
 *
 * https://leetcode.com/problems/3sum-smaller/
 * https://www.youtube.com/watch?v=_A8obVmv6mc 
 * Category: Medium,triplet, Tricky
 * Related: https://leetcode.com/problems/increasing-triplet-subsequence/ Medium
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/ Medium
 * https://leetcode.com/problems/count-good-triplets/ Easy
 * Count three sum greater than target, Need to try
 * 
 */

public class ThreeSumSmaller {



    /*
     * Approach 1: Brute Force
     * This approach checks all possible triplets (i, j, k) such that:
     * - 0 <= i < j < k < nums.length
     * - nums[i] + nums[j] + nums[k] < target
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     */
    public int threeSumSmallerBruteForce(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    /*
     * Approach 2: Sorting and Two Pointers
     *
     * The idea is to sort the array and use a two-pointer technique to efficiently find
     * all valid triplets where the sum is less than the target.
     *
     * Steps:
     * 1. Sort the array in ascending order. Sorting enables us to use the two-pointer strategy.
     * 2. Fix the first element of the triplet (nums[i]) and use two pointers (`left` and `right`)
     *    to find pairs such that nums[i] + nums[left] + nums[right] < target.
     * 3. If nums[i] + nums[left] + nums[right] < target, then all elements between `left` and `right`
     *    will also form valid triplets, because the array is sorted.
     * 4. Count the number of valid triplets for the current i and move the pointers accordingly.
     *
     * Time Complexity: O(n^2)
     * - Sorting takes O(n log n).
     * - The two-pointer loop runs O(n^2) in the worst case.
     * Space Complexity: O(1) (In-place sorting).
     */
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length -2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] >= target) {
                    right--;
                } else {
                    count += right - left;//Tricky
                    left++;
                }
            }
        }
        return count;
    }
    
}
