package com.interview.greedy;

import java.util.*;

/*
 * https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1
 * https://www.youtube.com/watch?v=II6ziNnub1Q&list=PLIA-9QRQ0RqHnG0S2GPaNhJEIL3RYqpGR&index=2
 * Category: Easy, Must Do
 * There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] is start time of meeting i and end[i] is finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time?

Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.


Example 1:

Input:
N = 6



Output: 
4
Explanation:
Maximum four meetings can be held with
given start and end timings.
The meetings are - (1, 2),(3, 4), (5,7) and (8,9)
Example 2:

Input:
N = 3
start[] = {10, 12, 20}
end[] = {20, 25, 30}
Output: 
1
Explanation:
Only one meetings can be held
with given start and end timings.

Your Task :
You don't need to read inputs or print anything. Complete the function maxMeetings() that takes two arrays start[] and end[] along with their size N as input parameters and returns the maximum number of meetings that can be held in the meeting room.


Expected Time Complexity : O(N*LogN)
Expected Auxilliary Space : O(N)


Constraints:
1 ≤ N ≤ 105
0 ≤ start[i] < end[i] ≤ 105
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
    };
    
    // Function to find the maximum number of meetings that can
    // be performed in a meeting room.
    public int maxMeetings(int start[], int end[], int n) {
        // Your code here
        List<Meeting> meet = new ArrayList<>();
        
        for (int i = 0; i < start.length; i++) {
            meet.add(new Meeting(start[i], end[i], i));
        }
        
        Collections.sort(meet, (a, b) -> {
            return a.end - b.end;//Tricky
        });
        int result = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(meet.get(0).pos);
        result++;
        int limit = meet.get(0).end;//Tricky
        
        for (int i = 1; i < start.length; i++) {
            if (meet.get(i).start > limit) {
                limit = meet.get(i).end;
                answer.add(meet.get(i).pos);
                result++;
            }
        }
        // To print meeting details
        /*
         * for(int i = 0;i<answer.size(); i++) { System.out.print(answer.get(i) +1 +
         * " "); }
         */
        return result;
    }

    /*
 * https://leetcode.com/problems/non-overlapping-intervals/
 * Category:
 * Medium, Tricky, Facebook
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 Related: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/ Medium


Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
    public static class NonoverlappingIntervals {
        public int eraseOverlapIntervals(int[][] intervals) {
        /*
         * Runtime: 47 ms, faster than 77.98% of Java online submissions for Non-overlapping Intervals.
Memory Usage: 96.7 MB, less than 58.48% of Java online submissions for Non-overlapping Intervals.
         */
            Arrays.sort(intervals, (int[] interval1, int[] interval2) -> {
                return interval1[1] - interval2[1];

            });
            int end = intervals[0][1];
            int R = intervals.length;
            int removalCount = 0;
            for (int i = 1; i < R; i++) {
                if (intervals[i][0] < end) {
                    removalCount++;
                    continue;
                }
                end = intervals[i][1];

            }
            return removalCount;
        }


        public int eraseOverlapIntervalsM2(int[][] intervals) {
        /*
         * Runtime: 130 ms, faster than 7.84% of Java online submissions for Non-overlapping Intervals.
Memory Usage: 105.8 MB, less than 46.66% of Java online submissions for Non-overlapping Intervals.
Note: Find better solution
         */
            int intervalCount = intervals.length;

            if (intervalCount <= 1) {
                return 0;
            }

            Arrays.sort(intervals, (aar1, arr2)-> {
                //sort by start time
                //sort by end time also work but not tried here
                return aar1[0] - arr2[0];
            });

            int last_included = 0;
            int count = 0;
            for (int i = 1; i < intervalCount; i++) {

                if (intervals[i][0] < intervals[last_included][1]) {//overlap, current interval start less than last included then overlap
                    count++;
                    //if partial overlap then include last included if complete then take smaller one (Tricky)
                    //Summary: update last included if current interval is smaller
                    if (intervals[i][1] < intervals[last_included][1]) {
                        last_included = i;
                    }

                } else {//non-overlap
                    last_included = i;

                }

            }
            return count;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }

    }
    
};
