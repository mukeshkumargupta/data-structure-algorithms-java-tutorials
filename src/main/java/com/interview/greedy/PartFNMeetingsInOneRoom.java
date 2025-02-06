package com.interview.greedy;

import java.util.*;

/*
 * https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
 * https://www.youtube.com/watch?v=II6ziNnub1Q&list=PLIA-9QRQ0RqHnG0S2GPaNhJEIL3RYqpGR&index=2
 *
 * Category: Easy, Must Do
 *
 * There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]),
 * where start[i] is the start time of meeting i and end[i] is the finish time of meeting i.
 *
 * What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can
 * be held in the meeting room at a particular time?
 *
 * Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
 *
 * Example 1:
 * Input: N = 6
 * Output: 4
 * Explanation: Maximum four meetings can be held with given start and end timings.
 *              The meetings are - (1, 2), (3, 4), (5, 7), and (8, 9).
 *
 * Example 2:
 * Input: N = 3
 *        start[] = {10, 12, 20}
 *        end[] = {20, 25, 30}
 * Output: 1
 * Explanation: Only one meeting can be held with the given start and end timings.
 *
 * Your Task:
 * You don't need to read inputs or print anything. Complete the function maxMeetings() that takes two arrays
 * start[] and end[] along with their size N as input parameters and returns the maximum number of meetings
 * that can be held in the meeting room.
 *
 * Expected Time Complexity: O(N*LogN)
 * Expected Auxiliary Space: O(N)
 *
 * Constraints:
 * 1 ≤ N ≤ 10^5
 * 0 ≤ start[i] < end[i] ≤ 10^5
 */
public class PartFNMeetingsInOneRoom {
    class Meeting {
        int start;
        int end;
        int pos;

        Meeting(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }

    // Function to find the maximum number of meetings that can be performed in a meeting room.
    public int maxMeetings(int start[], int end[], int n) {
        List<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < start.length; i++) {
            meetings.add(new Meeting(start[i], end[i], i));
        }
        /*
        Why sorting by end time works:
        The goal is to maximize the number of meetings in the room without overlap. To achieve this:

        Meetings that end earlier leave the room free sooner, allowing more subsequent meetings to be scheduled.
        By sorting by end time, you always prioritize the meetings that leave the room free the earliest.
        This greedy approach ensures that you're optimizing for the most room availability in the future, which leads to the maximum number of non-overlapping meetings.
         */
        // Sort meetings by their end times (Tricky)
        Collections.sort(meetings, (a, b) -> a.end - b.end);

        int result = 0;
        int limit = meetings.get(0).end; // The end time of the last selected meeting
        result++;

        for (int i = 1; i < start.length; i++) {
            if (meetings.get(i).start > limit) {
                limit = meetings.get(i).end;
                result++;
            }
        }

        return result;
    }


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
