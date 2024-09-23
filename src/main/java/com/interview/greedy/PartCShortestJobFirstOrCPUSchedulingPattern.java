package com.interview.greedy;

import java.util.Arrays;

/*
    https://www.youtube.com/watch?v=3-QbX1iDbXs&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=4
    Time Complexity: O(N logN + N) where N is the length of the jobs array. We sort the jobs array giving complexity O(N log N) and to calculate the waiting time we iterate through the sorted array taking O(N) complexity.

Space Complexity: O(1) as the algorithm uses only a constant amount of extra space regardless of the size of the input array. It does not require any additional data structures that scale with the input size.
 */
public class PartCShortestJobFirstOrCPUSchedulingPattern {
    // Function to calculate average
    // waiting time using Shortest
    // Job First algorithm
    static float shortestJobFirst(int[] jobs) {
        // Sort the jobs in ascending order
        Arrays.sort(jobs);

        // Initialize total waiting time
        float waitTime = 0;
        // Initialize total time taken
        int totalTime = 0;
        // Get the number of jobs
        int n = jobs.length;

        // Iterate through each job
        // to calculate waiting time
        for (int i = 0; i < n; ++i) {

            // Add current total
            // time to waiting time
            waitTime += totalTime;

            // Add current job's
            // time to total time
            totalTime += jobs[i];
        }

        // Return the average waiting time
        return waitTime / n;
    }
    public static void main(String[] args) {
        int[] jobs = {4, 3, 7, 1, 2};

        System.out.print("Array Representing Job Durations: ");
        for (int i = 0; i < jobs.length; i++) {
            System.out.print(jobs[i] + " ");
        }
        System.out.println();

        float ans = shortestJobFirst(jobs);
        System.out.println("Average waiting time: " + ans);
    }
}