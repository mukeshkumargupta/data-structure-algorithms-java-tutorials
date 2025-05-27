package com.interview.graph.PartDMinimumSpanningTree;

import java.util.*;
/*
 * Problem: Largest Component Size by Common Factor
 * LeetCode: https://leetcode.com/problems/largest-component-size-by-common-factor/
 *
 * Category: Hard, Google, Must Do
 *
 * Related Problems:
 * https://leetcode.com/problems/groups-of-strings/ Hard
 * https://leetcode.com/problems/distinct-prime-factors-of-product-of-array/ Medium
 *
 * Problem Description:
 * You are given an integer array of unique positive integers nums. Consider the following graph:
 *
 * - There are nums.length nodes, labeled nums[0] to nums[nums.length - 1].
 * - There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
 *
 * Return the size of the largest connected component in the graph.
 *
 * Examples:
 * Example 1:
 * Input: nums = [4, 6, 15, 35]
 * Output: 4
 *
 * Example 2:
 * Input: nums = [20, 50, 9, 63]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [2, 3, 6, 7, 4, 12, 21, 39]
 * Output: 8
 *
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - 1 <= nums[i] <= 10^5
 * - All the values of nums are unique.
 */
public class PartBDLargestComponentSizebyCommonFactor {
    /*
     * Problem Recap:
     * You are given an array nums of unique positive integers.
     *
     * Construct a graph where each node represents a number in nums.
     * An edge exists between two nodes if the corresponding numbers share a common factor > 1.
     *
     * Find the size of the largest connected component.
     *
     * Approach Using Union-Find:
     * Key Insight:
     * Two numbers belong to the same connected component if they share a common prime factor.
     *
     * Steps:
     * 1. For each number, find its prime factors.
     * 2. For each prime factor, union the number with other numbers that share this factor.
     * 3. Track the size of connected components by counting the size of each root set in DSU.
     * 4. Return the largest size.
     *
     * Why Union-Find?
     * Efficiently merges sets representing numbers sharing factors, and quickly queries root parents.
     *
     * Explanation:
     * Union-Find (Disjoint Set) data structure groups nodes that share a common factor.
     * For each number, extract its prime factors.
     * Map each factor to one representative index in the nums array.
     * Union all numbers sharing the same factor.
     * Finally, count the size of connected components by counting elements sharing the same root.
     *
     * Complexity Analysis:
     * Aspect                  | Complexity
     * ------------------------|-------------------------------
     * Time Complexity:        |
     * - Prime factorization   | O(N * sqrt(M)) where M = max(nums[i])
     * - Union-Find operations | Amortized ~ O(α(N)) per operation (α is inverse Ackermann function)
     * - Overall              | O(N * sqrt(M)) due to prime factorization on each number
     *
     * Space Complexity:       |
     * - Union-Find arrays     | O(N)
     * - Factor-to-index map   | O(N) (in worst case unique factors)
     * - Temporary sets for factors | O(sqrt(M)) per number (max factors)
     */
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int u) {
            if (parent[u] != u) parent[u] = find(parent[u]);
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        UnionFind uf = new UnionFind(n);

        // Map from factor to index of nums array
        Map<Integer, Integer> factorToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // Get prime factors of nums[i]
            Set<Integer> factors = getPrimeFactors(num);
            for (int f : factors) {
                if (!factorToIndex.containsKey(f)) {
                    factorToIndex.put(f, i);
                } else {
                    uf.union(i, factorToIndex.get(f));
                }
            }
        }

        // Count the size of each connected component
        int maxSize = 0;
        Map<Integer, Integer> rootCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            rootCount.put(root, rootCount.getOrDefault(root, 0) + 1);
            maxSize = Math.max(maxSize, rootCount.get(root));
        }

        return maxSize;
    }

    // Get prime factors of a number (unique factors)
    private Set<Integer> getPrimeFactors(int num) {
        Set<Integer> factors = new HashSet<>();

        // Check divisibility by 2
        while (num % 2 == 0) {
            factors.add(2);
            num /= 2;
        }

        // Check odd factors up to sqrt(num)
        for (int i = 3; i * i <= num; i += 2) {
            while (num % i == 0) {
                factors.add(i);
                num /= i;
            }
        }

        // If num > 2, it is prime
        if (num > 2) {
            factors.add(num);
        }

        return factors;
    }

    // Example run
    public static void main(String[] args) {
        PartBDLargestComponentSizebyCommonFactor sol = new PartBDLargestComponentSizebyCommonFactor();

        int[] nums1 = {4, 6, 15, 35};
        System.out.println(sol.largestComponentSize(nums1)); // Output: 4

        int[] nums2 = {20, 50, 9, 63};
        System.out.println(sol.largestComponentSize(nums2)); // Output: 2

        int[] nums3 = {2, 3, 6, 7, 4, 12, 21, 39};
        System.out.println(sol.largestComponentSize(nums3)); // Output: 8
    }
    
}
