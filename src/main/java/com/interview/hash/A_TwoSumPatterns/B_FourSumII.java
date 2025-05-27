package com.interview.hash.A_TwoSumPatterns;

import java.util.*;
/*
 * Problem: https://leetcode.com/problems/4sum-ii/
 * Category: Medium, Top150, Tricky, VVImp
 * Author: Mukesh Kumar Gupta
 *
 * Related Problems:
 * - Walls and Gates: https://leetcode.com/problems/walls-and-gates/ (Medium)
 * - Dice Roll Simulation: https://leetcode.com/problems/dice-roll-simulation/ (Hard)
 * - Find the Winner of the Circular Game: https://leetcode.com/problems/find-the-winner-of-the-circular-game/ (Medium)
 *
 * Problem Statement:
 * Given four integer arrays nums1, nums2, nums3, and nums4, each of length n,
 * return the number of tuples (i, j, k, l) such that:
 *    0 <= i, j, k, l < n
 *    nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * Note: This problem can be generalized for target sums other than 0.
 *
 * Examples:
 *
 * Example 1:
 * Input: nums1 = [1, 2], nums2 = [-2, -1], nums3 = [-1, 2], nums4 = [0, 2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * Example 2:
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 * Explanation:
 * There is only one tuple that sums to zero: (0, 0, 0, 0).
 *
 * Constraints:
 * - n == nums1.length == nums2.length == nums3.length == nums4.length
 * - 1 <= n <= 200
 * - -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 */
public class B_FourSumII {
    /*
    📌 Dry Run (Brute Force)
Input Example:
java
Copy
Edit
A = {1, 2}
B = {-2, -1}
C = {-1, 2}
D = {0, 2}
Step-by-Step Execution
A[i]	B[j]	C[k]	D[l]	Sum	Count
1	-2	-1	0	-2	0
1	-2	-1	2	0	1
1	-2	2	0	1	1
1	-2	2	2	3	1
1	-1	-1	0	-1	1
1	-1	-1	2	1	1
1	-1	2	0	2	1
1	-1	2	2	4	1
2	-2	-1	0	-1	1
2	-2	-1	2	1	2
2	-2	2	0	2	2
2	-2	2	2	4	2
2	-1	-1	0	0	3
2	-1	-1	2	2	3
2	-1	2	0	3	3
2	-1	2	2	5	3
Final Count: 2 ✅


     */
    private static class BruitForce {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            /*
             * TC: O(N4)
             *
             */
            int count = 0;
            for (int a : nums1) {
                for (int b: nums2) {
                    for (int c : nums3) {
                        for (int d : nums4) {
                            if (a + b + c + d == 0) {
                                count++;
                            }
                        }
                    }
                }
            }
            return count;

        }
    }

    private static class Better {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            /*
             * O(N3)
             */
            int count = 0;
            Map<Integer, Integer> lookup = new HashMap<>();
            for (int d : nums4) {
                lookup.put(d, lookup.getOrDefault(d, 0) + 1);
            }
            for (int a : nums1) {
                for (int b: nums2) {
                    for (int c : nums3) {
                        count += lookup.getOrDefault(-(a + b + c ), 0);
                    }
                }
            }
            return count;

        }
    }

    /*
        📌 Use Case: When Multiple Combinations Have the Same Sum

        Let’s consider the input:
        C = {1, 2, 2};
        D = {-2, -1, -1};

        We want to count how many times each sum (C[k] + D[l]) occurs.

        🔹 Step 1️⃣: Populate HashMap for C + D
        We iterate over all combinations of C[k] + D[l] and store their frequencies:

        | C[k] | D[l] | Sum (C[k] + D[l]) | HashMap (lookup) Entry  |
        |------|------|-------------------|--------------------------|
        |  1   | -2   |        -1         | { -1 → 1 }              |
        |  1   | -1   |        0          | { -1 → 1, 0 → 1 }       |
        |  1   | -1   |        0          | { -1 → 1, 0 → 2 }       |
        |  2   | -2   |        0          | { -1 → 1, 0 → 3 }       |
        |  2   | -1   |        1          | { -1 → 1, 0 → 3, 1 → 1 } |
        |  2   | -1   |        1          | { -1 → 1, 0 → 3, 1 → 2 } |

        📌 Final HashMap (lookup):
        { -1 → 1, 0 → 3, 1 → 2 }

        This means:
        - **Sum -1 appears 1 time**.
        - **Sum 0 appears 3 times** (from different combinations).
        - **Sum 1 appears 2 times**.

        🔹 Why is This Important?
        When we later check if `-(A[i] + B[j])` exists in `lookup`, we **directly add the count**
        instead of iterating multiple times, making the algorithm **O(N²) instead of O(N⁴)**.

        🔵 Dry Run With A & B
        Let's assume:
        A = {0, -1}
        B = {1, 1}

        🔹 Step 2️⃣: Check If -(A[i] + B[j]) Exists

        | A[i] | B[j] | Sum (A[i] + B[j]) | Complement -(A[i] + B[j]) | Exists in lookup? | Contribution to Count |
        |------|------|------------------|----------------------------|-------------------|----------------------|
        |  0   |  1   |        1         |            -1             | ✅ Yes (-1 → 1)   | **1**               |
        |  0   |  1   |        1         |            -1             | ✅ Yes (-1 → 1)   | **1**               |
        | -1   |  1   |        0         |             0             | ✅ Yes (0 → 3)    | **3**               |
        | -1   |  1   |        0         |             0             | ✅ Yes (0 → 3)    | **3**               |

        📌 **Final Count:** `1 + 1 + 3 + 3 = 8`
    */
    private static class Optimal {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
         * O(N2)
         * Runtime: 99 ms, faster than 95.45% of Java online submissions for 4Sum II.
Memory Usage: 39.4 MB, less than 50.26% of Java online submissions for 4Sum II.
         */
            int count = 0;
            Map<Integer, Integer> lookup = new HashMap<>();
            for (int c: nums3) {
                for (int d : nums4) {
                    lookup.put(c+d, lookup.getOrDefault(c+d, 0) + 1);
                }
            }

            for (int a : nums1) {
                for (int b: nums2) {
                    count += lookup.getOrDefault(-(a + b  ), 0);
                }
            }
            return count;

        }
    }

    


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
