package com.interview.greedy;

import java.util.Arrays;
import java.util.TreeMap;

/*
 * Reference: https://www.youtube.com/watch?v=cr6Ip0J9izc
 * https://www.youtube.com/watch?v=LjPx4wQaRIs&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=3 See this video
 * Category: Medium, VVImp, Must Do, Fundamental
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 * https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
 * Derived Question: Given total time get max profit then knapsack we can use
 * Derived: Find all job index also then i think below optimization might not work
 * Related : https://leetcode.com/problems/maximum-earnings-from-taxi/ Medium Imp
 * https://leetcode.com/problems/two-best-non-overlapping-events/ Medium Imp
 * 
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

 

Example 1:



Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job. 
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job. 
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
 

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
Accepted
81,097
Submissions
161,583
 */

/**
 * http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf
 * Given set of jobs with start and end interval and profit, how to maximize profit such that 
 * jobs in subset do not overlap.
 */
public class PartLWeightedJobSchedulingMaximumProfit {
    /*
      1. Brute Force: Recursion Without Optimization
      Idea:
      We can solve this problem by recursively considering each job and deciding whether to include it in the schedule or skip it.
      For each job:
      - If we take the current job, we need to find the next non-overlapping job.
      - If we skip the current job, we move to the next one.
      This approach explores all possible combinations of job selections.

      Time Complexity:
      - O(2^n) due to exploring all combinations (take/skip for each job).
      - Very inefficient for larger inputs.
    */

    public int jobSchedulingRecursive(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];

        /* Combine startTime, endTime, and profit into a single jobs array */
        for (int i = 0; i < startTime.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        /* Call the recursive function to calculate the maximum profit */
        return maxProfitRecursive(jobs, 0);
    }

    private int maxProfitRecursive(int[][] jobs, int index) {
        /* Base case: if we reach the end of the jobs array, return 0 */
        if (index >= jobs.length) return 0;

        /* Option 1: Skip the current job and move to the next one */
        int skip = maxProfitRecursive(jobs, index + 1);

        /* Option 2: Take the current job and find the next non-overlapping job */
        int nextIndex = index + 1;
        while (nextIndex < jobs.length && jobs[nextIndex][0] < jobs[index][1]) {
            nextIndex++; /* Find the next job that starts after the current job ends */
        }

        /* Calculate profit by taking the current job and adding the profit of future non-overlapping jobs */
        int take = jobs[index][2] + maxProfitRecursive(jobs, nextIndex);

        /* Return the maximum profit by either taking or skipping the job */
        return Math.max(skip, take);
    }


    /*
          2. Recursion with Memoization
          Idea:
          To avoid recalculating the maximum profit for the same job multiple times, we can store the results of previously computed jobs using memoization.

          Approach:
          - Use a memoization table to store results for each job and avoid redundant calculations.

          Time Complexity:
          - O(n^2) due to checking overlapping jobs and storing results in memoization.
          - Improves over brute force, but still suboptimal.
          Solution Accepted on leetcode
    */

    public int jobSchedulingMemoization(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];

        /* Combine startTime, endTime, and profit into a single jobs array */
        for (int i = 0; i < startTime.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        /* Sort jobs by start time for easier future computations */
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        /* Memoization table to store previously computed results */
        Integer[] memo = new Integer[startTime.length];

        /* Call the recursive function with memoization */
        return maxProfitMemoization(jobs, 0, memo);
    }

    private int maxProfitMemoization(int[][] jobs, int index, Integer[] memo) {
        /* Base case: no more jobs */
        if (index >= jobs.length) return 0;

        /* If result already computed, return it */
        if (memo[index] != null) return memo[index];

        /* Option 1: Skip current job */
        int skip = maxProfitMemoization(jobs, index + 1, memo);

        /* Option 2: Take current job */
        int nextIndex = index + 1;
        while (nextIndex < jobs.length && jobs[nextIndex][0] < jobs[index][1]) {
            nextIndex++;
        }
        int take = jobs[index][2] + maxProfitMemoization(jobs, nextIndex, memo);

        /* Store the result in the memo table */
        return memo[index] = Math.max(skip, take);
    }

    /*
      3. Dynamic Programming with Binary Search
      Idea:
      Use a more efficient approach by sorting the jobs and then using binary search to find the next non-conflicting job.

      Approach:
      - Sort jobs by end time.
      - Use a binary search to find the next job that does not overlap with the current one.

      Time Complexity:
      - O(n log n) due to sorting and binary search.
      - Efficient and avoids unnecessary computations.
    */

    public int jobSchedulingDPWithBinarySearch(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        /* Combine startTime, endTime, and profit into a single jobs array */
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        /* Sort jobs by end time */
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        /* DP array to store maximum profit at each job */
        int[] dp = new int[n];
        dp[0] = jobs[0][2];

        for (int i = 1; i < n; i++) {
            /* Profit if we take the current job */
            int includeProfit = jobs[i][2];

            /* Use binary search to find the last non-conflicting job */
            int low = 0, high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (jobs[mid][1] <= jobs[i][0]) {
                    if (jobs[mid + 1][1] <= jobs[i][0]) {
                        low = mid + 1;
                    } else {
                        includeProfit += dp[mid];
                        break;
                    }
                } else {
                    high = mid - 1;
                }
            }

            /* Max profit by taking or skipping the current job */
            dp[i] = Math.max(dp[i - 1], includeProfit);
        }

        /* The last entry in dp array contains the maximum profit */
        return dp[n - 1];
    }

    /*
      4. Dynamic Programming with TreeMap
      Idea:
      Instead of binary search, we can use a TreeMap to track maximum profits efficiently.

      Approach:
      - TreeMap stores the end times as keys and profits as values.
      - Use the ceiling/floor functions of TreeMap to quickly find the next job with a non-overlapping schedule.

      Time Complexity:
      - O(n log n) due to sorting and TreeMap operations.
      - Efficient with concise code and optimal performance.
*/

    public int jobSchedulingDPWithTreeMap(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        /* Combine startTime, endTime, and profit into a single jobs array */
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }

        /* Sort jobs by end time */
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        /* TreeMap to store end time and maximum profit at that time */
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // Base case: no jobs, no profit

        for (int[] job : jobs) {
            /* Find maximum profit up to the current job's start time */
            int currProfit = dp.floorEntry(job[0]).getValue() + job[2];

            /* If current profit is better, update the TreeMap */
            if (currProfit > dp.lastEntry().getValue()) {
                dp.put(job[1], currProfit);
            }
        }

        /* The last value in TreeMap gives the maximum profit */
        return dp.lastEntry().getValue();
    }

    static class Job {
        int startTime;
        int endTime;
        int profit;
        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

    /*
    Final Approaches
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int l = profit.length;
        Job[] jobs = new Job[l];
        for (int i = 0; i < l; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> a.endTime - b.endTime);
        int[] maxProfit = new int[l];
        maxProfit[0] = jobs[0].profit;
        for (int i = 1; i < l; i++) {
            maxProfit[i] = Math.max(maxProfit[i-1], jobs[i].profit);
            for (int j = i-1; j >= 0; j--) {
                if (jobs[j].endTime <= jobs[i].startTime) {
                    maxProfit[i] = Math.max(maxProfit[j] + jobs[i].profit, maxProfit[i]);
                    break;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < l; i++) {
            result = Math.max(result, maxProfit[i]);
        }
        return result;
    }
    
    public static void main(String args[]){
        Job jobs[] = new Job[6];
        jobs[0] = new Job(1, 3, 5);
        jobs[1] = new Job(2, 5, 6);
        jobs[2] = new Job(4, 6, 5);
        jobs[3] = new Job(6, 7, 4);
        jobs[4] = new Job(5, 8, 11);
        jobs[5] = new Job(7, 9, 2);
        PartLWeightedJobSchedulingMaximumProfit mp = new PartLWeightedJobSchedulingMaximumProfit();
        System.out.println(mp.jobScheduling(jobs));
    }
}
