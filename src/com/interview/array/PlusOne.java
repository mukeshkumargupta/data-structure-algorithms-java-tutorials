package com.interview.array;

/*
 * https://leetcode.com/problems/plus-one/ 
 * Category: Easy, Google, Must DO, Top150
 * Related:https://leetcode.com/problems/plus-one-linked-list/ Medium, Locked
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

 

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
Example 3:

Input: digits = [0]
Output: [1]
Explanation: The array represents the integer 0.
Incrementing by one gives 0 + 1 = 1.
Thus, the result should be [1].
Example 4:

Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
 

Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Plus One.
Memory Usage: 39.4 MB, less than 5.64% of Java online submissions for Plus One.
         */
        int index = digits.length-1;
        
        while(index >= 0) {
            if (digits[index] == 9) {
                digits[index] = 0;
                index--;
            } else {
                digits[index] += 1; 
                return digits;
            }
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = digits[i-1];
        }
        return result; 
    
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
