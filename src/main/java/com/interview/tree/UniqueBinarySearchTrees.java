package com.interview.tree;

import java.util.Arrays;

/*
 * Reference:
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Category: Medium, Tricky
 *
 * Video Explanation:
 * https://www.youtube.com/watch?v=YDf982Lb84o
 *
 * Problem Statement:
 * Given an integer `n`, return the number of structurally unique BSTs (binary search trees)
 * that can be formed using `n` unique values from `1` to `n`.
 *
 * Example 1:
 * ----------
 * Input:  n = 3
 * Output: 5
 *
 * Example 2:
 * ----------
 * Input:  n = 1
 * Output: 1
 *
 * Constraints:
 * ------------
 * 1 <= n <= 19
 */
public class UniqueBinarySearchTrees {

    /*
     * 💡 Intuition:
     * The number of unique BSTs can be determined by considering each number `i` as the root
     * and counting the number of BSTs that can be formed by placing nodes in the left and right subtrees.
     *
     * 🔹 For each `i` in [1, n]:
     *   - The left subtree consists of numbers [1, i-1] (size `i-1`).
     *   - The right subtree consists of numbers [i+1, n] (size `n-i`).
     *   - The total number of BSTs with `i` as the root is the product of the BSTs possible
     *     in its left and right subtrees.
     *
     * 📌 Recurrence Relation:
     *   G(n) = Σ (G(i - 1) * G(n - i))  for i = 1 to n
     *   where:
     *   - `G(n)` represents the number of unique BSTs for `n` nodes.
     *   - `G(i-1)` represents BSTs formed by the left subtree.
     *   - `G(n-i)` represents BSTs formed by the right subtree.
     *
     * 🕒 Time Complexity: O(2^N) → Exponential, since each call makes multiple recursive calls.
     * 🛠️ Space Complexity: O(N) → Due to recursion stack.
     */
    private static class Bruitforce {
        public int numTrees(int n) {
            if (n <= 1) return 1; // Base case: 1 node or empty tree has 1 BST

            int totalTrees = 0;
            for (int i = 1; i <= n; i++) {
                totalTrees += numTrees(i - 1) * numTrees(n - i);
            }

            return totalTrees;
        }
    }

    /*
     * ✅ Optimized Approach (Memoization - Top-Down DP)
     * Instead of recalculating values multiple times, we store intermediate results in a memoization array.
     *
     * 🔹 Define dp[i] as the number of unique BSTs that can be formed with i nodes.
     * 🔹 Compute dp[i] using smaller subproblems and store the results to avoid redundant computations.
     *
     * 🕒 Time Complexity: O(N^2) - Each subproblem is solved once.
     * 🛠️ Space Complexity: O(N) - For recursion stack and memoization array.
     */
    private static class Better {
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            return countTrees(n, dp);
        }

        private int countTrees(int n, int[] dp) {
            if (n <= 1) return 1;
            if (dp[n] != -1) return dp[n];

            int totalTrees = 0;
            for (int i = 1; i <= n; i++) {
                totalTrees += countTrees(i - 1, dp) * countTrees(n - i, dp);
            }

            return dp[n] = totalTrees;
        }
    }

    /*
     * ✅ Optimal Approach (Dynamic Programming - Bottom-Up)
     * We can solve this problem iteratively using DP.
     *
     * 🔹 Define dp[i] as the number of unique BSTs that can be formed with i nodes.
     * 🔹 Compute dp[i] using smaller subproblems:
     *     - Select each number as root.
     *     - Left subtree has (root-1) nodes.
     *     - Right subtree has (n-root) nodes.
     *     - Sum over all possible roots.
     *
     * 🕒 Time Complexity: O(N^2) - Nested loop iterating over nodes and roots.
     * 🛠️ Space Complexity: O(N) - Array to store intermediate results.
     */
    private static class Optimal {
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1; // Base case: 1 empty tree
            dp[1] = 1; // Base case: 1 node tree

            for (int nodes = 2; nodes <= n; nodes++) {
                for (int root = 1; root <= nodes; root++) {
                    dp[nodes] += dp[root - 1] * dp[nodes - root];
                }
            }

            return dp[n];
        }
    }

    /*
     * 💎 Most Optimal Approach (Catalan Number Formula)
     * From combinatorics, this problem is based on the Catalan number:
     *
     * G(n) = (2n)! / ((n+1)! * n!)
     *
     * Using direct mathematical computation instead of DP:
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * 🔥 Summary of Approaches
     *
     * | Approach                    | Time Complexity | Space Complexity | Notes                                      |
     * |-----------------------------|----------------|------------------|--------------------------------------------|
     * | Brute Force (Recursion)     | O(2^N)         | O(N)             | Exponential recursion                      |
     * | Memoization (Top-Down DP)   | O(N^2)         | O(N)             | Stores results to avoid redundant calculations |
     * | Bottom-Up DP                | O(N^2)         | O(N)             | Iterative approach using a DP table       |
     * | Catalan Formula             | O(N)           | O(1)             | Uses direct mathematical formula          |
     *
     * 🔷 Which Approach to Use?
     * - For small n (≤10) → Recursive approaches are okay.
     * - For moderate n (10-19) → Use Memoization or DP.
     * - For large n (≥19, max constraint) → Use Catalan formula.
     */
    private static class MostOptimalApproach {
        public int numTrees(int n) {
            long C = 1;
            for (int i = 0; i < n; i++) {
                C = C * 2 * (2 * i + 1) / (i + 2);
            }
            return (int) C;
        }
    }
    
}
