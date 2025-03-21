package com.interview.binarysearch.PartAAllPatterns;

public class AllPatternQuestions {
/*
 * Striver DSA Sheet
 * https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2
 *
 * Ultimate List of Unique Binary Search Patterns
 *
 * 1. Classic Binary Search (Finding an Element)
 *    - Search for a target element in a sorted array.
 *    - If found, return the index; otherwise, return -1.
 *    - Time Complexity: O(log N)
 *    - Example Problems:
 *      - Leetcode 704 - Binary Search
 *      - Leetcode 374 - Guess Number Higher or Lower
 *
 * 2. Binary Search on Answer (Search Space Reduction)
 *    - The answer lies in a range, and we binary search on it instead of an actual array.
 *    - Used when the function is monotonic (non-decreasing or non-increasing).
 *    - Time Complexity: O(log R), where R is the search space.
 *    - Example Problems:
 *      - Leetcode 410 - Split Array Largest Sum
 *      - Leetcode 1552 - Magnetic Force Between Balls
 *      - Leetcode 875 - Koko Eating Bananas
 *
 * 3. Lower Bound Search (First Occurrence)
 *    - Find the smallest index where arr[i] >= target.
 *    - If target is not found, return the insertion position.
 *    - Time Complexity: O(log N)
 *    - Example Problems:
 *      - Leetcode 34 - Find First and Last Position of Element in Sorted Array
 *      - Leetcode 300 - Longest Increasing Subsequence (Using lower_bound)
 *
 * 4. Upper Bound Search (Smallest Greater Element)
 *    - Find the smallest index where arr[i] > target.
 *    - Used for floor/ceil operations and insertion index calculations.
 *    - Time Complexity: O(log N)
 *    - Example Problems:
 *      - Leetcode 852 - Peak Index in a Mountain Array
 *
 * 5. Binary Search on Rotated Sorted Array
 *    - Search in a rotated sorted array efficiently.
 *    - Identify the rotation pivot and search in the correct half.
 *    - Time Complexity: O(log N)
 *    - Example Problems:
 *      - Leetcode 33 - Search in Rotated Sorted Array
 *      - Leetcode 153 - Find Minimum in Rotated Sorted Array
 *
 * 6. Binary Search on Infinite Search Space
 *    - Used when the array length is unknown or unbounded.
 *    - Instead of using arr.length, expand the search space exponentially.
 *    - Time Complexity: O(log N)
 *    - Example Problems:
 *      - Leetcode 702 - Search in a Sorted Infinite Array
 *
 * 7. Binary Search on Floating Point Values
 *    - Used when searching for real numbers instead of integers.
 *    - Stops when the difference is within an acceptable precision (epsilon).
 *    - Time Complexity: O(log(precision))
 *    - Example Problems:
 *      - Leetcode 644 - Maximum Average Subarray II
 *      - Leetcode 1231 - Divide Chocolate
 *
 * 8. Binary Search on Matrices
 *    - Search in a row-wise sorted or fully sorted 2D matrix.
 *    - Convert 2D indices into 1D index space for binary search.
 *    - Time Complexity: O(log (M * N))
 *    - Example Problems:
 *      - Leetcode 74 - Search a 2D Matrix
 *      - Leetcode 240 - Search a 2D Matrix II
 *
 * 9. Binary Search on Longest Increasing Subsequence (LIS)
 *    - Used in dynamic programming problems like Longest Increasing Subsequence.
 *    - Maintain a list and binary search for insertion position.
 *    - Time Complexity: O(N log N)
 *    - Example Problems:
 *      - Leetcode 300 - Longest Increasing Subsequence
 *      - Leetcode 354 - Russian Doll Envelopes
 *
 * 10. Binary Search on Graph Edge Weight
 *     - Used in graph problems to find the minimum/maximum cost path.
 *     - Time Complexity: O(log W * (N + E)), where W is the range of edge weights.
 *     - Example Problems:
 *       - Leetcode 1631 - Path With Minimum Effort
 *       - Leetcode 778 - Swim in Rising Water
 *
 * 11. Binary Search on Probability / Ratios / Continuous Functions
 *     - Used for problems involving ratios, probabilities, or continuous functions.
 *     - Time Complexity: O(log precision)
 *     - Example Problems:
 *       - Leetcode 644 - Maximum Average Subarray II
 *       - Leetcode 1231 - Divide Chocolate
 *
 * 12. Binary Search on Intervals
 *     - Used when dealing with overlapping intervals and trying to minimize/maximize a condition.
 *     - Can be combined with sorting + binary search.
 *     - Time Complexity: O(N log N)
 *     - Example Problems:
 *       - Leetcode 436 - Find Right Interval
 *       - Leetcode 981 - Time Based Key-Value Store
 *
 * 13. Binary Search on Greedy Problems
 *     - Problems where binary search is used to test feasibility of an answer.
 *     - Mostly used in scheduling and resource allocation problems.
 *     - Time Complexity: O(log N * F(N)), where F(N) is the function check.
 *     - Example Problems:
 *       - Leetcode 1283 - Find Smallest Divisor Given a Threshold
 *       - Leetcode 1891 - Cutting Ribbons
 *
 * 14. Binary Search on Strings
 *     - Find the longest common prefix using binary search.
 *     - Find the minimum valid string satisfying a condition.
 *     - Time Complexity: O(N log M), where M is string length.
 *     - Example Problems:
 *       - Leetcode 14 - Longest Common Prefix
 *       - Leetcode 1060 - Missing Element in Sorted Array
 *
 * 15. Binary Search on Games / Game Theory
 *     - Used in competitive games to determine the winning move.
 *     - Often applied in min-max strategy games.
 *     - Time Complexity: O(log N)
 *     - Example Problems:
 *       - Leetcode 375 - Guess Number Higher or Lower II
 *       - Leetcode 1539 - Kth Missing Positive Number
 *
 * 16. Binary Search with Bitwise Manipulation
 *     - Finding a number satisfying a bitwise condition efficiently using binary search.
 *     - Time Complexity: O(log N)
 *     - Example Problems:
 *       - Leetcode 1901 - Find a Peak Element II (Binary Search on Matrix)
 *       - Leetcode 1351 - Count Negative Numbers in a Sorted Matrix
 *
 * 17. Ternary Search (Variant of Binary Search)
 *     - Find the maximum/minimum of a unimodal function.
 *     - Find the peak element in an array.
 *     - Time Complexity: O(log N)
 *     - Example Problems:
 *       - Leetcode 852 - Peak Index in a Mountain Array
 *       - Leetcode 162 - Find Peak Element
 */


}
