package com.interview.bits;

import java.util.Scanner;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 * Category : Easy, Must Do
 * Related: https://leetcode.com/problems/reverse-bits/ Easy
 * https://leetcode.com/problems/counting-bits/ Easy
 * https://leetcode.com/problems/binary-watch/ Easy
 * https://leetcode.com/problems/binary-number-with-alternating-bits/ Easy
 * https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/ Easy
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Note:

Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type. It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.
 

Example 1:

Input: n = 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
Example 2:

Input: n = 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
Example 3:

Input: n = 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 

Constraints:

The input must be a binary string of length 32.
 

Follow up: If this function is called many times, how would you optimize it?
 * Category: Must Do
 */
public class CountBits {

/*
    Complexity Analysis:
    ---------------------
    Time Complexity: O(log n)
    - Each number takes O(log n) time to count bits,

    Space Complexity: O(1)
*/

    public int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);  // Check if the last bit is 1
            n >>= 1;  // Right shift to check the next bit
        }
        return count;
    }
    

}
