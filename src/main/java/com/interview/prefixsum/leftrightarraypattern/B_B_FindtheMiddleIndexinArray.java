package com.interview.prefixsum.leftrightarraypattern;

/*
 * Problem:
 * https://leetcode.com/problems/find-the-middle-index-in-array/
 *
 * Category: Easy, Must Do, Prefix Sum
 *
 * Related Problems:
 * https://leetcode.com/problems/find-pivot-index/ (Easy)
 * https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/ (Easy)
 * https://leetcode.com/problems/maximum-sum-score-of-array/ (Medium)
 *
 * 1991. Find the Middle Index in Array
 *
 * Description:
 * Given a 0-indexed integer array nums, find the leftmost middleIndex
 * (i.e., the smallest amongst all the possible ones).
 *
 * A middleIndex is an index where:
 * nums[0] + nums[1] + ... + nums[middleIndex - 1] == nums[middleIndex + 1] + ... + nums[nums.length - 1]
 *
 * If middleIndex == 0, the left side sum is considered to be 0.
 * If middleIndex == nums.length - 1, the right side sum is considered to be 0.
 *
 * Return the leftmost middleIndex that satisfies the condition, or -1 if no such index exists.
 *
 * Examples:
 *
 * Example 1:
 * Input: nums = [2, 3, -1, 8, 4]
 * Output: 3
 * Explanation: Left sum = 2 + 3 + (-1) = 4, Right sum = 4
 *
 * Example 2:
 * Input: nums = [1, -1, 4]
 * Output: 2
 * Explanation: Left sum = 1 + (-1) = 0, Right sum = 0
 *
 * Example 3:
 * Input: nums = [2, 5]
 * Output: -1
 * Explanation: No valid middle index exists.
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - -1000 <= nums[i] <= 1000
 *
 * Note:
 * This question is similar to 724: https://leetcode.com/problems/find-pivot-index/
 */
public class B_B_FindtheMiddleIndexinArray {
    public int findMiddleIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
