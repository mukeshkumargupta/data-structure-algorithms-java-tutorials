package com.interview.twopointer.mostWaterPattern;
/*
 * References
 * https://leetcode.com/problems/trapping-rain-water/
 * https://www.youtube.com/watch?v=1_5VuquLbXg&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=8
 * Related: https://leetcode.com/problems/trapping-rain-water-ii/ Hard VVImp
 * https://leetcode.com/problems/pour-water/ Medium Locked
 * https://www.youtube.com/watch?v=m18Hntz4go8&t=1s
 * Category: Hard, Must Do, Top150
 * Related: https://leetcode.com/problems/trapping-rain-water-ii/ Hard
 * https://leetcode.com/problems/pour-water/ Medium
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class B_TrappingWater {

    /**
     * **Approach 1: Brute Force (Nested Loops)**
     *
     * **Idea:**
     * For each index `i`, find the maximum height to the left and maximum height to the right.
     * The amount of trapped water at index `i` is calculated as:
     *
     * `water[i] = min(maxLeft, maxRight) - height[i]`
     *
     * **Algorithm:**
     * 1. Iterate through each bar.
     * 2. Find `maxLeft[i]` by scanning all elements to the left.
     * 3. Find `maxRight[i]` by scanning all elements to the right.
     * 4. Compute water trapped at `i` as `min(maxLeft, maxRight) - height[i]`.
     * 5. Sum up all trapped water.
     *
     * **Time Complexity:**
     * - Finding `maxLeft` & `maxRight` for each element: `O(n) + O(n) = O(n)`
     * - Looping through all elements: `O(n)`
     * - **Overall Complexity:** `O(n^2)`
     */
    public static class TrappingWaterBruitforce {
        public int trap(int[] height) {
            int n = height.length;
            int waterTrapped = 0;

            for (int i = 0; i < n; i++) {
                int maxLeft = 0, maxRight = 0;

                // Find max height to the left of index i
                for (int j = 0; j <= i; j++) {
                    maxLeft = Math.max(maxLeft, height[j]);
                }

                // Find max height to the right of index i
                for (int j = i; j < n; j++) {
                    maxRight = Math.max(maxRight, height[j]);
                }

                // Calculate trapped water at index i
                waterTrapped += Math.min(maxLeft, maxRight) - height[i];
            }

            return waterTrapped;
        }


    }

    /**
     * **Approach 2: Better Approach (Using Precomputed Arrays)**
     *
     * **Idea:**
     * Instead of recalculating `maxLeft` and `maxRight` for every index,
     * precompute and store these values in two arrays.
     *
     * **Algorithm:**
     * 1. Precompute `maxLeft[i]`: Maximum height from the left up to `i`.
     * 2. Precompute `maxRight[i]`: Maximum height from the right up to `i`.
     * 3. Compute water trapped using:
     *
     *    `water[i] = min(maxLeft[i], maxRight[i]) - height[i]`
     *
     * **Time Complexity:**
     * - Precomputing `maxLeft[]` and `maxRight[]`: `O(n)`
     * - Calculating trapped water: `O(n)`
     * - **Overall Complexity:** `O(n)`
     *
     * **Space Complexity:**
     * - `O(n)` (due to extra arrays)
     *
     * **Dry Run Table:**
     * ```
     * Index | height[i] | maxLeft[i] | maxRight[i] | min(maxLeft, maxRight) | Water Trapped
     * ----------------------------------------------------------------------------
     *  0    |    0     |     0      |      3      |          0             |      0
     *  1    |    1     |     1      |      3      |          1             |      0
     *  2    |    0     |     1      |      3      |          1             |      1
     *  3    |    2     |     2      |      3      |          2             |      0
     *  4    |    1     |     2      |      3      |          2             |      1
     *  5    |    0     |     2      |      3      |          2             |      2
     *  6    |    1     |     2      |      3      |          2             |      1
     *  7    |    3     |     3      |      3      |          3             |      0
     *  8    |    2     |     3      |      2      |          2             |      0
     *  9    |    1     |     3      |      2      |          2             |      1
     * 10    |    2     |     3      |      2      |          2             |      0
     * 11    |    1     |     3      |      1      |          1             |      0
     * ```
     * **Total Water Trapped = 1 + 1 + 2 + 1 + 1 = 6**
     *
     * ---
     *
     * ```
     */
    public class TrappingRainWaterBetter {
        public int trap(int[] height) {
            int n = height.length;
            if (n == 0) return 0;

            int[] maxLeft = new int[n];
            int[] maxRight = new int[n];

            // Compute maxLeft[]
            maxLeft[0] = height[0];
            for (int i = 1; i < n; i++) {
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
            }

            // Compute maxRight[]
            maxRight[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                maxRight[i] = Math.max(maxRight[i + 1], height[i]);
            }

            // Calculate trapped water
            int waterTrapped = 0;
            for (int i = 0; i < n; i++) {
                waterTrapped += Math.min(maxLeft[i], maxRight[i]) - height[i];
            }

            return waterTrapped;
        }

    }

    /**
     * Approach 3: Optimal (Two-Pointer Approach)
     *
     * **Idea:**
     * Instead of using extra space, use two pointers to track `maxLeft` and `maxRight` dynamically.
     *
     * **Algorithm:**
     * - Use two pointers, `left` and `right`.
     * - Maintain `maxLeft` and `maxRight` to track the max height seen so far.
     * - If `maxLeft < maxRight`, process the left pointer; otherwise, process the right pointer.
     * - Compute trapped water for the current position.
     *
     * **Time Complexity:**
     * - One pass through the array: **O(n)**
     * - Overall Complexity: **O(n)**
     *
     * **Space Complexity:**
     * - No extra arrays used → **O(1)**
     *
     * ---
     *
     * **Comparing Approaches**
     *
     * | Approach           | Time Complexity | Space Complexity | Key Idea |
     * |--------------------|----------------|-----------------|----------------------------------|
     * | **Brute Force**    | O(n²)          | O(1)            | Check left and right max for every element |
     * | **Precomputed Arrays** | O(n)    | O(n)            | Store left and right max in arrays |
     * | **Two-Pointer**    | O(n)           | O(1)            | Maintain `maxLeft` and `maxRight` dynamically |
     *
     * ---
     *
     * **Approach 3: Two-Pointer Method (O(n) Time, O(1) Space)**
     *
     * **Initial Pointers and Variables:**
     * ```
     * left = 0, right = 11
     * maxLeft = 0, maxRight = 0
     * waterTrapped = 0
     * ```
     *
     * **Dry Run Table:**
     *
     * | Step | left | right | height[left] | height[right] | maxLeft | maxRight | Water Trapped |
     * |------|------|-------|--------------|--------------|---------|---------|---------------|
     * |  1   |  0   |  11   |      0       |      1       |    0    |    1    |      0       |
     * |  2   |  1   |  11   |      1       |      1       |    1    |    1    |      0       |
     * |  3   |  2   |  11   |      0       |      1       |    1    |    1    |      1       |
     * |  4   |  3   |  11   |      2       |      1       |    2    |    1    |      0       |
     * |  5   |  3   |  10   |      2       |      2       |    2    |    2    |      0       |
     * |  6   |  3   |   9   |      2       |      1       |    2    |    2    |      1       |
     * |  7   |  3   |   8   |      2       |      2       |    2    |    2    |      0       |
     * |  8   |  3   |   7   |      2       |      3       |    2    |    3    |      0       |
     * |  9   |  4   |   7   |      1       |      3       |    2    |    3    |      1       |
     * | 10   |  5   |   7   |      0       |      3       |    2    |    3    |      2       |
     * | 11   |  6   |   7   |      1       |      3       |    2    |    3    |      1       |
     *
     * **Total Water Trapped = 1 + 1 + 2 + 1 + 1 = 6**
     *
     * ---
     *
     * **Key Takeaways:**
     *
     * | Approach                | Time Complexity | Space Complexity | Observations |
     * |-------------------------|----------------|-----------------|-------------------------------|
     * | **Brute Force (O(n²))**  | O(n²)          | O(1)            | Too slow for large inputs |
     * | **Precomputed Arrays (O(n))** | O(n)  | O(n)            | Faster, but extra space needed |
     * | **Two-Pointer (O(n))**  | O(n)           | O(1)            | Best approach in interviews |
     */
    public class TrappingRainWaterOptimal {
        public int trap(int[] height) {
            int left = 0, right = height.length - 1;
            int maxLeft = 0, maxRight = 0, waterTrapped = 0;

            while (left <= right) {
                if (maxLeft <= maxRight) {
                    if (height[left] >= maxLeft) {
                        maxLeft = height[left];
                    } else {
                        waterTrapped += maxLeft - height[left];
                    }
                    left++;
                } else {
                    if (height[right] >= maxRight) {
                        maxRight = height[right];
                    } else {
                        waterTrapped += maxRight - height[right];
                    }
                    right--;
                }
            }

            return waterTrapped;
        }

    }

}
