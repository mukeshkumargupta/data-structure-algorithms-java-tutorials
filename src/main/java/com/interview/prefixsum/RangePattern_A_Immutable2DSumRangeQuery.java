package com.interview.prefixsum;

/*
 * Date: 03/11/2017
 * @author Mukesh Kumar Gupta
 *
 * Reference:
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * Category: Medium, VVImp, Tricky
 * Related Problems:
 * - https://leetcode.com/problems/range-sum-query-immutable/ (Easy)
 * - https://leetcode.com/problems/range-sum-query-2d-mutable/ (Hard)
 *
 * Problem Statement:
 * Given a 2D matrix, handle multiple queries of the following type:
 * - Calculate the sum of elements inside a rectangle defined by its
 *   upper-left corner (row1, col1) and lower-right corner (row2, col2).
 *
 * Implementation:
 * - NumMatrix(int[][] matrix): Initializes the object with the integer matrix.
 * - int sumRegion(int row1, int col1, int row2, int col2):
 *   Returns the sum of the elements inside the specified rectangle.
 *
 * Time Complexity:
 * - Construction: O(n * m)
 * - Query: O(1)
 *
 * Space Complexity:
 * - O(n * m)
 *
 * Example:
 * Input:
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2],
 *    [5, 6, 3, 2, 1],
 *    [1, 2, 0, 1, 5],
 *    [4, 1, 0, 1, 7],
 *    [1, 0, 3, 0, 5]]],
 *   [2, 1, 4, 3],
 *   [1, 1, 2, 2],
 *   [1, 2, 2, 4]]
 *
 * Output:
 * [null, 8, 11, 12]
 *
 * Explanation:
 * NumMatrix numMatrix = new NumMatrix([
 *     [3, 0, 1, 4, 2],
 *     [5, 6, 3, 2, 1],
 *     [1, 2, 0, 1, 5],
 *     [4, 1, 0, 1, 7],
 *     [1, 0, 3, 0, 5]
 * ]);
 * numMatrix.sumRegion(2, 1, 4, 3); // returns 8 (sum of red rectangle)
 * numMatrix.sumRegion(1, 1, 2, 2); // returns 11 (sum of green rectangle)
 * numMatrix.sumRegion(1, 2, 2, 4); // returns 12 (sum of blue rectangle)
 *
 * Constraints:
 * - 1 <= m, n <= 200
 * - -10^5 <= matrix[i][j] <= 10^5
 * - 0 <= row1 <= row2 < m
 * - 0 <= col1 <= col2 < n
 * - At most 10^4 calls will be made to sumRegion.
 *
 * Stats:
 * - Accepted: 219,486
 * - Submissions: 462,761
 */
public class RangePattern_A_Immutable2DSumRangeQuery {
    /*
        Step 2: Brute Force Approach (Inefficient)

        Approach:
        - Iterate over the submatrix from (row1, col1) to (row2, col2).
        - Sum all the elements manually.

        Time Complexity:
        - O(n × m) per query, which is too slow for large inputs.

        Why is this slow?
        - If the matrix is 200 × 200, the worst case will require 40,000 operations per query.
        - Given 10⁴ queries, the total operations could be 4 × 10⁸ → TLE (Time Limit Exceeded).
    */
    private static class BruitForce {
        private int[][] matrix;

        public BruitForce(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    sum += matrix[i][j];
                }
            }
            return sum;
        }
    }


    /*
        Step 3: Optimized Approach (Using Prefix Sum)

        Optimized Idea:
        Instead of recalculating sums for every query, precompute a prefix sum for the matrix.
        This allows querying in O(1) time.

        Steps:
        1. Precompute a prefixSum matrix where:
           prefixSum[i][j] = Sum of all elements in the rectangle (0,0) to (i-1,j-1)

        2. Answer queries in O(1) using:
           Sum = prefixSum[row2+1][col2+1]
               - prefixSum[row1][col2+1]
               - prefixSum[row2+1][col1]
               + prefixSum[row1][col1]

        3. Inclusion-Exclusion Principle:
           - Remove extra regions that were added twice.

        Step 4: Complexity Analysis

        | Approach             | Preprocessing Time | Query Time | Space Complexity |
        |----------------------|-------------------|------------|------------------|
        | Brute Force         | O(1)              | O(nm)      | O(1)             |
        | Optimized (Prefix Sum) | O(nm)          | O(1)       | O(nm)            |

        Why is this better?
        ✅ Precompute in O(nm), then each query is O(1).
        ✅ Works for large matrices (200×200) efficiently.
        ✅ Only requires additional O(nm) space.

        Final Thoughts:
        - Brute Force is too slow for large inputs.
        - Prefix Sum reduces query time from O(nm) → O(1) while keeping space reasonable.
    */
    private static class Optimised {
        private int[][] prefixSum;

        public Optimised(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            prefixSum = new int[m + 1][n + 1];

            // Build prefix sum matrix
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    prefixSum[i][j] = matrix[i - 1][j - 1]
                            + prefixSum[i - 1][j]
                            + prefixSum[i][j - 1]
                            - prefixSum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSum[row2 + 1][col2 + 1]
                    - prefixSum[row1][col2 + 1]
                    - prefixSum[row2 + 1][col1]
                    + prefixSum[row1][col1];
        }
    }
}
