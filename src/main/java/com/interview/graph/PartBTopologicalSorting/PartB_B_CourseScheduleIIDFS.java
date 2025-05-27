package com.interview.graph.PartBTopologicalSorting;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/course-schedule-ii/
 * Video: https://www.youtube.com/watch?v=qe_pQCh09yU
 * Category: Medium, Must Do
 *
 * Related:
 * https://leetcode.com/problems/course-schedule/ Medium
 * https://leetcode.com/problems/alien-dictionary/ Hard Locked
 * https://leetcode.com/problems/minimum-height-trees/ Medium
 * https://leetcode.com/problems/sequence-reconstruction/ Medium Locked
 * https://leetcode.com/problems/course-schedule-iii/ Hard
 * https://leetcode.com/problems/parallel-courses/ Medium Locked
 * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/ Medium
 * https://leetcode.com/problems/build-a-matrix-with-conditions/ Hard
 * https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space/ Hard Locked
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
/*

Certainly! Let's adapt the given pattern for detecting cycles in a directed graph using DFS and use it in the context of the CourseScheduleIIDFS problem. We will integrate cycle detection with topological sorting, ensuring that the code is structured and easy to understand. Here's how you can implement this pattern:

Explanation
Adjacency List Construction:

We build an adjacency list to represent the graph, where each course points to a list of courses that depend on it.
Cycle Detection with DFS:

We use a DFS approach to detect cycles in the directed graph.
We maintain a recStack array to track nodes currently being explored. If a node is encountered that is already in the recursion stack, it indicates a cycle.
DFS Traversal:

We start DFS from each course that hasn't been visited.
If a cycle is detected during DFS, we return an empty array, as it's impossible to complete all courses.
Course Order Construction:

Once DFS completes without detecting a cycle, we pop courses from the stack to construct the order.
This implementation uses a cycle detection pattern similar to the one used in undirected graphs but adapted for directed graphs using a recursion stack (recStack) to keep track of the current path in DFS.
 */

public class PartB_B_CourseScheduleIIDFS {
    // Stack to hold the course order
    private final Stack<Integer> courseOrderStack = new Stack<>();

    // Method to perform DFS and detect cycles
    private boolean dfs(int course, List<List<Integer>> adjacencyList, boolean[] visited, boolean[] recStack) {
        // Mark the current course as visited and add it to the recursion stack
        visited[course] = true;
        recStack[course] = true;

        // Recur for all the courses dependent on this course
        for (int dependentCourse : adjacencyList.get(course)) {
            if (!visited[dependentCourse]) {
                if (dfs(dependentCourse, adjacencyList, visited, recStack)) {
                    return true; // Cycle detected
                }
            } else if (recStack[dependentCourse]) {
                return true; // Cycle detected
            }
        }

        // Remove the course from recursion stack and add to the order
        recStack[course] = false;
        courseOrderStack.push(course);
        return false;
    }

    // Method to detect a cycle in the graph and find the course order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }


        // Build the adjacency list
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjacencyList.get(prerequisiteCourse).add(course);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        // Check for cycles and perform DFS
        for (int course = 0; course < numCourses; course++) {
            if (!visited[course]) {
                if (dfs(course, adjacencyList, visited, recStack)) {
                    return new int[0]; // Return an empty array if a cycle is detected
                }
            }
        }

        // Construct the course order from the stack
        int[] order = new int[numCourses];
        int index = 0;
        while (!courseOrderStack.isEmpty()) {
            order[index++] = courseOrderStack.pop();
        }

        return order;
    }

    public static void main(String[] args) {
        PartB_B_CourseScheduleIIDFS scheduler = new PartB_B_CourseScheduleIIDFS();

        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(Arrays.toString(scheduler.findOrder(numCourses1, prerequisites1))); // Output: [0, 1]

        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(scheduler.findOrder(numCourses2, prerequisites2))); // Output: [0, 2, 1, 3] or [0, 1, 2, 3]

        int numCourses3 = 1;
        int[][] prerequisites3 = {};
        System.out.println(Arrays.toString(scheduler.findOrder(numCourses3, prerequisites3))); // Output: [0]
    }
}
