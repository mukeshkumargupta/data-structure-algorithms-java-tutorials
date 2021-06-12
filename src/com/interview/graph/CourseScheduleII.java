package com.interview.graph;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/course-schedule-ii/
 * Video: https://www.youtube.com/watch?v=qe_pQCh09yU
 */

public class CourseScheduleII {
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
        //Bui;ld adjujency list
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
