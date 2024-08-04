package com.interview.bits;

/*
 * https://leetcode.com/problems/hamming-distance/
 * https://www.youtube.com/watch?v=5UKMvO5bXPI&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=18
 * Category: Easy, Facebook, Must Do
 * 
 * Related: https://leetcode.com/problems/number-of-1-bits/ Easy
 * https://leetcode.com/problems/total-hamming-distance/ Medium
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, return the Hamming distance between them.

 

Example 1:

Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.
Example 2:

Input: x = 3, y = 1
Output: 1
 

Constraints:

0 <= x, y <= 231 - 1
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Hamming Distance.
Memory Usage: 35.5 MB, less than 91.45% of Java online submissions for Hamming Distance.

Other method just loop till 32 and & 1 extract one bit and using xor check 1 if then increment count then right shift both number
         */
        int xor = x ^ y;
        int countSetBit = 0;
        while (xor > 0) {
            xor = xor & xor-1;
            countSetBit++;
        }
        return countSetBit;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
