package com.interview.heap.ipoPatternHeapAndGreedy;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/minimum-number-of-refueling-stops/description/
Category: Hard, Facebook, FAANG, Greedy, Heap
A car travels from a starting position to a destination which is target miles east of the starting position.

There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.

The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.

Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.



Example 1:

Input: target = 1, startFuel = 1, stations = []
Output: 0
Explanation: We can reach the target without refueling.
Example 2:

Input: target = 100, startFuel = 1, stations = [[10,100]]
Output: -1
Explanation: We can not reach the target (or even the first gas station).
Example 3:

Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
Output: 2
Explanation: We start with 10 liters of fuel.
We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
We made 2 refueling stops along the way, so we return 2.


Constraints:

1 <= target, startFuel <= 109
0 <= stations.length <= 500
1 <= positioni < positioni+1 < target
1 <= fueli < 109
 */
public class C_MinimumNumberofRefuelingStops {

    /*
        🧠 Explanation
    🛠 Approach: Greedy + Max Heap
    Always refuel only when needed.

    When you can't move forward anymore, pick the station that gives you the most fuel among those you’ve passed — that’s greedy.

    We use a max heap to store fuel amounts of all reachable stations.

    🔁 Dry Run
    Input:
    target = 100, startFuel = 50, stations = [[25,25],[50,50]]

    Step-by-step:
    fuel = 50, stops = 0

    station[0] = [25, 25] → reachable → push 25 to heap

    station[1] = [50, 50] → reachable → push 50 to heap

    fuel < 100 → can't reach yet, so:

    poll maxHeap → get 50, fuel = 100

    stops = 1

    fuel = 100 → done

    ✅ Answer = 1

    ⏱ Time and Space Complexity
    Time Complexity: O(n log n)
    For n stations:

    Each station is pushed and possibly popped from the heap → O(log n)

    Total: O(n log n)

    Space Complexity: O(n)
    MaxHeap stores at most n fuel values
     */
    private static class Optimized {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a, b) -> b - a);
            int fuel = startFuel, stops = 0, i = 0, n = stations.length;

            while (fuel < target) {
                // Add all reachable stations to the maxHeap
                while (i < n && stations[i][0] <= fuel) {
                    maxHeap.offer(stations[i][1]); // Push fuel amount
                    i++;
                }

                // If no stations available to refuel from, return -1
                if (maxHeap.isEmpty()) return -1;

                // Refuel with the best (maximum fuel) station
                fuel += maxHeap.poll();
                stops++;
            }

            return stops;
        }
    }
}
