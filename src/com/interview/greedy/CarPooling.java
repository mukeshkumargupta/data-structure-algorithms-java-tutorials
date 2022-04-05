package com.interview.greedy;

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
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        /*
         * Runtime: 6 ms, faster than 47.26% of Java online submissions for Car Pooling.
Memory Usage: 45.1 MB, less than 7.78% of Java online submissions for Car Pooling.
         */
        Arrays.sort(trips, (a, b) ->  a[1]- b[1]);//sort by start
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
        for (int i =1; i < size; i++) {
            while (!pq.isEmpty() && trips[i][1] >= pq.peek()[2]) {//Note: only equal sign change from meeting room 2 problem, if end time in pq is less or exqual to current meeting start time then remove all element from pq
                int [] trip = pq.remove();
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
            return a[0]- b[0];//sort by end
        });
        Arrays.sort(endWithPassenger, (a, b) -> {
            return a[0]- b[0];//sort by end
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
        for (int i =1; i < size; i++) {
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
