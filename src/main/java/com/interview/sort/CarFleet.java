package com.interview.sort;

import java.util.*;
/*
 * https://leetcode.com/problems/car-fleet/
 * https://www.youtube.com/watch?v=P99yS9jLr6o&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=65
 * Category: Medium, Tricky
 * https://leetcode.com/problems/car-fleet-ii/ Hard
 * here are n cars going to the same destination along a one-lane road. The destination is target miles away.

You are given two integer array position and speed, both of length n, where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored (i.e., they are assumed to have the same position).

A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.

Return the number of car fleets that will arrive at the destination.

 

Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation: 
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.
Example 2:

Input: target = 10, position = [3], speed = [3]
Output: 1
 

Constraints:

n == position.length == speed.length
1 <= n <= 105
0 < target <= 106
0 <= position[i] < target
All the values of position are unique.
0 < speed[i] <= 106
Accepted
60,407
Submissions
130,175
 */
public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        /*
         * Runtime: 159 ms, faster than 19.08% of Java online submissions for Car Fleet.
         */
        class Details {
            int pos;
            double time;
            Details(int pos, double time) {
                this.pos = pos;
                this.time = time;
            }
        }
        PriorityQueue<Details> pq = new PriorityQueue<>((d1, d2) -> {
            return d2.pos - d1.pos;
        });
        for (int i = 0; i < position.length; i++) {
            double t = (double)(target - position[i])/(double)(speed[i]);
            Details d = new Details(position[i], t);
            pq.add(d);
        }
        int fleet = 0;
        
        if (pq.size() == 0) {
            return fleet;
        }
        while (true) {
            if (pq.size() == 1) {
                fleet++;
                break;
            }
            
            Details ahead = pq.remove();
            Details behind = pq.remove();
            if (ahead.time < behind.time) {//behind will never catch
                fleet++;
                pq.add(behind);
            } else {
                pq.add(ahead);
            }
        }
        return fleet;

        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
