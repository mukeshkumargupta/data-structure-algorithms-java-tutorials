package com.interview.array;

import java.util.Arrays;
import java.util.HashSet;

/*
 * Problem: Find the Duplicate Number
 * LeetCode: https://leetcode.com/problems/find-the-duplicate-number/
 * Video Explanation: https://www.youtube.com/watch?v=32Ll35mhWg0
 * Category: Medium, Tricky, FAANG
 * Related Topic: Cycle Detection in Array
 *
 * Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive.
 *
 * There is only one repeated number in `nums`, return this repeated number.
 *
 * Constraints:
 * - 1 <= n <= 10⁵
 * - nums.length == n + 1
 * - 1 <= nums[i] <= n
 * - All integers in `nums` appear only once except for precisely one integer which appears two or more times.
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Follow-up:
 * - How can we prove that at least one duplicate number must exist in `nums`?
 * - Can you solve the problem in linear runtime complexity?
 *
 * Related Problems:
 * | Problem                                    | Difficulty | Concept                     |
 * |--------------------------------------------|------------|-----------------------------|
 * | 442. Find All Duplicates in an Array       | Medium     | Uses index marking         |
 * | 41. First Missing Positive                 | Hard       | Cyclic sort technique      |
 * | 268. Missing Number                        | Easy       | XOR trick                  |
 * | 645. Set Mismatch                          | Easy       | Similar sign marking approach |
 *
 * Final Summary:
 * | Approach                   | Time Complexity | Space Complexity | Modifies Input? |
 * |----------------------------|----------------|------------------|----------------|
 * | Brute Force                | O(n²)          | O(1)             | No             |
 * | Sorting                    | O(n log n)     | O(1)             | Yes            |
 * | HashSet                    | O(n)           | O(n)             | No             |
 * | Index Marking              | O(n)           | O(1)             | Yes            |
 * | ✅ Floyd’s Cycle Detection | O(n)           | O(1)             | No             |
 *
 * Best Approach: 🏆 Floyd’s Cycle Detection Algorithm (O(n) time, O(1) space, no modifications)
 */
public class FindtheDuplicateNumber {
    /*
    Approaches with Time Complexity
1️⃣ Brute Force - Nested Loops
Idea:
Compare each element with every other element to check for duplicates.

Time Complexity:
O(n²) → Due to nested loops comparing every pair.
Space Complexity:
O(1) → No extra space used.
     */
    private static class Bruitforce {
        public int findDuplicate(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (nums[i] == nums[j]) {
                        return nums[i];
                    }
                }
            }
            return -1; // This case won't happen as per problem constraints
        }
    }

    /*
    Sorting Approach
Idea:
Sort the array and check adjacent elements for duplicates.
Time Complexity:
O(n log n) → Due to sorting.
Space Complexity:
O(1) → Sorting is in-place (if allowed).
     */

    private static class Better {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums); // O(n log n)
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return nums[i];
                }
            }
            return -1;
        }
    }

    /*
    Using HashSet (Extra Space)
Idea:
Store elements in a HashSet and check for duplicates.
Time Complexity:
O(n) → Single pass through array.
Space Complexity:
O(n) → Extra space for HashSet.
     */
    private static class Better2 {
        public int findDuplicate(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    return num;
                }
                set.add(num);
            }
            return -1;
        }
    }

    /*
    Index Marking Approach (Modifying Input)
Idea:
Treat array indices as markers using the sign flipping trick.
Since numbers are in [1, n], use index (abs(nums[i]) - 1) to track visits.
Time Complexity:
O(n) → Single pass through array.
Space Complexity:
O(1) → No extra space (modifies input).
Limitation:
Modifies input, which may not be allowed.
     */
    private static class OptimalButNotAllowedDueToModification {
        public int findDuplicate(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int idx = Math.abs(nums[i]) - 1;
                if (nums[idx] < 0) return Math.abs(nums[i]); // Found duplicate
                nums[idx] *= -1; // Mark index as visited
            }
            return -1;
        }
    }

    /*
     * Floyd’s Cycle Detection Algorithm (Optimal, O(1) Space)
     *
     * Idea:
     * - Treat the array as a linked list where nums[i] is the next pointer.
     * - A cycle must exist because one number appears twice.
     * - Use Floyd's cycle detection (Tortoise and Hare method) to find the duplicate.
     *
     * Time Complexity:
     * - O(n) → Slow and fast pointers traverse the cycle at most twice.
     *
     * Space Complexity:
     * - O(1) → No extra space used.
     *
     * Why is this optimal?
     * - Doesn't modify input.
     * - Runs in linear time with constant space.
     *
     * Step 1: Finding the Intersection Point
     * - Initialize slow and fast pointers:
     *   slow = nums[0] = 1
     *   fast = nums[0] = 1
     * - Move slow by 1 step & fast by 2 steps until they meet:
     *
     *   Step | Slow Pointer (nums[slow]) | Fast Pointer (nums[nums[fast]])
     *   ---------------------------------------------------------------
     *    1   | slow = nums[1] = 3        | fast = nums[nums[1]] = nums[3] = 2
     *    2   | slow = nums[3] = 2        | fast = nums[nums[3]] = nums[2] = 4
     *    3   | slow = nums[2] = 4        | fast = nums[nums[2]] = nums[4] = 2
     *    4   | slow = nums[4] = 2        | fast = nums[nums[4]] = nums[2] = 4
     *
     * - At step 4, both slow and fast meet at index 2 (value 4), confirming the cycle.
     *
     * Step 2: Finding the Entry Point of the Cycle
     * - Reset slow to the beginning (nums[0]), keep fast at intersection:
     *   slow = nums[0] = 1
     *   fast = 4 (from previous step)
     * - Move both slow and fast one step at a time until they meet:
     *
     *   Step | Slow Pointer (nums[slow]) | Fast Pointer (nums[fast])
     *   -----------------------------------------------------------
     *    1   | slow = nums[1] = 3        | fast = nums[4] = 2
     *    2   | slow = nums[3] = 2        | fast = nums[2] = 4
     *    3   | slow = nums[2] = 4        | fast = nums[4] = 2
     *    4   | slow = nums[4] = 2        | fast = nums[2] = 4
     *
     * - They meet at index 4 (value 2), which is the duplicate number.
     *
     * Final Answer: 2
     *
     * Key Observations:
     * - The slow pointer moves 1 step at a time, while the fast pointer moves 2 steps.
     * - The first meeting point proves a cycle exists.
     * - Resetting slow to nums[0] and moving both pointers at the same speed finds the duplicate.
     *
     * Time & Space Complexity:
     * ✅ Time Complexity: O(n) (Two pointer traversals)
     * ✅ Space Complexity: O(1) (No extra space used)
     *
     * This method is optimal as it does not modify the array and uses constant space.
     */
    private static class Optimal {
        public int findDuplicate(int[] nums) {
            // Step 1: Initialize slow and fast pointers
            int slow = nums[0];  // Moves one step at a time
            int fast = nums[nums[0]];  // Moves two steps at a time

            // Step 2: Detect cycle using Floyd's Tortoise and Hare algorithm
            while (slow != fast) {
                slow = nums[slow];        // Move slow one step
                fast = nums[nums[fast]];  // Move fast two steps
            }

            // Step 3: Find the cycle start (duplicate number)
            fast = 0; // Reset fast pointer to start
            while (slow != fast) {
                slow = nums[slow]; // Move both one step at a time
                fast = nums[fast];
            }

            // Step 4: Return the duplicate number
            return slow;
        }
    }
}
