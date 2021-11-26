package com.interview.greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Reference: https://www.youtube.com/watch?v=cr6Ip0J9izc
 * https://www.youtube.com/watch?v=LjPx4wQaRIs&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=3 See this video
 * Category: Medium, VVImp, Tricky, Must Do
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 * https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
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

class Job{
    int start;
    int end;
    int profit;
    Job(int start,int end,int profit){
        this.start= start;
        this.end = end;
        this.profit= profit;
    }
}

/**
 * http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf
 * Given set of jobs with start and end interval and profit, how to maximize profit such that 
 * jobs in subset do not overlap.
 */
public class WeightedJobSchedulingMaximumProfit {

    /**
     * Sort the jobs by finish time.
     * For every job find the first job which does not overlap with this job
     * and see if this job profit plus profit till last non overlapping job is greater
     * than profit till last job.
     * @param jobs
     * @return
     */
    public int maximum(Job[] jobs){
        int T[] = new int[jobs.length];
        Arrays.sort(jobs, (a,b) -> a.end - b.end);
        
        T[0] = jobs[0].profit;
        for(int i=1; i < jobs.length; i++){
            T[i] = Math.max(jobs[i].profit, T[i-1]); //VVImp point: Tricky Need to find why? Important condition I dont know why it is done i added below one to just fill individual. 
            for(int j=i-1; j >=0; j--){ //Start from right  good optimization
                if(jobs[j].end <= jobs[i].start){
                    T[i] = Math.max(T[i], jobs[i].profit + T[j]); // This is very good optimization start from right then break in first case
                    break;
                }
            }
        }
        int maxVal = Integer.MIN_VALUE;  //This thing can not be done in same loop because there may be case inner loop may not trigger so max will come negative
        for (int val : T) {
            if (maxVal < val) {
                maxVal = val;
            }
        }
        return maxVal;
    }
    
    //https://leetcode.com/problems/maximum-profit-in-job-scheduling/
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int T[] = new int[startTime.length];
        Job jobs[] = new Job[startTime.length];
        for (int nJob = 0 ; nJob < startTime.length; nJob++) {
            jobs[nJob] = new Job(startTime[nJob],endTime[nJob],profit[nJob]);
            
        }
        return maximum(jobs);

        
    }
    
    public static void main(String args[]){
        Job jobs[] = new Job[6];
        jobs[0] = new Job(1,3,5);
        jobs[1] = new Job(2,5,6);
        jobs[2] = new Job(4,6,5);
        jobs[3] = new Job(6,7,4);
        jobs[4] = new Job(5,8,11);
        jobs[5] = new Job(7,9,2);
        WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit();
        System.out.println(mp.maximum(jobs));
    }
}
