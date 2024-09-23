package com.interview.greedy;

import java.util.Arrays;

public class PartEJobSequencingProblem {

    /*
 * https://www.youtube.com/watch?v=LjPx4wQaRIs&list=PLIA-9QRQ0RqHnG0S2GPaNhJEIL3RYqpGR&index=4
 * https://www.youtube.com/watch?v=QbwltemZbRg&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=6
 * https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
 * reference:
 * https://chatgpt.com/c/a864cf4a-1662-4e06-9b82-9e35096d14a6
 * Category: Must Do
 *
 * Given a set of N jobs where each jobi has a deadline and profit associated with it. Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit if and only if the job is completed by its deadline. The task is to find the number of jobs done and the maximum profit.

Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job.


Example 1:

Input:
N = 4
Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
Output:
2 60
Explanation:
Job1 and Job3 can be done with
maximum profit of 60 (20+40).
Example 2:

Input:
N = 5
Jobs = {(1,2,100),(2,1,19),(3,2,27),
        (4,1,25),(5,1,15)}
Output:
2 127
Explanation:
2 jobs can be done with
maximum profit of 127 (100+27).

Your Task :
You don't need to read input or print anything. Your task is to complete the function JobScheduling() which takes an integer N and an array of Jobs(Job id, Deadline, Profit) as input and returns the count of jobs and maximum profit.


Expected Time Complexity: O(NlogN)
Expected Auxilliary Space: O(N)
 */


    public static class JobSequencing {
        class Job {
            int id, profit, deadline;
            Job(int x, int y, int z){
                this.id = x;
                this.deadline = y;
                this.profit = z;
            }
        }

        //Function to find the maximum profit and the number of jobs done.
        int[] JobScheduling(Job arr[], int n)
        {
            // Your code here
            Arrays.sort(arr, (a, b) -> {
                return b.profit - a.profit;

            });

            int maxDeadLine = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i].deadline > maxDeadLine) {
                    maxDeadLine = arr[i].deadline;
                }
            }
            int[] jobIds = new int[maxDeadLine];
            for (int i = 0; i < maxDeadLine; i++) {
                jobIds[i] = -1;
            }
            int maxProfit = 0;
            //Find total job done
            int jobCount = 0;
            for (int i = 0; i < n; i++) {
                int jobId = arr[i].id;
                //Go in that day
                for (int j = arr[i].deadline-1; j>=0; j--) {
                    if (jobIds[j] ==-1) {
                        jobIds[j] = jobId;
                        //jobIds[j] = i;
                        maxProfit += arr[i].profit;
                        jobCount++;
                        break;
                    }
                }
            }


            int[] result = new int[2];
            result[0] = jobCount;
            result[1] = maxProfit;
            //You can also print job id just print job id which dies not have -1
        /*for (int i =0; i < jobIds.length; i++) {
         * if (jobIds[i] != -1) {
         * System.out.println(jobIds[i]);
         * }
         *
        }*/
            return result;
        }

    }
}
