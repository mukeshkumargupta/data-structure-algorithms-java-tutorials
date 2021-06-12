package com.interview.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Date 03/01/2017
 * @author Mukesh Kumar Gupta
 *
 * https://leetcode.com/problems/course-schedule/
 * Video: https://www.youtube.com/watch?v=kXy0ABd1vwo&t=413s
 */
public class CourseSchedule {
    boolean isCycle(int[] visited, List<Integer>[] adjList, int current) {
        if (visited[current] ==1) return true;
        
        visited[current] = 1;
        List<Integer> adj = adjList[current];
        for (Integer elm: adj) {
            if (visited[elm] != 2 && isCycle(visited, adjList, elm)) {
                return true;  
            }
        }
        visited[current] = 2;
        return false;
        
    }


    public boolean canFinish(int numCourses, int[][] prerequisites)  {
        //Build adjucency list
       List<Integer>[] adjList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge :  prerequisites) {
            adjList[edge[0]].add(edge[1]);
        }

        int[] visited = new int[numCourses]; //here value 0 means unvisited, 1 means processing and 2 means processed
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (isCycle(visited, adjList, i)) {//DFS
                    return false;
                }
                
            }
            
        }
        return true;
    }
}
