package com.interview.greedy;

import java.util.*;

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
public class PartHMinimumMeetingRoomsPatterns {
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

        public int minMeetingRoomsUsingPQ(ArrayList<ArrayList<Integer>> A) {//Readable code
            Collections.sort(A, (a, b) -> a.get(0) - b.get(0));//sort by start
            PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));//sort fo end
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

    public static class MinimumRailwayPlatform {
        //Function to find the minimum number of platforms required at the
        //railway station such that no train waits.
        //This is generic solution, but pq is not required when only minimum platform is asked
        static int findPlatformUsingPQ(int arr[], int dep[], int n) {
            // add your code here

            List<List<Integer>> times = new ArrayList<>();
            int l = arr.length;
            for (int i = 0; i < l; i++) {
                List<Integer> time = new ArrayList<>();
                time.add(arr[i]);
                time.add(dep[i]);
                times.add(time);
            }
            Collections.sort(times, (a, b) -> a.get(0) - b.get(0));//sort by start
            PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));//sort fo end
            int platform = 0;
            int size = times.size();
            if (size >= 1) {
                platform = 1;
                pq.add(times.get(0));//add first element
            }
            for (int i = 1; i < size; i++) {
                while (!pq.isEmpty() && times.get(i).get(0) > pq.peek().get(1)) {//Note: only equal sign change from meeting room 2 problem, if end time in pq is less or exqual to current meeting start time then remove all element from pq
                    pq.remove();
                }
                pq.add(times.get(i));
                if (pq.size() > platform) {
                    platform = pq.size();
                }

            }
            return platform;

        }

        //Without pq, easy to understand as well
        static int findPlatformM1(int arr[], int dep[], int n) {
            // add your code heree
            Arrays.sort(arr);
            Arrays.sort(dep);//Tricky

            int platformNeeded = 1;
            int maxPlatform = 1;
            int l = arr.length;
            if (l >= 1) {
                platformNeeded = 1;
                maxPlatform = 1;
            }
            int j = 0;
            for (int i = 1; i < l; i++) {
                while (arr[i] > dep[j]) {
                    platformNeeded--;
                    j++;
                }

                platformNeeded++; //how u are adding in pq same here just increase

                if (platformNeeded > maxPlatform) {
                    maxPlatform = platformNeeded;
                }

            }
            return maxPlatform;

        }

        //This is not intutive, Ignore it , Function to find the minimum number of platforms required at the
        //railway station such that no train waits.
        //Here priority queue is not required, because min platform is asked but when question is changes like
    /*find peak time and return all those train available on the platform or find those list of train when only two or three etc platform is required then pq is maindator
            then this is optimized version of first method where pq is required but first is generic solution where may dervied question can be solved*/
        static int findPlatformM3(int arr[], int dep[], int n) {
            // add your code here
            Arrays.sort(arr);
            Arrays.sort(dep);//Tricky

            int platformNeeded = 1;
            int maxPlatform = 1;

            int i = 1;
            int j = 0;
            while (i < n && j < n) {
                if (arr[i] <= dep[j]) {
                    platformNeeded++;
                    i++;
                } else {
                    platformNeeded--;
                    j++;
                }
                if (platformNeeded > maxPlatform) {
                    maxPlatform = platformNeeded;
                }

            }
            return maxPlatform;
            //arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
            //dep[] = {0910, 1200, 1120, 1130, 1900, 2000}

        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            int[] arr = {900, 940, 950, 1100, 1500, 1800};
            int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
            System.out.println(findPlatformUsingPQ(arr, dep, arr.length));

        }
    }


    /*
 * https://leetcode.com/problems/car-pooling/
 * Category: Medium, Same as platform problem
 * https://www.youtube.com/watch?v=oNRr1WjJgw4&t=852s
 * Related: https://leetcode.com/problems/meeting-rooms-ii/ Medium Locked
 * Derived: Tell what time, there will be maximum passenger in bus if bus size is unlimited, if bus is able to carry all passenger, same like maximum platform needed
 *
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 */
    public static class CarPooling {
        public boolean carPoolingUsingPQ(int[][] trips, int capacity) {
        /*
         * Runtime: 6 ms, faster than 47.26% of Java online submissions for Car Pooling.
Memory Usage: 45.1 MB, less than 7.78% of Java online submissions for Car Pooling.
         */
            Arrays.sort(trips, (a, b) -> a[1] - b[1]);//sort by start
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);//sort fo end

            int currentCapacity = 0;
            int size = trips.length;
            if (size >= 1) {
                currentCapacity = trips[0][0];
                pq.add(trips[0]);//add first element
            }
            if (currentCapacity > capacity) {
                return false;
            }
            for (int i = 1; i < size; i++) {
                while (!pq.isEmpty() && trips[i][1] >= pq.peek()[2]) {//Note: only equal sign change from meeting room 2 problem, if end time in pq is less or exqual to current meeting start time then remove all element from pq
                    int[] trip = pq.remove();
                    currentCapacity -= trip[0];
                }
                currentCapacity += trips[i][0];
                pq.add(trips[i]);
                if (currentCapacity > capacity) {
                    return false;
                }

            }
            return true;

        }


        //Without using pq
        public boolean carPooling(int[][] trips, int capacity) {
            int l = trips.length;
            int[][] startWithPassenger = new int[l][2];
            int[][] endWithPassenger = new int[l][2];
            for (int i = 0; i < l; i++) {
                startWithPassenger[i][0] = trips[i][1];//start
                startWithPassenger[i][1] = trips[i][0]; //passenger,
                endWithPassenger[i][0] = trips[i][2]; //end,
                endWithPassenger[i][1] = trips[i][0]; //passenger,
            }
            //Note: input is not given in  sorted order
        /*
        [[7,5,6],[6,7,8],[10,1,6]]
16*/
            Arrays.sort(startWithPassenger, (a, b) -> {
                return a[0] - b[0];//sort by end
            });
            Arrays.sort(endWithPassenger, (a, b) -> {
                return a[0] - b[0];//sort by end
            });

            int currentCapacity = 0;
            int size = l;
            if (size >= 1) {
                currentCapacity = startWithPassenger[0][1];
            }
            if (currentCapacity > capacity) {
                return false;
            }
            int endIndex = 0;
            for (int i = 1; i < size; i++) {
                while (startWithPassenger[i][0] >= endWithPassenger[endIndex][0]) {
                    currentCapacity -= endWithPassenger[endIndex][1];
                    endIndex++;
                }
                currentCapacity += startWithPassenger[i][1];
                if (currentCapacity > capacity) {
                    return false;
                }

            }
            return true;

        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }

    }
}
