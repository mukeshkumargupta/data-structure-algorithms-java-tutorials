package com.interview.array.majarityElmementPattern;
import java.util.*;

/*
 * https://leetcode.com/problems/majority-element-ii/
 * https://www.youtube.com/watch?v=yDbkQd9t2ig&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=74
 * Category: Medium, Must Do
 * Related:
 * https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/ Easy
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

 

Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]
 

Constraints:

1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109
 */
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {//Runtime: 1 ms, faster than 99.89% of Java online submissions for Majority Element II.
        int l = nums.length;
        int count1 = 0;
        int count2 = 0;
        int candidate1 = -1;
        int candidate2 = -1;
        
        for (int elm : nums) {
            if (elm == candidate1) {
               count1++; 
            } else if (elm == candidate2) {
                count2++; 
            } else if (count1 == 0) {
                count1 = 1;
                candidate1 = elm;
            } else if (count2 == 0) {
                count2 = 1;
                candidate2 = elm;
            } else {
                count1--;
                count2--;
            }
            
        }
        
        count1 = 0;
        count2 = 0;
        List<Integer> result = new ArrayList<>();
        for (int elm : nums) {
            if (candidate1 == elm) {
                count1++;
            } else  if (candidate2 == elm) {//[-1,-1,-1], to make candidate1 and candidate 2 different
                count2++;
            }
        }
        if (count1 > l/3) {
            result.add(candidate1);
        }
        if (count2 > l/3) {
            result.add(candidate2);
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
