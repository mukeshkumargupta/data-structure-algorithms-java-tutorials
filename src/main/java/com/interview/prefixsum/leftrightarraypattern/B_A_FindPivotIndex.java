package com.interview.prefixsum.leftrightarraypattern;

/*
 * https://leetcode.com/problems/find-pivot-index/
 * Category: Easy, Tricky
 * 724. Find Pivot Index
Easy

3093

367

Add to List

Share
Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.

 

Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
Example 3:

Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0
 

Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000
 

Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/

Accepted
303,230
Submissions
588,420
 */
public class B_A_FindPivotIndex {
    /*
    Two pass for left and right sum with two array
     */
    private static class Better {
        public int pivotIndex(int[] nums) {
            int len = nums.length;
            int[] leftSum = new int[len];
            int[] rightSum = new int[len];
            for (int i = 1; i < len; i++) {
                leftSum[i] = leftSum[i-1] + nums[i-1];
            }

            for (int i = len-2; i >= 0; i--) {
                rightSum[i] = rightSum[i+1] + nums[i+1];
            }

        /*for (int i = 0; i < len; i++) {
            System.out.println(leftSum[i]);
            //System.out.println(rightSum[i]);
        }*/

            for (int i = 0; i < len; i++) {
                if (i == 0 && rightSum[i] == 0) {
                    return i;
                }else if (i == len-1 && leftSum[i] == 0) {
                    return i;
                } else if (leftSum[i] == rightSum[i]) {
                    return i;
                }
            }
            return -1;

        }
    }

    /*
    📊 Complexity Analysis

    Metric	Value
    Time Complexity	O(n)
    Space Complexity	O(1)
    O(n) for a single pass to calculate totalSum and another for pivot check.

    No extra space used apart from variables.
     */
    private static class Optimize {
        public int pivotIndex(int[] nums) {
            int totalSum = 0;
            for (int num : nums) {
                totalSum += num;
            }

            int leftSum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (leftSum == totalSum - leftSum - nums[i]) {
                    return i;
                }
                leftSum += nums[i];
            }

            return -1;
        }

        public static void main(String[] args) {
            Optimize finder = new Optimize();
            int[] nums = {1, 7, 3, 6, 5, 6};
            System.out.println("Pivot Index: " + finder.pivotIndex(nums)); // Output: 3
        }
    }
    
}
