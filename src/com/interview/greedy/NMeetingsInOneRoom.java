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
public class NMeetingsInOneRoom {
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
            return a.end - b.end;
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
    
};
