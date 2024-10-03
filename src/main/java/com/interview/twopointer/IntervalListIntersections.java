package com.interview.twopointer;

import java.util.*;

/*
 * Problem: Interval List Intersections
 * Link: https://leetcode.com/problems/interval-list-intersections/
 *
 * Category: Medium, Must Do, Top 150
 * Video Explanation: https://www.youtube.com/watch?v=Qh8ZjL1RpLI
 *
 * Related Problems:
 * - Merge Intervals: https://leetcode.com/problems/merge-intervals/ (Medium)
 * - Employee Free Time: https://leetcode.com/problems/employee-free-time/ (Hard, Locked, Try Good Question)
 *
 * Description:
 * You are given two lists of closed intervals, firstList and secondList, where:
 * - firstList[i] = [starti, endi]
 * - secondList[j] = [startj, endj]
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that are either empty
 * or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Examples:
 *
 * Example 1:
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]],
 *        secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Example 2:
 * Input: firstList = [[1,3],[5,9]],
 *        secondList = []
 * Output: []
 *
 * Example 3:
 * Input: firstList = [],
 *        secondList = [[4,8],[10,12]]
 * Output: []
 *
 * Example 4:
 * Input: firstList = [[1,7]],
 *        secondList = [[3,10]]
 * Output: [[3,7]]
 *
 * Constraints:
 * - 0 <= firstList.length, secondList.length <= 1000
 * - firstList.length + secondList.length >= 1
 * - 0 <= starti < endi <= 10^9
 * - endi < starti + 1
 * - 0 <= startj < endj <= 10^9
 * - endj < startj + 1
 */
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> intersections = new ArrayList<>();
        int i = 0, j = 0;

        while (i < firstList.length && j < secondList.length) {
            // Find the intersection
            int start1 = firstList[i][0], end1 = firstList[i][1];
            int start2 = secondList[j][0], end2 = secondList[j][1];

            // Check for intersection
            if (start1 <= end2 && start2 <= end1) {
                intersections.add(new int[]{Math.max(start1, start2), Math.min(end1, end2)});
            }

            // Move the pointer that ends first
            if (end1 < end2) {
                i++;
            } else {
                j++;
            }
        }

        // Convert the list to an array and return
        //return intersections.toArray(new int[intersections.size()][]);
        int[][] result = new int[intersections.size()][2];
        i = 0;
        for (int[] intersection: intersections) {
            result[i][0] = intersection[0];
            result[i][1] = intersection[1];
            i++;
        }
        return result;

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
