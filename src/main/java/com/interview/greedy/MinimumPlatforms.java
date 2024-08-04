package com.interview.greedy;

import java.util.*;

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
public class MinimumPlatforms {
    
    //Function to find the minimum number of platforms required at the
    //railway station such that no train waits.
    //This is generic solution, but pq is not required when only minimum platform is asked
    static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        
        List<List<Integer>> times = new ArrayList<>();
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            List<Integer> time = new ArrayList<>();
            time.add(arr[i]);
            time.add(dep[i]);
            times.add(time);
        }
        Collections.sort(times, (a, b) ->  a.get(0) - b.get(0));//sort by start
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(1) - b.get(1));//sort fo end
        int platform = 0;
        int size = times.size();
        if (size >= 1) {
            platform = 1;
            pq.add(times.get(0));//add first element
        }
        for (int i =1; i < size; i++) {
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
    static int findPlatformM1(int arr[], int dep[], int n)
    {
        // add your code heree
        Arrays.sort(arr);
        Arrays.sort(dep);//Tricky
        
        int platformNeeded =1;
        int maxPlatform = 1;
        int l = arr.length;
        if (l >= 1) {
            platformNeeded = 1;
            maxPlatform = 1;
        }
        int j = 0;
        for (int i =1; i < l; i++) {
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
    static int findPlatformM3(int arr[], int dep[], int n)
    {
        // add your code here
        Arrays.sort(arr);
        Arrays.sort(dep);//Tricky
        
        int platformNeeded =1;
        int maxPlatform = 1;
        
        int i = 1;
        int j =0;
        while(i < n && j < n) {
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
        System.out.println(findPlatform(arr, dep, arr.length));
        
    }
    
}
