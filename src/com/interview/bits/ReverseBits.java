package com.interview.bits;

/**
 * https://leetcode.com/problems/reverse-bits/
 * https://www.youtube.com/watch?v=ZW7st_pN05w
 * Related: https://leetcode.com/problems/single-number-iii/ Medium
 * https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/ Hard
 * https://leetcode.com/problems/count-the-number-of-consistent-strings/ Easy
 * Category: Easy, Must Do, Top150
 * Reverse bits of a given 32 bits unsigned integer.

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 

Example 1:

Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
Example 2:

Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 

Constraints:

The input must be a binary string of length 32
 

Follow up: If this function is called many times, how would you optimize it?
 */
public class ReverseBits {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        /*
         * Runtime: 1 ms, faster than 99.06% of Java online submissions for Reverse Bits.
Memory Usage: 38.6 MB, less than 74.73% of Java online submissions for Reverse Bits.
TC O(32)
         */
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<=1;
            int mask = n&1;
            result |= mask;
            
            
            n >>= 1;
        }
        return result;
        
    }

    
    
    public static void main(String args[]){
        ReverseBits rb = new ReverseBits();
        System.out.println(rb.reverseBits(418));
    }
}
