package com.interview.graph;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/course-schedule-ii/
 * Video: https://www.youtube.com/watch?v=qe_pQCh09yU
 * Category: Medium, Must Do
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
Accepted
 */

public class CourseScheduleIIDFS {
    Stack<Integer> s = new Stack<>();
    boolean isCycle(int[] visited, List<Integer>[] adjList, int curr) {
        if (visited[curr] == 1) {
            return true;
        }
        visited[curr] = 1;
        List<Integer> adj = adjList[curr];
        for (int elm : adj) {
            if (visited[elm] != 2 && isCycle(visited, adjList, elm)) {
                return true;
            }
        }
        visited[curr] = 2;
        return false;
    }
    boolean detectCycle(int numCourses, List<Integer>[] adjList) {

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (isCycle(visited, adjList, i)) {
                    return true;
                } 
            }
            
        }
        return false;
        
    }
    void DFS(boolean[] visited, List<Integer>[] adjList, int curr) {
        visited[curr] = true;
        List<Integer> adj = adjList[curr];
        for (int elm : adj) {
            if (!visited[elm]) {
               DFS(visited, adjList, elm);
            }
        }
        s.push(curr);
        
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        List<Integer>[] adjList = new ArrayList[numCourses];
        //Build adjujency list
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] elm : prerequisites) {
            adjList[elm[1]].add(elm[0]);
        }
        if (detectCycle(numCourses, adjList)) {
            return new int[0];
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                DFS(visited, adjList, i);
            }
            
        }
        //Pop all element of stack
        int index =0;
        while (!s.isEmpty()) {
            result[index++] = s.pop();
        }
        return result;

    }
}
