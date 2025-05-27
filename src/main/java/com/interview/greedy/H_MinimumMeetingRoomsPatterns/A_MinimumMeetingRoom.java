package com.interview.greedy.H_MinimumMeetingRoomsPatterns;

import java.util.*;

public class A_MinimumMeetingRoom {
        /*
 * https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#
 * https://www.youtube.com/watch?v=dxVcMDI7vyI&list=PLIA-9QRQ0RqHnG0S2GPaNhJEIL3RYqpGR&index=3
 *
 * Category: Medium, Must Do, Fundamental
 * Related: Car pooling exactly : https://www.youtube.com/watch?v=oNRr1WjJgw4
 *
 * Given arrival and departure times of all trains that reach a railway station. Find the minimum number of platforms required for the railway station so that no train is kept waiting.
Consider that all the trains arrive on the same day and leave on the same day. Arrival and departure time can never be the same for a train but we can have arrival time of one train equal to departure time of the other. At any given instance of time, same platform can not be used for both departure of a train and arrival of another train. In such cases, we need different platforms.

This problem is exactly same as meeting room 2 where mimim meeting room asked here minimum platform is asked , in
meeting room == was required here will not because when one train come and same time other train leave also then min two platform are required

Example 1:

Input: n = 6
arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
Output: 3
Explanation:
Minimum 3 platforms are required to
safely arrive and depart all trains.
Example 2:

Input: n = 3
arr[] = {0900, 1100, 1235}
dep[] = {1000, 1200, 1240}
Output: 1
Explanation: Only 1 platform is required to
safely manage the arrival and departure
of all trains.
Your Task:
You don't need to read input or print anything. Your task is to complete the function findPlatform() which takes the array arr[] (denoting the arrival times), array dep[] (denoting the departure times) and the size of the array as inputs and returns the minimum number of platforms required at the railway station such that no train waits.

Note: Time intervals are in the 24-hour format(HHMM) , where the first two characters represent hour (between 00 to 23 ) and the last two characters represent minutes (between 00 to 59).


Expected Time Complexity: O(nLogn)
Expected Auxiliary Space: O(n)


Constraints:
1 ≤ n ≤ 50000
0000 ≤ A[i] ≤ D[i] ≤ 2359
 */

    /*
     * Date 05/01/2017
     *
     * @author Mukesh Kumar Gupta
     * <p>
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     * <p>
     * Both methods have time complexity of nlogn
     * Method 1 has space complexity of O(1)
     * <p>
     * https://leetcode.com/problems/meeting-rooms-ii/
     * https://www.interviewbit.com/problems/meeting-rooms/
     * Reference: https://www.youtube.com/watch?v=xLbXbG6uK-A
     * Category: Must Do, Medium , Fundamental
     * Derived Question:
     * Find all meeting running when given meeting running(Sol: size of priority queue), find all those meeting when only two meeting running (Soln: (size of priority queue comapre and return result)
     * Find meeting when max meeting will be running(Return all those meeting), populate pq in ArrayList when even ever max pq is found,
     *
     * Greedy + Sorting + Heap (Priority Queue)
     *
     * Meeting Rooms II
     *
     * Car Pooling
     *
     * Minimum Platforms Required
     *
     * Maximum Events Attended
     */
    public static class MinimumMeetingRoom {
        /*
         * Without pq, pq only required if you want all running meeting at that time
         */
        public int minMeetingRooms(ArrayList<ArrayList<Integer>> A) {
            int size = A.size();
            int[] startTime = new int[size];
            int[] endTime = new int[size];
            int i = 0;
            for (List<Integer> time : A) {
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
            for (i = 1; i < size; i++) {
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
        //Refer this approach
        public int minMeetingRoomsUsingPQ(ArrayList<ArrayList<Integer>> A) {//Readable code
            Collections.sort(A, (a, b) -> a.get(0) - b.get(0));//sort by start
            PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));//sort fo end tricky think why
            int rooms = 0;
            int size = A.size();
            if (size >= 1) {
                rooms = 1;
                pq.add(A.get(0));//add first element
            }
            for (int i = 1; i < size; i++) {
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

            Interval() {
                start = 0;
                end = 0;
            }

            Interval(int s, int e) {
                start = s;
                end = e;
            }
        }

        public int minMeetingRoomsM2(Interval[] intervals) {
            Arrays.sort(intervals, (a, b) -> a.start - b.start);
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


}
