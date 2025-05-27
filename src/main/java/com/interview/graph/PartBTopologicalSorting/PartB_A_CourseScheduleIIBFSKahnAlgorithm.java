package com.interview.graph.PartBTopologicalSorting;

import java.util.*;


/*
 * References:
 * - https://www.youtube.com/watch?v=tggiFvaxjrY&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=28 (Medium)
 * - https://chatgpt.com/c/e1e14989-33c6-43f1-a5ba-289a0108d777
 * - https://www.youtube.com/watch?v=rZv_jHZva34&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=52 (One more explanation)
 *
 * Category: Medium, Must Do
 *
 * Related Problems:
 * - https://leetcode.com/problems/parallel-courses/ (Medium)
 * - https://leetcode.com/problems/minimum-height-trees/ (Medium)
 * - https://leetcode.com/problems/course-schedule-iii/ (Hard)
 * - https://leetcode.com/problems/sequence-reconstruction/ (Hard)
 * - https://leetcode.com/problems/alien-dictionary/ (Hard)
 *
 * Problem Description:
 * You are given numCourses courses, labeled from 0 to numCourses - 1.
 * You are also given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1] indicates that to take course 0, you must first
 * take course 1. Your task is to return the ordering of courses you should take
 * to finish all courses. If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0, 1]
 * Explanation: There are 2 courses. To take course 1, you should first finish
 * course 0. So the correct order is [0, 1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0, 2, 1, 3]
 * Explanation: There are 4 courses. To take course 3, you should have finished
 * both courses 1 and 2. Both courses 1 and 2 should be taken after course 0.
 * So the correct order could be [0, 2, 1, 3] or [0, 1, 2, 3].
 *
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * Constraints:
 * - 1 <= numCourses <= 2000
 * - 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * - prerequisites[i].length == 2
 * - 0 <= ai, bi < numCourses
 * - ai != bi
 * - All pairs [ai, bi] are distinct.
 */
public class PartB_A_CourseScheduleIIBFSKahnAlgorithm {
  /*Kanhs algorithms*/
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Create an array to track the indegree of each course
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjacencyList.get(prerequisiteCourse).add(course);
            indegree[course]++;
        }

        // Initialize a queue to hold all courses with indegree of 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Array to store the course order
        int[] order = new int[numCourses];
        int index = 0;

        // Process each course using BFS
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            order[index++] = currentCourse;

            // Iterate over all the courses that depend on the current course
            for (int dependentCourse : adjacencyList.get(currentCourse)) {
                indegree[dependentCourse]--; // Decrease the indegree
                if (indegree[dependentCourse] == 0) {
                    queue.add(dependentCourse);
                }
            }
        }

        // Check if all courses are processed; if not, there's a cycle
        if (index == numCourses) {
            return order;
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(Arrays.toString(findOrder(numCourses1, prerequisites1))); // Output: [0, 1]

        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(findOrder(numCourses2, prerequisites2))); // Output: [0, 2, 1, 3] or [0, 1, 2, 3]

        int numCourses3 = 1;
        int[][] prerequisites3 = {};
        System.out.println(Arrays.toString(findOrder(numCourses3, prerequisites3))); // Output: [0]
    }
}
