package com.interview.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * https://leetcode.com/problems/buildings-with-an-ocean-view/description/
 * Category: Medium, Facebook, FAANG
 *
 * 🚨 Problem Summary:
 * You are given an array heights[], where heights[i] represents the height of the i-th building.
 * A building has an ocean view if all buildings to its right are shorter than it.
 *
 * You need to return the list of indices of buildings that have an ocean view, in increasing order.
 */
public class BuildingsWithanOceanView {

    /*
     * 🧠 Brute Force Approach
     *
     * 🔍 Idea:
     * For each building, check all buildings to its right.
     * If none of them are taller or equal, it has an ocean view.
     *
     * 🧮 Time Complexity:
     * O(n²), where n is the number of buildings.
     */
    private static class BruitForce {
        public int[] findBuildings(int[] heights) {
            List<Integer> result = new ArrayList<>();
            int n = heights.length;

            for (int i = 0; i < n; i++) {
                boolean hasView = true;
                for (int j = i + 1; j < n; j++) {
                    if (heights[j] >= heights[i]) {
                        hasView = false;
                        break;
                    }
                }
                if (hasView) {
                    result.add(i);
                }
            }

            // Convert list to array
            return result.stream().mapToInt(i -> i).toArray();
        }
    }


    /*
     * ⚡ Optimized Approach (Right to Left Traversal)
     *
     * 🔍 Idea:
     * Traverse from the end (right side, where the ocean is).
     * Keep track of the maxHeight seen so far.
     * If the current building is taller than maxHeight, it has an ocean view.
     *
     * 🧮 Time Complexity:
     * O(n) time, O(n) space (for result list).
     *
     * 🧪 Example:
     * Input: heights = [4, 2, 3, 1]
     * Output: [0, 2, 3]
     *
     * Explanation:
     * - Building 0 (height 4) is taller than all to its right.
     * - Building 2 (height 3) is taller than building 3 (height 1).
     * - Building 3 (height 1) has no buildings to the right.
     */
    private static class Optimized {
        public int[] findBuildings(int[] heights) {
            List<Integer> result = new ArrayList<>();
            int maxHeight = 0;
            int n = heights.length;

            // Start from the rightmost building
            for (int i = n - 1; i >= 0; i--) {
                if (heights[i] > maxHeight) {
                    result.add(i);
                    maxHeight = heights[i];
                }
            }

            // Result is in reverse order, so reverse it
            Collections.reverse(result);

            return result.stream().mapToInt(i -> i).toArray();
        }
    }
}
