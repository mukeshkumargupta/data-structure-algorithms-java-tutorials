package com.interview.greedy.F_NMeetingsInOneRoomPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class A_NMeetingsInOneRoom {
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
        int endingTime = meetings.get(0).end; // The end time of the last selected meeting
        result++;

        for (int i = 1; i < start.length; i++) {
            if (meetings.get(i).start > endingTime) {
                endingTime = meetings.get(i).end;
                result++;
            }
        }

        return result;
    }
}
