package com.interview.graph.PartFCycleDetection;

/*

https://www.youtube.com/watch?v=9twcmtQj4DU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=19,
Course schedule us already used, kahns is used for using bfs to solve directed graph
code is exactly same as undirected graph cycle detection but there parent used and here recustion stack is used in case of directed
Detecting cycles in a directed graph and an undirected graph requires different approaches due to the nature of their edges. Here's an explanation of why the approaches differ and how you might adapt an undirected graph cycle detection algorithm to work with a directed graph.

Differences Between Directed and Undirected Graphs
Edge Direction: In directed graphs, edges have a direction (from node A to node B). In undirected graphs, edges are bidirectional (from node A to node B and from node B to node A). This difference impacts how cycles are detected.

Cycle Detection Approach:

Undirected Graphs: To detect a cycle, you can use BFS or DFS while keeping track of visited nodes and their parent nodes. A cycle is detected if you encounter a node that has been visited and is not the immediate parent of the current node.
Directed Graphs: To detect a cycle, you need to use a method that tracks nodes currently being explored in the recursion stack. This is because a back edge in DFS indicates a cycle.
Adapting Undirected Graph Cycle Detection for Directed Graphs
You can’t directly use undirected graph cycle detection algorithms for directed graphs due to the difference in cycle definition. Instead, you need to adapt the approach for directed graphs. Here's a method to detect cycles in a directed graph using DFS:

Use Two Arrays:

visited[]: Keeps track of visited nodes.
recStack[]: Keeps track of nodes currently in the recursion stack (i.e., nodes being explored).
DFS Traversal:

Traverse each node using DFS.
If you encounter a node that is already in the recursion stack, a cycle exists.
Here’s a Java implementation of cycle detection in a directed graph using DFS:


 */

/*
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
public class DirectedGraphCycleDetection {
    // Stack to hold the course order
    private Stack<Integer> courseOrderStack = new Stack<>();

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

        // Remove the course from recursion stack and add to the order
        recStack[course] = false;
        courseOrderStack.push(course);
        return false;
    }

    // Method to detect a cycle in the graph and find the course order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        CourseScheduleIIDFS scheduler = new CourseScheduleIIDFS();

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