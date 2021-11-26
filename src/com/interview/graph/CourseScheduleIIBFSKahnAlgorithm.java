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
 * 
 *  * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

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
