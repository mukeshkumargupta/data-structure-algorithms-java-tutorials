package com.interview.array;

/*
 * https://leetcode.com/problems/find-the-duplicate-number/
 * Category: Medium, Tricky
 */
public class FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
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
