package com.interview.graph.PartFCycleDetection;

/*
 * Video Reference:
 * https://www.youtube.com/watch?v=9twcmtQj4DU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=19
 *
 * Overview:
 * - The "Course Schedule" problem uses Kahn’s Algorithm (BFS) to solve cycle detection in directed graphs.
 * - This version uses DFS, similar to undirected graph cycle detection, but instead of tracking parent nodes,
 *   we use a recursion stack.
 *
 * Why Different Approaches?
 * - Detecting cycles in a directed vs undirected graph differs due to edge directionality.
 *
 * Differences Between Directed and Undirected Graphs:
 * 1. Edge Direction:
 *    - Directed: Edges have direction (A → B).
 *    - Undirected: Edges are bidirectional (A ↔ B).
 *
 * 2. Cycle Detection Strategy:
 *    - Undirected Graph:
 *      - Use BFS or DFS.
 *      - Keep track of visited nodes and their parent.
 *      - A cycle exists if a visited node is encountered that is not the immediate parent.
 *
 *    - Directed Graph:
 *      - Use DFS.
 *      - Maintain a recursion stack to track nodes currently being explored.
 *      - A cycle exists if a node is revisited while still in the recursion stack (back edge).
 *
 * Adapting Undirected to Directed:
 * - You cannot directly apply the undirected approach to directed graphs.
 * - Instead, use DFS with tracking arrays as below.
 *
 * Cycle Detection Using DFS in Directed Graph:
 * - Use two arrays:
 *   1. visited[]   → Tracks visited nodes.
 *   2. recStack[]  → Tracks nodes in the current recursion stack.
 *
 * DFS Traversal:
 * - For each unvisited node:
 *   - Start DFS.
 *   - If during traversal, you encounter a node already in recStack, a cycle exists.
 *
 * Below is a Java implementation of this logic.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * Explanation:
 *
 * Adjacency List Construction:
 * - We build an adjacency list to represent the graph,
 *   where each course points to a list of courses that depend on it.
 *
 * Cycle Detection with DFS:
 * - We use a DFS approach to detect cycles in the directed graph.
 * - A `recStack` array is maintained to track nodes currently in the recursion stack.
 * - If a node is encountered that is already in `recStack`, it indicates a cycle.
 *
 * DFS Traversal:
 * - We start DFS from each course that hasn't been visited.
 * - If a cycle is detected during DFS, we return an empty array,
 *   as it's impossible to complete all courses.
 *
 * Course Order Construction:
 * - Once DFS completes without detecting a cycle,
 *   we pop courses from the stack to construct the valid order.
 *
 * Note:
 * - This implementation uses a cycle detection pattern similar to the one in undirected graphs,
 *   but adapted for directed graphs using the recursion stack (`recStack`)
 *   to track the current path in DFS.
 */
public class B_A_DirectedGraphCycleDetection {
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
        B_A_DirectedGraphCycleDetection scheduler = new B_A_DirectedGraphCycleDetection();

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
