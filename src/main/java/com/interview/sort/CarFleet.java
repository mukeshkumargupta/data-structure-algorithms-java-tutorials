package com.interview.sort;

import java.util.*;
/*
 * Problem: Car Fleet
 * LeetCode: https://leetcode.com/problems/car-fleet/
 * Video Explanation: https://www.youtube.com/watch?v=P99yS9jLr6o&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=65
 * Difficulty: Medium, Tricky, Google, FAANG
 * Related Problem: Car Fleet II (Hard) - https://leetcode.com/problems/car-fleet-ii/
 * https://leetcode.com/problems/count-collisions-on-a-road/ Medium
 *
 * Description:
 * There are `n` cars traveling toward the same destination along a one-lane road.
 * The destination is `target` miles away.
 *
 * You are given two integer arrays `position` and `speed`, both of length `n`, where:
 * - `position[i]` is the position of the `i-th` car.
 * - `speed[i]` is the speed of the `i-th` car (in miles per hour).
 *
 * Rules:
 * - A car cannot pass another car ahead of it, but it can catch up to it.
 * - If a car catches up to another, they will move together at the same speed.
 * - A **car fleet** is a non-empty set of cars driving together at the same position and speed.
 * - A single car is also considered a fleet.
 * - If a car reaches another fleet exactly at the destination, they are still considered a single fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 * Example 1:
 * Input: target = 12, position = [10, 8, 0, 5, 3], speed = [2, 4, 1, 1, 3]
 * Output: 3
 * Explanation:
 * - The cars starting at `10` and `8` form a fleet, meeting at `12`.
 * - The car starting at `0` does not catch up to any fleet, so it is a separate fleet.
 * - The cars starting at `5` and `3` form a fleet, meeting at `6`.
 * - No other cars meet these fleets before the destination, so the total number of fleets is `3`.
 *
 * Example 2:
 * Input: target = 10, position = [3], speed = [3]
 * Output: 1
 *
 * Constraints:
 * - `n == position.length == speed.length`
 * - `1 <= n <= 10^5`
 * - `0 < target <= 10^6`
 * - `0 <= position[i] < target` (All values in position are **unique**)
 * - `0 < speed[i] <= 10^6`
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
