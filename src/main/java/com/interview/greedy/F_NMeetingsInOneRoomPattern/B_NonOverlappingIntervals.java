package com.interview.greedy.F_NMeetingsInOneRoomPattern;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/non-overlapping-intervals/
 *
 * Category: Medium, Tricky, Facebook
 *
 * Given an array of intervals where intervals[i] = [starti, endi], return the minimum number of intervals
 * you need to remove to make the rest of the intervals non-overlapping.
 *
 * Related: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/ (Medium)
 *
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 *
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any intervals since they're already non-overlapping.
 *
 * Constraints:
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 */
public class B_NonOverlappingIntervals {
    public static class NonOverlappingIntervals {
        public int eraseOverlapIntervals(int[][] intervals) {
            // Base case: if there are no intervals, no removal is needed
            if (intervals.length == 0) {
                return 0;
            }

            // Step 1: Sort intervals by their end times
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

            // Step 2: Initialize variables
            int nonOverlappingCount = 1; // Count of non-overlapping intervals
            int lastEnd = intervals[0][1]; // End time of the last non-overlapping interval

            // Step 3: Iterate through intervals
            for (int i = 1; i < intervals.length; i++) {
                // If the current interval starts after or when the last interval ends, it's non-overlapping
                if (intervals[i][0] >= lastEnd) {
                    nonOverlappingCount++;
                    lastEnd = intervals[i][1]; // Update the end time of the last non-overlapping interval
                }
            }

            // Step 4: Calculate and return the number of intervals to remove
            return intervals.length - nonOverlappingCount;
        }

        public void main(String[] args) {
            NonOverlappingIntervals solution = new NonOverlappingIntervals();

            // Test cases
            int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
            System.out.println(solution.eraseOverlapIntervals(intervals1)); // Output: 1

            int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
            System.out.println(solution.eraseOverlapIntervals(intervals2)); // Output: 2

            int[][] intervals3 = {{1, 2}, {2, 3}};
            System.out.println(solution.eraseOverlapIntervals(intervals3)); // Output: 0
        }
    }
}
