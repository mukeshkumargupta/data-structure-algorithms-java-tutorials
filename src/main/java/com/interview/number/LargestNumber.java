package com.interview.number;

import java.util.*;

/*
 * https://leetcode.com/problems/largest-number/submissions/
 * https://www.youtube.com/watch?v=qEIGhVtZ-sg
 * Category: Medium, Must Do, Top150
 * Related: https://leetcode.com/problems/orderly-queue/ Hard
 * https://leetcode.com/problems/long-pressed-name/ Easy
 * https://leetcode.com/problems/put-boxes-into-the-warehouse-i/ Medium
 * iven a list of non-negative integers nums, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.

 

Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"
Example 3:

Input: nums = [1]
Output: "1"
Example 4:

Input: nums = [10]
Output: "10"
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 109
 */


/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * https://leetcode.com/problems/largest-number/
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        /*
         * TC: NLOGN* O(len(largest string)
         */
        
        String[] input = new String[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            //input[i] = "" + nums[i];
            /*
            Runtime: 9 ms, faster than 44.31% of Java online submissions for Largest Number.
Memory Usage: 38.7 MB, less than 59.50% of Java online submissions for Largest Number.
    
*/          
            input[i] = String.valueOf(nums[i]);
            /*
            Runtime: 5 ms, faster than 76.81% of Java online submissions for Largest Number.
Memory Usage: 38.5 MB, less than 77.22% of Java online submissions for Largest Number.
*/
            
        }
        if (input.length == 1) {//Not required sorting truncate
            return input[0];
        }
        
        Arrays.sort(input, (arr1, arr2) -> {
            String ij = arr1 + arr2;
            String ji = arr2 + arr1;
            return ji.compareTo(ij);
            
        });
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            result.append(input[i]);
        }
        if (result.length() > 0 && result.charAt(0) == '0') {//This is case: [0,0]
            
            return "0";
        }
        return result.toString();
        
    }
}
