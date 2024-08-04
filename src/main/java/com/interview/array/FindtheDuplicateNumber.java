package com.interview.array;

/*
 * https://leetcode.com/problems/find-the-duplicate-number/
 * https://www.youtube.com/watch?v=32Ll35mhWg0
 * Category: Medium, Tricky
 * Related: Cycle detection in array
 * 
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

 

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
 

Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.
 

Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
Accepted
745,602
Submissions
1,276,094
 */
public class FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        /*
         * Runtime: 5 ms, faster than 86.91% of Java online submissions for Find the Duplicate Number.
Memory Usage: 76.9 MB, less than 18.30% of Java online submissions for Find the Duplicate Number.
         */
        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        //System.out.println(slow);
        fast = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        //System.out.println(slow);
        return slow;
        
    }
}
