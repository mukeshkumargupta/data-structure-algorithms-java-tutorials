package com.interview.greedy.K_FractionalKnapsack;

import java.util.Arrays;

/*
 * ✅ Problem: Maximum Units on a Truck
 * 🔗 Link: https://leetcode.com/problems/maximum-units-on-a-truck/
 * 🗂️ Category: Easy, Must Do (Greedy)
 *
 * 📌 Related Problems:
 *
 * - https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/ Medium
 *
 * 📝 Problem Statement:
 * You are assigned to load boxes onto a truck. You are given a 2D array `boxTypes`,
 * where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi].
 *
 * - `numberOfBoxesi` is the number of boxes of type i.
 * - `numberOfUnitsPerBoxi` is the number of units in each box of type i.
 *
 * You are also given an integer `truckSize`, which is the maximum number of boxes
 * the truck can carry. Return the **maximum total number of units** that can be
 * loaded onto the truck.
 *
 * 📘 Example 1:
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation:
 *   - Take 1 box of type 1 (3 units)
 *   - Take 2 boxes of type 2 (2 * 2 = 4 units)
 *   - Take 1 box of type 3 (1 unit)
 *   => Total = 3 + 4 + 1 = 8 units
 *
 * 📘 Example 2:
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 91
 *
 * 🔒 Constraints:
 * - 1 <= boxTypes.length <= 1000
 * - 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 * - 1 <= truckSize <= 10⁶
 */
public class B_MaximumUnitsonaTruck {
    public class MaximumUnitsonaTruck {
        public int maximumUnits(int[][] boxTypes, int truckSize) {
        /*
         * Runtime: 15 ms, faster than 31.92% of Java online submissions for Maximum Units on a Truck.
        Memory Usage: 48.2 MB, less than 13.25% of Java online submissions for Maximum Units on a Truck.
         */
            Arrays.sort(boxTypes, (arr1, arr2)-> {
                return arr2[1] - arr1[1];
            });

            int maxUnit = 0;
            for (int[] boxType: boxTypes) {
                if (truckSize < boxType[0]) {
                    maxUnit += truckSize*boxType[1];
                    break;
                } else {
                    maxUnit += boxType[0]*boxType[1];
                    truckSize -= boxType[0];
                }

            }
            return maxUnit;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }

    }
}
