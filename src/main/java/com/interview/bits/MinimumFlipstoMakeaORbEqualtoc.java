package com.interview.bits;

/*
https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/description/?envType=study-plan-v2&envId=leetcode-75
https://www.youtube.com/watch?v=HbRJ4b2pdHE
Category: Medium, top75, Must Do, tricky
Related:
https://leetcode.com/problems/most-profit-assigning-work/ Medium
https://leetcode.com/problems/longest-subsequence-with-limited-sum/ Easy
https://leetcode.com/problems/maximum-matching-of-players-with-trainers/ Medium
Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.



Example 1:



Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
Example 2:

Input: a = 4, b = 2, c = 7
Output: 1
Example 3:

Input: a = 1, b = 2, c = 3
Output: 0


Constraints:

1 <= a <= 10^9
1 <= b <= 10^9
1 <= c <= 10^9

 */
public class MinimumFlipstoMakeaORbEqualtoc {
    /*
TC: O(1) because loop max can run up to 32 time becaus eof 32 bit in wors cas which is almost constant
*/
    public int minFlips(int a, int b, int c) {
        int minFlip = 0;
        while (a > 0 || b > 0 || c > 0) {
            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;

            if (bitC == 0) { //if both bit A nad bitB 1 then both need to flip
                minFlip += bitA + bitB;
            } else {
                if (bitA == 0 && bitB == 0) {//Both should be 0 to flip required
                    minFlip += 1;
                }

            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return minFlip;

    }
}
