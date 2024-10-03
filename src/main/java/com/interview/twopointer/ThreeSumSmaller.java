package com.interview.twopointer;

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
Approach 1: Brute Force
The brute force approach involves checking all possible triplets, which results in a time complexity of
𝑂
(
𝑛
3
)
O(n
3
 ).
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
    Approach 2: Sorting and Two Pointers
An improved approach involves sorting the array first and then using a two-pointer technique. By fixing one element, we can apply the two-pointer technique to find valid pairs for each triplet combination.

Sort the Array: Sorting allows us to use a two-pointer strategy effectively.
Two-Pointer Technique: For each fixed element, use two pointers to find pairs that add up to less than target.
This approach has a time complexity of
𝑂
(
𝑛
2
)
O(n
2
 ).
 Explanation of the Optimal Solution
Sorting helps to quickly eliminate combinations that exceed the target by ensuring that when sum >= target, we only need to adjust the pointers without recalculating sums for already checked pairs.
Counting Valid Combinations: For each fixed i, if nums[i] + nums[left] + nums[right] < target, then all elements between left and right will also form valid pairs with i, adding to the count efficiently.
This solution achieves an optimal time complexity of
𝑂
(
𝑛
2
)
O(n
2
 ), significantly faster than the brute-force approach.
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
