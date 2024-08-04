package com.interview.twopointer;

/*
 * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
 * Category: Easy, Tricky
 * https://www.youtube.com/watch?v=4QDmhQwNIlc&t=209s
 * Related:
 * https://leetcode.com/problems/4sum-ii/ Medium
 * https://leetcode.com/problems/rle-iterator/ Medium
 * https://leetcode.com/problems/sentence-similarity-iii/ Medium
 * Given a 0-indexed integer array nums, return true if it can be made strictly increasing after removing exactly one element, or false otherwise. If the array is already strictly increasing, return true.

The array nums is strictly increasing if nums[i - 1] < nums[i] for each index (1 <= i < nums.length).

 

Example 1:

Input: nums = [1,2,10,5,7]
Output: true
Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
[1,2,5,7] is strictly increasing, so return true.
Example 2:

Input: nums = [2,3,1,2]
Output: false
Explanation:
[3,1,2] is the result of removing the element at index 0.
[2,1,2] is the result of removing the element at index 1.
[2,3,2] is the result of removing the element at index 2.
[2,3,1] is the result of removing the element at index 3.
No resulting array is strictly increasing, so return false.
Example 3:

Input: nums = [1,1,1]
Output: false
Explanation: The result of removing any element is [1,1].
[1,1] is not strictly increasing, so return false.
Example 4:

Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is already strictly increasing, so return true.
 

Constraints:

2 <= nums.length <= 1000
1 <= nums[i] <= 1000
 */
public class RemoveOneElementtoMaketheArrayStrictlyIncreasing {//
    
    public boolean canBeIncreasing(int[] nums) {
        int count=0;
         for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]>=nums[i+1])
            {
                count++;
                if(i>0 && nums[i-1]>=nums[i+1])
                {
                    nums[i+1] = nums[i];
                    //We can not do like this: nums[i+1] = nums[i-1];//Case: [541,783,433,744]
                }
            }
            if(count>1)
                return false;
        }
       return true;   
    }
    public boolean canBeIncreasingInTwoLoop(int[] nums) {//Runtime: 2 ms, faster than 20.58% of Java online submissions for Remove One Element to Make the Array Strictly Increasing.
        int l = nums.length;
        int last = nums[l-1];
        int leftCount = 0;
        int rightCount = 0;
        for (int i = l-2; i >=0; i--) {
            if (nums[i] < last) {
                last = nums[i];
            } else {
                rightCount++;
            }
            
        }
        int first = nums[0];
        for (int i = 1; i < l; i++) {
            if (nums[i] > first) {
                first = nums[i];
            } else {
                leftCount++;
            }
            
        }
        return Math.min(rightCount, leftCount) <=1;

    }
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
