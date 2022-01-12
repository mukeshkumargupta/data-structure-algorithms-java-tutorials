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
 * https://www.interviewbit.com/problems/meeting-rooms/
 * Reference: https://www.youtube.com/watch?v=xLbXbG6uK-A
 * Category: Must Do, Medium
 * Derived Question:
 * Find all meeting running when given meeting running(Sol: size of priority queue), find all those meeting when only two meeting running (Soln: (size of priority queue comapre and return result)
 * Find meeting when max meeting will be running(Return all those meeting), populate pq in ArrayList when even ever max pq is found, 
 */
public class MeetingRooms {
    /*
     * Without pq, pq only required if you want all running meeting at that time
     */
    public int minMeetingRooms(ArrayList<ArrayList<Integer>> A) {
        int size = A.size();
        int[] startTime = new int[size];
        int[] endTime = new int [size];
        int i = 0;
        for (List<Integer> time: A) {
            startTime[i] = time.get(0);
            endTime[i] = time.get(1);
            i++;
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int minRoom = 0;
        int currentRoom = 0;
        if (size >= 1) {
            minRoom = 1;
            currentRoom = 1;
        }
        int j = 0;
        for (i =1; i < size; i++) {
            while (j < size && startTime[i] >= endTime[j]) {
                currentRoom--;
                j++;
            }
            currentRoom++;
            if (currentRoom > minRoom) {
                minRoom = currentRoom;
            }

        }
        return minRoom;
    }
    
    public int minMeetingRooms(ArrayList<ArrayList<Integer>> A) {
        Collections.sort(A, (a, b) ->  a.get(0) - b.get(0));//sort by start
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));//sort fo end
        int rooms = 0;
        int size = A.size();
        if (size >= 1) {
            rooms = 1;
            pq.add(A.get(0));//add first element
        }
        for (int i =1; i < size; i++) {
            while (!pq.isEmpty() && A.get(i).get(0) >= pq.peek().get(1)) {//if end time in pq is less or exqual to current meeting start time then remove all element from pq
                pq.remove();
            }
            pq.add(A.get(i));
            if (pq.size() > rooms) {
                rooms = pq.size();
            }

        }
        return rooms;
    }
    

    
    public static class Interval {
          int start;
          int end;
          Interval() { start = 0; end = 0; }
          Interval(int s, int e) { start = s; end = e; }
    }
    
    public int minMeetingRoomsM2(Interval[] intervals) {
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
