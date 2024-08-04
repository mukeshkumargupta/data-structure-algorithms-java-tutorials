package com.interview.bits;

/*
 * https://leetcode.com/problems/divide-two-integers/
 * https://www.youtube.com/watch?v=m4L_5qG4vG8&t=424s
 * Category: Medium, Top150
 * Related:
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/ Hard, VVImp
 * https://leetcode.com/problems/robot-bounded-in-circle/ Medium VVImp
 * https://leetcode.com/problems/find-xor-sum-of-all-pairs-bitwise-and/ Hard VImp
 * 
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

 

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 

Constraints:

-231 <= dividend, divisor <= 231 - 1
divisor != 0
Accepted
456,556
Submissions
2,680,044
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        /*
         * Runtime: 1 ms, faster than 97.97% of Java online submissions for Divide Two Integers.
Memory Usage: 41.9 MB, less than 18.52% of Java online submissions for Divide Two Integers
TC: O(Lon(N^2)
SC: O(1)
         */
        if (dividend == 1 << 31 && divisor == -1) {//if dividend is extream negative and divisor -1 then overflow divisition
            return Integer.MAX_VALUE;
        }
        boolean sign = (dividend >= 0) == (divisor >= 0) ? true : false;// if both are same sign
        
        
        int result = 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while(dividend - divisor >= 0) {
            int count = 0;
            while (dividend - (divisor << 1 << count) >= 0) {
                
                count++;
                
            }
            dividend -= divisor << count;
            result += 1 << count;
            
        }

        return sign ? result : -result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
