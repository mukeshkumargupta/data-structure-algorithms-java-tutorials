package com.interview.graph.PartBTopologicalSorting;

import java.util.ArrayList;
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
/*
    Solution: https://chatgpt.com/c/e1e14989-33c6-43f1-a5ba-289a0108d777
 */
public class PartBBCourseSchedule {
    // Method to perform DFS and detect cycles
    private boolean dfs(int course, List<Integer>[] adjacencyList, boolean[] visited, boolean[] recStack) {
        // Mark the current course as visited and add it to the recursion stack
        visited[course] = true;
        recStack[course] = true;

        // Recur for all the courses dependent on this course
        for (int dependentCourse : adjacencyList[course]) {
            if (!visited[dependentCourse]) {
                if (dfs(dependentCourse, adjacencyList, visited, recStack)) {
                    return true; // Cycle detected
                }
            } else if (recStack[dependentCourse]) {
                return true; // Cycle detected
            }
        }

        // Remove the course from recursion stack
        recStack[course] = false;
        return false;
    }

    // Method to detect a cycle in the graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjacencyList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Build the adjacency list
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjacencyList[prerequisiteCourse].add(course);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        // Check for cycles
        for (int course = 0; course < numCourses; course++) {
            if (!visited[course]) {
                if (dfs(course, adjacencyList, visited, recStack)) {
                    return false; // Cycle detected, cannot finish all courses
                }
            }
        }

        return true; // No cycles detected, can finish all courses
    }

    public static void main(String[] args) {
        PartBBCourseSchedule scheduler = new PartBBCourseSchedule();

        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(scheduler.canFinish(numCourses1, prerequisites1)); // Output: true

        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(scheduler.canFinish(numCourses2, prerequisites2)); // Output: false

        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println(scheduler.canFinish(numCourses3, prerequisites3)); // Output: true
    }
}
