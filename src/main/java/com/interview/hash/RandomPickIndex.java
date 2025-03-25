package com.interview.hash;

import java.util.*;

/*
https://leetcode.com/problems/random-pick-index/description/
Category: Medium, Facebook, FAANG, Tricky, Reservoir Sampling,
https://www.youtube.com/watch?v=paCJBO-yi9Q&t=127s
Related:
https://leetcode.com/problem-list/reservoir-sampling/
Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the array nums.
int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.


Example 1:

Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]

Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.


Constraints:

1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
target is an integer from nums.
At most 104 calls will be made to pick.
 */
public class RandomPickIndex {
    /*
        🔴 Approach 1: Brute Force (Storing Indices in a List)
    💡 Idea:
    Store all indices of target in an array.
    When a query comes, randomly pick an index from the stored list.

    🔹 Steps:
    1. Preprocess: Traverse nums and store all indices of each value in a HashMap.
    2. Query: Randomly pick an index from the stored list.

    🔵 Time Complexity:
       - Preprocessing: O(n) (Iterate once to store indices)
       - Pick index: O(1) (Random access in a list)
       - Total: O(n) + O(1)

    🔵 Space Complexity:
       - O(n), since we store all indices.

    ✅ Why This is Suboptimal?
       - Extra space required to store indices.
       - Not ideal for large nums where memory matters.
    */
    class BruitForce {
        private Map<Integer, List<Integer>> indexMap;
        private Random rand;

        public BruitForce(int[] nums) {
            indexMap = new HashMap<>();
            rand = new Random();

            for (int i = 0; i < nums.length; i++) {
                indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> indices = indexMap.get(target);
            return indices.get(rand.nextInt(indices.size())); // Randomly select index
        }
    }


    /*
    🟡 Approach 2: Better (Single Pass with Reservoir Sampling)

    💡 Idea:
    Instead of storing all indices, use reservoir sampling to randomly select an index on the fly.
    Ensures each target index has an equal probability of being chosen.

    🔹 Steps:
    1. Initialize `count = 0` and `chosenIndex = -1`.
    2. Iterate through `nums`:
       - If `nums[i] == target`, increase `count`.
       - Select `i` with probability `1/count` (Reservoir Sampling).

    🔵 Time Complexity:
       - Preprocessing: O(1) (No extra storage needed)
       - Pick index: O(n) (Single pass for each query)

    🔵 Space Complexity:
       - O(1), since no extra storage is used.

    ✅ Why This is Better?
       - Uses **constant space** instead of O(n).
       - **Efficient** for large datasets.

    🚀 Code (Optimized Reservoir Sampling)
    */
    private static class Optimised {
    private int[] nums;
    private Random rand;

    public Optimised(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int count = 0, chosenIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
                if (rand.nextInt(count) == 0) { // Probability 1/count
                    chosenIndex = i;
                }
            }
        }

        return chosenIndex;
    }
    }
}
