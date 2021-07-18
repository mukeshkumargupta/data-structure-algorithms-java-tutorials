package com.interview.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Date 03/01/2017
 * 
 * @author Mukesh Kumar Gupta
 *
 *         https://leetcode.com/problems/course-schedule/ Category: Medium, Must
 *         Know Video: https://www.youtube.com/watch?v=kXy0ABd1vwo&t=413s There
 *         are a total of numCourses courses you have to take, labeled from 0 to
 *         numCourses - 1. You are given an array prerequisites where
 *         prerequisites[i] = [ai, bi] indicates that you must take course bi
 *         first if you want to take course ai.
 * 
 *         For example, the pair [0, 1], indicates that to take course 0 you
 *         have to first take course 1. Return true if you can finish all
 *         courses. Otherwise, return false.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: numCourses = 2, prerequisites = [[1,0]] Output: true
 *         Explanation: There are a total of 2 courses to take. To take course 1
 *         you should have finished course 0. So it is possible. Example 2:
 * 
 *         Input: numCourses = 2, prerequisites = [[1,0],[0,1]] Output: false
 *         Explanation: There are a total of 2 courses to take. To take course 1
 *         you should have finished course 0, and to take course 0 you should
 *         also have finished course 1. So it is impossible.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= numCourses <= 105 0 <= prerequisites.length <= 5000
 *         prerequisites[i].length == 2 0 <= ai, bi < numCourses All the pairs
 *         prerequisites[i] are unique.
 */
public class CourseSchedule {
    boolean isCycle(int[] visited, List<Integer>[] adjList, int current) {
        if (visited[current] == 1)
            return true;
        
        visited[current] = 1;
        List<Integer> adj = adjList[current];
        for (Integer elm : adj) {
            if (visited[elm] != 2 && isCycle(visited, adjList, elm)) {
                return true;
            }
        }
        visited[current] = 2;
        return false;
        
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjucency list
        List<Integer>[] adjList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : prerequisites) {
            adjList[edge[0]].add(edge[1]);
        }
        
        int[] visited = new int[numCourses]; // here value 0 means unvisited, 1 means processing and 2 means processed
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (isCycle(visited, adjList, i)) {// DFS
                    return false;
                }
                
            }
            
        }
        return true;
    }
}
