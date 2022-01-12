package com.interview.bits;

/*
 * https://leetcode.com/problems/number-complement/submissions/
 * Category: Easy, Facebook, Must Do
 * Related: https://leetcode.com/problems/minimum-incompatibility/ Hard
 * https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/ Medium
 * https://leetcode.com/problems/closest-subsequence-sum/ Hard
 * 
 * Other solution can be tried but not optimized: like take list of int and make right shift and collect each element by doing &1 and then tougle and store in list and derived login from there, not sure will it work
 * 
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.

For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
Given an integer num, return its complement.

 

Example 1:

Input: num = 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:

Input: num = 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 

Constraints:

1 <= num < 231
 

Note: This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/

Accepted
222,623
Submissions
340,173
 */
public class NumberComplement {
    public int findComplement(int num) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Number Complement.
Memory Usage: 35.9 MB, less than 64.70% of Java online submissions for Number Complement.
         */
        int numBits = (int)(Math.log(num)/Math.log(2)) + 1; //here log in java has base 10 only so log properties is followed to calculated in log 2
        int mask = (1 << numBits) -1 ;//note: for all 31 , 1 it will fail in c++ but in java it will be promited to unsigned by default so casting not required, In Java SE 8 and later, you can use the int data type to represent an unsigned 32-bit integer
        return num ^ mask;
        
    }
    public int findComplementM2(int num) {//Not tested//Not work if all 0000
        int result = 0;
        //while(num > 0) {32 time loop then try, it will be slow
            //int xbit = !(num & 1);//toggle
            int xbit = (num & 1);
            if (xbit == 1) {
                xbit = 0;
            } else {
                xbit = 1;
            }
            num >>=1;
            result <<=1;
            result ^= xbit;
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        NumberComplement instance = new NumberComplement();
        instance.findComplement(5);
        
    }
    
}
