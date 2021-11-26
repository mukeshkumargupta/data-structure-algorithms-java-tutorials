package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/happy-number/submissions/
 * Category: Easy, Tricky, Facebook
 * Related:
 * https://leetcode.com/problems/ugly-number/ Easy
 * https://leetcode.com/problems/sum-of-digits-of-string-after-convert/ Easy
 * https://www.youtube.com/watch?v=EQM7Ylkv7AE&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=8
 * 
 * Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

 

Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false
 

Constraints:

1 <= n <= 231 - 1
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        /*
         * Runtime: 3 ms, faster than 25.04% of Java online submissions for Happy Number.
Memory Usage: 38.3 MB, less than 10.13% of Java online submissions for Happy Number.
         */
        Set<Integer> set = new HashSet<>();
        
        
        while (n > 0) {
            if (!set.contains(n)) {
                set.add(n);
            } else {
                return false;
            }
            
            //Get all number and square it
            int sum = 0;
            while (n > 0) {
                int num = n % 10;
                sum += num * num;
                //System.out.println(sum);
                n = n/10;
            }
            
            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
            
            
        }
        return false;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
