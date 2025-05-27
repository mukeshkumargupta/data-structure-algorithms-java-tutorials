package com.interview.bits;

/*
https://leetcode.com/problems/bitwise-and-of-numbers-range/description/?envType=study-plan-v2&envId=top-interview-150
https://www.youtube.com/watch?v=-qrpJykY2gE
Category: Medium, top150, Tricky
Related:
https://leetcode.com/problems/longest-nice-subarray/ Medium
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.



Example 1:

Input: left = 5, right = 7
Output: 4
Example 2:

Input: left = 0, right = 0
Output: 0
Example 3:

Input: left = 1, right = 2147483647
Output: 0


Constraints:

0 <= left <= right <= 231 - 1
 */
public class BitwiseANDofNumbersRange {
    /*
    ⏱ Time: O(1) (since max 32 bits)
    🧠 Space: O(1)
    ✅ Highly optimized — shifts instead of looping over entire range.
     */
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left != right) {
            left = left >>1;
            right = right >>1;
            shift += 1;
        }
        return left<<shift; //u can take here left or right because it will be same now


    }

/*
 ✅ Category: Derived Questions from Bitwise AND in Range

 1. Bitwise OR of Numbers in a Range
    Question: Given two integers left and right, return the bitwise OR of all numbers in the range [left, right].
    Input: left = 5, right = 7
    Output: 7

 2. Bitwise XOR of Numbers in a Range
    Question: Return the XOR of all numbers between left and right inclusive.
    Input: left = 3, right = 6
    Output: 4  // 3 ^ 4 ^ 5 ^ 6 = 4

 3. Count of Set Bits Between left and right
    Question: Count total number of 1 bits in binary representations of all numbers between left and right.
    Input: left = 5, right = 7
    Output: 6  // 5: 101 → 2, 6: 110 → 2, 7: 111 → 2 → total = 6

 4. Find Most Significant Bit (MSB) in a Range
    Question: Find the most significant bit that is common in all numbers between left and right.
    Input: left = 26, right = 30
    Output: 16  // because 11010 & 11110 = 11000 → MSB is 16

 5. Binary Representation of AND Result
    Question: Return the binary string representation of the AND result of a range.
    Input: left = 5, right = 7
    Output: "100"

 6. Check if All Numbers in Range Share Same MSB
    Question: Return true if all numbers in [left, right] share the same most significant bit.
    Input: left = 8, right = 15
    Output: true

 7. Minimize Range to Keep Same Bitwise AND
    Question: Given left and right, reduce the range [left, right] as small as possible such that the AND result remains the same.
    Input: left = 5, right = 7
    Output: [5, 6]  // AND of 5 & 6 is 4, and same as AND of 5,6,7

 8. Find All Subranges with Same AND
    Question: Count number of subranges [i, j] within [left, right] such that AND of all numbers in subrange is same.

 9. Find Leftmost Set Bit Position in a Number
    Question: Return index of highest set bit (zero-based).
    Input: n = 18 (10010)
    Output: 4

 10. Range AND with Step
    Question: Compute bitwise AND from left to right in steps of k.
    Input: left = 5, right = 15, k = 2
    Output: 1  // Range: 5, 7, 9, 11, 13, 15 → AND = 1

 11. Sum of All Bitwise AND Results in a Range
    Question: Sum of all AND values between all unique pairs (i, j) such that left <= i < j <= right.

 12. Find Range Where Bitwise AND is Zero
    Question: Find smallest [l, r] such that AND(l to r) == 0.

 13. Range Update with Bitwise AND in Segment Tree
    Question:
      a. Update range [l, r] with bitwise AND of x
      b. Query AND of [i, j]

 14. Find Next Power of 2 After AND of Range
    Question: After computing AND from left to right, return the next power of 2.
    Input: left = 5, right = 7 → AND = 4
    Output: 8

 15. Count of Bitwise AND Results Equal to Target
    Question: Count how many subranges in [left, right] have AND equal to a given target.

 🔚 Summary Table
 ----------------------------------------------------------------
 | #  | Type                                 | Tags              |
 |----|--------------------------------------|-------------------|
 | 1  | Bitwise OR in range                  | Bit manipulation  |
 | 2  | Bitwise XOR in range                 | Bit manipulation  |
 | 3  | Count of set bits in range           | Counting bits     |
 | 4  | Common MSB of range                  | AND + Bit logic   |
 | 5  | Binary representation of result      | String + Bit      |
 | 6  | Check if MSB is same across range    | MSB logic         |
 | 7  | Shrink range with same AND           | Optimization      |
 | 8  | Count subranges with same AND        | Sliding window    |
 | 9  | Leftmost set bit                     | Math, bits        |
 | 10 | Range AND with step                  | Iteration         |
 | 11 | Sum of all ANDs of unique pairs      | Math, combination |
 | 12 | Find subrange where AND is 0         | Brute/Greedy      |
 | 13 | Segment tree with AND                | Advanced DS       |
 | 14 | Next power of 2 from AND result      | Bits + Math       |
 | 15 | Count subranges with AND == target   | Subarray logic    |
 ----------------------------------------------------------------
*/

    /**
     * 1. Bitwise OR of Numbers in a Range
     * Time: O(right - left + 1), Space: O(1)
     */
    public int rangeBitwiseOr(int left, int right) {
        int result = left;
        for (int i = left + 1; i <= right; i++) {
            result |= i;
        }
        return result;
    }

    /**
     * 2. Bitwise XOR of Numbers in a Range
     * Time: O(right - left + 1), Space: O(1)
     */
    public int rangeXor(int left, int right) {
        int result = 0;
        for (int i = left; i <= right; i++) {
            result ^= i;
        }
        return result;
    }

    /**
     * 3. Count of Set Bits Between left and right
     * Time: O((right - left + 1) * 32), Space: O(1)
     */
    public int countSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            count += Integer.bitCount(i);
        }
        return count;
    }

    /**
     * 4. Most Significant Bit (MSB) in a Range
     * Time: O(1), Space: O(1)
     */
    public int findMSB(int left, int right) {
        int shift = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    /**
     * 5. Binary Representation of AND Result
     * Time: O(log n), Space: O(1)
     */
    public String binaryAndRepresentation(int left, int right) {
        int result = left;
        for (int i = left + 1; i <= right; i++) {
            result &= i;
        }
        return Integer.toBinaryString(result);
    }

    /**
     * 6. Check if all numbers share same MSB
     * Time: O(1), Space: O(1)
     */
    public boolean isSameMSB(int left, int right) {
        return Integer.highestOneBit(left) == Integer.highestOneBit(right);
    }

    /**
     * 7. Minimize Range to Keep Same Bitwise AND
     * Time: O(right - left + 1), Space: O(1)
     */
    public int[] minimizeRange(int left, int right) {
        int target = left;
        for (int i = left + 1; i <= right; i++) {
            target &= i;
        }
        for (int i = left; i <= right; i++) {
            int val = i;
            for (int j = i + 1; j <= right; j++) {
                val &= j;
                if (val == target) return new int[]{i, j};
            }
        }
        return new int[]{left, right};
    }

    /**
     * 8. Count All Subranges with Same AND (Brute)
     * Time: O(n^2), Space: O(1)
     */
    public int countSameAndSubranges(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int and = i;
            for (int j = i; j <= right; j++) {
                and &= j;
                count++;
            }
        }
        return count;
    }

    /**
     * 9. Find Leftmost Set Bit Position (0-based)
     * Time: O(1), Space: O(1)
     */
    public int leftmostSetBit(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    /**
     * 10. Range AND with Step
     * Time: O((right-left)/k), Space: O(1)
     */
    public int rangeAndStep(int left, int right, int k) {
        int result = -1;
        for (int i = left; i <= right; i += k) {
            if (result == -1) result = i;
            else result &= i;
        }
        return result;
    }

    /**
     * 11. Sum of Bitwise AND of All Unique Pairs
     * Time: O(n^2), Space: O(1)
     */
    public int sumOfAllAndPairs(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                sum += (i & j);
            }
        }
        return sum;
    }

    /**
     * 12. Smallest Range Where AND = 0
     * Time: O(n^2), Space: O(1)
     */
    public int[] smallestRangeZeroAnd(int left, int right) {
        for (int i = left; i <= right; i++) {
            int val = i;
            for (int j = i; j <= right; j++) {
                val &= j;
                if (val == 0) return new int[]{i, j};
            }
        }
        return new int[]{};
    }

/**
 * 13. Segment Tree AND Operations — Skipped (Large code, usually handled with templates)
 */

    /**
     * 14. Next Power of Two after AND of Range
     * Time: O(log n), Space: O(1)
     */
    public int nextPowerOfTwoFromAnd(int left, int right) {
        int and = left;
        for (int i = left + 1; i <= right; i++) {
            and &= i;
        }
        if (and == 0) return 1;
        int power = 1;
        while (power <= and) power <<= 1;
        return power;
    }

    /**
     * 15. Count Subranges with AND == Target (Brute)
     * Time: O(n^2), Space: O(1)
     */
    public int countSubrangeAndEqualsTarget(int left, int right, int target) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int val = i;
            for (int j = i; j <= right; j++) {
                val &= j;
                if (val == target) count++;
            }
        }
        return count;
    }
}
