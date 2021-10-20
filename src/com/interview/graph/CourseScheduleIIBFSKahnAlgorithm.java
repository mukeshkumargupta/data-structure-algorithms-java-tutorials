package com.interview.graph;

import java.util.*;


/*
 * Reference: https://www.youtube.com/watch?v=tggiFvaxjrY&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=28 Medium
 * One more explanation: https://www.youtube.com/watch?v=rZv_jHZva34&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=52
 * Category: Medium, Must Do
 * Related
 * https://leetcode.com/problems/parallel-courses/ Medium
 * https://leetcode.com/problems/minimum-height-trees/ Medium
 * https://leetcode.com/problems/course-schedule-iii/ Hard
 * https://leetcode.com/problems/sequence-reconstruction/ Hard
 * https://leetcode.com/problems/alien-dictionary/ Hard
 */
public class CourseScheduleIIBFSKahnAlgorithm {
    
    boolean kahnsAlgo(List<Integer>[] adjList, int numCourses, Map<Integer, Integer> indegree, int[] result) {
        Queue<Integer> q = new LinkedList<>();
        
        //Push which indegree is zero
        for (int i = 0; i < numCourses; i++) {
            if (indegree.getOrDefault(i, 0) == 0) {
                q.add(i);
            }
        }
        int count = 0;
        while(!q.isEmpty()) {
            int current = q.remove();
            result[count] = current;
            count++;
            List<Integer> adj = adjList[current];
            for (int elm : adj) {
                //Update indegree
                indegree.put(elm, indegree.getOrDefault(elm, 0) -1);
                if (indegree.getOrDefault(elm, 0) == 0) {
                    q.add(elm);
                }
            }
            
        }
        if (count < numCourses) {
            return false;
        }
        return true;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {//42.19 runtime
        int[] result = new int[numCourses];
        List<Integer>[] adjList = new ArrayList[numCourses];
        Map<Integer, Integer> indegree = new HashMap<>();
        //Build adjacency list
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] elm : prerequisites) {
            adjList[elm[1]].add(elm[0]);
            indegree.put(elm[0], indegree.getOrDefault(elm[0], 0) +1);
        }
        if (kahnsAlgo(adjList, numCourses, indegree, result)) {
            return result;
        } else {
            return new int[0];
        }
    }
}
