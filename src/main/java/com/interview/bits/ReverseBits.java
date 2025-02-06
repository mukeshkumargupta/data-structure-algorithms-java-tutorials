package com.interview.bits;

/**
 * https://leetcode.com/problems/reverse-bits/
 * Video Explanation: https://www.youtube.com/watch?v=ZW7st_pN05w
 * Related Problems:
 * - https://leetcode.com/problems/single-number-iii/ (Medium)
 * - https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/ (Hard)
 * - https://leetcode.com/problems/count-the-number-of-consistent-strings/ (Easy)
 *
 * Category: Easy, Must Do, Top150
 *
 * Reverse the bits of a given 32-bit unsigned integer.
 *
 * Note:
 * - In Java, integers are represented in two's complement form.
 * - The input and output should be treated as unsigned values.
 *
 * Example 1:
 * Input:  n = 00000010100101000001111010011100
 * Output: 964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string represents 43261596. After reversal, it becomes 964176192.
 *
 * Example 2:
 * Input:  n = 11111111111111111111111111111101
 * Output: 3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string represents 4294967293. After reversal, it becomes 3221225471.
 *
 * Constraints:
 * - The input must be a binary string of length 32.
 *
 * Follow-up:
 * - If this function is called multiple times, how would you optimize it?
 */
public class ReverseBits {

    /**
     * Brute Force Approach (Bit-by-Bit Reversal)
     *
     * Idea:
     * - Extract each bit one by one from the rightmost side.
     * - Insert it into the reversed position by shifting left.
     * - Repeat for 32 bits.
     *
     * Steps:
     * 1. Initialize result = 0.
     * 2. Iterate 32 times:
     *    - Extract the last bit using (n & 1).
     *    - Shift result left by 1 position.
     *    - Append the extracted bit to result using bitwise OR (|).
     *    - Right shift n to process the next bit.
     * 3. Return result.
     *
     * Complexity Analysis:
     * - Time Complexity: O(1) (Fixed 32-bit processing)
     * - Space Complexity: O(1) (Only a few variables are used)
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1); // Shift left and append last bit
            n >>= 1; // Shift right to process the next bit
        }
        return result;
    }

    /**
     * Main method for testing.
     */
    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
        System.out.println(rb.reverseBits(418)); // Example test case
    }
}