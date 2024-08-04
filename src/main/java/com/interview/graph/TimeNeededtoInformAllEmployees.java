package com.interview.graph;

import java.util.*;
/*
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/
 * Category: Medium, VImp, Similar to maximum height in narray tree pattern, bfs, dfs
 * 
 * https://leetcode.com/problems/contain-virus/ Hard Bad
 * https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/ Medium, VVImp, Must Do
 * https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/ Hard, VImp
 * 
 * 1376. Time Needed to Inform All Employees
Medium

1666

98

Add to List

Share
A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.

Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.

The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.

The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).

Return the number of minutes needed to inform all the employees about the urgent news.

 

Example 1:

Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.
Example 2:


Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
Output: 1
Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
The tree structure of the employees in the company is shown.
 

Constraints:

1 <= n <= 105
0 <= headID < n
manager.length == n
0 <= manager[i] < n
manager[headID] == -1
informTime.length == n
0 <= informTime[i] <= 1000
informTime[i] == 0 if employee i has no subordinates.
It is guaranteed that all the employees can be informed.
 */
public class TimeNeededtoInformAllEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        /*
         * Runtime: 58 ms, faster than 86.52% of Java online submissions for Time Needed to Inform All Employees.
Memory Usage: 59.3 MB, less than 91.55% of Java online submissions for Time Needed to Inform All Employees.
         */
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        //Adjacency list
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adjList[manager[i]].add(i);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {headID, 0});

        //bfs code
        int maxTime = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] pair = q.remove();
                int node = pair[0];
                int time = pair[1];
                maxTime = Math.max(maxTime, time + informTime[node]);
                for (Integer child : adjList[node]) {
                    q.add(new int[] {child, time + informTime[node]});
                }
            }


        }
        return maxTime;
    }
    
    /*
    DFS
    */
    
    public int numOfMinutesUtil(int node, List<Integer>[] adjList, int[] informTime) {
        int maxTime = 0;
        for (Integer child : adjList[node]) {
            maxTime = Math.max(maxTime, informTime[node] + numOfMinutesUtil(child, adjList, informTime) );
        }
        return maxTime;
    }
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        /*
         * Runtime: 60 ms, faster than 86.27% of Java online submissions for Time Needed to Inform All Employees.
Memory Usage: 60.3 MB, less than 89.61% of Java online submissions for Time Needed to Inform All Employees.
         */
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        //Adjacency list
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adjList[manager[i]].add(i);
            }
        }
        return numOfMinutesUtil(headID, adjList, informTime);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
