package com.interview.twopointer;

/*
 * https://leetcode.com/problems/container-with-most-water/
 * https://www.youtube.com/watch?v=2DjZ8AHcofE
 * Category: Medium, Tricky, Google, Top150
 * Related: https://leetcode.com/problems/trapping-rain-water/ Hard
 *
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i are at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis, form a container such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 *              In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * Example 3:
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 * Example 4:
 * Input: height = [1,2,1]
 * Output: 2
 *
 * Constraints:
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 */

public class ContainerWithMostWater {
    /*
     * Approach: Two Pointer Technique
     *
     * Why Two Pointers?
     * A brute-force approach involves checking all pairs of lines, which results in O(n²) time complexity.
     * Two pointers reduce the time complexity to O(n) by efficiently narrowing down the search space.
     *
     * Algorithm:
     * 1. Initialize Pointers: Place one pointer at the left end (`left`) and the other at the right end (`right`) of the array.
     * 2. Compute Area: The amount of water the container can hold is determined by the shorter line among the two pointers,
     *    multiplied by the distance between them:
     *    area = min(height[left], height[right]) * (right - left)
     * 3. Move the Pointer:
     *    Since the water is limited by the shorter line, move the pointer pointing to the shorter line inward to potentially
     *    find a taller line and maximize the area.
     * 4. Repeat Until the Pointers Meet: Keep track of the maximum area seen so far.
     */

    public int maxArea(int[] height) {
        // Initialize two pointers
        int left = 0, right = height.length - 1;
        int maxArea = 0; // To store the maximum area found

        // Iterate while the two pointers do not meet
        while (left < right) {
            // Calculate the current area
            int currentArea = Math.min(height[left], height[right]) * (right - left);

            // Update the maximum area if the current one is larger
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer pointing to the shorter line inward
            if (height[left] < height[right]) {
                left++; // Move the left pointer to the right
            } else {
                right--; // Move the right pointer to the left
            }
        }

        // Return the maximum area found
        return maxArea;
    }

    public static void main(String[] args) {
        // Example usage
        ContainerWithMostWater solver = new ContainerWithMostWater();

        // Test case 1
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Area (Test Case 1): " + solver.maxArea(height1)); // Output: 49

        // Test case 2
        int[] height2 = {1, 1};
        System.out.println("Max Area (Test Case 2): " + solver.maxArea(height2)); // Output: 1

        // Test case 3
        int[] height3 = {4, 3, 2, 1, 4};
        System.out.println("Max Area (Test Case 3): " + solver.maxArea(height3)); // Output: 16

        // Test case 4
        int[] height4 = {1, 2, 1};
        System.out.println("Max Area (Test Case 4): " + solver.maxArea(height4)); // Output: 2
    }
}
