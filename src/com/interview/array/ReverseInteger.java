package com.interview.array;

/*
 * https://leetcode.com/problems/reverse-integer/
 * Category: Medium, Must Do, Top150
 */
public class ReverseInteger {
    public int reverse(int x) {
        boolean isNegative = (x < 0) ? true : false;
        if (isNegative) {
            x = x*-1;
        }
        long result = 0;
        while(x > 0) {
           result = result * 10 + x%10;
           x = x/10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        
        return isNegative ? (int)result*-1 :(int)result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
