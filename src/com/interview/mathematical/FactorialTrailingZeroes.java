package com.interview.mathematical;
/*
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 * https://www.youtube.com/watch?v=fx8rUY_iIms
 * Category: medium, Top150
 * Given an integer n, return the number of trailing zeroes in n!.

Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

 

Example 1:

Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Example 3:

Input: n = 0
Output: 0
 

Constraints:

0 <= n <= 104
 

Follow up: Could you write a solution that works in logarithmic time complexity?
 */
public class FactorialTrailingZeroes {
    int trailingZeroes(int n) {
        /*
         * 100% speed
         */
        int p = 5;
        int factorFiveCount = 0;
        while(n/p >0) {
            factorFiveCount += n/p;
            p = p*5;
        }
        return factorFiveCount;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
