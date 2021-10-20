package com.interview.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Date 05/01/2017
 * @author Mukesh Kumar Gupta
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Both methods have time complexity of nlogn
 * Method 1 has space complexity of O(1)
 * 
 * https://leetcode.com/problems/meeting-rooms-ii/
 * Reference: https://www.youtube.com/watch?v=xLbXbG6uK-A
 * Category: Must Do
 */
public class MeetingRooms {

    public static class Interval {
          int start;
          int end;
          Interval() { start = 0; end = 0; }
          Interval(int s, int e) { start = s; end = e; }
    }
    
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) ->  a.start - b.start);
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        int rooms = 0;
        for (Interval interval : intervals) {
            if (!pq.isEmpty() && interval.start >= pq.peek().end) {
                pq.poll();
            } else {
                rooms++;
            }
            pq.offer(interval);
        }
        return rooms;
    }
}
