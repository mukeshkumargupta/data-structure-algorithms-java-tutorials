package com.interview.array;

import java.util.*;
/*
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * Category: Medium, Tricky, FAANG
 *
 * Given an integer array nums of length n where all the integers of nums
 * are in the range [1, n] and each integer appears once or twice,
 * return an array of all the integers that appear twice.
 *
 * You must write an algorithm that runs in O(n) time and uses only constant extra space.
 *
 * Example 1:
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 *
 * Example 2:
 * Input: nums = [1,1,2]
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: []
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 10^5
 * - 1 <= nums[i] <= n
 * - Each element in nums appears once or twice.
 *
 * Runtime: 11 ms, faster than 40.06% of Java online submissions.
 * Memory Usage: 63.1 MB, less than 24.60% of Java online submissions.
 *
 * Next Challenges:
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/ (Easy)
 */
public class FindAllDuplicatesinanArray {
    /*
Brute Force Approach (Using Nested Loops)
Idea
Compare each element with every other element to check if it appears twice.
This is a naive approach using two nested loops.

Complexity Analysis
Time Complexity = O(n²) (Two nested loops)
Space Complexity = O(n) (for storing duplicates)
🔴 Why is this bad?

Too slow for large inputs (n can be large).
Requires extra space to store results.
     */
    private static class BruitForce {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();
            int n = nums.length;

            for (int i = 0; i < n; i++) {
                int count = 0;
                for (int j = 0; j < n; j++) {
                    if (nums[i] == nums[j]) {
                        count++;
                    }
                }
                if (count == 2 && !result.contains(nums[i])) {
                    result.add(nums[i]);
                }
            }

            return result;
        }
    }

    /*
            Better Approach (Using Sorting)
        Idea
        Sort the array.
        Traverse once and check adjacent elements

        Complexity Analysis
Time Complexity = O(n log n) (Due to sorting)
Space Complexity = O(1) (Ignoring result list)
🟡 Why is this better?

Faster than brute force, but sorting takes extra time.
     */

    private static class Better {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();
            Arrays.sort(nums);  // O(n log n)

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    result.add(nums[i]);
                }
            }

            return result;
        }
    }

    /*
     * Optimal Approach (Using Index Marking)
     *
     * 🔹 Key Observations:
     *   - The numbers are in the range [1, n].
     *   - We can use an indexing trick to track visits.
     *
     * 🔹 Steps:
     *   1. Use the index of a number (abs(nums[i]) - 1) as a marker.
     *   2. Flip the number at that index negative to mark it as visited.
     *   3. If a number is already negative, it means we have visited this index before → This is a duplicate.
     *
     * 🔹 Complexity Analysis:
     *   - Time Complexity  = O(n) (Single pass through the array)
     *   - Space Complexity = O(1) (No extra space, modifying input)
     *
     * ✅ Why is this optimal?
     *   - Runs in O(n) time.
     *   - Uses only constant extra space (O(1)) by modifying input.
     *
     * 🔍 Summary of Approaches:
     *   | Approach         | Time Complexity | Space Complexity | Idea                                    |
     *   |-----------------|----------------|------------------|-----------------------------------------|
     *   | Brute Force     | O(n²)          | O(n)             | Compare each pair using two loops      |
     *   | Sorting         | O(n log n)     | O(1)             | Sort and find adjacent duplicates      |
     *   | Index Marking   | O(n)           | O(1)             | Use sign flipping to track visits      |
     *
     * 🔹 Works when numbers are in the range [1, n].
     * 🔹 Similar problems include:
     *   - Finding the missing number
     *   - Finding the first duplicate
     *   - Finding numbers that appear once or twice
     *
     * 🔹 How the Trick Works:
     *   - We use the given array itself as a frequency tracker without extra space.
     *   - Instead of keeping a separate frequency array, we modify the input array by making the values negative.
     *   - When a number is visited, we go to its corresponding index (abs(num) - 1) and flip the number there to negative.
     *   - If we ever see a negative number at that index, it means this number has been visited before → Duplicate found!
     *
     * 📌 Step-by-Step Walkthrough:
     *   Example 1:
     *   Input: nums = [4, 3, 2, 7, 8, 2, 3, 1]
     *
     *   Step 1: Start Iterating and Mark Numbers
     *   | Index | Current Num | Target Index (abs(num) - 1) | Before Marking       | After Marking        |
     *   |-------|------------|----------------------------|----------------------|----------------------|
     *   | 0     | 4          | 3                          | [4, 3, 2, 7, 8, 2, 3, 1]  | [4, 3, 2, -7, 8, 2, 3, 1] |
     *   | 1     | 3          | 2                          | [4, 3, 2, -7, 8, 2, 3, 1] | [4, 3, -2, -7, 8, 2, 3, 1] |
     *   | 2     | -2         | 1                          | [4, 3, -2, -7, 8, 2, 3, 1] | [4, -3, -2, -7, 8, 2, 3, 1] |
     *   | 3     | -7         | 6                          | [4, -3, -2, -7, 8, 2, 3, 1] | [4, -3, -2, -7, 8, 2, -3, 1] |
     *   | 4     | 8          | 7                          | [4, -3, -2, -7, 8, 2, -3, 1] | [4, -3, -2, -7, 8, 2, -3, -1] |
     *   | 5     | 2          | 1                          | [4, -3, -2, -7, 8, 2, -3, -1] | 🚨 Duplicate Found (2)! |
     *   | 6     | -3         | 2                          | [4, -3, -2, -7, 8, 2, -3, -1] | 🚨 Duplicate Found (3)! |
     *   | 7     | -1         | 0                          | [4, -3, -2, -7, 8, 2, -3, -1] | [-4, -3, -2, -7, 8, 2, -3, -1] |
     *
     *   Step 2: Collect the Duplicates
     *   - The numbers that caused a negative number encounter were **2** and **3**, which means they appeared twice.
     *
     *   Final Output: [2, 3]
     *
     * ⏱ Complexity Analysis:
     *   - **Time Complexity:** O(n)
     *     - We traverse the array once (O(n)) to mark numbers.
     *     - No nested loops, no sorting.
     *   - **Space Complexity:** O(1)
     *     - We don’t use extra space, just modify the array.
     */
    private static class Optimal {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                int index = Math.abs(nums[i]) - 1; // Get index based on value

                if (nums[index] < 0) {  // If already marked, it's a duplicate
                    result.add(Math.abs(nums[i]));
                } else {
                    nums[index] = -nums[index];  // Mark the index as visited
                }
            }

            return result;
        }
    }
    
}
